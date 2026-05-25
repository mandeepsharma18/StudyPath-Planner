# Vercel Deployment Guide

This project has two parts:

- Java console app: the main Java + DSA project.
- Web demo: `index.html`, `styles.css`, and `script.js` for online deployment.

## Easiest Deployment Method

Use GitHub + Vercel.

## Step 1: Create a GitHub Repository

1. Go to GitHub.
2. Create a new repository named `StudyPath-Planner`.
3. Upload this project folder.

Upload these important files and folders:

```text
src/
index.html
styles.css
script.js
README.md
PROJECT_GUIDE.md
VERCEL_DEPLOYMENT_GUIDE.md
run.bat
vercel.json
```

Do not upload:

```text
out/
*.class
```

Those are compiled files and are not needed online.

## Step 2: Deploy On Vercel

1. Go to Vercel.
2. Sign in with GitHub.
3. Click `Add New Project`.
4. Import `StudyPath-Planner`.
5. Select framework preset: `Other`.
6. Keep build command empty.
7. Keep output directory empty.
8. Click `Deploy`.

## What To Say During Demo

Say this:

```text
This is StudyPath Planner, a Java DSA project. The original version is a Java console app.
I also made a web demo so it can be deployed and viewed online.
The project uses ArrayList, HashMap, Stack, Queue, Merge Sort, Binary Search, and BFS.
```

## Best Demo Features

Show these on the web page:

1. Add a topic.
2. Mark a topic completed.
3. Sort by priority.
4. Search for `Arrays`.
5. Add a topic to queue.
6. Click Study Next.
7. Find path from `Variables` to `Sorting`.

