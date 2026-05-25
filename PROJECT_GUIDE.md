# Project Guide

## What You Built

StudyPath Planner is a Java console app for managing study topics.

The app stores topics like:

- Variables
- Loops
- Arrays
- Recursion
- Sorting
- Graphs

Then it lets the user mark progress, search topics, sort by priority, queue study sessions, and find prerequisite paths.

## DSA Used

## 1. ArrayList

Used in `StudyPlanner` to store all topics.

Why: The number of topics can grow when the user adds new topics.

## 2. HashMap

Used in `StudyPlanner` to find a topic quickly by id.

Example:

```java
topicById.get(id)
```

Why: Searching by id is faster than checking every topic one by one.

## 3. Stack

Implemented in `ActionStack`.

Used for undo.

Example:

1. User completes Arrays.
2. Arrays id is pushed onto the stack.
3. User chooses undo.
4. Last completed topic is popped from the stack.

This follows LIFO: Last In, First Out.

## 4. Queue

Implemented in `StudyQueue`.

Used for study order.

Example:

1. Add Arrays to queue.
2. Add Sorting to queue.
3. Study next gives Arrays first.

This follows FIFO: First In, First Out.

## 5. Merge Sort

Implemented in `TopicSorter`.

Used to sort topics by priority score.

Priority formula:

```java
(importance * 2) + difficulty
```

## 6. Binary Search

Implemented in `TopicSearch`.

Used to search a topic by name after sorting topics alphabetically.

## 7. Graph and BFS

Implemented in `PrerequisiteGraph`.

Used to find a path from one topic to another.

Example:

```text
Variables -> Loops -> Arrays -> Sorting
```

BFS means Breadth First Search. It is useful for finding the shortest path in an unweighted graph.

## Files To Explain First

Start with these files when learning the code:

1. `src/model/Topic.java`
2. `src/StudyPlanner.java`
3. `src/dsa/ActionStack.java`
4. `src/dsa/StudyQueue.java`
5. `src/dsa/TopicSorter.java`
6. `src/dsa/TopicSearch.java`
7. `src/dsa/PrerequisiteGraph.java`

## How We Can Deploy Later

The current app is a console app. For online deployment, we can choose one of these paths:

1. Easiest: Deploy it as an online console app on Replit.
2. Better portfolio version: Convert it to a small web app using Java's built-in `HttpServer`.
3. More advanced later: Convert it to Spring Boot after you learn frameworks.

For your current level, option 2 is the best next step because it still uses core Java.

