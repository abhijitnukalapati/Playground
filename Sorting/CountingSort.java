import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        int[] my_array = new int[]{ 4, 10, 3, 5, 12, 2, 15, 3, 11, 20, 14 };

        countingSort(my_array, 100);

        System.out.println(Arrays.toString(my_array));
    }

    private static void countingSort(int[] array, int max) {
        int[] map = new int[max + 1];

        for(int i = 0; i < array.length; i++) {
            map[array[i]]++;            
        }   

        for(int i = 0, j = 0; j < map.length; j++) {
            if(map[j] > 0) {
                int count = map[j];
            
                while(count > 0){
                    array[i++] = j;
                    count--;
                }
            }
        }
    }


}
