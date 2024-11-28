package com.yandex.kanban.service;

import com.yandex.kanban.model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node> listHystoryById = new HashMap<>();
    private Node<Task> first;
    private Node<Task> last;

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
            removeNode(listHystoryById.remove(id));
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void clearHystory(Collection<Integer> listId) {
        for (Object id : listId) {
            removeNode(listHystoryById.get(id));
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
        final Node<Task> newNode = new Node<>(last, task, null);
        listHystoryById.put(task.getId(), newNode);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
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

    private void removeNode(Node node) {
        if (node != null) {
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
}
