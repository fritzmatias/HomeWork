# check out 
For a new Java Project inside this folder, change to branch and check the branch ejercicios-java/README.md

# How To work with my sub projects structure
i.e:
```
HomeWork (git-project: https://github.com/fritzmatias/HomeWork.git)
 \\_ folder1
 ···\\_ proj1                  (remoteBranchName: folder1-proj1)
 ···\\_ proj2                  (remoteBranchName: folder1-proj2)
 \\_ folder2
 ···\\_ folder1
 ......\\_ proj1       (remoteBranchName: folder2-folder1-proj1)
 ...\\_ proj2          (remoteBranchName: folder2-proj2)
 \\_ folder3
```
#### For new projects
* git clone --single-branch -b ${remoteBranchName} ${projectPath} ${remoteBranchName}-${newProjectName}

> i.e.: 

``` 
remoteBranchName=ejercicios-java;
projectPath=https://github.com/fritzmatias/ejercicios.git;
newProjectName=DocumentReader;

  git clone --single-branch -b ${remoteBranchName} ${projectPath} ${remoteBranchName}-${newProjectName}
```
* git push --set-upstream origin ${remoteBranchName}-${newProjectName}

> i.e.: 

``` 
remoteBranchName=ejercicios-java-DocumentReader;
projectPath=https://github.com/fritzmatias/HomeWork.git;
newProjectName=DocumentReader;

  git push --set-upstream origin ${remoteBranchName}-${newProjectName}
```


#### For reading
* git clone --single-branch -b ${remoteBranchName} ${projectPath} ${remoteBranchName}-clone

> i.e.: 

``` 
remoteBranchName=ejercicios-java-DocumentReader;
projectPath=https://github.com/fritzmatias/HomeWork.git;

  git clone --single-branch -b ${remoteBranchName} ${projectPath} ${remoteBranchName}-clone
```

#### For Modify
* (after actions for reading)
* cd ${remoteBranchName}-clone
* git branch --track ${remoteBranchName}-fix
* git checkout ${remoteBranchName}-fix
* .... (modify)
* git commit -a -m '<some comment about the fix>'
* git push --set-upstream origin ${remoteBranchName}-fix

#### Merge - server side -
* (into the ProjectPath)
* git checkout ${remoteBranchName}
* git tag ${remoteBranchName}-preMerge
* git merge ${remoteBranchName}-fix
* git branch -d ${remoteBranchName}-fix 

