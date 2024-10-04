package com.yandex.kanban;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;
import com.yandex.kanban.service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

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
        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу #2", "БОЛЬШАЯ ЗАДАЧА №2");
        taskManager.addEpic(epic2);
        Subtask subtask3 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №2.1", "маленькая задача №2.1", epic2.getId());
        taskManager.addSubtask(subtask3);

        System.out.println(taskManager.getListTask());
        System.out.println("");
        System.out.println(taskManager.getListEpic());
        System.out.println("");
        System.out.println(taskManager.getListSubtask());
        System.out.println("---------------");

        Subtask subtask4 = new Subtask(StatusTask.DONE, "Заменить подзадачу №1", "Задача на замену", epic1.getId());
        subtask4.setId(subtask1.getId());
        taskManager.updateSubtask(subtask4);

        System.out.println(taskManager.getListEpic());
        System.out.println("");
        System.out.println(taskManager.getListSubtask());
        System.out.println("---------------");

        taskManager.removeTask(1);
        taskManager.removeEpic(3);

        System.out.println(taskManager.getListTask());
        System.out.println("");
        System.out.println(taskManager.getListEpic());
        System.out.println("");
        System.out.println(taskManager.getListSubtask());
        System.out.println("");


    }
}
