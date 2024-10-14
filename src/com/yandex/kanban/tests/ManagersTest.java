package com.yandex.kanban.tests;

import com.yandex.kanban.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ManagersTest {

    @Test
    void getDefault() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertEquals(new InMemoryTaskManager(), taskManager);

    }

    @Test
    void getDefaultHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Assertions.assertEquals(new InMemoryHistoryManager(), historyManager);
    }
}