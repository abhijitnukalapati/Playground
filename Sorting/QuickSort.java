import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] my_array = new int[]{ 4, 10, 3, 5, 12, 2, 15, 11, 1, 20, 14 };

        quickSort(my_array, 0, my_array.length - 1);

        System.out.println(Arrays.toString(my_array));
    }


    private static void quickSort(int[] array, int begin, int end) {
        if(begin >= end) {
            return;
        }

        int partitionIndex = partition(array, begin, end);
        quickSort(array, begin, partitionIndex - 1);
        quickSort(array, partitionIndex + 1, array.length - 1);
    }


    private static int partition(int[] array, int begin, int end){
        int pivot = array[end];
        int partitionIndex = begin;
        
        for(int i = begin; i < end; i++) {
            if(array[i] <= pivot) {
                int temp = array[i];
                array[i] = array[partitionIndex];
                array[partitionIndex] = temp;
                partitionIndex++;
            }          
        }

        array[end] = array[partitionIndex];
        array[partitionIndex] = pivot;

        return partitionIndex;
    }
}
