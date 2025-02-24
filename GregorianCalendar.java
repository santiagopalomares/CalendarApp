import java.util.Date;

public class GregorianCalendar {
   public Date convertDateToGregorian(Date date) {
      if (date == null) {
          throw new IllegalArgumentException("Invalid Date");
      }
      java.util.Calendar calendar = new java.util.GregorianCalendar();
      calendar.setTime(date);

      return calendar.getTime();
  }
 }