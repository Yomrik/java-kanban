package com.yandex.kanban;
import com.yandex.kanban.model.*;
import com.yandex.kanban.service.FileBackedTaskManager;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        FileBackedTaskManager data = new FileBackedTaskManager("data.txt");
        FileBackedTaskManager.loadFromFile(new File("data.txt"));

        System.out.println("");
        System.out.println("---------------");
        System.out.println("");

        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        data.addTask(task4);
        Task task5 = new Task(StatusTask.NEW, "сделать обычную задачу №2", "обычная задача №2", TypeTask.TASK);
        data.addTask(task5);
        EpicTask epic6 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1", TypeTask.EPICTASK);
        data.addEpic(epic6);
        Subtask subtask7 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic6.getId(), TypeTask.SUBTASK);
        data.addSubtask(subtask7);
        Subtask subtask8 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.2", "маленькая задача №1.2", epic6.getId(), TypeTask.SUBTASK);
        data.addSubtask(subtask8);
        EpicTask epic9 = new EpicTask("Сделать БОЛЬШУЮ задачу #2", "БОЛЬШАЯ ЗАДАЧА №2", TypeTask.EPICTASK);
        data.addEpic(epic9);
        Subtask subtask0 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №2.1", "маленькая задача №2.1", epic9.getId(), TypeTask.SUBTASK);
        data.addSubtask(subtask0);


        for (Task item : data.getListTask()) {
            System.out.println(item);
        }
        for (Task item : data.getListEpic()) {
            System.out.println(item);
        }
        for (Task item : data.getListSubtask()) {
            System.out.println(item);
        }
        System.out.println("---------------");
        System.out.println(data.getNextId());

    }
}
