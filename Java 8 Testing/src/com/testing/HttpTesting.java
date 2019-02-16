package com.testing;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HttpTesting {

    public static void main(String[] args){
        List<Todo> arr = new ArrayList<Todo>();
        IntStream.range(0,4).forEach( rec -> {
            String res = getRequest("https://jsonplaceholder.typicode.com/todos/" + (rec + 1));
            Gson gson = new Gson();
            Todo todo = new Todo();
            todo = gson.fromJson(res, Todo.class);
            arr.add(todo);

        });

        arr.stream().filter(r -> r.getCompleted().equalsIgnoreCase("false")).forEach(rec -> {
            System.out.println(rec);
        });



    }



    private static String getRequest(String url){

        try{
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            // add request header
            //request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(request);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        catch(Exception e){
            return null;
        }
    }
}
