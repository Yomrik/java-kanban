import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    public HashMap<Integer, Task> tasks = new HashMap<>();
    public HashMap<Integer, EpicTask> epics = new HashMap<>();
    public HashMap<Integer, Subtask> subtasks = new HashMap<>();
    public int nextId = 1;

    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public ArrayList<Task> getListTask() {
        ArrayList<Task> list = new ArrayList<>();
        subtasks.forEach((id, task) -> list.add(task));
        return list;
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
        epics.put(epicTask.getId(), epicTask);
    }

    public ArrayList<Task> getListEpic() {
        ArrayList<Task> list = new ArrayList<>();
        epics.forEach((id, epicTask) -> list.add(epicTask));
        return list;
    }

    public void clearEpics() {
        epics.clear();
    }

    public void removeEpics(Integer id) {
        epics.remove(id);
    }

    public Task getEpic(Integer id) {
        return epics.get(id);
    }

    public void addSubtask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);

        EpicTask epic = epics.get(subtask.epicId);
        epic.subTaskIds.add(subtask.getId());

        int countNew = 0;
        int countDone = 0;

        for (Integer taskId : epic.subTaskIds) {
            Subtask task = subtasks.get(taskId);
            if (task.status == StatusTask.NEW) {
                countNew = countNew + 1;
            } else if (task.status == StatusTask.DONE) {
                countDone = countDone + 1;
            }
        }

        if (countNew == epic.subTaskIds.size()) {
            epic.status = StatusTask.NEW;
        } else if (countDone == epic.subTaskIds.size()) {
            epic.status = StatusTask.DONE;
        } else {
            epic.status = StatusTask.IN_PROGRESS;
        }
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        EpicTask epic = epics.get(subtask.epicId);

        int countNew = 0;
        int countDone = 0;

        for (Integer taskId : epic.subTaskIds) {
            Subtask task = subtasks.get(taskId);
            if (task.status == StatusTask.NEW) {
                countNew = countNew + 1;
            } else if (task.status == StatusTask.DONE) {
                countDone = countDone + 1;
            }
        }

        if (countNew == epic.subTaskIds.size()) {
            epic.status = StatusTask.NEW;
        } else if (countDone == epic.subTaskIds.size()) {
            epic.status = StatusTask.DONE;
        } else {
            epic.status = StatusTask.IN_PROGRESS;
        }
    }

    public ArrayList<Task> getListSubtask() {
        ArrayList<Task> list = new ArrayList<>();
        subtasks.forEach((id, subtask) -> list.add(subtask));
        return list;
    }

    public void clearSubtasks() {
        subtasks.clear();
    }

    public void removeSubtasks(Integer id) {
        subtasks.remove(id);
    }

    public Task getSubtask(Integer id) {
        return subtasks.get(id);
    }

    public ArrayList<Task> getListSubtaskOfEpic(EpicTask epicTask) {
        ArrayList<Task> list = new ArrayList<>();
        for (Integer item : epicTask.subTaskIds)
            list.add(subtasks.get(item));
        return list;
    }


    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks=" + tasks +
                ", epics=" + epics +
                ", subtasks=" + subtasks +
                ", nextId=" + nextId +
                '}';
    }
}
