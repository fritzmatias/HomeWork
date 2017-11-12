# Non intrusive PS1 git status info 
  This is a bash script wich use a cache file to show the status of the git repo.

## Enable Disable
  Additionally is defined 2 functions to enable or disable the whole interaction.
  ```gitCacheEnable```   - enables the use of the cache and the intercepting of git actions
  ```gitCacheDisable```  - disables the script in this console and uses the original definition of PS1

## Cache
 The cache use a file from disk (git parent repo folder) or from a tmpfs (if it is added to fstab)


### Cache Updates
  Intercepting the git interactions and updating the cache when is possible. (usefull on big repo)

## install
  To be called from ~/.bashrc add a line:

 ```. <filePath>/ps1_git.sh```
 
  This modify the PS1 behavor for the user related to ~
