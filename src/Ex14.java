public class Ex14 {

    /**
     * complexity level: O(log(n))
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
     * count num in given array
     * @@param a sorted array of nums
     * @param x num to find & count
     * @return
     */
    public static int count(int[] a, int x){
        int firstIndex = binarySearchAndCount(a,a.length,x,true);
        if(firstIndex == -1) {
            return 0;
        } else {
            return binarySearchAndCount(a,a.length,x,false) - firstIndex + 1;
        }
    }

    public static int alternating(String s){
        if(s == null){
            return 0;
        }
        int runTime = s.length()-1;
        int switches = 0;
        for(int j=0; j<runTime; j++) {
            if(s.charAt(j)==s.charAt(j+1)){
                switches++;
            }
        }
        return switches / 2;
    }

    public static boolean isWay(int[] a, int index, int last){
        if(index == last){
//            System.out.println("index is: " + index + " and last.");
            return true;
        } else {
            boolean moveRight = false;
            if( (a[index] % 2) == 0) {
                moveRight = true;
            }

//            System.out.println("received index is: " + index + " and value of it is: " + a[index]);

//            System.out.println("current index: " + index + ", value " + a[index]);

            //check where to move
            int tmp = a[index];
            if(moveRight){
                if(index > last){
                    index = Math.abs(index - a[index]);
                } else {
                    index = Math.abs(index + a[index]);
                }
            } else {
                index = Math.abs(index - a[index]);
            }
            if(a[index] == tmp){
//                System.out.println("==============================");
//                System.out.println("recurcive loop on index " + index + " and value is: " + a[index]);
                return false;
            }

//            System.out.println("new index: " + index + ", moveRight : " + moveRight);
//            System.out.println("==============================");

            if(index != last){
                return isWay(a, index, last);
            } else {
                return true;
            }
        }
    }

    public static boolean isWay(int[] a) {
        if(a == null){
            return false;
        }
        int lastCell = a.length - 1;
        return  isWay(a, 0,lastCell);
    }

    public static int[] prepareNearestSteps(int[][] mat, int row, int col){
//        System.out.println(mat.length); // rows
//        System.out.println(mat[row].length); // length in row
        int top = ( row > 0 ) ? mat[row-1][col] : 0;
        int right = ( col+1 < mat[row].length ) ? mat[row][col+1] : 0;
        int bottom = ( (row + 1) < mat.length ) ? mat[row+1][col] : 0;
        int left = ( col > 0 ) ? mat[row][col-1] : 0;
        int[] dv = {top, right, bottom, left};
        return dv;
    }

    public static void printPath (int[][] mat, int row, int col, String path) {
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
    }

    /**
     * find closest hill and return its path on asc scale
     * @param mat ( 2d array )
     */
    public static void printPath (int[][] mat){
        long startTime = System.currentTimeMillis();
        printPath(mat, 0, 0, "(0,0)");
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }

    public static void main(String []args) {

        ////////////////// ASSIGNMENT 4 //////////////////
        /*
        int[][] arrMap = {
                {3, 8, 7, 1},
                {5, 15, 2, 40},
                {12, 11, 13, 22},
                {14, 16, 17, 52}
        };
        printPath(arrMap);
        System.exit(0);
        */

        ////////////////// ASSIGNMENT 3 //////////////////
//        int[] legalArr = { 2, 4, 1, 6, 4, 2, 4, 3, 5 };
//        int[] notLegalArr = { 1, 4, 3, 1, 2, 4, 3  };
//        System.out.println("answer should be true: " + isWay(legalArr));
//        System.out.println("answer should be false: " + isWay(notLegalArr));
//        System.exit(0);


        ////////////////// ASSIGNMENT 2 //////////////////
        //00011011 - 2
        //00101011 - 1
//        String bits = "00011011100101101001100011";
//        System.out.println(bits.charAt(10));
//        int timesToSwitch = alternating(bits);
//        System.out.println(timesToSwitch);
//        System.out.println(bits.charAt(7));


        ////////////////// ASSIGNMENT 1 //////////////////
        /*
        int[] nums = { -5, -5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 67, 67, 99 };
        int x = count(nums, 34);
        System.out.println(x);
        */
    }
}