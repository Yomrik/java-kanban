package com.yandex.kanban.service;

import com.yandex.kanban.model.*;

import java.io.*;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {
    String fileName;

    public FileBackedTaskManager(String fileName) {
        this.fileName = fileName;
    }

    private void save() {
        File file = new File(fileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fileWriter);

            for (Task task : tasks.values()) {
                writer.println(task.toString());
            }
            for (Task task : epics.values()) {
                writer.println(task.toString());
            }
            for (Task task : subtasks.values()) {
                writer.println(task.toString());
            }

            writer.close();

        } catch (FileNotFoundException e) {
            System.out.print("Файл отсутствует");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Task fromString(String value) {
        try {
            String[] taskLine = value.split(",");
            switch (TypeTask.valueOf(taskLine[1])) {
                case TASK:
                    Task task = new Task(StatusTask.valueOf(taskLine[4]), taskLine[3], taskLine[2], TypeTask.TASK);
                    task.setType(TypeTask.TASK);
                    task.setId(Integer.parseInt(taskLine[0]));
                    return task;
                case EPICTASK:
                    EpicTask epicTask = new EpicTask(taskLine[3], taskLine[2], TypeTask.EPICTASK);
                    epicTask.setStatus(StatusTask.valueOf(taskLine[4]));
                    epicTask.setType(TypeTask.EPICTASK);
                    epicTask.setId(Integer.parseInt(taskLine[0]));
                    return epicTask;
                case SUBTASK:
                    Subtask subtask = new Subtask(StatusTask.valueOf(taskLine[4]), taskLine[3], taskLine[2],
                            Integer.parseInt(taskLine[0]), TypeTask.SUBTASK);
                    subtask.setType(TypeTask.SUBTASK);
                    subtask.setId(Integer.parseInt(taskLine[0]));
                    return subtask;
            }

        } catch (NullPointerException e) {
            System.out.println("Строка отсутствует");
        }
        return null;
    }

    public static void loadFromFile(File file) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        int countId = 1;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            while (br.ready()) {
                String line = br.readLine();
                if (line.isEmpty()) {
                    return;
                }
                System.out.println(line);
                Task task = fromString(line);
                assert task != null;
                if (task.getType().toString().equals("TASK")) {
                    inMemoryTaskManager.addTask(task);
                } else if (task.getType().toString().equals("EPICTASK")) {
                    inMemoryTaskManager.addEpic((EpicTask) task);
                } else if (task.getType().toString().equals("SUBTASK")) {
                    inMemoryTaskManager.addSubtask((Subtask) task);
                }
                countId++;
            }
            inMemoryTaskManager.nextId = countId;

            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
    }

    @Override
    public List<Task> getListTask() {
        return super.getListTask();
    }

    @Override
    public void clearTasks() {
        super.clearTasks();
    }

    @Override
    public void removeTask(Integer id) {
        super.removeTask(id);
    }

    @Override
    public Task getTask(Integer id) {
        return super.getTask(id);
    }

    @Override
    public void addEpic(EpicTask epicTask) {
        super.addEpic(epicTask);
        save();
    }

    @Override
    public void updateEpic(EpicTask epicTask) {
        super.updateEpic(epicTask);
    }

    @Override
    public List<Task> getListEpic() {
        return super.getListEpic();
    }

    @Override
    public void clearEpics() {
        super.clearEpics();
    }

    @Override
    public void removeEpic(Integer id) {
        super.removeEpic(id);
    }

    @Override
    public Task getEpic(Integer id) {
        return super.getEpic(id);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
    }

    @Override
    public List<Task> getListSubtask() {
        return super.getListSubtask();
    }

    @Override
    public void clearSubtasks() {
        super.clearSubtasks();
    }

    @Override
    public void removeSubtask(Integer id) {
        super.removeSubtask(id);
    }

    @Override
    public Task getSubtask(Integer id) {
        return super.getSubtask(id);
    }

    @Override
    public List<Task> getListSubtaskOfEpic(int epicId) {
        return super.getListSubtaskOfEpic(epicId);
    }

    @Override
    public void updateStatus(Integer foundEpicId) {
        super.updateStatus(foundEpicId);
    }

    @Override
    public List<Task> getListHystory() {
        return super.getListHystory();
    }
}

