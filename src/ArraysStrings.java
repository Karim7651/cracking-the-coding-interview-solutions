import java.time.chrono.MinguoDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//page 101 in pdf
//solutions in page 204 pdf
//hints page 664

public class ArraysStrings {
    //is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
    //cannot use additional data structures?
    public static boolean isUnique(String s) {
        //O(N) O(1) => hashMap max size 128

        //ASCII -> max 128 character
        //extended ASCII => 256 character
        //assume ASCII

        //Assume lowercase characters are different from uppercase characters
        //Assume spaces are to be ignored
        if (s.length() > 128)
            return false;
        HashMap<Character, Integer> map = new HashMap<>(128);
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == ' ')
                continue;
            if (map.containsKey(current)) {
                return false;
            }
            map.put(current, 1);
        }
        return true;
    }

    public static boolean isUniqueWithoutDataStructures(String s) {
        //approach1 => O(n^2) O(1)
        //approach2 => sort string and linearly check for neighbouring characters
        //approach2 => O(n log(n)) (n)
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        for (int i = 0; i < charArray.length - 1; i++) {
            if (charArray[i] == charArray[i + 1])
                return false;
        }
        return true;
    }

    //Given two strings, write a method to decide if one is a permutation of the other.
    public static boolean checkPermutations(String s1, String s2) {
        //O(n log(n)) O(n)

        //string s of length n has (n!) permutations
        //assume case-sensitive and white space is significant

        //strings of different lengths cannot be permutations
        //approach 1 -> sort the check if both are equal
        if (s1.length() != s2.length()) return false;
        return sortString(s1).equals(s2);

    }

    public static boolean checkPermutations2(String s1, String s2) {
        //check if character count in both strings are equal
        //chars are automatically cast to int
        if (s1.length() != s2.length()) return false;
        int[] chars = new int[128];

        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            int c = s1.charAt(i);
            chars[c]--;
            if (chars[c] < 0)
                return false;
        }
        return true;


    }

    private static String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    //URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string
    //has sufficient space at the end to hold the additional characters, and that you are given the "true"
    //length of the string. (Note: If implementing in Java, please use a character array so that you can
    //perform this operation in place.)
    //EXAMPLE
    //Input: "Mr John Smith", 13
    //Output: "Mr%20John%20Smith"

    //O(N)
    public static void urlify(char[] str, int trueLength) {
        int spaceCount = 0;
        for (int i = 0; i < trueLength; i++) {
            if (str[i] == ' ')
                spaceCount++;
        }
        int index = trueLength + 2 * spaceCount - 1;
        for (int i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index] = '0';
                str[index - 1] = '2';
                str[index - 2] = '%';
                index -= 3;
            } else {
                str[index] = str[i];
                index--;
            }
        }
    }

    //Palindrome Permutation: Given a string, write a function to check if it is a permutation of
    //a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
    //permutation is a rearrangement of letters. The palindrome does not need to be limited to just
    //dictionary words.
    //EXAMPLE
    //Input: Tact Coa
    //Output: True (permutations: "taco cat'; "atco eta·; etc.)

    //O(N)
    //another approach is to keep a oddCounter and at the end check if this odd counter <= 1 => return true => this saves time traversing the hashMap
    public static boolean palindromePermutation(String s) {
        //check if string contains even number of each character(even length) OR even number of each character with only 1 odd character(odd length)
        //spaces don't matter
        if (s.length() <= 1) return true;
        HashMap<Character, Integer> frequencyTable = buildFrequencyTable(s);
        return checkPalindromePermutation(frequencyTable);
    }

    private static boolean checkPalindromePermutation(HashMap<Character, Integer> frequencyTable) {
        boolean flagOdd = false;
        for (Character key : frequencyTable.keySet()) {
            int value = frequencyTable.get(key);
            if (value % 2 != 0) {
                if (flagOdd) {
                    return false;
                } else {
                    flagOdd = true;
                }
            }
        }
        return true;
    }

    private static HashMap<Character, Integer> buildFrequencyTable(String s) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (Character.isLetter(current)) {
                current = Character.toLowerCase(current);
                frequencyMap.put(current, frequencyMap.getOrDefault(current, 0) + 1);
            }
        }
        return frequencyMap;
    }


    //One Away: There are three types of edits that can be performed on strings: insert a character,
    //remove a character, or replace a character. Given two strings, write a function to check if they are
    //one edit (or zero edits) away.
    //EXAMPLE
    //pale, ple -> true
    //pales, pale -> true
    //pale, bale -> true
    //pale, bake -> false
    //Hints:#23, #97, #130
    //O(N)
    public static boolean isOneEditAway(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return onEditReplace(s1, s2);
        } else if (s1.length() == s2.length() + 1) {
            return onEditInsert(s2, s1);
        } else if (s1.length() + 1 == s2.length()) {
            return onEditInsert(s1, s2);
        }
        return false;
    }

    private static boolean onEditReplace(String s1, String s2) {
        boolean foundDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {
                    return false;
                } else {
                    foundDifference = true;
                }
            }
        }
        return true;
    }

    //insert into s2 to be s1
    private static boolean onEditInsert(String s1, String s2) {
        int index1 = 0, index2 = 0;
        while (index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                } else {
                    index2++;
                }
            } else {
                index2++;
                index1++;
            }
        }
        return true;
    }

    //String Compression: Implement a method to perform basic string compression using the counts
    //of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
    //"compressed" string would not become smaller than the original string, your method should return
    //the original string. You can assume the string has only uppercase and lowercase letters (a - z).
    public static String stringCompression(String input) {
        if (input.length() < 3) {
            return input;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        char currentChar = input.charAt(0);
        for (int i = 0; i < input.length(); i++) {
            char loopChar = input.charAt(i);
            if (loopChar != currentChar) {
                stringBuilder.append(currentChar);
                stringBuilder.append(counter);
                currentChar = loopChar;
                counter = 1;
            } else {
                counter++;
            }
        }
        stringBuilder.append(currentChar);
        stringBuilder.append(counter);
        return input.length() > stringBuilder.length() ? stringBuilder.toString() : input;
    }

    //Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
    //bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
    //Hints: #51, # 100
    //O(N) O(1) where n is the input
    public static void rotateMatrix(int[][] array) {
        if (array.length == 0 || array.length != array[0].length) {
            System.out.print("Matrix has to be a square matrix");
            return;
        }
        //approach 1 transpose then rowReverse
        transposeMatrix(array);
        rowReverse(array);
        printMatrix(array);
        //
    }

    private static void transposeMatrix(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            //j = i not j = 0 because starting from j = 0 would transpose it twice resulting in no change
            for (int j = i; j < array[0].length; j++) {
                int temp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = temp;
            }
        }
    }

    private static void rowReverse(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length / 2; j++) {
                int rightPointer = array[0].length - j - 1;
                int temp = array[i][j];
                array[i][j] = array[i][rightPointer];
                array[i][rightPointer] = temp;
            }
        }
    }

    private static void printMatrix(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    //Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
    //column are set to 0.
    //Hints:#17, #74, #702
    public static void zeroMatrix(int[][] array) {
        //to make this more space efficient use an arrayList
        boolean[] targetRows = new boolean[array.length];
        boolean[] targetColumns = new boolean[array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] == 0){
                    targetRows[i] = true;
                    targetColumns[j] = true;
                }
            }
        }
        for(int i = 0 ; i < targetRows.length ; i++){
            if(targetRows[i] == true) {
                nullifyRow(array, i);
            }
        }
        for(int j = 0 ; j < targetColumns.length ; j++){
            if(targetColumns[j] == true) {
                nullifyColumn(array, j);
            }
        }
        printMatrix(array);
    }

    private static void nullifyRow(int[][] array , int i){
        for(int j = 0 ; j < array[i].length;j++){
            array[i][j] = 0;
        }
    }
    private static void nullifyColumn(int[][] array , int j){
        for(int i = 0 ; i < array.length ;i++){
            array[i][j] = 0;
        }
    }

    //String Rotation:Assume you have a method isSubstring which checks if oneword is a substring
    //of another. Given two strings, sl and s2, write code to check if s2 is a rotation of sl using only one
    //call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
    //Hints: #34, #88, # 7 04
    //is s2 a rotation of s1 ? (meaning a substring with)
    //solution : If we imagine that 52 is a rotation of 51, then we can ask what the rotation point is. For example, if you
    //rotate waterbottle after wat. you get erbottlewat. In a rotation, we cut 51 into two parts, x and y,
    //and rearrange them to get 52.
    //s1 = xy = waterbottle
    //x = wat
    //y = erbottle
    //s2 = yx = erbottlewat
    //So, we need to check if there's a way to split s1 into x and y such that xy = s1 and yx = s2. Regardless of
    //where the division between x and y is, we can see thatyx will always be a substring of xyxy.That is, s2 will
    //always be a substring of s1s1.
    //And this is precisely how we solve the problem: simply do isSubstring(slsl, s2).
    //s1s1 = xyxy
    //x2   = yx guaranteed to be a stringString of s1s1
    //O(N) where n is the input size assuming isSubString runs in O(N)
    public static boolean stringRotation(String s1, String s2){
        if(s1.length() == s2.length() && s1.length()>0){
            String s1s1 = s1 + s1;
            //return isSubString(s1s1,s2);
        }
        return false;
    }
}
