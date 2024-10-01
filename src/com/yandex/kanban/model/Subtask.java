package com.yandex.kanban.model;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(StatusTask status, String description, String name, int epicId) {
        super(status, description, name);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}

