package timl.task;

public class Events extends Task {
    protected static final String ASSIGNED_SYMBOL = "[E]";
    protected String startDate;
    protected String endDate;

    public Events(String description, String startDate, String endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String getStatus(){
        return ASSIGNED_SYMBOL + super.getStatus() + " (from: " + startDate + " to " + endDate + ")";
    }
}
