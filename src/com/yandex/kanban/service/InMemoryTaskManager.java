package com.yandex.kanban.service;

import com.yandex.kanban.model.EpicTask;
import com.yandex.kanban.model.StatusTask;
import com.yandex.kanban.model.Subtask;
import com.yandex.kanban.model.Task;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, EpicTask> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId = 1;

    @Override
    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public List<Task> getListTask() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public void removeTask(Integer id) {
        tasks.remove(id);
    }

    @Override
    public Task getTask(Integer id) {
       Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public void addEpic(EpicTask epicTask) {
        epicTask.setId(nextId++);
        epics.put(epicTask.getId(), epicTask);
    }

    @Override
    public void updateEpic(EpicTask epicTask) {
        final EpicTask oldEpic = epics.get(epicTask.getId());
        oldEpic.setName(epicTask.getName());
        oldEpic.setDescription(epicTask.getDescription());
    }

    @Override
    public List<Task> getListEpic() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void clearEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void removeEpic(Integer id) {
        EpicTask epic = epics.remove(id);
        for (Integer item : epic.getSubTaskIds()) {
            subtasks.remove(item);
        }
    }

    @Override
    public Task getEpic(Integer id) {
        Task epicTask = epics.get(id);
        historyManager.add(epicTask);
        return epicTask;
    }

    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);
        EpicTask epic = epics.get(subtask.getEpicId());
        epic.getSubTaskIds().add(subtask.getId());
        updateStatus(epic.getId());
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateStatus(subtask.getEpicId());
    }

    @Override
    public List<Task> getListSubtask() { return new ArrayList<>(subtasks.values()); }

    @Override
    public void clearSubtasks() {
        subtasks.clear();
        for (EpicTask epicTask : epics.values()) {
            List<Integer> values = epicTask.getSubTaskIds();
            values.clear();
            epicTask.setStatus(StatusTask.NEW);
        }
    }

    @Override
    public void removeSubtask(Integer id) {
       int foundEpicId =  subtasks.remove(id).getEpicId();
       epics.get(foundEpicId).getSubTaskIds().remove(id);
       updateStatus(foundEpicId);
    }

    @Override
    public Task getSubtask(Integer id) {
        Task sabtask = subtasks.get(id);
        historyManager.add(sabtask);
        return sabtask;
    }

    @Override
    public List<Task> getListSubtaskOfEpic(int epicId) {
        List<Task> list = new ArrayList<>();
        for (Integer item : epics.get(epicId).getSubTaskIds())
            list.add(subtasks.get(item));
        return list;
    }

    @Override
    public void updateStatus(Integer foundEpicId) {
        final EpicTask epicTask = epics.get(foundEpicId);
        final List<Integer> subTaskIds = epicTask.getSubTaskIds();

        int countNew = 0;
        int countDone = 0;

        for (Integer taskId : subTaskIds) {
            Subtask task = subtasks.get(taskId);
            if (task.getStatus() == StatusTask.NEW) {
                countNew++;
            } else if (task.getStatus() == StatusTask.DONE) {
                countDone++;
            } else {
                epicTask.setStatus(StatusTask.IN_PROGRESS);
                return;
            }
        }

        if (countNew == subTaskIds.size()) {
            epicTask.setStatus(StatusTask.NEW);
        } else if (countDone == subTaskIds.size()) {
            epicTask.setStatus(StatusTask.DONE);
        } else {
            epicTask.setStatus(StatusTask.IN_PROGRESS);
        }
    }

    @Override
    public List<Task> getListHystory() {
        return historyManager.getHistory();
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        InMemoryTaskManager that = (InMemoryTaskManager) object;
        return nextId == that.nextId && Objects.equals(tasks, that.tasks) && Objects.equals(epics, that.epics) && Objects.equals(subtasks, that.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks, epics, subtasks, nextId);
    }
}
