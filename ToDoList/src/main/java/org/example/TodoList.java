package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private final String url;
    private final String user;
    private final String password;

    public TodoList(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            createTasksTable(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTasksTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                    "id SERIAL PRIMARY KEY," +
                    "description VARCHAR(255) NOT NULL," +
                    "completed BOOLEAN DEFAULT FALSE" +
                    ")";
            statement.executeUpdate(sql);
        }
    }

    public void addTask(String description) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (description) VALUES (?)")) {
            statement.setString(1, description);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTask(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void markTaskCompleted(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET completed = TRUE WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                boolean completed = resultSet.getBoolean("completed");
                tasks.add(new Task(id, description, completed));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }


    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/todolist";
        String user = "postgres";
        String password = "password";

        TodoList todoList = new TodoList(url, user, password);

        todoList.addTask("Купить молоко");
        todoList.addTask("Сделать домашнее задание");
        todoList.markTaskCompleted(1);


        for (Task task : todoList.getTasks()) {
            System.out.println(task);
        }
        List<Task> tasks = todoList.getTasks();
        for(Task task : tasks){
            System.out.println(task.toString());
        }
    }
}
