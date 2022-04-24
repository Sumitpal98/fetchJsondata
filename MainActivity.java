package com.example.jsonfile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Object> userList;
    Handler mainHandler=new Handler();
    ProgressDialog progressDialog = new progressDialog(context MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    class fetchData extends Thread{

        String data="";

        @Override
        public void run(){

            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    progressDialog.setMessage("Fatching Data");
                   progressDialog.setCancelable(false);
                   progressDialog.show();
                }
            });

            try{
            URL url=new Url(spec: "https://my-json-server.typicode.com/easygautam/data/users");
            HttpURLConnection httpURLConnection=(url.openConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line=bufferedReader.readLine())!=null){
                data=data+line;
            }

            if (!data.isEmpty()){
                JSONObject jsonObject=new JSONObject(data);
                JSONArray users=jsonObject.getJSONArray("id");
                userList.clear();

                for(int i=0;id.length();i++){
                    JSONObject names= users.getJSONObject(i);
                    Object name=names.getString("name");
                    userList.add(name);
                }
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            });
        }
}}
