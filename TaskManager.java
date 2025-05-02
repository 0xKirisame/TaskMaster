package taskMaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class TaskManager {
    private LinkedList<Task> tasks = new LinkedList<Task>();
    private final XOR encrypt = new XOR("");
    private final String FILE_PATH = "Tasks.txt";
    
    public void addTask(Task t) {
        tasks.add(t);
    }
    
    public void removeTask(Task t) {
        tasks.remove(t);
    }
    
    public LinkedList<Task> getAllTasks() {
        return tasks;
    }
    
    public LinkedList<Task> getPendingTasks() {
        LinkedList<Task> pendingTasks = new LinkedList<Task>();
        for (Task task : tasks) {
            if (!task.getCompleted()) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }
    
    public LinkedList<Task> getCompletedTasks() {
        LinkedList<Task> completedTasks = new LinkedList<Task>();
        for (Task task : tasks) {
            if (task.getCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }
    
    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(encrypt.encrypt(task.toString()));
                writer.newLine();
            }
            System.out.println("Tasks saved successfully.");
        } 
        catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    public void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        tasks.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            try {
				while ((line = reader.readLine()) != null) {
       
				String decrypted = encrypt.encrypt(line.trim());
				Task task = parseTask(decrypted);
				
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

    private Task parseTask(String taskStr) {        
        try {
            taskStr = taskStr.substring(6, taskStr.length() - 1); // Remove "Task [" and "]"
            String[] parts = taskStr.split("\\|");
            
            if (parts.length < 7) {
                throw new IllegalArgumentException("Invalid task format");
            }

            int id = Integer.parseInt(parts[0].split("=")[1].trim());
            String title = parts[1].split("=")[1].trim();
            String description = parts[2].split("=")[1].trim();
            
            String[] dateParts = parts[3].split("=")[1].trim().split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            
            String priority = parts[4].split("=")[1].trim();
            String category = parts[5].split("=")[1].trim();
            boolean completed = Boolean.parseBoolean(parts[6].split("=")[1].trim());

            Task task = new Task(id, title, description, day, month, year, priority, category);
            if (completed) task.markCompleted();
            return task;
        } catch (Exception e) {
            System.out.println("Failed to parse task: " + e.getMessage());
            return null;
        }
    }

    public XOR getEncrypt() {
        return encrypt;
    }
}
