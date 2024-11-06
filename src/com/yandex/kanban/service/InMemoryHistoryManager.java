package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node> listHystoryById = new HashMap<>();
    public Node<Task> first;
    public Node<Task> last;

    @Override
    public void add(Task task) {
        if (task != null) {
            remove(task.getId());
            linkLast(task);
        } else {
            System.out.println("Задача отсутствует");
        }
    }

    @Override
    public void remove(int id) {
        if (listHystoryById.containsKey(id)) {
            nodeRemove(listHystoryById.get(id));
            listHystoryById.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void clearHystory(List listId) {
        for (Object id : listId) {
            nodeRemove(listHystoryById.get(id));
            listHystoryById.remove(id);
        }
    }

    private static class Node<T> {
        Task task;
        Node<Task> next;
        Node<Task> prev;

        public Node(Node<Task> prev, Task task, Node<Task> next) {
            this.prev = prev;
            this.task = task;
            this.next = next;
        }
    }

    private void linkLast(Task task) {
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

    private List<Task> getTasks() {
        List<Task> listHistory = new LinkedList<>();
        Node current = first;
        while (current != null) {
            listHistory.add(current.task);
            current = current.next;
        }
        return listHistory;
    }

    private void nodeRemove(Node node) {
        if (node == first && node == last) {
            first = null;
            last = null;
        } else if (node == first) {
            first = node.next;
        } else if (node == last) {
            last = node.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}
