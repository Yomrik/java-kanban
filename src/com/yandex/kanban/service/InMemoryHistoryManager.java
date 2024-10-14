package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryHistoryManager implements HistoryManager  {
   private final List<Task> listHistory = new ArrayList<>();
    private int count = 0;

    @Override
    public void add(Task task) {
        if (listHistory.size() >= 10) {
            listHistory.add(0, task);
            listHistory.remove(count+1);
            if (count == 9) {
                count = -1;
            }
            count++;
        } else {
            listHistory.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return listHistory;
    }

    @Override
    public String toString() {
        return "InMemoryHistoryManager{" +
                "listHistory=" + listHistory +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        InMemoryHistoryManager that = (InMemoryHistoryManager) object;
        return Objects.equals(listHistory, that.listHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(listHistory);
    }
}
