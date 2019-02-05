package com.testing;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerTesting {

    public static void main(String[] args){
        Map<String, BiConsumer<String, Integer>> bc = new HashMap<String, BiConsumer<String,Integer>>();
        bc.put("first", bc1);
        bc.put("second", bc2);

        bc.get("first").accept("kevin",2);

    }

    static BiConsumer<String, Integer> bc1 = (a, b) -> {
        System.out.println("bc1 " + a + b.toString());
    };

    static BiConsumer<String, Integer> bc2 = (a, b) -> {
        System.out.println("bc2 " + a + b.toString());
    };


}