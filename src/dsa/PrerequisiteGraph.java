package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PrerequisiteGraph {
    private HashMap<String, ArrayList<String>> graph;

    public PrerequisiteGraph() {
        graph = new HashMap<String, ArrayList<String>>();
    }

    public void addTopic(String topicName) {
        if (!graph.containsKey(topicName)) {
            graph.put(topicName, new ArrayList<String>());
        }
    }

    public void addPrerequisite(String before, String after) {
        addTopic(before);
        addTopic(after);
        graph.get(before).add(after);
    }

    public ArrayList<String> findPath(String start, String end) {
        ArrayList<String> emptyPath = new ArrayList<String>();

        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return emptyPath;
        }

        Queue<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        HashMap<String, String> parent = new HashMap<String, String>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.remove();

            if (current.equalsIgnoreCase(end)) {
                return buildPath(parent, start, current);
            }

            ArrayList<String> neighbours = graph.get(current);
            for (String neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    parent.put(neighbour, current);
                    queue.add(neighbour);
                }
            }
        }

        return emptyPath;
    }

    public void printGraph() {
        for (Map.Entry<String, ArrayList<String>> entry : graph.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private ArrayList<String> buildPath(HashMap<String, String> parent, String start, String end) {
        ArrayList<String> path = new ArrayList<String>();
        String current = end;

        while (current != null) {
            path.add(0, current);

            if (current.equalsIgnoreCase(start)) {
                break;
            }

            current = parent.get(current);
        }

        return path;
    }
}
