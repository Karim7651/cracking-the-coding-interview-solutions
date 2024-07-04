import java.util.*;
public class LeetCodeArraysStrings {
    //49. Group Anagrams
    //O(N) O(N)
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        HashMap<Character,Integer> sMap = new HashMap<>();
        HashMap<Character,Integer> tMap = new HashMap<>();
        getCharactersFrequency(sMap,s);
        getCharactersFrequency(tMap,t);
        return sMap.equals(tMap);
    }
    public boolean isAnagram1(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        HashMap<Character,Integer> sMap = new HashMap<>();
        getCharactersFrequency(sMap,s);
        for(int i = 0 ; i < t.length() ; i++){
            int numOcc = sMap.getOrDefault(t.charAt(i),0) -1 ;
            if(numOcc < 0){
                return false;
            }
            sMap.put(t.charAt(i),numOcc);
        }
        return true;

    }
    //O(NLog(n)) O(N)
    public boolean isAnagram2(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        Arrays.sort(sChar);
        Arrays.sort(tChar);

        return Arrays.equals(sChar,tChar);
    }

    private void getCharactersFrequency(HashMap<Character,Integer> map , String s){
        for(int i = 0 ; i < s.length() ; i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
    }

    //2273. Find Resultant Array After Removing Anagrams
    public List<String> removeAnagrams(String[] words) {
        List<String> list = new ArrayList<>();
        if(words.length == 0 ){
            return list;
        }
        if(words.length == 1){
            list.add(words[0]);
            return list;
        }
        int leftPointer = 0;
        int rightPointer = 1;

        while(leftPointer < words.length && rightPointer < words.length){
            if(checkAnagrams(words[leftPointer],words[rightPointer])){
                rightPointer++;
            }else{
                list.add(words[leftPointer]);
                leftPointer = rightPointer;
                rightPointer++;
            }
        }
        if(!list.contains(words[leftPointer])){
            list.add(words[leftPointer]);
        }
        return list;
    }
    private boolean checkAnagrams(String s1 , String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        //get s1 freq
        for(int i = 0 ; i < s1.length() ; i++){
            map.put(s1.charAt(i),map.getOrDefault(s1.charAt(i),0)+1);
        }
        for(int i = 0 ; i < s2.length() ; i++){
            int numOcc = map.getOrDefault(s2.charAt(i),0) - 1;
            if(numOcc < 0){
                return false;
            }
            map.put(s2.charAt(i),numOcc);
        }
        return true;
    }

    //49. Group Anagrams
    //O(N*klog(k)) k => length of string, n length of strs array
    //O(N)
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for(int i = 0 ; i < strs.length ; i++){
            String current = strs[i];
            char[] arrayString = current.toCharArray();
            Arrays.sort(arrayString);
            String sortedString = new String(arrayString);
            if(!map.containsKey(sortedString)){
                map.put(sortedString,new ArrayList<>());
            }
            map.get(sortedString).add(current);
        }
        return new ArrayList<>(map.values());
    }

}
