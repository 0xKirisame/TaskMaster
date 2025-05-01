package taskMaster;

import java.time.LocalDate;

public class Task {
	
	private int id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private String priority;
	private String category;
	private boolean completed = false;
	
	public Task(int id, String title, String description, int Day, int month, int year, String priority, String category) {
		
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate.of(year, month, Day);
		this.priority = priority;
		this.category = category;
		
	}
	
	public void markCompleted() {
		
		this.completed = true;
		
	}

	@Override
	public String toString() {
		return "Task [id=" + id + "| title=" + title + "| description=" + description + "| dueDate=" + dueDate.toString()
				+ "| priority=" + priority + "| category=" + category + "| completed=" + completed + "]\n";
	}

	public int getId() {
		return id;
	}
	
	public boolean getCompleted() {
		
		return completed;
	}
	

}
