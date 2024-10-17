package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager  {
    private final List<Task> listHistory = new LinkedList<>();
    private static final int MAX_SIZE = 10;

    @Override
    public void add(Task task) {
        if (task != null) {
            if (listHistory.size() == MAX_SIZE) {
                listHistory.removeLast();
            }
            listHistory.addFirst(task);
        } else {
            System.out.println("Задача отсутствует");
        }
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(listHistory);
    }
}
