import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] my_array = new int[]{ 4, 10, 3, 5, 12, 2, 15, 11, 1, 20, 14 };

        selectionSort(my_array);

        System.out.println(Arrays.toString(my_array));
    }

    private static void selectionSort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = i + 1; j < array.length; j++) {
                if(array[j] < array[i]) {
                    swap(array, i, j);
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
