package com.testing;

import java.util.Arrays;
import java.util.List;

public class NumberLetterAdd {

    public static void main(String[] args){


        //1f2e3d = 1 + 2 + 3 = 6
        //f234d3e7 = 234 + 3 + 7 = 244
        List<String> listArr = Arrays.asList("1f2e3d", "f234d3e7");
        listArr.stream().forEach(rec -> {
            System.out.println(sumIt(rec));
        });

    }

    private static int sumIt(String str){
        System.out.println("here");
        List<String> arr = Arrays.asList(str.split(""));
        int sum = 0;
        String temp = "";
        for(String rec: arr){
            if(rec.matches("\\d")){
                temp += rec;
            }
            else if(temp != ""){
                sum += Integer.parseInt(temp);
                temp = "";
            }
        }


        if(temp != ""){
            sum += Integer.parseInt(temp);
        }
        return sum;

    }
}
