#! /bin/bash
##
## Setting the git branch and syncronized status into PS1 definition
##
## The comments defines the values preseted by the distribution thought ~/.bashrc
##
## Instalation:
##	copy the script to /home/${user}/
## 	execute the script inside the ~/.bashrc with: '. scriptName'
##
#

usage(){
cat <<EOF
For optimization as root create memory tmpfs and set the environment MEMCACHE variable
mkdir /memCache
chmod 777 /memCache
mount -t tmpfs none -o size=5m /memCache
EOF
}
[ -z "$MEMCACHE" ] && export MEMCACHE=/memCache

isMemCacheAvailable(){
  mount | grep 'tmpfs' | grep "${MEMCACHE}" >/dev/null 2>&1
}
! isMemCacheAvailable &&  usage

gitCache(){
	local repoRoot="$(git rev-parse --show-toplevel 2>/dev/null)";
	local cacheFile="${repoRoot}/.git.cache";
	if [ -d ${MEMCACHE} ] && isMemCacheAvailable ; then
		if ! [ -d "${MEMCACHE}${repoRoot}" ]; then
			mkdir -p "${MEMCACHE}${repoRoot}"
		fi
		echo "${MEMCACHE}${cacheFile}"
		return;
	fi
	echo ${cacheFile}
}



addCacheToIgnoreFile(){
	if [ -d ${MEMCACHE} ]; then
		return;
	fi

	local ignoreFile="$(git rev-parse --show-toplevel 2>/dev/null)/.gitignore"
	if ! grep '.git.cache' ${ignoreFile} >/dev/null 2>&1; then
		echo ".git.cache" >>"${ignoreFile}"
		command git add "${ignoreFile}"
		echo "INFO: The cache was added to git ignore file."
	else
		echo "INFO: The cache file is in the ignore file. Nothing to do."
	fi
}



buildCache(){
  isGitCacheEnable && command git status -s >"$1" 2>/dev/null; 
}



cd(){
	local repoRoot="$(git rev-parse --show-toplevel 2>/dev/null)";
	if isGitCacheEnable && [ "$(command cd $@ 2>/dev/null; pwd)"x == "${repoRoot}"x ]; then
		echo "INFO: updating the cache status in background each time the root of the repo is accesed "
		buildCache $(gitCache) &
	fi
	command cd "$@"
}



git(){
	if isGitCacheEnable && ( [ "$1" == "add" ] || [ "$1" == "rm" ] || [ "$1" == "commit" ] || [ "$1" == "reset" ] \
	       ||  [ "$1" == "pull" ]  || [ "$1" == "merge" ] ||  [ "$1" == "fetch" ] ) ; then
		local cf="$(gitCache)"
		rm "${cf}" 1>/dev/null 2>&1
	fi
	if  isGitCacheEnable && [ "$1" == "status" ] && [ "$2" == "-s" ]; then
		local cf="$(gitCache)"
		buildCache "${cf}"; cat "${cf}" 
	else
		command git "$@"
	fi
}



gitCacheDisable(){
if [ "${OLDPS1}"x != "$PS1"x ]; then
	PS1=$OLDPS1;
fi
export GITCACHEENABLE=false;
echo "INFO: use gitCacheEnable to set the git status format in the console."
}



isGitCacheEnable(){
	[ "${GITCACHEENABLE}"x == "true"x ]
}


gitCacheEnable(){
if echo ${PS1} | egrep 'git:' >/dev/null 2>/dev/null ; then
	# fix multiple calls to this function
	echo "WARNING: calling multiple times to gitCacheEnable. Aborting action."
	return;
fi
echo "INFO: using $(gitCache)"
addCacheToIgnoreFile

if [ "${OLDPS1}"x != "${PS1}"x ]; then
	export OLDPS1=$PS1
fi
export GITCACHEENABLE=true;

## check if some color is set
if echo "$PS1" | grep '\\\[\\033\[' >/dev/null 2>&1 ; then
#       PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$'
        PS1="${PS1}"\
"\$( [ "$GITCACHEENABLE"x == "true"x ] && branch=\$(git branch 2>/dev/null) && echo '\[\033[01;30m\]git:'\$(remote=\$(git remote get-url origin 2>/dev/null | sed -e 's/.*:\/\/\|www.\|\.\(com\|org\|gov\|edu\).*//g'); echo \${remote} | egrep '^/' >/dev/null 2>&1 && echo local || echo \${remote} )' : '\$(echo \"\${branch}\"| grep '^*' | colrm 1 2 &&\
  cachefile=\$(gitCache) &&\
  if [ \$(! [ -f \"\${cachefile}\" ] && buildCache \"\${cachefile}\" ; cat \"\${cachefile}\" 2>/dev/null | wc -l ) -gt 0 ];then\
         echo '\[\033[01;31m\]:unsync(M:'\$(egrep '^[ AMDRCU]{2,2}' \${cachefile} 2>/dev/null | wc -l)',?:'\$(egrep '^\?\?' \${cachefile} 2>/dev/null | wc -l)')';\
else echo '';fi)'\[\033[01;30m\] \$\[\033[00m\] ')";

else
#       PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w\$'
        PS1="${PS1}"\
"\$( [ "$GITCACHEENABLE"x == "true"x ] && branch=\$(git branch 2>/dev/null) && echo 'git:'\$(remote=\$(git remote get-url origin 2>/dev/null | sed -e 's/.*:\/\/\|www.\|\.\(com\|org\|gov\|edu\).*//g'); echo \${remote} | egrep '^/' >/dev/null 2>&1 && echo local || echo \${remote} )' : '\$(echo \"\${branch}\"| grep '^*' | colrm 1 2 &&\
  cachefile=\$(gitCache) &&\
  if [ \$(! [ -f \"\${cachefile}\" ] && buildCache \"\${cachefile}\" ; cat \"\${cachefile}\" 2>/dev/null | wc -l ) -gt 0 ];then\
         echo ':unsync(M:'\$(egrep '^[ AMDRCU]{2,2}' \${cachefile} 2>/dev/null | wc -l)',?:'\$(egrep '^\?\?' \${cachefile} 2>/dev/null | wc -l)')';\
else echo '';fi)' \$ ')";

fi
PS1="${PS1}"' '

echo "INFO: use gitCacheDisable to disable this format and the use of status caching."
}



gitCacheEnable
