package com.yandex.kanban.service;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;

import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void updateTask(Task task);

    List<Task> getListTask();

    void clearTasks();

    void removeTask(Integer id);

    Task getTask(Integer id);

    void addEpic(EpicTask epicTask);

    void updateEpic(EpicTask epicTask);

    List<Task> getListEpic();

    void clearEpics();

    void removeEpic(Integer id);

    Task getEpic(Integer id);

    void addSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    List<Task> getListSubtask();

    void clearSubtasks();

    void removeSubtask(Integer id);

    Task getSubtask(Integer id);

    List<Task> getListSubtaskOfEpic(int epicId);

    void updateStatus(Integer foundEpicId);

    List<Task> getListHystory();
}
