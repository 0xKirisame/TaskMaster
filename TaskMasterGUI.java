package taskMaster;

import java.util.Scanner;

public class TaskMasterGUI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        
        System.out.println("Welcome to TaskMaster!");
        
        while (true) {
            System.out.println("""
                    ===== TaskMaster Menu =====
                    1. Add Task
                    2. View All Tasks
                    3. View Pending Tasks
                    4. View Completed Tasks
                    5. Mark Task as Completed
                    6. Save Tasks
                    7. Load Tasks
                    8. Exit
                    """);

            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1" -> {
                    try {
                        System.out.println("Enter task details:");
                        System.out.print("ID: ");
                        int id = Integer.parseInt(input.nextLine());
                        System.out.print("Title: ");
                        String title = input.nextLine();
                        System.out.print("Description: ");
                        String description = input.nextLine();
                        System.out.print("Day: ");
                        int day = Integer.parseInt(input.nextLine());
                        System.out.print("Month: ");
                        int month = Integer.parseInt(input.nextLine());
                        System.out.print("Year: ");
                        int year = Integer.parseInt(input.nextLine());
                        System.out.print("Priority: ");
                        String priority = input.nextLine();
                        System.out.print("Category: ");
                        String category = input.nextLine();

                        Task task = new Task(id, title, description, day, month, year, priority, category);
                        tm.addTask(task);
                        System.out.println("✅ Task added.");
                    } catch (Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
                case "2" -> {
                    System.out.println("All Tasks:");
                    if (tm.getAllTasks().isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        tm.getAllTasks().forEach(System.out::println);
                    }
                }
                case "3" -> {
                    System.out.println("Pending Tasks:");
                    if (tm.getPendingTasks().isEmpty()) {
                        System.out.println("No pending tasks.");
                    } else {
                        tm.getPendingTasks().forEach(System.out::println);
                    }
                }
                case "4" -> {
                    System.out.println("Completed Tasks:");
                    if (tm.getCompletedTasks().isEmpty()) {
                        System.out.println("No completed tasks.");
                    } else {
                        tm.getCompletedTasks().forEach(System.out::println);
                    }
                }
                case "5" -> {
                    System.out.print("Enter Task ID to mark as completed: ");
                    try {
                        int id = Integer.parseInt(input.nextLine());
                        boolean found = false;
                        for (Task t : tm.getAllTasks()) {
                            if (t.getId() == id) {
                                t.markCompleted();
                                System.out.println("✅ Task marked as completed.");
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("❌ Task not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid ID format.");
                    }
                }
                case "6" -> {
                	if (tm.getEncrypt().getKey().equals("")) {
                		System.out.print("Enter encryption password: ");
                        tm.getEncrypt().setKey(input.nextLine());
                	}
                	tm.saveTasks();
                }
                case "7" -> {
                	if (tm.getEncrypt().getKey().equals("")) {
                        System.out.print("Enter decryption password: ");
                        tm.getEncrypt().setKey(input.nextLine());
                    }
                    tm.loadTasks();
                }
                case "8" -> {
                    System.out.println("👋 Goodbye!");
                    input.close();
                    return;
                }
                default -> System.out.println("❓ Invalid choice. Please try again.");
            }
            
            System.out.println(); // Add a blank line for better readability
        }
    }
}
