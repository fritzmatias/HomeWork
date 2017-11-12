# Non intrusive PS1 git status info 
  bash script wich use a cache file to show the status info constantly, intercepting the git interactions and performing the cached when is possible. (usefull on big repo)
  additionally defines 2 functions to enable or disable the whole interaction.
  gitCacheEnable - enables the use of the cache and the intercepting of git actions
  gitCacheDisable - disables the script in this console and uses the original definition of PS1

  call it from ~/.bashrc
