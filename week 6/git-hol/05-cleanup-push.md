# Git-HOL 5 — Clean up & push back to remote

**Task:** verify master is clean, pull the remote, and push pending changes.

```bash
git status                 # verify master is in a clean state
git branch -a              # list all local + remote branches

git pull origin master     # bring the remote's changes into master
git push origin master     # push your pending commits back
```
After pushing, refresh the repository on GitHub/GitLab — your commits appear there.
Use a **Personal Access Token** when asked for a password on push.
