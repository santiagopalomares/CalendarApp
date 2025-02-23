public class CalendarApp {

    public static void addCalendar(Calendar calendar) {
        System.out.println("addCalendar() called with Calendar: " + calendar);
    }

    public static void removeCalendar(String calendarName) {
        System.out.println("removeCalendar() called with Calendar Name: " + calendarName);
    }

    public static void updateCalendar(Calendar calendar) {
        System.out.println("updateCalendar() called with Calendar: " + calendar);
    }

    public static void listCalendars(String viewType) {
        System.out.println("listCalendars() called with ViewType: " + viewType);
    }

    public static void main(String[] args) {
        Calendar myCalendar = new Calendar();
        
        addCalendar(myCalendar);
        removeCalendar("Work Calendar");
        updateCalendar(myCalendar);
        listCalendars(ViewType.DAY);
    }
}
