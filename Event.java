import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Event {
   private String name;
   private LocalDateTime startTime;
   private LocalDateTime endTime;
   private List<User> sharedWith;

   public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
      this.name = name;
      this.startTime = startTime;
      this.endTime = endTime;
      this.sharedWith = new ArrayList();
   }

   public String getName() {
      return this.name;
   }

   public void setStartTime(LocalDateTime startTime) {
      this.startTime = startTime;
   }

   public void setEndTime(LocalDateTime endTime) {
      this.endTime = endTime;
   }

   public void eventNavigation(Scanner scanner) {
      while(true) {
         System.out.println("\nNavigation:\n-UpdateStartTime\n-UpdateEndTime\n-Back\n->");
         switch (scanner.nextLine().trim().toLowerCase()) {
            case "updatestarttime":
               this.updateStartTime(scanner);
               break;
            case "updateendtime":
               this.updateEndTime(scanner);
               break;
            case "back":
               return;
            default:
               System.out.println("\nInvalid input");
         }
      }
   }

   public void updateStartTime(Scanner scanner) {
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      while (true) {
         try {
            System.out.println("\nEnter a start date and time in this format: yyyy/MM/dd HH:mm:ss");
            String userStartTime = scanner.nextLine();
            LocalDateTime newStartTime = LocalDateTime.parse(userStartTime, dateFormat);
            this.startTime = newStartTime;
            return;
         } catch (DateTimeParseException e) {
            System.out.println("\nInvalid format.");
            continue;
         }
      }
   }

   public void updateEndTime(Scanner scanner) {
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      while (true) {
         try {
            System.out.println("\nEnter a end date and time in this format: yyyy/MM/dd HH:mm:ss");
            String userEndTime = scanner.nextLine();
            LocalDateTime newEndTime = LocalDateTime.parse(userEndTime, dateFormat);

            if (!newEndTime.isBefore(this.startTime)) {
               this.endTime = newEndTime;
               return;
            } else {
               System.out.println("\nPlease enter a end date and time after the start date and time.");
               continue;
            }
         } catch (DateTimeParseException e) {

         }
      }
   }

   public LocalDateTime getStartTime() {
      return this.startTime;
   }

   public LocalDateTime getEndTime() {
      return this.endTime;
   }
}
