import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class View {
    private String theme;
    private Calendar calendar;

    public View(String theme, Calendar calendar) {
        this.theme = theme;
        this.calendar = calendar;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void visualizeDailyEvents() {
        System.out.println("Daily Events:");
        LocalDate today = LocalDate.now();
        printFilteredEvents(today, today);
    }

    public void visualizeWeeklyEvents() {
        System.out.println("Weekly Events:");
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.plusDays(7);
        printFilteredEvents(today, endOfWeek);
    }

    public void visualizeMonthlyEvents() {
        System.out.println("Monthly Events:");
        LocalDate today = LocalDate.now();
        calendar.getEvents().stream()
                .filter(event -> isSameMonth(event.getStartTime(), today))
                .forEach(this::printEvent);
    }

    public void visualizeYearlyEvents() {
        System.out.println("Yearly Events:");
        int currentYear = LocalDate.now().getYear();
        calendar.getEvents().stream()
                .filter(event -> event.getStartTime().getYear() == currentYear)
                .forEach(this::printEvent);
    }

    private void printFilteredEvents(LocalDate start, LocalDate end) {
        List<Event> events = calendar.getEvents();
        for (Event event : events) {
            LocalDate eventDate = event.getStartTime().toLocalDate();
            if (!eventDate.isBefore(start) && !eventDate.isAfter(end)) {
                printEvent(event);
            }
        }
    }

    private boolean isSameMonth(LocalDateTime eventTime, LocalDate today) {
        return eventTime.getMonth() == today.getMonth() && eventTime.getYear() == today.getYear();
    }

    private void printEvent(Event event) {
        System.out.println(event.getName() + " at " + event.getStartTime());
    }
}
