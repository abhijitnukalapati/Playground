import java.util.Set;
import java.util.HashSet;

public class Palindrome {

    private static boolean isPalindrome(String test) {
        if(test == null || test.isEmpty()) {
            return false;
        }

        int length = test.length(); 
        for(int i = 0; i < length/2; i++) {
            if(test.charAt(i) != test.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean doesHavePermutationPalindrome(String test) {
        if(test == null || test.isEmpty()) {
            return false;
        }

        Set<Character> characters = new HashSet<>();
        
        for(int i = 0; i < test.length(); i++) {
            char curr = test.charAt(i);
            if(characters.contains(curr)) {
                characters.remove(curr);
            } else {
                characters.add(curr);
            }
        }


        return characters.size() <= 1;
    } 

}
