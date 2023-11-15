package com.example.dothiquynhmy_arrayobjectjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Customadapter customadapter;
    ArrayList<JsonConstructors> mangsanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadJSON().execute("https://dummyjson.com/carts/1");
        listView = findViewById(R.id.lvJSON);
        mangsanpham = new ArrayList<JsonConstructors>();
    }

    private class ReadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("products");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject objectproduct = array.getJSONObject(i);
                    String tieude = objectproduct.getString("title");
                    String gia = objectproduct.getString("price");
                    String soluong = objectproduct.getString("quantity");
                    String anh = objectproduct.getString("thumbnail");

                    mangsanpham.add(new JsonConstructors(tieude,gia,soluong,anh));
                }
                customadapter = new Customadapter(MainActivity.this, android.R.layout.simple_list_item_1,mangsanpham);
                listView.setAdapter(customadapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}