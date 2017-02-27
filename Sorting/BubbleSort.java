import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] my_array = new int[]{ 4, 10, 3, 5, 12, 2, 15, 11, 1, 20, 14 };

        bubbleSort(my_array);

        System.out.println(Arrays.toString(my_array));
    }

    private static void bubbleSort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length - (i + 1); j++) {
                if(array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
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
