package com.yandex.kanban.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTaskTest {

    @Test
    void testEquals() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic1.setId(1);
        epic2.setId(1);
        assertEquals(epic2, epic1);
    }

    @Test
    void testHashCode() {
        EpicTask epic1 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        EpicTask epic2 = new EpicTask("Сделать БОЛЬШУЮ задачу №1", "БОЛЬШАЯ ЗАДАЧА №1");
        epic1.setId(1);
        epic2.setId(1);
        assertEquals(Objects.hash(epic2), Objects.hash(epic1));
    }
}