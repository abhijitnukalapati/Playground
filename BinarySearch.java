public class BinarySearch {
  
    public static void main(String[] args) {
        // sample data
        int sorted_array = new int[]{4, 7, 9, 15, 20, 24, 25, 35, 36, 55, 67, 78, 103};
        int element_to_find = 67;
          
        // usage
        int result_one = binarySearch(sorted_array, element_to_find);
        int result_two = binarySearchWithRecursion(sorted_array, 0, sorted_array.length, element_to_find);
    }
    
    // precondition - input array is sorted
    // 
    // output - index of the element e in the array, 
    // returns -1 if e isn't found
    private static int binarySearch(int[] arr, int e) {
        int start = 0;
        int end = arr.length;

        while(start <= end) {
            int mid = (start + end)/2;

            if(arr[mid] == e) {
                return mid;
            }

            if(e > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return -1;
    }
    
    // precondition - input array is sorted
    // 
    // output - index of the element e in the array, 
    // returns -1 if e isn't found
    private static int binarySearchWithRecursion(int[] arr, int start, int end, int e) {
        if(start > end) {
            return -1;
        }

        int mid = (start + end)/2;
        if(arr[mid] == e) {
            return mid;
        } else if(e > arr[mid]) {
            return binarySearchWithRecursion(arr, mid + 1, end, e);
        } else {
            return binarySearchWithRecursion(arr, start, mid - 1, e);
        }
    }
  
}
