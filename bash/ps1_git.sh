#! /bin/bash
##
## Setting the git branch and syncronized status into PS1 definition
##
## The comments defines the values preseted by the distribution thought ~/.bashrc
##
## Instalation:
##	copy the script to /home/${user}/
## 	execute the script inside the ~/.bashrc with: '. scriptName'

###
usage(){
cat <<EOF
Info: 
gitCache 	-- the current cache file used

Management : 
gitCacheDisable/gitCacheEnable  -- enable disable the use of the cache, update & execution
addCacheToIgnoreFile  		-- adds the cache to the ignore file only if is disk cache
setGitCacheBG/setGitCacheFG 	-- modifies the execution of updates  

Performance 
gitCachePerformanceSLOW/gitCachePerformanceOK -- modifies when the cache is updated on git add and git rm

git helper functions:
gitrmdeleted 	-- celan the git stage area with the deleted files in multiple calls of 2500 files
gitshowdifffiles [prev commit number] -- shows the files commited in the commit prevous
gitignore <ignore criteria> -- adds the ignore criteria into the local repo .gitignore

EOF

}
###
memcache_help(){
cat <<EOF

For optimization as root create memory tmpfs and set the environment MEMCACHE variable
		mkdir /memCache
		chmod 777 /memCache
		mount -t tmpfs none -o size=5m /memCache

EOF
}
[ -z "$MEMCACHE" ] && export MEMCACHE=/memCache
###
gitrmdeleted(){
 command cd $(gitRepoLocalRootPath)
 local files=$(git ls-files --deleted)
    while [ -n "${files}" ] && git rm --cached $(echo "${files}" | head -2500 ) ; do
	files=$(git ls-files --deleted)
    done
}
###
gitshowdifffiles(){
local commit="$1"
	[ "${commit}"x == x ] && commit="HEAD~0" && echo "INFO: you can set HEAD~n or commit id " >&2
	echo "Diff files in commit ${commit} "
	git diff-tree --no-commit-id --name-only -r ${commit}
}
###
gitignore(){
local repoRoot=$(gitRepoLocalRootPath)
local file=.gitignore
local gitignorefile="${repoRoot}/${file}"
local criterias="$(echo "$@" | sort -u)"
local data=$(cat ${gitignorefile} 2>/dev/null )
local newline='
'
local newdata
	[ -z "${data}" ] && echo "$@" >>"${gitignorefile}" && return $?
	for c in ${criterias} ; do
	 	! echo ${data} | egrep '^'"${c}"'$' >/dev/null && newdata="${newdata}${newline}${c}" || echo "INFO: criteria: ${c} exist">&2 
	done
	echo "Added: ${newdata} ${newline} to ${gitignorefile}"
	echo "${newdata}">>"${gitignorefile}" && return $?
}
###
isMemCacheAvailable(){
  mount | grep 'tmpfs' | grep "${MEMCACHE}" >/dev/null 2>&1
}
###
gitRepoLocalRootPath(){
if [[ "$PWD" != "${GITREPOLOCALROOTPATH}" ]]; then 
	export GITREPOLOCALROOTPATH=$(command git rev-parse --show-toplevel 2>/dev/null) ;
fi 
echo ${GITREPOLOCALROOTPATH} ;
}
###
gitCache(){
local repoRoot="$(gitRepoLocalRootPath)"
 gitBashCache "${repoRoot}" || gitMemCache "${repoRoot}" || gitDiskCache "${repoRoot}"
}
###
gitBashCache(){
isGitCacheBashVar && GITCACHEBASHVAR=${repoRoot}  && echo $GITCACHEBASHVAR
}

###
isGitCacheBashVar(){
 [ "$GITCACHEBASHVAR"x == "$(gitRepoLocalRootPath)"x ]
}
###
gitCacheBashVarDisable(){
GITCACHEBASHVAR=""
}
###
gitCacheBashVarEnable(){
GITCACHEBASHVAR="$(gitRepoLocalRootPath)"
echo "using BASH variable GITCACHEVAR. To use a file cache, set gitCacheBashVarDisable"
}
###
gitDiskCache(){
	local cacheFile="${1}/.git.cache";
	echo ${cacheFile}
}
###
gitMemCache(){
	local repoRoot="${1}"
	local cacheFile="${1}/.git.cache";
	if [ -d ${MEMCACHE} ] && isMemCacheAvailable ; then
		if ! [ -d "${MEMCACHE}${repoRoot}" ]; then
			mkdir -p "${MEMCACHE}${repoRoot}"
			rm $(gitDiskCache "${repoRoot}" )
		fi
		echo "${MEMCACHE}${cacheFile}"
		return 0;
	fi
	return 1;
}
###
isGitRepo(){
  git branch >/dev/null 2>/dev/null 
  return $?
}
###
gitCurrentBranch(){
  git branch | grep '^*' | colrm 1 2 
}
###
addCacheToIgnoreFile(){
	if [ -d "${MEMCACHE}" ] && [[ "${MEMCACHE}" == "$(gitCache)" ]] ; then
		return;
	fi

	local ignoreFile="$(gitRepoLocalRootPath)/.gitignore"
	if ! grep '.git.cache' ${ignoreFile} >/dev/null 2>&1; then
		echo ".git.cache" >>"${ignoreFile}"
		command git add "${ignoreFile}"
		echo "INFO: The cache was added to git ignore file."
	else
		echo "INFO: The cache file is in the ignore file. Nothing to do."
	fi
}
###
buildCache(){
  isGitCacheEnable && ( (isGitCacheBashVar && export GITCACHEVAR=$(command git status -s 2>/dev/null ))\
	|| ( gitCacheBashVarDisable && command git status -s >"$1" 2>/dev/null ) );
}
###
catCache(){
isGitCacheBashVar && echo "$GITCACHEVAR" || cat "$@" 
}
###
buildCacheBG(){
 if [ "${GITCACHEBUILDBG}"x == "true"x ]; then
   buildCache "$1" &
 else
   buildCache "$1"
 fi
}
###
setGitCacheBG(){
export GITCACHEBUILDBG=true
echo "INFO: the cache build will be in bg,  change  use 'setGitCacheFG'"
}
setGitCacheFG(){
export GITCACHEBUILDBG=false
echo "INFO: the cache build will be in fg,  change  use 'setGitCacheBG'"
}
###
cd(){
	command cd "$@" &&  \
	if isGitCacheEnable && [ "$PWD"x == "$(gitRepoLocalRootPath)"x ]; then
		echo "INFO: updating the cache status in background each time the root of the repo is accesed. Use 'setGitCacheFG' to disable "
		buildCacheBG $(gitCache) 
	fi
}
###
isGitCacheEmpty(){
local cf=$(gitCache)
 ! [ -f ${cf} ] || [ $(catCache ${cf} 2>/dev/null | wc -l) -eq 0 ]
}
###
isSmallEnoght(){
 isGitCacheEmpty || [ "$GITCACHEPERFORMANCE"x  == "ok"x ] 
}
###
gitCachePerformance(){
# slow, ok
export GITCACHEPERFORMANCE=${1}
}
gitCachePerformanceSLOW(){
gitCachePerformance "slow"
}
gitCachePerformanceOK(){
gitCachePerformance "ok"
}
###
isRepoCommited(){
cachefile="$1"
[ "$cachefile"x == x ] && cachefile=$(gitCache) 
#buildCacheBG "${cachefile}"
 [ $(catCache  "${cachefile}" 2>/dev/null | wc -l ) -eq 0 ]

}
###
git(){
local cf="$(gitCache)"
	if isGitCacheEnable  && ( ( isSmallEnoght && ( [ "$1" == add ] || [ "$1" == "rm" ] )) || [ "$1" == "commit" ] || [ "$1" == "reset" ] \
	       ||  [ "$1" == "pull" ]  || [ "$1" == "merge" ] ||  [ "$1" == "fetch" ] || [ "$1" == checkout ] )  ; then
		command git "$@"
		buildCacheBG "$(gitCache)" 
		return;
	fi
	## intercepting mantenance commands
	if [ "$1" == gc ]; then
		## only mantinance if the repo is commited
		if git status -s && isRepoCommited "${cf}"; then
			## doing the mantenance
			command git "$@"
			## a commit related to the mantenance is not required
		else
			## forcing to update the cache
			echo "ERROR: first commit all your changes."
		fi
		return
	fi

	# Don't care if the cache is enable or not, the time required is similar
	if [ "$1" == "status" ] && [ "$2" == "-s" ]; then
		#local cf="$(gitCache)"
		buildCache "${cf}"; catCache  "${cf}" 
	else
		command git "$@"
	fi
}
###
gitCacheDisable(){
if [ "${OLDPS1}"x != "$PS1"x ]; then
	PS1=$OLDPS1;
fi
export GITCACHEENABLE=false;
echo "INFO: use gitCacheEnable to set the git status format in the console."
}
###
isGitCacheEnable(){
	[ "${GITCACHEENABLE}"x == "true"x ]
}
###
ps1_gitType(){
	[ "$(git rev-parse --is-bare-repository)"x == "true"x ] && echo "bare" || echo "git" 
}
###
ps1_showUnsync(){
local cachefile=$1
         echo ':unsync(M:'$(catCache "${cachefile}" | egrep '^[ AMDRCU]{2,2}' 2>/dev/null | wc -l)',?:'$(catCache "${cachefile}" | egrep '^\?\?' 2>/dev/null | wc -l)')';
}
###
gitCurrentPushBranch(){
	git branch -vv | grep '^*'|cut -d'[' -f 2 | cut -d']' -f 1
}
###
ps1_showPush(){
local pendingPush=$(git rev-parse @{push}... 2>/dev/null| sed -e 's/\^//g' | sort -u |wc -l)
    [ ${pendingPush} -gt 1 ] && echo ":push $(gitCurrentPushBranch)"
}
###
ps1_showOrigin(){
local origin=$(git remote get-url origin 2>/dev/null )
local remote=$(echo ${origin} | sed -e 's/.*:\/\/\|www.\|\.\(com\|org\|gov\|edu\).*//g')
local baseName=$(basename ${origin} 2>/dev/null)
	([ -z ${remote} ] && echo 'self') || (echo "${remote}" | egrep '^/|@' >/dev/null 2>&1 && echo "local/${baseName}") || echo "${remote}/${baseName}" 
}
###
export CUSTOM='\n'
gitCacheEnable(){
if isGitCacheEnable; then
	# fix multiple calls to this function
	echo "WARNING: calling multiple times to gitCacheEnable. Aborting action."
	return;
fi

if [ "${OLDPS1}"x != "${PS1}"x ]; then
	export OLDPS1=$PS1
fi
export GITCACHEENABLE=true;
#gitCacheBashVarEnable # don't work yet,  the bachground processing generates new shells
echo "INFO: using $(gitCache)"
addCacheToIgnoreFile

## check if some color is set
if echo "$PS1" | grep '\\\[\\033\[' >/dev/null 2>&1 ; then
#       PS1='${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u@\h\[\033[00m\]:\[\033[01;34m\]\w\[\033[00m\]\$'
        PS1="${PS1}"\
"\$( [ "${GITCACHEENABLE}"x == truex ] && isGitRepo && echo '\[\033[01;30m\]'\$(ps1_gitType)':'\$(ps1_showOrigin)' : '\$(echo \$(gitCurrentBranch) &&\
  cachefile=\$(gitCache) &&\
  if ! isRepoCommited \${cachefile}  ;then\
         echo '\[\033[01;31m\]'\$(ps1_showUnsync \${cachefile} );\
  else echo '\[\033[01;31m\]'\$(ps1_showPush);\
  fi)'\[\033[01;30m\]${CUSTOM} \$\[\033[00m\] ')";

else
#       PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w\$'
        PS1="${PS1}"\
"\$( [ "$GITCACHEENABLE"x == "true"x ] && isGitRepo && echo \$(ps1_gitType)':'\$(ps1_showOrigin)' : '\$(echo \$(gitCurrentBranch) &&\
  cachefile=\$(gitCache) &&\
  if ! isRepoCommited \${cachefile}  ;then\
         echo \$(ps1_showUnsync \${cachefile} );\
  else echo \$(ps1_showPush);\
  fi)'${CUSTOM} \$ ')";

fi
PS1="${PS1}"' '

echo "INFO: use gitCacheDisable to disable this format and the use of status caching."
}
#############################################################################

usage
! isMemCacheAvailable && memcache_help 
gitCachePerformanceOK
setGitCacheFG
gitCacheEnable
