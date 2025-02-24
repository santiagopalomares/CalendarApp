import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calendar {
   private String name;
   private User owner;
   private List<Event> events;
   private boolean isPublic;

   public Calendar(String name, User owner, boolean isPublic) {
      this.name = name;
      this.owner = owner;
      this.isPublic = isPublic;
      this.events = new ArrayList();
   }

   public String getName() {
      return this.name;
   }

   public boolean isPublic() {
      return this.isPublic;
   }

   public void setPublic(boolean isPublic) {
      this.isPublic = isPublic;
   }

   public void calendarNavigation(Scanner scanner) {
      while(true) {
         System.out.println("\nNavigation:\n-CreateEvent\n-EditEvent\n-RemoveEvent\n-Back\n--------");
         switch (scanner.nextLine().trim().toLowerCase()) {
            case "createevent":
               this.addEvent(scanner);
               break;
            case "editevent":
               this.updateEvent(scanner);
               break;
            case "removeevent":
               this.removeEvent(scanner);
               break;
            case "back":
               return;
            default:
               System.out.println("\nInvalid input");
         }
      }
   }

   public void addEvent(Scanner scanner) {
      while(true) {
         System.out.println("\nEnter a unique name: ");
         String name = scanner.nextLine().toLowerCase();
         if (name.isEmpty()) {
            System.out.println("\nEnter at least 1 character.");
         } else {
            if (this.findEvent(name) == null) {
               List<LocalDateTime> times = this.setEventTimes(scanner);
               Event newEvent = new Event(name, times.get(0), times.get(1));
               this.events.add(newEvent);
               System.out.println("Event added: " + name);
               return;
            }
            System.out.println("\n" + name + " has been taken.");
         }
      }
   }

   public Event findEvent(String name) {
      for (Event event : events) {
         if (name.equals(event.getName())) {
            return event;
         }
      }
      return null;
   }

   public void printEvents() {
      for (Event event : events) {
         System.out.println(event.getName());
      }
   }

   public List<LocalDateTime> setEventTimes(Scanner scanner) {
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      while(true) {
         try {
            System.out.println("\nEnter a start date and time in this format: yyyy/MM/dd HH:mm:ss");
            String userStartTime = scanner.nextLine();
            LocalDateTime startTime = LocalDateTime.parse(userStartTime, dateFormat);

            System.out.println("\nEnter a end date and time in this format: yyyy/MM/dd HH:mm:ss");
            String userEndTime = scanner.nextLine();
            LocalDateTime endTime = LocalDateTime.parse(userEndTime, dateFormat);

            if (!endTime.isBefore(startTime)) {
               List<LocalDateTime> times = new ArrayList();
               times.add(startTime);
               times.add(endTime);
               return times;
            } else {
               System.out.println("\nPlease enter a end date and time after the start date and time.");
               continue;
            }
            
         } catch (DateTimeParseException e) {
            System.out.println("\nInvalid format.");
            continue;
         }
      }
   }

   public void removeEvent(Scanner scanner) {
      if (this.events.isEmpty()) {
         System.out.println("\nThere are no events.");
      } else {
         while(true) {
            System.out.println("\nEnter a event you wish to remove: ");
            this.printEvents();
            String name = scanner.nextLine().toLowerCase();
            if (this.name.isEmpty()) {
               System.out.println("\nEnter at least 1 character.");
            } else {
               Event event = this.findEvent(name);
               if (event != null) {
                  this.events.remove(this.events.indexOf(event));
                  System.out.println("\nEvent removed: " + name);
                  return;
               }

               System.out.println("\n" + name + " cannot be found.");
            }
         }
      }
   }

   public void updateEvent(Scanner scanner) {
      while(true) {
         System.out.println("\nWhich event do you wish to edit?: ");
         this.printEvents();
         String name = scanner.nextLine().toLowerCase();

         if (name.isEmpty()) {
            System.out.println("\nERROR: Enter at least 1 character.");
         } else {
            Event event = this.findEvent(name);
            if (event == null) {
               System.out.println("\nERROR: " + name + " cannot be found.");
            } else {
               event.eventNavigation(scanner);
               return;
            }
         }
      }
   }

   public List<Event> getEvents() {
      return this.events;
   }
}
