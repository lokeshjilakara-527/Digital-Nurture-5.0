# Git-HOL 2 — Ignoring files with .gitignore

**Task:** create a `.log` file and a `log/` folder, then make Git ignore both.

```bash
# create the .gitignore with the ignore rules
printf "*.log\nlog/\n" > .gitignore

# create the files that should be ignored
echo "debug output" > app.log
mkdir log && echo "trace" > log/trace.log

git add .
git status -s
```
Because of `.gitignore`, only `.gitignore` itself is staged — `app.log` and the `log/`
folder are ignored:
```
A  .gitignore
```
After `git commit -m "Add gitignore"`, `git status` is clean (the log files are untracked
and ignored, so they never reach the repository).
