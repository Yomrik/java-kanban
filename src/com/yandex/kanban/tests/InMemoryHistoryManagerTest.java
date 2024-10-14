package com.yandex.kanban.tests;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;
import com.yandex.kanban.service.HistoryManager;
import com.yandex.kanban.service.Managers;
import com.yandex.kanban.service.TaskManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    TaskManager taskManager = Managers.getDefault();
    HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void add() {
        ArrayList<Task> hystory = new ArrayList<>();

        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        taskManager.addTask(task1);
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №2", "обычная задача №2");
        taskManager.addTask(task2);
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.2", "маленькая задача №1.2", epic1.getId());
        taskManager.addSubtask(subtask2);

        historyManager.add(taskManager.getTask(1));
        historyManager.add(taskManager.getTask(2));
        historyManager.add(taskManager.getEpic(3));
        historyManager.add(taskManager.getSubtask(4));
        historyManager.add(taskManager.getSubtask(5));

        hystory.add(task1);
        hystory.add(task2);
        hystory.add(epic1);
        hystory.add(subtask1);
        hystory.add(subtask2);

        assertEquals(hystory, historyManager.getHistory());


    }

}