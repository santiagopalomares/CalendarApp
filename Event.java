import java.util.Date;

public class Event {
    // Attributes
    private String id;
    private String title;
    private Date startTime;
    private Date endTime;
    private boolean isPrivate;

    // Constructor
    public Event(String id, String title, Date startTime, Date endTime, boolean isPrivate) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isPrivate = isPrivate;
    }

    // Method to share event with a user
    public void shareWithUser(String user) {
        System.out.println("shareWithUser() called with user: " + user);
    }
}
