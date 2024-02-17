package timl.task;

public class Deadline extends Task {
    protected static final String ASSIGNED_SYMBOL = "[D]";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getStatus(){
        return ASSIGNED_SYMBOL + super.getStatus() + " (by: " + by + ")";
    }
}
