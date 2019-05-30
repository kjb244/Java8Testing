package com.testing;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paranthesis {

    public static void main(String[] args){
        List<String> paran = Arrays.asList("[{()}]","[{()}]{}", "{[(}]}");
        paran.stream().forEach(rec -> {
            System.out.println(matchIt(rec));
        });

    }

    private static boolean matchIt(String rec){
        if (rec.length() <2 || !(rec.length() % 2 == 0)) return false;

        List<String> patternArr = Arrays.asList("\\(\\)","\\[\\]","\\{\\}");
        String finalString = rec;
        int prevLength = 0;
        while(prevLength != finalString.length()){
            prevLength = finalString.length();
            for(String patternRec: patternArr){
                Pattern pattern = Pattern.compile(patternRec);
                Matcher matcher = pattern.matcher(finalString);
                finalString = matcher.replaceAll("");
            }

        }


        if(finalString.length() == 0) return true;
        return false;

    }
}
