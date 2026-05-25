import dsa.ActionStack;
import dsa.PrerequisiteGraph;
import dsa.StudyQueue;
import dsa.TopicSearch;
import dsa.TopicSorter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Topic;

public class StudyPlanner {
    private ArrayList<Topic> topics;
    private HashMap<Integer, Topic> topicById;
    private ActionStack undoStack;
    private StudyQueue studyQueue;
    private TopicSorter sorter;
    private TopicSearch searcher;
    private PrerequisiteGraph graph;
    private Scanner scanner;
    private int nextId;

    public StudyPlanner(Scanner scanner) {
        this.scanner = scanner;
        topics = new ArrayList<Topic>();
        topicById = new HashMap<Integer, Topic>();
        undoStack = new ActionStack();
        studyQueue = new StudyQueue();
        sorter = new TopicSorter();
        searcher = new TopicSearch();
        graph = new PrerequisiteGraph();
        nextId = 1;
        loadSampleData();
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            System.out.println();

            switch (choice) {
                case 1:
                    viewTopics();
                    break;
                case 2:
                    addTopicFromInput();
                    break;
                case 3:
                    markTopicCompleted();
                    break;
                case 4:
                    undoLastCompletion();
                    break;
                case 5:
                    showPriorityOrder();
                    break;
                case 6:
                    searchTopic();
                    break;
                case 7:
                    addTopicToQueue();
                    break;
                case 8:
                    studyNextTopic();
                    break;
                case 9:
                    findPrerequisitePath();
                    break;
                case 10:
                    graph.printGraph();
                    break;
                case 0:
                    running = false;
                    System.out.println("Keep building. Small steps become strong skills.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("==================================");
        System.out.println("        StudyPath Planner");
        System.out.println("==================================");
        System.out.println("1. View all topics");
        System.out.println("2. Add a new topic");
        System.out.println("3. Mark a topic as completed");
        System.out.println("4. Undo last completion");
        System.out.println("5. Show topics by priority");
        System.out.println("6. Search topic by name");
        System.out.println("7. Add topic to study queue");
        System.out.println("8. Study next queued topic");
        System.out.println("9. Find prerequisite path");
        System.out.println("10. View prerequisite graph");
        System.out.println("0. Exit");
    }

    private void loadSampleData() {
        addTopic("Variables", "Java Fundamentals", 1, 5);
        addTopic("Loops", "Java Fundamentals", 2, 5);
        addTopic("Arrays", "Java Fundamentals", 3, 5);
        addTopic("OOP Basics", "Java Fundamentals", 3, 4);
        addTopic("Recursion", "DSA", 4, 5);
        addTopic("Stack", "DSA", 3, 4);
        addTopic("Queue", "DSA", 3, 4);
        addTopic("Sorting", "DSA", 4, 5);
        addTopic("Binary Search", "DSA", 4, 5);
        addTopic("Graphs", "DSA", 5, 5);

        graph.addPrerequisite("Variables", "Loops");
        graph.addPrerequisite("Loops", "Arrays");
        graph.addPrerequisite("Arrays", "Sorting");
        graph.addPrerequisite("Arrays", "Binary Search");
        graph.addPrerequisite("OOP Basics", "Stack");
        graph.addPrerequisite("OOP Basics", "Queue");
        graph.addPrerequisite("Queue", "Graphs");
        graph.addPrerequisite("Recursion", "Graphs");
    }

    private void viewTopics() {
        for (Topic topic : topics) {
            System.out.println(topic);
        }
    }

    private void addTopicFromInput() {
        String name = readText("Topic name: ");
        String category = readText("Category: ");
        int difficulty = readScore("Difficulty from 1 to 5: ");
        int importance = readScore("Importance from 1 to 5: ");

        Topic topic = addTopic(name, category, difficulty, importance);
        graph.addTopic(topic.getName());
        System.out.println("Added: " + topic.getName());
    }

    private Topic addTopic(String name, String category, int difficulty, int importance) {
        Topic topic = new Topic(nextId, name, category, difficulty, importance);
        topics.add(topic);
        topicById.put(nextId, topic);
        nextId++;
        return topic;
    }

    private void markTopicCompleted() {
        int id = readInt("Enter topic id: ");
        Topic topic = topicById.get(id);

        if (topic == null) {
            System.out.println("Topic not found.");
            return;
        }

        if (topic.isCompleted()) {
            System.out.println("This topic is already completed.");
            return;
        }

        topic.markCompleted();
        undoStack.push(topic.getId());
        System.out.println("Completed: " + topic.getName());
    }

    private void undoLastCompletion() {
        int topicId = undoStack.pop();

        if (topicId == -1) {
            System.out.println("Nothing to undo.");
            return;
        }

        Topic topic = topicById.get(topicId);
        topic.markPending();
        System.out.println("Undo complete. Back to pending: " + topic.getName());
    }

    private void showPriorityOrder() {
        ArrayList<Topic> sorted = sorter.sortByPriority(topics);
        for (Topic topic : sorted) {
            System.out.println(topic);
        }
    }

    private void searchTopic() {
        String name = readText("Enter exact topic name: ");
        Topic topic = searcher.binarySearchByName(topics, name);

        if (topic == null) {
            System.out.println("Topic not found.");
        } else {
            System.out.println(topic);
        }
    }

    private void addTopicToQueue() {
        int id = readInt("Enter topic id: ");

        if (!topicById.containsKey(id)) {
            System.out.println("Topic not found.");
            return;
        }

        studyQueue.enqueue(id);
        System.out.println("Added to queue. Queue size: " + studyQueue.size());
    }

    private void studyNextTopic() {
        int id = studyQueue.dequeue();

        if (id == -1) {
            System.out.println("Study queue is empty.");
            return;
        }

        Topic topic = topicById.get(id);
        System.out.println("Study now: " + topic.getName());
    }

    private void findPrerequisitePath() {
        String start = readText("Start topic: ");
        String end = readText("End topic: ");
        ArrayList<String> path = graph.findPath(start, end);

        if (path.size() == 0) {
            System.out.println("No path found.");
            return;
        }

        System.out.println("Path:");
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) {
                System.out.print(" -> ");
            }
            System.out.print(path.get(i));
        }
        System.out.println();
    }

    private int readScore(String prompt) {
        int score = readInt(prompt);

        while (score < 1 || score > 5) {
            System.out.println("Please enter a number from 1 to 5.");
            score = readInt(prompt);
        }

        return score;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
