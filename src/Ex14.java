/**
 * Ex14 Assigment
 * @author Leon Beilis
 */
public class Ex14 {

    /**
     * @complexity: O(log(n))
     * @return timesAppear/Excist
     */
    public static int binarySearchAndCount(int[] data, int n, int key, boolean searchFirst) {
        int low = 0;
        int high = n - 1;
        int result = -1;
        while(low <= high) {
            int middle = (low + high) / 2;
            if(data[middle] == key) {
                result = middle;
                if(searchFirst){
                    high = middle - 1;
                } else {
                    low = middle + 1;
                }
            } else if ( key < data[middle] ){
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return result;
    }

    /**
     * count num in given array,
     * empty | null array | null value - return value 0
     * @param a sorted array of nums
     * @param x num to find & count
     * @return count
     */
    public static int count(int[] a, int x){
        try {
            if(a.length == 0 || a == null){
                return 0;
            }
            int firstIndex = binarySearchAndCount(a,a.length,x,true);
            if(firstIndex == -1) {
                return 0;
            } else {
                return binarySearchAndCount(a,a.length,x,false) - firstIndex + 1;
            }
        } catch (NullPointerException e){
            return 0;
        }
    }

    /**
     * check how many switches needed on chars in string
     * for valid odd,even or even,odd series
     * @complexity: O(n)^2
     * @param s - String
     * @return int - switch times
     */
    public static int alternating(String s){
        if(s == null){
            return 0;
        }
        int runTime = s.length()-1;
        int switches = 0, alternate = 0;
        int first = s.charAt(0);
        for(int j=0; j<runTime; j++) {
            if(s.charAt(j) != first){
                switches++;
            } else if(s.charAt(j)==s.charAt(j+1)){
                switches++;
            }
            j++;
        }
        if(switches > 2){
            for(int j=0; j<runTime; j++) {
                if(s.charAt(j) == first){
                    alternate++;
                } else if(s.charAt(j)==s.charAt(j+1)){
                    alternate++;
                }
                j++;
            }
            switches = Math.min(switches, alternate);
            if( (switches % 2) == 0){
                return switches / 2;
            } else {
                return (switches * 2) / switches;
            }
        }
        return switches;
    }

    /**
     * Helper function, checks if move is legal is given array.
     * @example if num is odd move left, if cant move right.
     *          if num is even move right, if cant move left.
     *          checks for recursive issue when way is stuck
     *          on recurcive loop in array and returns false
     * @param a array
     * @param index start index
     * @param last last index in array
     * @return boolean
     */
    public static boolean isWay(int[] a, int index, int last){
        if(index == last){
            return true;
        } else {
            boolean moveRight = false;
            if( (a[index] % 2) == 0) {
                moveRight = true;
            }
            int tmpIndex = a[index];
            if(moveRight){
                if(index > last){
                    index = Math.abs(index - a[index]);
                } else {
                    index = Math.abs(index + a[index]);
                }
            } else {
                index = Math.abs(index - a[index]);
            }
            if( (a[index] == tmpIndex) && (index != last) ){
                return false;
            }
            if(index != last){
                return isWay(a, index, last);
            } else {
                return true;
            }
        }
    }

    /**
     * checks if way is legal by given array, start on index 0.
     * more details in function above that receive more parameters to work with.
     * @param a positive int array
     * @return boolean - legal or not legal move inside array
     */
    public static boolean isWay(int[] a) {
        try {
            if(a == null || a.length == 0){
                return false;
            }
            int lastCell = a.length - 1;
            return  isWay(a, 0,lastCell);
        } catch (NullPointerException e){ // catch null array
            return false;
        }
    }

    /**
     * Helper function to printPath
     * receive original matrix and cords
     * return movement values: top, right, bottom, left
     * @param mat - original matrix
     * @param row - matrix y
     * @param col - matrix x
     * @return
     */
    public static int[] prepareNearestSteps(int[][] mat, int row, int col){
        int top = ( row > 0 ) ? mat[row-1][col] : 0;
        int right = ( col+1 < mat[row].length ) ? mat[row][col+1] : 0;
        int bottom = ( (row + 1) < mat.length ) ? mat[row+1][col] : 0;
        int left = ( col > 0 ) ? mat[row][col-1] : 0;
        int[] d = {top, right, bottom, left};
        return d;
    }

    /**
     * prints closest path to hill from given point
     * hill must be the highest value above its all direction: top, right, bottom, left
     * @param mat - matrix map, array
     * @param row - row index
     * @param col - column index
     * @param path - string path
     */
    public static void printPath (int[][] mat, int row, int col, String path) {
        try {
            int[] directionValues = prepareNearestSteps(mat, row, col);
            int target = mat[row][col];
            int tmp = target;
            if(directionValues[0] > target){
                row--;
                target = directionValues[0];
            }
            if(directionValues[1] > target){
                col++;
                target = directionValues[1];
            }
            if(directionValues[2] > target){
                row++;
                target = directionValues[2];
            }
            if(directionValues[3] > target){
                col--;
                target = directionValues[3];
            }
            if(target == tmp){
                System.out.println("finish, coords to hill: " + path);
                return;
            } else {
                path += " (" + row + "," + col + ")";
                printPath( mat, row, col, path);
            }
        } catch (NullPointerException e){ // null catch
            System.out.println("nulled matrix not accepted");
        }
    }

    /**
     * find closest hill and return its path in ascending scale
     * start at point (0,0)
     * @param mat ( 2d array )
     */
    public static void printPath (int[][] mat){
        printPath(mat, 0, 0, "(0,0)");
    }

    public static void main(String []args) {

    }
}