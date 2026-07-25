# CI/CD Hands-on — GitHub Actions

Continuous Integration (CI) automatically builds/tests your code on every push.
This example runs the Week 5 React build on GitHub's servers.

## How to use
1. In your repository, create the folders `.github/workflows/`.
2. Copy `github-actions-ci.yml` there and rename it to `ci.yml`.
3. Commit and push. Open the **Actions** tab on GitHub to watch it run.
4. A green check = build passed (CI working); a red X = build broke.

Adjust `working-directory` to point at the folder that contains your `package.json`.
Popular CI/CD tools: GitHub Actions, Jenkins, GitLab CI/CD, CircleCI.
