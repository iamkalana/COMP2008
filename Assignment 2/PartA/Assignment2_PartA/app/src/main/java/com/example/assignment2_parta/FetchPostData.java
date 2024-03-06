package com.example.assignment2_parta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class FetchPostData implements Callable<ArrayList<Post>> {
    private ArrayList<Post> postList = new ArrayList<>();
    String jason = "";

    @Override
    public ArrayList<Post> call() throws Exception {

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                jason = jason + line;
            }

            if (!jason.isEmpty()) {
                JSONArray jsonArray = new JSONArray(jason);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    int userId = jsonObject.getInt("userId");
                    String title = jsonObject.getString("title");
                    String body = jsonObject.getString("body");

                    postList.add(new Post(id, userId, title, body));
                    System.out.println(title);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postList;
    }
}
