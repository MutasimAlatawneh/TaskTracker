package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        List<String> tasks = new ArrayList<>();
        System.out.println("Welcome to TaskTracker CLI!");

        while (isRunning) {
            System.out.print("task-cli> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            String[] commandArgs = input.split(" ");
            String command = commandArgs[0];

            switch (command) {
                case "add":
                    if (input.length() > 4) {
                        String description = input.substring(4).trim();
                        tasks.add(description);
                        System.out.println("Task added successfully: '" + description + "'");
                    } else {
                        System.out.println("Error: Please provide a task description. Example: add Study Java");
                    }
                    break;

                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("Your task list is empty.");
                    } else {
                        System.out.println("--- Your Tasks ---");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("------------------");
                    }
                    break;

                case "update":
                    if (commandArgs.length >= 3) {
                        try {
                            int taskId = Integer.parseInt(commandArgs[1]);
                            int listIndex = taskId - 1;
                            if (listIndex >= 0 && listIndex < tasks.size()) {
                                StringBuilder newDescription = new StringBuilder();
                                for (int i = 2; i < commandArgs.length; i++) {
                                    newDescription.append(commandArgs[i]).append(" ");
                                }
                                tasks.set(listIndex, newDescription.toString().trim());
                                System.out.println("Task " + taskId + " updated successfully.");

                            } else {
                                System.out.println("Error: Task ID " + taskId + " does not exist.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Task ID must be a number. Example: update 1 Buy groceries");
                        }
                    } else {
                        System.out.println("Error: Invalid format. Example: update 1 Buy groceries");
                    }
                    break;

                case "exit":
                    isRunning = false;
                    System.out.println("Exiting TaskTracker. Goodbye!");
                    break;

                default:
                    System.out.println("Unknown command. Available commands: add, list, update, exit.");
            }
        }
        scanner.close();
    }
}