package com.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GraphMapping {

    public static void main(String [] args){
        List<KeyValue<Integer, Integer>> kvArr = new ArrayList<KeyValue<Integer, Integer>>();
        kvArr.add(new KeyValue(30,45));
        kvArr.add(new KeyValue(10,20));
        kvArr.add(new KeyValue(20,30));
        kvArr.add(new KeyValue(1,10));

        final int[] value = new int[1];
        for(int i=0; i<kvArr.size(); i++){
            value[0] = kvArr.get(i).getValue();
            int counter = 0;
            System.out.print(kvArr.get(i).toString());
            for(int j=0; j<kvArr.size()-1;j++){
                Optional<KeyValue<Integer, Integer>> temp =  kvArr.stream()
                        .filter(r -> r.getKey() == value[0])
                        .findFirst();
                if(temp.isPresent()){
                    counter++;
                    value[0] = temp.get().getValue();
                    System.out.print(" " + temp.get().toString());
                }
                else{

                    break;
                }
            }
            if(counter == kvArr.size()-1){
                break;
            }
            System.out.println("");
            counter=0;
        }

    }


}
