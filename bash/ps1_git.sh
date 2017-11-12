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
# $(git status -s | grep ?? 2>/dev/null | wc -l)
# git rev-parse --show-toplevel
#

gitCache(){
	echo "$(git rev-parse --show-toplevel)/.git.cache";
}
addCacheToIgnoreFile(){
	local ignoreFile="$(git rev-parse --show-toplevel)/.gitignore"
	if ! grep '.git.cache' ${ignoreFile} >/dev/null 2>&1; then
		echo ".git.cache" >>"${ignoreFile}"
		command git add "${ignoreFile}"
		echo "INFO: The cache was added to git ignore file."
	else
		echo "INFO: The cache file is in the ignore file. Nothing to do."
	fi
}
git(){
	if [[ "$1" == "add" ]] || [[ "$1" == "rm" ]] || [[ "$1" == "commit" ]] || [[ "$1" == "reset" ]] \
	       ||  [[ "$1" == "pull" ]]  || [[ "$1" == "merge" ]] ||  [[ "$1" == "fetch" ]]; then
		rm "$(git rev-parse --show-toplevel)/.git.cache" 1>/dev/null 2>&1
	fi
	if   [[ "$1" == "status" ]] && [[ "$2" == "-s" ]]; then
		local cf="$(git rev-parse --show-toplevel)/.git.cache"
		command	git status -s >"$cf" 2>/dev/null; cat "$cf" 
	else
		command git "$@"
	fi
}


gitCacheDisable(){
if [[ ${OLDPS1} != $PS1 ]]; then
	PS1=$OLDPS1;
fi
export gitenable=false;
echo "INFO: use gitCacheEnable to set the git status format in the console."
}
gitCacheEnable(){
if echo ${PS1} | egrep 'git:' >/dev/null 2>/dev/null ; then
	# fix multiple calls to this function
	echo "WARNING: calling multiple times to gitCacheEnable. Aborting action."
	return;
fi

addCacheToIgnoreFile

if [[ ${OLDPS1} != ${PS1} ]]; then
	export OLDPS1=$PS1
fi
export gitenable=true;

## check if some color is set
if echo "$PS1" | grep '\\\[\\033\[' >/dev/null 2>&1 ; then
#       PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$'
        PS1="${PS1}"\
"\$( [ "$gitenable"x == "true"x ] && git branch >/dev/null 2>&1 && echo '\[\033[01;30m\]git: '\$(git branch 2>/dev/null | grep '^*' | colrm 1 2 &&\
  cachefile=\$(gitCache) &&\
  if [ \$(! [ -f \"\${cachefile}\" ] && git status -s >\"\${cachefile}\" 2>/dev/null ; cat \"\${cachefile}\" 2>/dev/null | wc -l ) -gt 0 ];then\
         echo '\[\033[01;31m\]:unsync(AMD:'\$(egrep '^[ AMD]{2,2}' \${cachefile} 2>/dev/null | wc -l)',?:'\$(egrep '^\?\?' \${cachefile} 2>/dev/null | wc -l)')';\
else echo '';fi)'\[\033[01;30m\] \$\[\033[00m\] ')";

else
#       PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w\$'
        PS1="${PS1}""\$(git branch >/dev/null 2>&1 && echo 'git:'\$(git branch 2>/dev/null | grep '^*' | colrm 1 2)\
\$(if [ \$(git status -s 2>/dev/null | wc -l) -gt 0 ];then echo ':unsync(AMD:'\$(git status -s | egrep '^[ AMD]{2,2}' 2>/dev/null | wc -l)',?:'\$(git status -s | egrep '^\?\?' 2>/dev/null | wc -l)')'; \
else echo ''; fi)' \$')";

fi
PS1="${PS1}"' '

echo "INFO: use gitCacheDisable to disable this format and the use of status caching."
}



gitCacheEnable
