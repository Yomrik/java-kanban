package com.yandex.kanban.model;

import java.util.Objects;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(StatusTask status, String description, String name, int epicId) {
        super(status, description, name);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Subtask subtask = (Subtask) object;
        return epicId == subtask.epicId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }
}

