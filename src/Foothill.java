
public class Foothill
{
    public static void main (String[] args)
    {
        Student[] evenClass = {
                new Student("lee","stan", 95),
                new Student("hamill","mark",123),
                new Student("fisher","carrie", 195),
                new Student("ford","harrison",148),

        };
        Student[] oddClass = {
                new Student("lee","stan", 95),
                new Student("hamill","mark",123),
                new Student("fisher","carrie", 195),
                new Student("ford","harrison",148),
                new Student("ridley", "daisy", 132)

        };
        Student[] smallClass = {
                new Student("lee","stan", 95),
                new Student("hamill","mark",123),
        };
        Student[] noClass = {

        };
        StudentArrayUtilities.toString("Before: ", evenClass);
        StudentArrayUtilities.toString("(DEFAULT) After: ", evenClass);
        StudentArrayUtilities.arraySort(evenClass);
        StudentArrayUtilities.toString("(FIRST) After: ", evenClass);
        StudentArrayUtilities.toString("(TOTAL) After: ", evenClass);

        StudentArrayUtilities.getMedianDestructive(evenClass);
        Student.getSortKey();

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
    // print the array with string as a title for the message box
    // this is somewhat controversial - we may or may not want an I/O
    // methods in this class.  we'll accept it today
    public static void toString(String title, Student[] data)
    {
        String output = "";

        // build the output string from the individual Students:
        for (int k = 0; k < data.length; k++){
            output += " " + data[k].toString();
            return;
        }

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