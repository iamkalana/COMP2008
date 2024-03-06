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

public class FetchUserData implements Callable<ArrayList<User>> {

    private ArrayList<User> userList = new ArrayList<>();
    String jason = "";

    @Override
    public ArrayList<User> call() throws Exception {

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
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
                    String name = jsonObject.getString("name");
                    String userName = jsonObject.getString("username");
                    String email = jsonObject.getString("email");

                    JSONObject jsonAddress = new JSONObject(jsonObject.getString("address"));
                    String address = jsonAddress.getString("street") + ",\n";
                    address = address + jsonAddress.getString("suite") + ",\n";
                    address = address + jsonAddress.getString("city") + ".\n";
                    address = address + jsonAddress.getString("zipcode") + " (zipcode)\n";
                    JSONObject jasonGeo = new JSONObject(jsonAddress.getString("geo"));
                    address = address + jasonGeo.getString("lat") + " (latitude)\n";
                    address = address + jasonGeo.getString("lng") + " (longitude)";
//                    String address = jsonObject.getString("address");

                    String phone = jsonObject.getString("phone");
                    String website = jsonObject.getString("website");

                    JSONObject jsonCompany = new JSONObject(jsonObject.getString("company"));
                    String company = jsonCompany.getString("name") + "\n";
                    company = company + jsonCompany.getString("catchPhrase") + "\n";
                    company = company + jsonCompany.getString("bs");
//                    String company = jsonObject.getString("company");
                    //System.out.println(name);
                    userList.add(new User(id, name, userName, email, address, phone, website, company));
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
