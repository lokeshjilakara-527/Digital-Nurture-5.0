# Git-HOL 3 — Branching & merging

**Task:** create branch **GitNewBranch**, commit to it, then merge into master.

```bash
git checkout -b GitNewBranch     # create + switch
git branch                       # list branches; "*" marks the current one

echo "feature" > feature.txt
git add feature.txt
git commit -m "Add feature.txt"
git status

git checkout master              # switch back to master
git diff master GitNewBranch     # list differences
git merge GitNewBranch           # merge the branch in
```
Real merge output:
```
 1 file changed, 1 insertion(+)
 create mode 100644 feature.txt
```
Clean up after merging: `git branch -d GitNewBranch`.
