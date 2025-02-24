import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
   private static List<User> users = new ArrayList();
   private static User currentUser = null;

   public App() {
   }

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      while(true) {
         if (currentUser == null) {
            System.out.println("Navigation:\n-Login\n-Signup\n-Remove\n-Exit\n--------");
         } else {
            System.out.println("Navigation:\n-Logout\n-Remove\n-Exit\n--------");
         }

         String userNavigation = scanner.nextLine().toLowerCase();
         if (currentUser == null) {
            switch (userNavigation) {
               case "login":
                  loginUser(scanner);
                  break;
               case "signup":
                  addUser(scanner);
                  break;
               case "remove":
                  removeUser(scanner);
                  break;
               case "exit":
                  scanner.close();
                  return;
               default:
                  System.out.println("\nInvalid input");
            }
         } else {
            switch (userNavigation) {
               case "logout":
                  logoutUser();
                  break;
               case "remove":
                  removeUser(scanner);
                  break;
               case "exit":
                  scanner.close();
                  return;
               default:
                  System.out.println("\nInvalid input");
            }
         }
      }
   }

   public static void addUser(Scanner scanner) {
      while(true) {
         System.out.println("\nEnter a unique username: ");
         String username = scanner.nextLine().toLowerCase();
         if (username.isEmpty()) {
            System.out.println("\nEnter at least 1 character.");
         } else {
            if (findUser(username) == null) {
               ZoneId timeZone = setTimeZone(scanner);
               User user = new User(username, timeZone);
               users.add(user);
               System.out.println("\n" + username + " created\n");
               return;
            }

            System.out.println("\n" + username + " is already taken.");
         }
      }
   }

   public static void removeUser(Scanner scanner) {
      if (users.isEmpty()) {
         System.out.println("\nThere are no users.");
      } else {
         while(true) {
            while(true) {
               System.out.println("\nEnter the username you wish to remove: ");
               printUsers();
               String username = scanner.nextLine().toLowerCase();
               if (username.isEmpty()) {
                  System.out.println("\nEnter at least 1 character.");
               } else {
                  User user = findUser(username);
                  if (user != null) {
                     users.remove(user);
                     System.out.println("\n" + username + " removed");
                     if (currentUser != null && currentUser.equals(user)) {
                        currentUser = null;
                        System.out.println("\nYou were logged out because your account was removed.");
                     }

                     return;
                  }
                  System.out.println("\n" + username + " cannot be found.");
               }
            }
         }
      }
   }

   public static void loginUser(Scanner scanner) {
      if (users.isEmpty()) {
         System.out.println("\nThere are no users.");
         return;
      }

      while(true) {
         System.out.println("\nEnter your username: ");
         String username = scanner.nextLine().toLowerCase();

         if (username.isEmpty()) {
            System.out.println("\nEnter at least 1 character.");
            continue;
         } else {
            User user = findUser(username);
            if (user != null) {
               currentUser = user;
               System.out.println("\nLogged in as " + username);
               user.userNavigation(scanner);
               return;
            } else {
               System.out.println("\n" + username + " cannot be found.");
            }
         }
      }
      
   }

   public static void logoutUser() {
      if (currentUser != null) {
         System.out.println("logged out.");
         currentUser = null;
      }

   }

   public static void printUsers() {
      for (User user : users) {
         System.out.println(user.getUsername());
      }

   }

   public static User findUser(String username) {
      for (User user : users) {
         if (username.equals(user.getUsername())) {
            return user;
         }
      }
      return null;
   }

   public static ZoneId setTimeZone(Scanner scanner) {
      while(true) {
         System.out.println("\nEnter a valid timezone in Continent/City format (e.g. America/Los_Angeles, Europe/Paris, etc)");
         String timeZone = scanner.nextLine().trim();

         try {
            return ZoneId.of(timeZone);
         } catch (DateTimeException var3) {
            System.out.println("\nInvalid format");
         }
      }
   }
}
