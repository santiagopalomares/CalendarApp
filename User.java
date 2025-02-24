import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.ZonedDateTime;

public class User {
   private String username;
   private List<Calendar> calendars;
   private ZoneId timeZone;

   public User(String username, ZoneId timeZone) {
      this.username = username;
      this.calendars = new ArrayList();
      this.timeZone = timeZone;
   }

   public void userNavigation(Scanner scanner) {
      while(true) {
         System.out.println("\nNavigation:\n-CreateCalendar\n-EditCalendar\n-RemoveCalendar\n-ChangeTimeZone\n-Logout\n--------");
         switch (scanner.nextLine().trim().toLowerCase()) {
            case "createcalendar":
               this.addCalendar(scanner);
               break;
            case "viewcalendar":
               this.viewCalendar(scanner);
               break;
            case "editcalendar":
               this.updateCalendar(scanner);
               break;
            case "removecalendar":
               this.removeCalendar(scanner);
               break;
            case "changetimezone":
               this.changeTimeZone(scanner);
               break;
            case "logout":
               return;
            default:
               System.out.println("\nInvalid input");
         }
      }
   }

   public String getUsername() {
      return this.username;
   }

   public List<Calendar> getCalendars() {
      return this.calendars;
   }

   public void addCalendar(Scanner scanner) {
      boolean isPublic = false;
      while(true) {
         System.out.println("\nEnter a unique name for your calendar: ");
         String name = scanner.nextLine().toLowerCase();

         if (this.findCalendar(name) == null) {
            System.out.println("Would you want your calendar to be public? Enter yes or no.");
            switch (scanner.nextLine().toLowerCase()) {
               case "yes":
                  isPublic = true;
                  break;
               case "no":
                  isPublic = false;
                  break;
               default:
                  System.out.println("\nInvalid input");
            }

            Calendar calendar = new Calendar(name, this, isPublic);
            this.calendars.add(calendar);
            System.out.println("\nCalendar added: " + name);
            return;

         } else {
            System.out.println("\n" + name + " has been taken.");
         }
      }
   }

   public void viewCalendar(Scanner scanner) {
      if (calendars.isEmpty()) {
          System.out.println("\nNo calendars available.");
          return;
      }

      System.out.println("\nSelect a calendar to view:");
      printCalendars();
      String calendarName = scanner.nextLine().trim().toLowerCase();
      Calendar selectedCalendar = findCalendar(calendarName);

      if (selectedCalendar == null) {
          System.out.println("\nCalendar not found.");
          return;
      }

      View calendarView = new View("default", selectedCalendar);
      while (true) {
          System.out.println("\nNavigation:\n-DailyEvents\n-WeeklyEvents\n-MonthlyEvents\n-YearlyEvents\n-Exit\n--------");
          switch (scanner.nextLine().trim().toLowerCase()) {
              case "dailyevents":
                  calendarView.visualizeDailyEvents();
                  break;
              case "weeklyevents":
                  calendarView.visualizeWeeklyEvents();
                  break;
              case "monthlyevents":
                  calendarView.visualizeMonthlyEvents();
                  break;
              case "yearlyevents":
                  calendarView.visualizeYearlyEvents();
                  break;
              case "exit":
                  return;
              default:
                  System.out.println("\nInvalid input");
          }
      }
   }

   public Calendar findCalendar(String name) {
      for (Calendar calendar : calendars) {
         if (name.equals(calendar.getName())) {
            return calendar;
         }
      }
      return null;
   }

   public void printCalendars() {
      for (Calendar calendar : calendars) {
         System.out.println(calendar.getName());
      }
   }

   public void removeCalendar(Scanner scanner) {
      if (this.calendars.isEmpty()) {
         System.out.println("\nThere are no calendars.");
      } else {
         while(true) {
            System.out.println("\nEnter a the calendar you wish to remove: ");
            this.printCalendars();
            String name = scanner.nextLine().toLowerCase();
            if (name.isEmpty()) {
               System.out.println("\nEnter at least 1 character.");
               continue;
            } else {
               Calendar calendar = this.findCalendar(name);
               if (calendar != null) {
                  this.calendars.remove(this.calendars.indexOf(calendar));
                  System.out.println("Calendar removed: " + name);
                  return;
               }

               System.out.println("\n" + name + " cannot be found.");
            }
         }
      }
   }

   public void updateCalendar(Scanner scanner) {
      if (this.calendars.isEmpty()) {
         System.out.println("\nThere are no calendars.");
         return;
      }
      while(true) {
         System.out.println("\nWhich calendar do you wish to edit?: ");
         this.printCalendars();
         String name = scanner.nextLine().toLowerCase();
         if (name.isEmpty()) {
            System.out.println("\nEnter at least 1 character.");
         } else {
            Calendar calendar = this.findCalendar(name);
            if (calendar == null) {
               System.out.println("\n" + name + " cannot be found.");
            } else {
               System.out.println("Would you want your calendar to be public? Enter yes or no.");
               switch (scanner.nextLine().toLowerCase()) {
                  case "yes":
                     this.setCalendarPublic(calendar);
                     break;
                  case "no":
                     this.setCalendarPrivate(calendar);
                     break;
                  default:
                     System.out.println("\nInvalid input");
                     continue;
               }

               calendar.calendarNavigation(scanner);
               return;
            }
         }
      }
   }

   public void setCalendarPublic(Calendar calendar) {
      calendar.setPublic(true);
      System.out.println(calendar.getName() + " is now public");
   }

   public void setCalendarPrivate(Calendar calendar) {
      calendar.setPublic(false);
      System.out.println(calendar.getName() + " is now private");
   }

   public void setTimeZone(ZoneId timeZone) {
      this.timeZone = timeZone;
   }

   public ZoneId getTimeZone() {
      return this.timeZone;
   }

   public void changeTimeZone(Scanner scanner) {
      while(true) {
         System.out.println("\nEnter a valid timezone in Continent/City format (e.g. America/Los_Angeles, Europe/Paris, etc)");
         String userTimeZone = scanner.nextLine().trim();

         try {
            ZoneId timeZone = ZoneId.of(userTimeZone);
            this.timeZone = timeZone;
            updateEventTimes();
            break;
         } catch (DateTimeException e) {
            System.out.println("\nInvalid format");
         }
      }
   }

   public void updateEventTimes() {
      for (Calendar calendar : this.calendars) {
         for (Event event : calendar.getEvents()) {
             // Convert start time
             ZonedDateTime oldStart = event.getStartTime().atZone(ZoneId.systemDefault());
             ZonedDateTime newStart = oldStart.withZoneSameInstant(this.timeZone);
             event.setStartTime(newStart.toLocalDateTime());
 
             // Convert end time
             ZonedDateTime oldEnd = event.getEndTime().atZone(ZoneId.systemDefault());
             ZonedDateTime newEnd = oldEnd.withZoneSameInstant(this.timeZone);
             event.setEndTime(newEnd.toLocalDateTime());
         }
     }
      System.out.println("Updated Event Times");
   }
}
