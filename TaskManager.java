package taskMaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class TaskManager {
	
	private LinkedList<Task> tasks = new LinkedList<Task>();
	
	public void addTask(Task t) {
		
		tasks.add(t);
		
	}
	
	public void removeTask(Task t) {
		
		for (Task task : tasks) {
			if (task.equals(t)) {
				tasks.remove(task);
				break;
			}
			
		}
	}
	
	public LinkedList<Task> getAllTasks(){
		
		return tasks;
		
	}
	
	public LinkedList<Task> getPendingTasks(){
		
		LinkedList<Task> pendingTasks = new LinkedList<Task>();
		for (Task task : tasks) {
			if (!task.getCompleted()) {
				pendingTasks.add(task);
			}
		}
		
		return pendingTasks;
		
	}
	
	public LinkedList<Task> getCompletedTasks(){
		
		LinkedList<Task> completedTasks = new LinkedList<Task>();
		for (Task task : tasks) {
			if (task.getCompleted()) {
				completedTasks.add(task);
			}
		}
		
		return completedTasks;
		
	}
	
	public void saveTasks() {
		
		try {
			String path = System.getProperty("user.home") + "/Tasks.txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (Task task : tasks) {
				writer.write(task.toString());
			}
			
			writer.close();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTasks() {
		
		try {
			try (BufferedReader reader = new BufferedReader(new FileReader("Tasks.txt"))) {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            // Remove the prefix and suffix
		            line = line.replace("Task [", "").replace("]", "");

		            // Split by the "|"
		            String[] parts = line.split("\\|");

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

		            tasks.add(task);
		        }
		}} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


}
