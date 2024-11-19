package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList{

    private List<Task> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        int nextId = tasks.size() + 1;
        Task newTask = new Task(nextId, description, false);
        tasks.add(newTask);
        System.out.println("Задача добавлена.");
    }

    public void removeTask(int id) {
        Task taskToRemove = findTask(id);
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Задача удалена.");
        } else {
            System.out.println("Задача с указанным ID не найдена.");
        }
    }

    public void markTaskCompleted(int id) {
        Task taskToComplete = findTask(id);
        if (taskToComplete != null) {
            taskToComplete.setCompleted(true);
            System.out.println("Задача отмечена как выполненная.");
        } else {
            System.out.println("Задача с указанным ID не найдена.");
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private Task findTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ToDoList todoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Удалить задачу");
            System.out.println("3. Отметить задачу как выполненную");
            System.out.println("4. Показать все задачи");
            System.out.println("5. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите описание задачи: ");
                    String description = scanner.nextLine();
                    todoList.addTask(description);
                    break;
                case 2:
                    System.out.print("Введите ID задачи для удаления: ");
                    int idToRemove = scanner.nextInt();
                    todoList.removeTask(idToRemove);
                    break;
                case 3:
                    System.out.print("Введите ID задачи для выполнения: ");
                    int idToComplete = scanner.nextInt();
                    todoList.markTaskCompleted(idToComplete);
                    break;
                case 4:
                    todoList.listTasks();
                    break;
                case 5:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}

class Task {
    private int id;
    private String description;
    private boolean completed;

    public Task(int id, String description, boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return id + ". " + description + (completed ? " (Выполнено)" : "");
    }
}
