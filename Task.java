package taskMaster;

import java.time.LocalDate;
import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String category;
    private boolean completed = false;
    
    public Task(int id, String title, String description, int day, int month, int year, String priority, String category) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = LocalDate.of(year, month, day); // Fixed - using LocalDate.of correctly
        this.priority = priority;
        this.category = category;
    }
    
    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return String.format(
            "Task [id=%d|title=%s|description=%s|date=%04d-%02d-%02d|priority=%s|category=%s|completed=%b]",
            id, title, description, dueDate.getYear(), dueDate.getMonthValue(), dueDate.getDayOfMonth(),
            priority, category, completed
        );
    }

    public int getId() {
        return id;
    }
    
    public boolean getCompleted() {
        return completed;
    }
}
