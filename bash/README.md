# Non intrusive PS1 git status info 
  This is a bash script wich use a cache file to show the status of the git repo.

## Info showed
* repo type (bare | git)
* branch name
* parent origin (i.e. 'github/projectName.git')
* status	(only when is useful)
* push pending  (only after commits)

## Enable Disable
  Additionally is defined 2 functions to enable or disable the whole interaction.
  ```gitCacheEnable```   - enables the use of the cache and the intercepting of git actions
  ```gitCacheDisable```  - disables the script in this console and uses the original definition of PS1

## help functions
  ```gitrmdeleted```   - syncronize the staged deleted files on massive files
  

## Cache
 The cache use a file from disk (git parent repo folder) or from a tmpfs (if it is added to fstab)

### Cache Updates
  Intercepting the git and cd actions, the cache is updated when is possible. (usefull on big repo)
  (sometimes in bg.)


## install
  To be called from ~/.bashrc add a line:

 ```. <filePath>/ps1_git.sh```
 
  This modify the PS1 behavor for the user related to ~
