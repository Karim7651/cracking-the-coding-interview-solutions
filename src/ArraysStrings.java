import java.util.Arrays;
import java.util.HashMap;
//page 101 in pdf
//solutions in page 204 pdf

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
            if(charArray[i] == charArray[i+1])
                return false;
        }
        return true;
    }

    //Given two strings, write a method to decide if one is a permutation of the other.
    public static boolean checkPermutations(String s1 , String s2){
        //O(n log(n)) O(n)

        //string s of length n has (n!) permutations
        //assume case-sensitive and white space is significant

        //strings of different lengths cannot be permutations
        //approach 1 -> sort the check if both are equal
        if(s1.length() != s2.length()) return false;
        return sortString(s1).equals(s2);

    }

    public static boolean checkPermutations2(String s1,String s2){
        //check if character count in both strings are equal
        //chars are automatically cast to int
        if(s1.length() != s2.length()) return false;
        int[] chars = new int[128];

        for(int i = 0 ; i < s1.length() ; i++){
            chars[s1.charAt(i)]++;
        }

        for(int i = 0 ; i< s2.length() ; i++){
            int c = s1.charAt(i);
            chars[c]--;
            if(chars[c] < 0)
                return false;
        }
        return true;


    }
    private static String sortString(String s){
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
    public static void urlify(char[] str , int trueLength){
        int spaceCount = 0;
        for(int i = 0 ; i < trueLength ; i++){
            if(str[i] == ' ')
                spaceCount++;
        }
        int index = trueLength + 2*spaceCount -1;
        for(int i = trueLength - 1 ; i >= 0 ; i--){
            if(str[i] == ' '){
                str[index] = '0';
                str[index-1] = '2';
                str[index-2] = '%';
                index-=3;
            }else{
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
    public static void palindromePermutation(){

    }
}
