# Git-HOL 4 — Resolving a merge conflict

**Task:** create branch **GitWork** with `hello.xml`, change the same file on master,
then merge and resolve the conflict.

```bash
git checkout -b GitWork
echo "<msg>from branch</msg>" > hello.xml
git add hello.xml && git commit -m "hello.xml on branch"

git checkout master
echo "<msg>from master</msg>" > hello.xml   # different content
git add hello.xml && git commit -m "hello.xml on master"

git log --oneline --graph --decorate --all
git merge GitWork
```
Git reports the conflict:
```
Auto-merging hello.xml
CONFLICT (add/add): Merge conflict in hello.xml
Automatic merge failed; fix conflicts and then commit the result.
```
`hello.xml` now contains conflict markers:
```
<<<<<<< HEAD
<msg>from master</msg>
=======
<msg>from branch</msg>
>>>>>>> GitWork
```
**Resolve:** edit the file to keep the correct content and delete the `<<<<<<<`,
`=======`, `>>>>>>>` lines, then:
```bash
git add hello.xml
git commit -m "Resolve merge conflict in hello.xml"
```
(On Windows you can use a visual tool like **P4Merge** via `git mergetool`.)
