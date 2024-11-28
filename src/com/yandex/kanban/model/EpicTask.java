package com.yandex.kanban.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EpicTask extends Task {
    private final List<Integer> subTaskIds = new ArrayList<>();
    private TypeTask type;

    public EpicTask(String description, String name, TypeTask type) {
        super(StatusTask.NEW, description, name, TypeTask.EPICTASK);
        this.type = type;
    }

    public List<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        EpicTask task = (EpicTask) object;
        return Objects.equals(subTaskIds, task.subTaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTaskIds);
    }
}

