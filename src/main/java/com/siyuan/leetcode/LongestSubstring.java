package com.siyuan.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {

        int max = 0;
        String s1  = "";
        for (int i=0;i<s.length();i++){
            boolean contains = s1.contains(s.charAt(i)+"");
            if (contains){
                if (max < s1.length()){
                    max = s1.length();
                }
                int index = s1.indexOf(s.charAt(i));
                s1 = s1.substring(index,s1.length()) + s.charAt(i);
            }else {
                s1 += s.charAt(i);
                if (max < s1.length()){
                    max = s1.length();
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();
        System.out.println(longestSubstring.lengthOfLongestSubstring("asljlj"));
    }
}
