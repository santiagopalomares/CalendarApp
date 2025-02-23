public class GregorianCalendar {

    public boolean isLeapYear(int year) {
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println("isLeapYear() called with year: " + year + " -> " + isLeap);
        return isLeap;
    }

    public int calculateLeapYear(int year) {
        int nextLeapYear = year + 1;
        while (!isLeapYear(nextLeapYear)) {
            nextLeapYear++;
        }
        System.out.println("calculateLeapYear() called with year: " + year + " -> Next Leap Year: " + nextLeapYear);
        return nextLeapYear;
    }
}
