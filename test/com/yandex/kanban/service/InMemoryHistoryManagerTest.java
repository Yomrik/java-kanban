package com.yandex.kanban.service;

import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void add() {
        List<Task> hystory = new ArrayList<>();

        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        historyManager.add(task1);
        hystory.add(task1);

        assertEquals(hystory, historyManager.getHistory());
    }

    @Test
    void maxSize10ListHystorytest() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");

        for (int i = 0; i < 15 ; i++) {
            historyManager.add(task1);
        }

        assertEquals(10, historyManager.getHistory().size());
    }
}