package com.testing;

import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HttpTesting {

    public static void main(String[] args){
        List<Todo> arr = new ArrayList<Todo>();
        IntStream.range(0,0).forEach( rec -> {
            String res = getRequest("https://jsonplaceholder.typicode.com/todos/" + (rec + 1));
            Gson gson = new Gson();
            Todo todo = new Todo();
            todo = gson.fromJson(res, Todo.class);
            arr.add(todo);

        });

        arr.stream().filter(r -> r.getCompleted().equalsIgnoreCase("false")).forEach(rec -> {
            System.out.println(rec);
        });

        //test post
        String postPayload = postRequest("http://ptsv2.com/t/56f3p-1550324317/post");
        System.out.println(postPayload);


        //test gson
        String res = getRequest("https://jsonplaceholder.typicode.com/todos/1");
        JsonParser jsonParser = new JsonParser();
        JsonObject obj = jsonParser.parse(res).getAsJsonObject();
        JsonArray jsonArray = new JsonArray();
        IntStream.range(0,1).forEach(rec -> {
            JsonObject innerObj = new JsonObject();
            innerObj.addProperty("id", rec);
            jsonArray.add(innerObj);
        });
        obj.add("values", jsonArray);
        System.out.println(obj.toString());



    }

    private static String postRequest(String url){
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        try {

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("name", "kevin"));


            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String line = "";
            StringBuffer result = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                result.append(line);

            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
