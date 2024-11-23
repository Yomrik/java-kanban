package com.yandex.kanban.service;

import com.yandex.kanban.model.*;
import java.io.*;

public class FileBackedTaskManager extends InMemoryTaskManager {

    public void save() {
        File file = new File("data.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fileWriter);

            for (Task task : super.getTasks().values()) {
                writer.println(task.toString());
            }
            for (Task task : super.getEpics().values()) {
                writer.println(task.toString());
            }
            for (Task task : super.getSubtasks().values()) {
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
          if (taskLine[1].equals("TASK")) {
              Task task = new Task(StatusTask.valueOf(taskLine[4]), taskLine[3], taskLine[2]);
              task.setType(TypeTask.TASK);
              task.setId(Integer.parseInt(taskLine[0]));
              return task;
          } else if (taskLine[1].equals("EPICTASK")) {
              EpicTask epicTask = new EpicTask(taskLine[3], taskLine[2]);
              epicTask.setStatus(StatusTask.valueOf(taskLine[4]));
              epicTask.setType(TypeTask.EPICTASK);
              epicTask.setId(Integer.parseInt(taskLine[0]));
              return epicTask;
          } else if (taskLine[1].equals("SUBTASK")) {
              Subtask subtask = new Subtask(StatusTask.valueOf(taskLine[4]), taskLine[3], taskLine[2],
                      Integer.parseInt(taskLine[0]));
              subtask.setType(TypeTask.SUBTASK);
              subtask.setId(Integer.parseInt(taskLine[0]));
              return subtask;
          }
      } catch (NullPointerException e) {
          System.out.println("null");
      }
        return null;
    }

    public static void loadFromFile(File file) {
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
                    tasks.put(task.getId(), task);
                } else if (task.getType().toString().equals("EPICTASK")) {
                    epics.put(task.getId(), (EpicTask) task);
                } else if (task.getType().toString().equals("SUBTASK")) {
                    subtasks.put(task.getId(), (Subtask) task);
                }
                countId++;
            }
            nextId = countId;
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
    public void addEpic(EpicTask epicTask) {
        super.addEpic(epicTask);
        save();
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        save();
    }
}

