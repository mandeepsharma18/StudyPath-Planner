package dsa;

import java.util.LinkedList;
import java.util.Queue;

public class StudyQueue {
    private Queue<Integer> topicIds;

    public StudyQueue() {
        topicIds = new LinkedList<Integer>();
    }

    public void enqueue(int topicId) {
        topicIds.add(topicId);
    }

    public int dequeue() {
        if (topicIds.isEmpty()) {
            return -1;
        }

        return topicIds.remove();
    }

    public boolean isEmpty() {
        return topicIds.isEmpty();
    }

    public int size() {
        return topicIds.size();
    }
}
