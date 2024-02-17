package timl.task;

import timl.task.Task;

public class Todo extends Task {
    protected static final String ASSIGNED_SYMBOL = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatus(){
        return ASSIGNED_SYMBOL + super.getStatus();
    }
}

