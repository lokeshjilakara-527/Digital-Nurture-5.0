#!/usr/bin/env bash
# Runnable demo of Git-HOL 2,3,4 in a throwaway temp folder.
set -e
DIR=$(mktemp -d); cd "$DIR"; echo "Working in $DIR"
git init -q -b master && git config user.name Demo && git config user.email d@e.com
echo "# GitDemo" > README.md && git add . && git commit -qm "Initial commit"

echo "[HOL2] .gitignore for *.log and log/"
printf "*.log\nlog/\n" > .gitignore; echo x > app.log; mkdir log; echo y > log/t.log
git add .; git status -s; git commit -qm "Add gitignore"

echo "[HOL3] branch GitNewBranch + merge"
git checkout -q -b GitNewBranch; echo feature > feature.txt; git add .; git commit -qm "feature"
git checkout -q master; git merge GitNewBranch

echo "[HOL4] conflict on hello.xml"
git checkout -q -b GitWork; echo "<m>branch</m>" > hello.xml; git add .; git commit -qm "branch"
git checkout -q master; echo "<m>master</m>" > hello.xml; git add .; git commit -qm "master"
git merge GitWork || true
echo "Conflicted hello.xml:"; cat hello.xml
