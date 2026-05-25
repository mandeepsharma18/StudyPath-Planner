package model;

public class Topic {
    private int id;
    private String name;
    private String category;
    private int difficulty;
    private int importance;
    private boolean completed;

    public Topic(int id, String name, String category, int difficulty, int importance) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.importance = importance;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getImportance() {
        return importance;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public void markPending() {
        completed = false;
    }

    public int getPriorityScore() {
        return (importance * 2) + difficulty;
    }

    public String getStatusText() {
        if (completed) {
            return "Completed";
        }
        return "Pending";
    }

    @Override
    public String toString() {
        return id + ". " + name
                + " | Category: " + category
                + " | Difficulty: " + difficulty
                + " | Importance: " + importance
                + " | Priority: " + getPriorityScore()
                + " | " + getStatusText();
    }
}
