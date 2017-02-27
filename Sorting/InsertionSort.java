import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] my_array = new int[]{ 4, 10, 3, 5, 12, 2, 15, 3, 11, 20, 14 };

        insertionSort(my_array);

        System.out.println(Arrays.toString(my_array));
    }

    private static void insertionSort(int[] array) {
        if(array.length <= 1) {
            return;
        }

        for(int i = 1; i < array.length; i++) {
            int key = array[i];
            int lastSortedIndex = i - 1;
            while(lastSortedIndex >= 0 && array[lastSortedIndex] > key) {
                array[lastSortedIndex + 1] = array[lastSortedIndex];
                lastSortedIndex--;
            }
             
            array[lastSortedIndex + 1] = key;       
        }
    }


}
