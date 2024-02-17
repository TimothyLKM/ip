package timl.task;

public abstract class Task {
    protected static final String MARKED = "[X]";
    protected static final String UNMARKED = "[ ]";
    protected boolean isMarked;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        String icon = isMarked ? MARKED : UNMARKED;
        return icon + " " + description;
    }
}


