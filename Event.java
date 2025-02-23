import java.util.Date;

public class Event {
    private String id;
    private String title;
    private Date startTime;
    private Date endTime;
    private boolean isPrivate;

    public Event(String id, String title, Date startTime, Date endTime, boolean isPrivate) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isPrivate = isPrivate;
    }

    public void shareWithUser(String user) {
        System.out.println("shareWithUser() called with user: " + user);
    }
}
