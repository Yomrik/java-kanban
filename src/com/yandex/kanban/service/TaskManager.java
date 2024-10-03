package com.yandex.kanban.service;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, EpicTask> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId = 1;

    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public ArrayList<Task> getListTask() {
        return new ArrayList<>(tasks.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void removeTask(Integer id) {
        tasks.remove(id);
    }

    public Task getTask(Integer id) {
       return tasks.get(id);
    }

    public void addEpic(EpicTask epicTask) {
        epicTask.setId(nextId++);
        epics.put(epicTask.getId(), epicTask);
    }

    public void updateEpic(EpicTask epicTask) {
        final EpicTask oldEpic = epics.get(epicTask.getId());
        oldEpic.setName(epicTask.getName());
        oldEpic.setDescription(epicTask.getDescription());
    }

    public ArrayList<Task> getListEpic() {
        return new ArrayList<>(epics.values());
    }

    public void clearEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void removeEpic(Integer id) {
        EpicTask epic = epics.remove(id);
        for (Integer item : epic.getSubTaskIds()) {
            subtasks.remove(item);
        }
    }

    public Task getEpic(Integer id) {
        return epics.get(id);
    }

    public void addSubtask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);
        EpicTask epic = epics.get(subtask.getEpicId());
        epic.getSubTaskIds().add(subtask.getId());
        int foundEpicId =  subtasks.get(subtask.getId()).getEpicId();
        updateStatus(foundEpicId);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        int foundEpicId =  subtasks.get(subtask.getId()).getEpicId();
        updateStatus(foundEpicId);
    }

    public ArrayList<Task> getListSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    public void clearSubtasks() {
        subtasks.clear();
        for (EpicTask epicTask : epics.values()) {
            ArrayList<Integer> values = epicTask.getSubTaskIds();
            values.clear();
            epicTask.setStatus(StatusTask.NEW);
        }
    }

    public void removeSubtask(Integer id) {
       int foundEpicId =  subtasks.get(id).getEpicId();
       epics.get(foundEpicId).getSubTaskIds().remove(id);
       subtasks.remove(id);
       updateStatus(foundEpicId);
    }

    public Task getSubtask(Integer id) {
        return subtasks.get(id);
    }

    public ArrayList<Task> getListSubtaskOfEpic(int epicId) {
        ArrayList<Task> list = new ArrayList<>();
        for (Integer item : epics.get(epicId).getSubTaskIds())
            list.add(subtasks.get(item));
        return list;
    }

    private void updateStatus(Integer foundEpicId) {

        int countNew = 0;
        int countDone = 0;

        for (Integer taskId : epics.get(foundEpicId).getSubTaskIds()) {
            Subtask task = subtasks.get(taskId);
            if (task.getStatus() == StatusTask.NEW) {
                countNew++;
            } else if (task.getStatus() == StatusTask.DONE) {
                countDone++;
            } else {
                epics.get(foundEpicId).setStatus(StatusTask.IN_PROGRESS);
                return;
            }
        }

        if (countNew == epics.get(foundEpicId).getSubTaskIds().size()) {
            epics.get(foundEpicId).setStatus(StatusTask.NEW);
        } else if (countDone == epics.get(foundEpicId).getSubTaskIds().size()) {
            epics.get(foundEpicId).setStatus(StatusTask.DONE);
        } else {
            epics.get(foundEpicId).setStatus(StatusTask.IN_PROGRESS);
        }
    }

    @Override
    public String toString() {
        return "com.yandex.kanban.service.TaskManager{" +
                "tasks=" + tasks +
                ", epics=" + epics +
                ", subtasks=" + subtasks +
                ", nextId=" + nextId +
                '}';
    }
}
