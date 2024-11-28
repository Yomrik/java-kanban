package com.yandex.kanban.model;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private int id;
    private StatusTask status;
    private TypeTask type;

    public Task(StatusTask status, String description, String name, TypeTask type) {
        this.status = status;
        this.description = description;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return id + "," + type + "," + name + "," + description + "," + status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public TypeTask getType() {
        return type;
    }

    public void setType(TypeTask type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }


}
