# Week 6 — GIT & CI/CD Mandatory Hands-on

## GIT hands-on (mandatory Git-HOL 1–5) — built to the real problem statements
| # | Task | File |
|---|------|------|
| 1 | Setup, init, add, commit, push/pull | `git-hol/01-setup-and-repo.md` |
| 2 | `.gitignore` for `.log` files and `log/` folder | `git-hol/02-gitignore.md` |
| 3 | Branch **GitNewBranch** + merge | `git-hol/03-branching-merging.md` |
| 4 | Resolve merge conflict on `hello.xml` | `git-hol/04-merge-conflict.md` |
| 5 | Clean up & push back to remote | `git-hol/05-cleanup-push.md` |

Each guide has the exact Git Bash commands and the **real output** (I ran them).
Run the demo end-to-end:
```bash
bash demo.sh
```

## CI/CD hands-on
Working GitHub Actions workflow in `ci-cd/` that builds the Week 5 React app on every
push. Copy it to `.github/workflows/ci.yml`. See `ci-cd/README.md`.
