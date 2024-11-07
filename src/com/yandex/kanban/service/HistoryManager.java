package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;

import java.util.Collection;
import java.util.List;

public interface HistoryManager {

    void add(Task task);

    List<Task> getHistory();

    void remove(int id);

    void clearHystory(Collection<Integer> id);

}