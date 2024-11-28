package com.yandex.kanban.service;

import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Task;
import com.yandex.kanban.model.TypeTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void add() {
        List<Task> hystory = new ArrayList<>();

        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        historyManager.add(task1);
        hystory.add(task1);

        assertEquals(hystory, historyManager.getHistory());
    }

    @Test
    void removeCopiesListHystoryTest() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        task1.setId(1);
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №2", "обычная задача №2", TypeTask.TASK);
        task2.setId(2);
        Task task3 = new Task(StatusTask.NEW, "сделать обычную задачу №3", "обычная задача №3", TypeTask.TASK);
        task3.setId(1);
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);

        assertEquals(2, historyManager.getHistory().size());
    }

    @Test
    void orderAdditionListHystoryTest() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        task1.setId(1);
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №2", "обычная задача №2", TypeTask.TASK);
        task2.setId(2);
        Task task3 = new Task(StatusTask.NEW, "сделать обычную задачу №3", "обычная задача №3", TypeTask.TASK);
        task3.setId(3);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        assertEquals(taskList, historyManager.getHistory());
    }

    @Test
    void removeTaskAndRemoveTaskHystoryTest() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        task1.setId(1);
        historyManager.add(task1);
        historyManager.remove(1);

        assertEquals(new ArrayList<>(), historyManager.getHistory());
    }
}