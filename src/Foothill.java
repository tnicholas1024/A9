public class Foothill {

    public static void main(String[] args) {

    }
}

class Student{
    //public members
    public static int SORT_BY_FIRST = 88;
    public static int SORT_BY_LAST = 98;
    public static int SORT_BY_POINTS = 108;
    public static final char  MAX_NUM = 88;
    public static final char MIN_NUM = 108;


    //private members
    int sortKey = SORT_BY_LAST;


    //mutator (set)
    boolean setSortKey(int key){

        if (key <= MAX_NUM && key >= MIN_NUM)
        {
            sortKey = key;
            return true;
        }
        else {
            return false;
        }
    }
    //accessor (get)
    int getSortKey(){return sortKey;}

    //modification
    public static int compareTwoStudents( Student firstStud, Student secondStud )
    {
        return 0;
    }

    //change
    public static double getMediaDestructive(Student[]array){

        return 1.0;
    }

   public String toString(){

        return null;
    }



}
