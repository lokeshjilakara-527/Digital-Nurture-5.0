# Git-HOL 1 — Setup & first repository

**Task:** configure Git, create a repo, add a file, commit, push/pull.

```bash
# check Git is installed
git --version

# user-level configuration
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
git config --list                     # verify

# (optional) set a default editor, e.g. Notepad++
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"

# create a repository and add a file
mkdir GitDemo && cd GitDemo
git init
echo "# GitDemo" > README.md
git add README.md
git commit -m "Initial commit"

# connect to a remote and push
git remote add origin https://github.com/<user>/GitDemo.git
git push -u origin master
git pull origin master
```
Note: create a **personal** GitHub/GitLab account (not Cognizant credentials), and use a
**Personal Access Token** when prompted for a password.
