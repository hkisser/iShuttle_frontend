package com.ishuttle.kodah;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class log_in extends AppCompatActivity {
    InputStream is=null;
    String line=null;
    String result=null;
    String data=null;
    EditText UsernameEt, PasswordEt;
    String[] UsernameArray,PasswordArray,IDArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        UsernameEt=(EditText)findViewById(R.id.username_id);
        PasswordEt=(EditText)findViewById(R.id.password_id);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        isNetworkAvailable();

    }
    public void LoginClick(View view){

        String username=UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();

        //Toast.makeText(this,"Pass first Stage",Toast.LENGTH_SHORT).show();
        //id=Login(username,password);
        /*ProgressDialog dialog=new ProgressDialog(getApplicationContext());
        dialog.setMessage("Login in...");
        dialog.show();*/
        if(isNetworkAvailable())
        new LoginTask().execute(username,password);
        //Toast.makeText(this,"Pass Second Stage",Toast.LENGTH_SHORT).show();
        //dialog.dismiss();



    }


    class LoginTask extends AsyncTask<String,String,String>{


        @Override
        protected String doInBackground(String... params) {
            try {

                String username=params[0];
                String password=params[1];
                URL url=new URL("https://kodahinc.000webhostapp.com/testlogin.php");
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                is=new BufferedInputStream(con.getInputStream());
                //READ IS content into a string
                BufferedReader br=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb=new StringBuilder();


                while ((line=br.readLine())!=null) {
                    sb.append(line).append("\n");
                }

                result=sb.toString();
                is.close();
                br.close();
                //System.out.println(".............Here is my data.........");
                //System.out.println(result);
                //PARse JSON DATA
                //

                JSONArray ja=new JSONArray(result);

                UsernameArray=new String[ja.length()];
                PasswordArray=new String[ja.length()];
                IDArray=new String[ja.length()];
                data="fault";

                for(int i=0;i<ja.length();i++){
                    JSONObject jo=ja.getJSONObject(i);
                    IDArray[i]=jo.getString("Drivers_id");
                    UsernameArray[i]=jo.getString("Username");
                    PasswordArray[i]=jo.getString("Password");
                    if((UsernameArray[i].equals(username))&& (PasswordArray[i].equals(password))){
                        data=IDArray[i];

                    }


                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            //String id = null;
            if(s.equals("fault")){

                Toast.makeText(getApplicationContext(),"Wrong Password or You haven't registered,Click on the REGISTER BUTTON to register",Toast.LENGTH_LONG).show();
            }else{

                Toast.makeText(getApplicationContext(),"LOG IN Successful MR."+s,Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        }
    }
    public boolean isNetworkAvailable(){
        ConnectivityManager manager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable=false;
        if (networkInfo != null&& networkInfo.isConnectedOrConnecting()) {
            isAvailable = true;
        }else {
            Toast.makeText(log_in.this,"Not Connected", Toast.LENGTH_SHORT).show();
        }
        return isAvailable;
    }

}
