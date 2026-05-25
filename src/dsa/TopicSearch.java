package dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Topic;

public class TopicSearch {
    public Topic binarySearchByName(ArrayList<Topic> topics, String targetName) {
        ArrayList<Topic> sorted = new ArrayList<Topic>(topics);

        Collections.sort(sorted, new Comparator<Topic>() {
            @Override
            public int compare(Topic first, Topic second) {
                return first.getName().compareToIgnoreCase(second.getName());
            }
        });

        int left = 0;
        int right = sorted.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            Topic current = sorted.get(middle);
            int result = current.getName().compareToIgnoreCase(targetName);

            if (result == 0) {
                return current;
            } else if (result < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null;
    }
}
