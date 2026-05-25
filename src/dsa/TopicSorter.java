package dsa;

import java.util.ArrayList;
import model.Topic;

public class TopicSorter {
    public ArrayList<Topic> sortByPriority(ArrayList<Topic> topics) {
        ArrayList<Topic> copy = new ArrayList<Topic>(topics);
        mergeSort(copy, 0, copy.size() - 1);
        return copy;
    }

    private void mergeSort(ArrayList<Topic> topics, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(topics, left, middle);
        mergeSort(topics, middle + 1, right);
        merge(topics, left, middle, right);
    }

    private void merge(ArrayList<Topic> topics, int left, int middle, int right) {
        ArrayList<Topic> merged = new ArrayList<Topic>();
        int i = left;
        int j = middle + 1;

        while (i <= middle && j <= right) {
            Topic first = topics.get(i);
            Topic second = topics.get(j);

            if (first.getPriorityScore() >= second.getPriorityScore()) {
                merged.add(first);
                i++;
            } else {
                merged.add(second);
                j++;
            }
        }

        while (i <= middle) {
            merged.add(topics.get(i));
            i++;
        }

        while (j <= right) {
            merged.add(topics.get(j));
            j++;
        }

        for (int index = 0; index < merged.size(); index++) {
            topics.set(left + index, merged.get(index));
        }
    }
}
