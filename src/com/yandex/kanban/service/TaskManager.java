package com.yandex.kanban.service;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;

import java.util.ArrayList;

public interface TaskManager {
    void addTask(Task task);

    void updateTask(Task task);

    ArrayList<Task> getListTask();

    void clearTasks();

    void removeTask(Integer id);

    Task getTask(Integer id);

    void addEpic(EpicTask epicTask);

    void updateEpic(EpicTask epicTask);

    ArrayList<Task> getListEpic();

    void clearEpics();

    void removeEpic(Integer id);

    EpicTask getEpic(Integer id);

    void addSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    ArrayList<Task> getListSubtask();

    void clearSubtasks();

    void removeSubtask(Integer id);

    Subtask getSubtask(Integer id);

    ArrayList<Task> getListSubtaskOfEpic(int epicId);

    void updateStatus(Integer foundEpicId);



}
