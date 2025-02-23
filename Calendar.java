public class Calendar {

    public void addEvent(Event event) {
        System.out.println("addEvent() called");
    }

    public void removeEvent(String eventID) {
        System.out.println("removeEvent() called");
    }

    public void updateEvent(Event event) {
        System.out.println("updateEvent() called");
    }

    public void listEvents(ViewType viewType) {
        System.out.println("listEvents() called");
    }

    public void shareCalendar(String userID) {
        System.out.println("shareCalendar() called");
    }

    public void setPublic(Boolean isPublic) {
        System.out.println("setPublic() called");
    }
}
