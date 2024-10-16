package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager  {
    private final List<Task> listHistory = new LinkedList<>();
    private int count = 0;
    private static final int MAX_SIZE = 10;

    @Override
    public void add(Task task) {
        if (listHistory.size() >= MAX_SIZE) {
            listHistory.addFirst(task);
            listHistory.remove(count+1);
            if (count == 9) {
                count = -1;
            }
            count++;
        }
        else {
            listHistory.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(listHistory);
    }
}
