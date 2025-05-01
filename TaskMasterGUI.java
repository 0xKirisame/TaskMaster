package taskMaster;

import java.util.Scanner;

public class TaskMasterGUI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        tm.loadTasks();

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
                        System.out.println("Enter: id, title, description, day, month, year, priority, category");
                        int id = Integer.parseInt(input.nextLine());
                        String title = input.nextLine();
                        String description = input.nextLine();
                        int day = Integer.parseInt(input.nextLine());
                        int month = Integer.parseInt(input.nextLine());
                        int year = Integer.parseInt(input.nextLine());
                        String priority = input.nextLine();
                        String category = input.nextLine();

                        Task task = new Task(id, title, description, day, month, year, priority, category);
                        tm.addTask(task);
                        System.out.println("✅ Task added.");
                    } catch (Exception e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
                case "2" -> tm.getAllTasks().forEach(System.out::println);
                case "3" -> tm.getPendingTasks().forEach(System.out::println);
                case "4" -> tm.getCompletedTasks().forEach(System.out::println);
                case "5" -> {
                    System.out.print("Enter Task ID to mark as completed: ");
                    int id = Integer.parseInt(input.nextLine());
                    for (Task t : tm.getAllTasks()) {
                        if (t.getId() == id) {
                            t.markCompleted();
                            System.out.println("✅ Marked as completed.");
                            break;
                        }
                    }
                }
                case "6" -> {
                    tm.saveTasks();
                    System.out.println("💾 Tasks saved.");
                }
                case "7" -> {
                    tm.loadTasks();
                    System.out.println("📂 Tasks loaded.");
                }
                case "8" -> {
                    System.out.println("👋 Goodbye.");
                    input.close();
                    return;
                }
                default -> System.out.println("❓ Invalid choice.");
            }
        }
    }
}
