package com.yandex.kanban.service;

import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Task;
import com.yandex.kanban.model.TypeTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FileBackedTaskManagerTest {

    @Test
    void saveTest() throws IOException {
        FileBackedTaskManager data = new FileBackedTaskManager("data.txt");

        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        data.addTask(task4);

        File file = File.createTempFile("test", ".tmp");
        PrintWriter writer = new PrintWriter(file);
        writer.println("1,TASK,обычная задача №1,сделать обычную задачу №1,NEW");
        writer.close();

        assertEquals(Files.readString(Path.of("data.txt")), Files.readString(Path.of(file.getPath())));

        File file1 = new File("data.txt");
        PrintWriter writer1 = new PrintWriter(file1);
        writer1.println("");
        writer1.close();
        data.setNextId(1);
    }

    @Test
    void fromStringTest() throws FileNotFoundException {
        FileBackedTaskManager data = new FileBackedTaskManager("data.txt");
        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        Task task = data.fromString("0,TASK,обычная задача №1,сделать обычную задачу №1,NEW");
        assertEquals(task4, task);
        File file1 = new File("data.txt");
        PrintWriter writer1 = new PrintWriter(file1);
        writer1.println("");
        writer1.close();
    }

    @Test
    void loadFromFileTest() throws IOException {
        FileBackedTaskManager data = new FileBackedTaskManager("data.txt");
        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1", TypeTask.TASK);
        data.addTask(task4);

        File file = File.createTempFile("test", ".tmp");
        PrintWriter writer = new PrintWriter(file);
        writer.println("1,TASK,обычная задача №1,сделать обычную задачу №1,NEW");
        writer.close();

        data.loadFromFile(file);

        String line = data.getListTask().toString().replace("]", "");
        String line1 = line.replace("[", "");
        String exp = "1,TASK,обычная задача №1,сделать обычную задачу №1,NEW";

        assertEquals(exp , line1);


    }
}