package com.yandex.kanban.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    void testEquals() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic1.setId(1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        subtask1.setId(2);
        subtask2.setId(2);
        assertEquals(subtask2, subtask1);
    }

    @Test
    void testHashCode() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic1.setId(1);
        Subtask subtask1 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        Subtask subtask2 = new Subtask(StatusTask.NEW, "сделать маленькую задачу №1.1", "маленькая задача №1.1", epic1.getId());
        subtask1.setId(2);
        subtask2.setId(2);
        assertEquals(Objects.hash(subtask2), Objects.hash(subtask1));
    }
}