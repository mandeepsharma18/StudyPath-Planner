# StudyPath Planner

A beginner-friendly Java console project built using Java fundamentals and DSA.

## Topic

StudyPath Planner helps a student track learning topics, choose what to study next, and find prerequisite paths between topics.

It uses only core Java:

- Classes and objects
- Arrays and loops
- Methods
- Encapsulation
- `ArrayList`, `HashMap`, `HashSet`
- Stack for undo
- Queue for study session planning
- Merge sort for priority ranking
- Binary search for topic lookup
- Graph BFS for prerequisite path finding

## Project Structure

```text
src/
  Main.java
  StudyPlanner.java
  model/
    Topic.java
  dsa/
    ActionStack.java
    StudyQueue.java
    TopicSorter.java
    TopicSearch.java
    PrerequisiteGraph.java
```

## Run Locally

From this folder:

```powershell
javac -d out src\Main.java src\StudyPlanner.java src\model\Topic.java src\dsa\*.java
java -cp out Main
```

Or use:

```powershell
.\run.ps1
```

If PowerShell scripts are blocked on your computer, use:

```powershell
.\run.bat
```

## Features

1. View all topics
2. Add your own topic
3. Mark a topic as completed
4. Undo the last completion
5. See topics sorted by priority
6. Search for a topic using binary search
7. Add topics to a study queue
8. Study the next queued topic
9. Find a prerequisite path between two topics

## Web Demo

This project also includes a simple web demo for deployment:

```text
index.html
styles.css
script.js
vercel.json
```

Open `index.html` in a browser to view the demo locally.

## Why This Is Good For Your Current Level

This project avoids frameworks, databases, APIs, and advanced Java features. It focuses on the concepts you said you know right now: Java fundamentals and DSA.

## Deployment

The Java console app is the main project. The web demo is included so the project can be deployed online.

Recommended deployment:

1. Upload this project to GitHub.
2. Go to Vercel.
3. Import the GitHub repository.
4. Keep the framework setting as `Other`.
5. Deploy.

After deployment, the live site will show the web demo from `index.html`.
