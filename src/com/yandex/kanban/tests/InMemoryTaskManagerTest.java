package com.yandex.kanban.tests;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;
import com.yandex.kanban.service.Managers;
import com.yandex.kanban.service.TaskManager;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    TaskManager taskManager = Managers.getDefault();


    @Test
    void addTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        taskManager.addTask(task1);
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        task2.setId(1);
        tasks.add(task2);
        assertEquals(tasks, taskManager.getListTask());
    }

    @Test
    void getTask() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        taskManager.addTask(task1);
        assertEquals(task1, taskManager.getTask(task1.getId()));
    }

    @Test
    void addEpic() {
        ArrayList<Task> epicTasks = new ArrayList<>();
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic2.setId(1);
        epicTasks.add(epic2);
        assertEquals(epicTasks, taskManager.getListEpic());
    }

    @Test
    void getEpic() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        assertEquals(epic1, taskManager.getEpic(epic1.getId()));
    }

    @Test
    void addSubtask() {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        subtask2.setId(2);
        subtasks.add(subtask2);
        assertEquals(subtasks, taskManager.getListSubtask());
    }

    @Test
    void getSubtask() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask1);
        assertEquals(subtask1, taskManager.getSubtask(subtask1.getId()));
    }
}