package com.yandex.kanban.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testEquals() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        task1.setId(1);
        task2.setId(1);
        assertEquals(task2,task1);
    }

    @Test
    void testHashCode() {
        Task task1 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        Task task2 = new Task(StatusTask.NEW, "сделать обычную задачу №1", "обычная задача №1");
        task1.setId(1);
        task2.setId(1);
        assertEquals(Objects.hash(task2), Objects.hash(task1));
    }
}