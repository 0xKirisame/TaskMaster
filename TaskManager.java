package taskMaster;

import java.io.*;
import java.util.LinkedList;

public class TaskManager {
	
    private static LinkedList<Task> tasks = new LinkedList<Task>();
    private final static AES encrypt = new AES("");
    private static final String fileName = "session.ser";
    private static File file = new File(fileName);
    
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
    
    public void saveTasks(LinkedList<Task> tasks) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Task task : tasks) {
                writer.writeObject(task);
            }
            System.out.println("Tasks saved successfully.");
            AES.encrypt(fileName, encrypt.getKey());
        } 
        catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error encrypting tasks: " + e.getMessage());
        }
    }
    
    public static LinkedList<Task> loadTasks() {
        
        if (!file.exists()) {
        	
        	try {
        		System.out.println("No saved tasks found.");
                throw new FileNotFoundException();
        	}
        	catch (FileNotFoundException e){
        		System.out.println("theres no saved session");
        	}
        }

        tasks.clear();
        
        try {
            AES.encrypt(fileName, encrypt.getKey());
        } catch (Exception e) {
            System.out.println("Error decrypting tasks: " + e.getMessage());
            return tasks;
        }
        
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))) {
            
        		while(true) {
        		try {
        		tasks.add((Task) reader.readObject());
        		}
        		catch (EOFException e) {
        			break;
        		}
        	}
        		
            } catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return tasks;
          }        

    public AES getEncrypt() {
        return encrypt;
    }
}
