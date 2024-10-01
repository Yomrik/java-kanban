package com.yandex.kanban.model;

public class Task {
    private String name;
    private String description;
    private int id;
    public StatusTask status;


    public Task(StatusTask status, String description, String name) {
        this.status = status;
        this.description = description;
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.yandex.kanban.model.Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
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
}
