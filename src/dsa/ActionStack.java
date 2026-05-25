package dsa;

import java.util.ArrayList;

public class ActionStack {
    private ArrayList<Integer> completedTopicIds;

    public ActionStack() {
        completedTopicIds = new ArrayList<Integer>();
    }

    public void push(int topicId) {
        completedTopicIds.add(topicId);
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }

        int lastIndex = completedTopicIds.size() - 1;
        int topicId = completedTopicIds.get(lastIndex);
        completedTopicIds.remove(lastIndex);
        return topicId;
    }

    public boolean isEmpty() {
        return completedTopicIds.size() == 0;
    }
}
