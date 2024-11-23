package com.yandex.kanban.service;

import com.yandex.kanban.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryTaskManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();
    TaskManager taskManager = Managers.getDefault();

    @Test
    void addTask() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        taskManager.addTask(task1);
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        task2.setId(1);
        task2.setType(TypeTask.TASK);
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
        List<Task> epicTasks = new ArrayList<>();
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic2.setId(1);
        epic2.setType(TypeTask.EPICTASK);
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
        List<Subtask> subtasks = new ArrayList<>();
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        subtask2.setId(2);
        subtask2.setType(TypeTask.SUBTASK);
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

    @Test
    void statusOfEpic() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(StatusTask.DONE, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask2);

        assertEquals(StatusTask.IN_PROGRESS, epic1.getStatus());
    }

    @Test
    void taskInListHistory() {
        List<Task> hystory = new ArrayList<>();
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        taskManager.addTask(task1);
        taskManager.getTask(task1.getId());
        hystory.add(task1);
        assertEquals(hystory, taskManager.getListHystory());
    }

    @Test
    void removeEpicRemoveSubtasks() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask1);
        Subtask subtask2 = new Subtask(StatusTask.DONE, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        taskManager.addSubtask(subtask2);

        taskManager.removeEpic(epic1.getId());

        assertTrue(taskManager.getListSubtask().isEmpty());
    }

    @Test
    void taskEqualTaskById() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        task2.setId(1);
        taskManager.addTask(task1);
        assertEquals(task2, task1);
    }

    @Test
    void EpictaskEqualEpictaskById() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic2.setId(1);
        epic2.setType(TypeTask.EPICTASK);
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
        subtask2.setType(TypeTask.SUBTASK);
        assertEquals(subtask2, subtask1);
    }

    @Test
    void removeEpicAndRemoveSubtasksHystoryTest() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic1.setId(1);
        epic1.setType(TypeTask.EPICTASK);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        subtask1.setId(2);
        subtask1.setType(TypeTask.SUBTASK);
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.2", "маленькая задача №1.2", epic1.getId());
        subtask2.setId(3);
        subtask2.setType(TypeTask.SUBTASK);

        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.removeEpic(1);

        assertEquals(0, historyManager.getHistory().size());
    }

    @Test
    void clearEpicsTest() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic1.setId(1);
        epic1.setType(TypeTask.EPICTASK);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        subtask1.setId(2);
        subtask1.setType(TypeTask.SUBTASK);
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.2", "маленькая задача №1.2", epic1.getId());
        subtask2.setId(3);
        subtask2.setType(TypeTask.SUBTASK);

        taskManager.addEpic(epic1);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.clearEpics();

        assertEquals(0, historyManager.getHistory().size());
    }
}
