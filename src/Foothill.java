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

    boolean setSORT_BY_FIRST(int key){
        if (key <= MAX_NUM && key >= MIN_NUM)
        {
            SORT_BY_FIRST = key;
            return true;
        }
        else {
            return false;
        }
    }

    boolean setSORT_BY_LAST(int key){
        if (key <= MAX_NUM && key >= MIN_NUM)
        {
            SORT_BY_LAST = key;
            return true;
        }
        else {
            return false;
        }
    }

    boolean setSORT_BY_POINTS(int key){
        if (key <= MAX_NUM && key >= MIN_NUM)
        {
            SORT_BY_POINTS = key;
            return true;
        }
        else {
            return false;
        }
    }

    public static int compareTwoStudents( Student firstStud, Student secondStud )
    {
        return 0;
    }


    //accessor (get)
    int getSortKey(){return sortKey;}

    //Modification



}
