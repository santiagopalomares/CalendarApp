public class GregorianCalendar {

    // Method to check if a given year is a leap year
    public boolean isLeapYear(int year) {
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println("isLeapYear() called with year: " + year + " -> " + isLeap);
        return isLeap;
    }

    // Method to calculate and return the next leap year after a given year
    public int calculateLeapYear(int year) {
        int nextLeapYear = year + 1;
        while (!isLeapYear(nextLeapYear)) {
            nextLeapYear++;
        }
        System.out.println("calculateLeapYear() called with year: " + year + " -> Next Leap Year: " + nextLeapYear);
        return nextLeapYear;
    }
}
