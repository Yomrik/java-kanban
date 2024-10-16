package com.yandex.kanban;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;
import com.yandex.kanban.service.Managers;
import com.yandex.kanban.service.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    TaskManager taskManager = Managers.getDefault();

    @Test
    void taskEqualTaskById() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        task2.setId(1);
        taskManager.addTask(task1);
        assertEquals(task2,task1);
    }

    @Test
    void EpictaskEqualEpictaskById() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic2.setId(1);
        taskManager.addEpic(epic1);
        assertEquals(epic2, epic1);
    }

    @Test
    void SubtaskEqualSubtaskById() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        subtask2.setId(2);
        assertEquals(subtask2, subtask1);
    }



}

