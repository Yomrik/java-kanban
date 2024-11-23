package com.yandex.kanban.service;

import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FileBackedTaskManagerTest {

    @Test
    void saveTest() throws IOException {
        FileBackedTaskManager data = new FileBackedTaskManager();

        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        data.addTask(task4);

        File file = File.createTempFile("test", ".tmp");
        PrintWriter writer = new PrintWriter(file);
        writer.println("1,TASK,обычная задача №1,сделать обычную задачу №1,NEW");
        writer.close();

        assertEquals(Files.readString(Path.of("data.txt")), Files.readString(Path.of(file.getPath())));
    }

    @Test
    void fromStringTest() {
        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        Task task = FileBackedTaskManager.fromString("0,TASK,обычная задача №1,сделать обычную задачу №1,NEW");
        assertEquals(task4, task);
    }

    @Test
    void loadFromFileTest() throws IOException {
        FileBackedTaskManager data = new FileBackedTaskManager();
        Task task4 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        data.addTask(task4);

        File file = File.createTempFile("test", ".tmp");
        PrintWriter writer = new PrintWriter(file);
        writer.println("1,TASK,обычная задача №1,сделать обычную задачу №1,NEW");
        writer.close();

        FileBackedTaskManager.loadFromFile(file);

        String line = data.getTasks().values().toString().replace("]", "");
        String line1 = line.replace("[", "");

        assertEquals("1,TASK,обычная задача №1,сделать обычную задачу №1,NEW", line1);

    }
}