package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node> listHystoryById = new HashMap<>();
    public Node<Task> first;
    public Node<Task> last;

    private static class Node<Task> {
        Task task;
        Node<Task> next;
        Node<Task> prev;

        public Node(Node<Task> prev, Task task, Node<Task> next) {
            this.prev = prev;
            this.task = task;
            this.next = next;
        }
    }

    public void linkLast(Task task) {
        final Node<Task> oldLast = last;
        final Node<Task> newNode = new Node<>(oldLast, task, null);
        listHystoryById.put(task.getId(), newNode);
        last = newNode;
        if (oldLast == null) {
            first = newNode;
        } else {
            oldLast.next = newNode;
        }
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            removeNode(task.getId());
            linkLast(task);
        } else {
            System.out.println("Задача отсутствует");
        }
    }

    public List<Task> getTasks() {
        ArrayList<Task> listHistory = new ArrayList<>();
        Node current = first;
        while (current != null) {
            listHistory.add((Task) current.task);
            current = current.next;
        }
        return listHistory;
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void removeNode(int id) {
        if (listHystoryById.containsKey(id)) {
            nodeRemove(listHystoryById.get(id));
            listHystoryById.remove(id);
        }
    }

    private void nodeRemove(Node node) {
        if (node == first && node == last) {
            first = null;
            last = null;
        } else if (node == first) {
            first = node.next;
            if (first != null) {
                first.prev = null;
            }
        } else if (node == last) {
            last = node.prev;
            if (last != null) {
                last.next = null;
            }
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}
