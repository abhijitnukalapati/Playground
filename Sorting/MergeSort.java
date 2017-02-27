import java.util.Arrays;

public class MergeSort {

    private static boolean debug = false;

    public static void main(String[] args) {

        if(args != null && args.length > 0) {
            debug = Boolean.valueOf(args[0]);
        }

        int[] my_unsorted_array = new int[]{ 4, 10, 3, 5, 12, 2, 15, 11, 1, 20, 14};
        int[] my_sorted_array = mergeSort(my_unsorted_array);

        System.out.println("Sorted Array: " + Arrays.toString(my_sorted_array));
    }

    private static int[] mergeSort(int[] array) {
        if(array.length < 2) {
            return array;
        }

        int mid = array.length/2;

        int[] first_array = mergeSort(Arrays.copyOfRange(array, 0, mid));
        int[] second_array = mergeSort(Arrays.copyOfRange(array, mid, array.length));    
       
        if(debug) { 
            System.out.println("First Array: " + Arrays.toString(first_array));
            System.out.println("Second Array: " + Arrays.toString(second_array));
        }


        // stitch the array back
        int i = 0, j = 0, array_index = 0;
        while(i < first_array.length && j < second_array.length) {
            if(first_array[i] <= second_array[j]) {
                array[array_index++] = first_array[i++];
            } else {
                array[array_index++] = second_array[j++];
            } 
        }
   
        fillArray(array, first_array, array_index, i);
        fillArray(array, second_array, array_index, j);
        
        if(debug) {
            System.out.println("Merged Array: " + Arrays.toString(array));
        }
        return array;
    }

    /**
     * Copies elements of orig beginning from index beginOrig to end of the array,
     * to dest beginning from index beginDest
     */   
    private static void fillArray(int[] dest, int[] orig, int beginDest, int beginOrig) {
        while(beginOrig < orig.length) {
            dest[beginDest++] = orig[beginOrig++];
        }
    }
}
