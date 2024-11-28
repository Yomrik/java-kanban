package com.yandex.kanban;

import com.yandex.kanban.model.*;
import com.yandex.kanban.service.FileBackedTaskManager;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        FileBackedTaskManager data = new FileBackedTaskManager("data.txt");
        FileBackedTaskManager.loadFromFile(new File("data.txt"));
       // TaskManager taskManager = Managers.getDefault();

//
//      //  Добавляем задачи
//
//        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
//        taskManager.addTask(task1);
//        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №2", "обычная задача №2");
//        taskManager.addTask(task2);
//        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
//        taskManager.addEpic(epic1);
//        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
//        taskManager.addSubtask(subtask1);
//        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.2", "маленькая задача №1.2", epic1.getId());
//        taskManager.addSubtask(subtask2);
//        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу #2", "БОЛЬШАЯ ЗАДАЧА №2");
//        taskManager.addEpic(epic2);
//        Subtask subtask3 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №2.1", "маленькая задача №2.1", epic2.getId());
//        taskManager.addSubtask(subtask3);
//
//        //Выводим списки задач
//
//        System.out.println("");
//        System.out.println("Выводим списки задач");
//        System.out.println("");
//
//
//        for (Task item : taskManager.getListTask()) {
//            System.out.println(item);
//
//
//        }
//        System.out.println("");
//
//        for (Task item : taskManager.getListEpic()) {
//            System.out.println(item);
//        }
//        System.out.println("");
//
//        for (Task item : taskManager.getListSubtask()) {
//            System.out.println(item);
//        }
//        System.out.println("");
//        System.out.println("---------------");
//        System.out.println("");
//        // обновляем подзадачу
//
//        Subtask subtask4 = new Subtask(StatusTask.DONE, "Заменить подзадачу №1", "Задача на замену", epic1.getId());
//        subtask4.setId(subtask1.getId());
//        taskManager.updateSubtask(subtask4);
//
//        System.out.println("");
//        System.out.println("Выводим обновленные списки задач");
//        System.out.println("");
//        //Выводим обновленные списки задач
//
//        for (Task item : taskManager.getListEpic()) {
//            System.out.println(item);
//        }
//        System.out.println("");
//
//        for (Task item : taskManager.getListSubtask()) {
//            System.out.println(item);
//        }
//        System.out.println("");
//        System.out.println("---------------");
//        System.out.println("");
//
//        //Удаляем одну простую задачу и эпик
//
//        taskManager.removeTask(1);
//        taskManager.removeEpic(6);
//        //Выводим обновленные списки задач
//
//        System.out.println("");
//        System.out.println("Выводим обновленные списки задач");
//        System.out.println("");
//
//        for (Task item : taskManager.getListTask()) {
//            System.out.println(item);
//        }
//        System.out.println("");
//
//        for (Task item : taskManager.getListEpic()) {
//            System.out.println(item);
//        }
//        System.out.println("");
//
//        for (Task item : taskManager.getListSubtask()) {
//            System.out.println(item);
//        }
//        System.out.println("");
//        System.out.println("---------------");
//        System.out.println("");
//
//        //Добавляем просмотренные задачи в список истории
//
//        System.out.println("");
//        System.out.println("Выводим историю");
//        System.out.println("");
//
//        taskManager.getTask(2);
//        taskManager.getSubtask(5);
//        taskManager.getTask(2);
//        taskManager.getEpic(3);
//        taskManager.getSubtask(4);
//        taskManager.getSubtask(5);
//        taskManager.getEpic(3);
//        taskManager.getTask(2);
//        taskManager.getEpic(3);
//        taskManager.getSubtask(4);
//        taskManager.getSubtask(5);
//        taskManager.getEpic(3);
//        taskManager.getSubtask(10);
//
//
//
//        /*
//        Проверяем, что количество задач не превышает 10, новые задачи
//        добавляются в начало списка, а самые старые удаляются
//        */
//
//        for (Task item : taskManager.getListHystory()) {
//            System.out.println(item);
//        }


        System.out.println("");
        System.out.println("---------------");
        System.out.println("");

//        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
//        data.addTask(task4);
//        Task task5 = new Task(StatusTask.NEW, "сделать обычную задачу №2", "обычная задача №2", TypeTask.TASK);
//        data.addTask(task5);
//        EpicTask epic6 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1",TypeTask.EPICTASK);
//        data.addEpic(epic6);
//        Subtask subtask7 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic6.getId(), TypeTask.SUBTASK);
//        data.addSubtask(subtask7);
//        Subtask subtask8 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.2", "маленькая задача №1.2", epic6.getId(), TypeTask.SUBTASK);
//        data.addSubtask(subtask8);
//        EpicTask epic9 = new EpicTask("Сделать БОЛЬШУЮ задачу #2", "БОЛЬШАЯ ЗАДАЧА №2",TypeTask.EPICTASK);
//        data.addEpic(epic9);
//        Subtask subtask0 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №2.1", "маленькая задача №2.1", epic9.getId(), TypeTask.SUBTASK);
//        data.addSubtask(subtask0);


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
