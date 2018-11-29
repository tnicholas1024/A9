//Tiffany Nicholas -- Lab Assignment 9: Changing Sort Keys.
import javax.swing.*;
public class Foothill
{
    public static void main (String[] args)
    {
        double median;
        String outputStr;
        //array setup
        Student[] arrayZeroStud = new Student[0];

        Student[] arrayOneStud = new Student[1];
        arrayOneStud[0] = new Student("Anthony", "Jacobs", 622);

        Student[] arrayFifteenStud =
                {
                        new Student("Kelsey", "Robbins", 2),
                        new Student("Tiffany", "Lewis", 933),
                        new Student("George", "Barkett", 212),
                        new Student("Jim", "Kosturos", 100),
                        new Student("James", "Nicholas", 721),
                        new Student("Hillary", "Sanders", 480),
                        new Student("Lex", "Sullivan", 634),
                        new Student("Lauren", "Sosa", 128),
                        new Student("Billy", "MacIntosh", 91),
                        new Student("Samantha", "McMullen", 318),
                        new Student("Alice", "Curry", 222),
                        new Student("Stephen", "Thompson", 43),
                        new Student("Doug", "Azure",872),
                        new Student("Holly", "Wallis",652),
                        new Student("Billie", "Holiday",119)
                };
        Student[] arraySixteenStud;
        arraySixteenStud = new Student[16];

        for (int i = 0; i < 15; i++)
        {
            arraySixteenStud[i] = arrayFifteenStud[i];
        }
        arraySixteenStud[15] = new Student("Andrew", "Bogart", 231);

        //initial display
        System.out.println("Array of 15 before sort: ");
       StudentArrayUtilities.toString("Array of 15 before sort", arrayFifteenStud);


        // initial sort display
        StudentArrayUtilities.arraySort(arraySixteenStud);
        System.out.println("Array of 16 after initial sort: ");
        StudentArrayUtilities.toString("Array of 16 after "
                + "initial sort: ", arraySixteenStud);
        
        // firstName sort and display
        System.out.println("Array of 16 after firstName sort: ");
        Student.setSortKey(Student.SORT_BY_FIRST);
        StudentArrayUtilities.arraySort(arraySixteenStud);
        
        StudentArrayUtilities.toString("Array of 16 after "
                + "first name sort: ", arraySixteenStud);
      
        // totalPoints sort and display
        System.out.println("Array of 16 after totalPoints sort: ");
        Student.setSortKey(Student.SORT_BY_POINTS);
        StudentArrayUtilities.arraySort(arraySixteenStud);
        
        StudentArrayUtilities.toString("Array of 16 after "
                + "total points sort: ", arraySixteenStud);
        

        //median destructive
        Student.setSortKey(Student.SORT_BY_FIRST);
        median = StudentArrayUtilities.getMedianDestructive(arraySixteenStud);
        outputStr = "The median of the array of sixteen students is : "
                + median;
        System.out.println(outputStr);

        // output sorkKey to show it is unchanged
        outputStr = "\nThe sort key of the Student class after call to "
                + "getMedianDestructive() is: " + Student.getSortKey();
        System.out.println(outputStr);
        if (Student.getSortKey() == Student.SORT_BY_FIRST)
            System.out.println("(Sort key has been preserved!)");
        else
            System.out.println("(Sort key has not been preserved!!)");

        //median of array of 15
        median = StudentArrayUtilities.getMedianDestructive(arrayFifteenStud);
        outputStr = "\nThe median score of the array of 15 is: " + median;
        System.out.println(outputStr);

        // median of array of 1
        median = StudentArrayUtilities.getMedianDestructive(arrayOneStud);
        outputStr = "\nThe median score of the array of 1 is: " + median;
        System.out.println(outputStr);

        // median of array of 0
        median = StudentArrayUtilities.getMedianDestructive(arrayZeroStud);
        outputStr = "\nThe median score of the array of 0 is: " + median;
        System.out.println(outputStr);
    }

}

class Student
{
    private static int sortKey;
    private String lastName;
    private String firstName;
    private int totalPoints;

    public static final String DEFAULT_NAME = "zz-error";
    public static final int DEFAULT_POINTS = 0;
    public static final int MAX_POINTS = 1000;
    public static final int SORT_BY_FIRST = 88;
    public static final int SORT_BY_LAST = 98;
    public static final int SORT_BY_POINTS = 108;

    // parameter taking constructor
    public Student( String last, String first, int points)
    {
        if ( !setLastName(last) )
            lastName = DEFAULT_NAME;
        if ( !setFirstName(first) )
            firstName = DEFAULT_NAME;
        if ( !setPoints(points) )
            totalPoints = DEFAULT_POINTS;
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public int getTotalPoints() { return totalPoints; }
    public static int getSortKey(){return sortKey;} //MODIFICATION

    //MODIFICATION
    public static boolean setSortKey(int key){
        if(key >= DEFAULT_POINTS && key <= MAX_POINTS) {
            sortKey = key;
            return true;
        }
        else {
            return false;
        }

    }

    public boolean setLastName(String last)
    {
        if ( !validString(last) )
            return false;
        lastName = last;
        return true;
    }

    public boolean setFirstName(String first)
    {
        if ( !validString(first) )
            return false;
        firstName = first;
        return true;
    }

    public boolean setPoints(int pts)
    {
        if ( !validPoints(pts) )
            return false;
        totalPoints = pts;
        return true;
    }

    public static int compareTwoStudents( Student firstStud, Student secondStud )
    {
        int result = 0;
        //this switch statement tests several different possibilities like first, last, and points.
        switch (Student.getSortKey()){
            case SORT_BY_FIRST:
                result = firstStud.getFirstName().compareToIgnoreCase(secondStud.getFirstName());
                break;
            case SORT_BY_LAST:
                result = firstStud.getLastName().compareToIgnoreCase(secondStud.getLastName());
                break;
            case SORT_BY_POINTS:
                result = firstStud.getTotalPoints() - secondStud.getTotalPoints();
                break;
        }
        //if none of the cases criteria are met then just return result as 0.
        return result;
    }


    public String toString()
    {
        String resultString;

        resultString = " "+ lastName
                + ", " + firstName
                + " points: " + totalPoints
                + "\n";
        return resultString;
    }

    private static boolean validString( String testStr )
    {
        if (
                testStr != null && testStr.length() > 0
                        && Character.isLetter(testStr.charAt(0))
        )
            return true;
        return false;
    }

    private static boolean validPoints( int testPoints )
    {
        if (testPoints >= 0 && testPoints <= MAX_POINTS)
            return true;
        return false;
    }
}


// **the StudentArrayUtilities Class**
class StudentArrayUtilities
{

    public static void toString(String title, Student[] data)
    {
        String output = "";

        // build the output string from the individual Students:
        for (int k = 0; k < data.length; k++){
            output += " " + data[k].toString();
        }
        System.out.println(output);

    }

    // returns true if a modification was made to the array
    private static boolean floatLargestToTop(Student[] data, int top)
    {
        boolean changed = false;
        Student temp;

        // compare with client call to see where the loop stops
        for (int k = 0; k < top; k++)
            if ( Student.compareTwoStudents(data[k], data[k + 1]) > 0 )
            {
                temp = data[k];
                data[k] = data[k + 1];
                data[k + 1] = temp;
                changed = true;
            }
        return changed;
    }

    // public callable arraySort() - assumes Student class has a compareTo()
    public static void arraySort(Student[] array)
    {
        for (int k = 0; k < array.length; k++)
            // compare with method def to see where inner loop stops
            if ( !floatLargestToTop(array, array.length - 1 - k) )
                return;
    }

    public static double getMedianDestructive(Student[] array)
    {
        // remember users sortKey
        int rememberKey;
        rememberKey = Student.getSortKey();

        double middleLeft, middleRight;
        double median = 0.0;

        Student.setSortKey(Student.SORT_BY_POINTS);
        arraySort(array);

        if (array.length == 0)
            return median; //returns 0.0

        if (array.length == 1)
        {
            median = array[0].getTotalPoints();
            return median; //returns 0.0
        }
        if (array.length % 2 == 0)
        {
            middleLeft = array[(array.length/2)-1].getTotalPoints();
            middleRight = array[(array.length/2)].getTotalPoints();

            median = (middleLeft + middleRight)/2.;
        }

        if ((array.length % 2) == 1)
            median = array[array.length/2].getTotalPoints();

        // reset the sortKey
        Student.setSortKey(rememberKey);

        return median;
    }


}
