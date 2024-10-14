package com.yandex.kanban.model;

import java.util.ArrayList;
import java.util.Objects;

public class EpicTask extends Task {
    private final ArrayList<Integer> subTaskIds = new ArrayList<>();


    public EpicTask(String description, String name) {
        super(StatusTask.NEW, description, name);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }

}

