package com.example.pritampc.httpurlconnectiontest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by pritamPC on 1/18/2018.
 */

public class JSONHandler extends AsyncTask<String,String,String> {
    Context context;
    ListView view;
    InputStream stream;
    HttpURLConnection connection;
    private static final String TAG="Handler";
    JSONParser parser;
    ProgressDialog dialog;
    public JSONHandler(Context context,ListView view)
    {
        this.view=view;
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(context);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            //connect to url
            URL url=new URL(strings[0]);
            connection=(HttpURLConnection) url.openConnection();
            connection.connect();
            //get the input data from input stream
            stream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
            StringBuilder sb =new StringBuilder();
            String result=" ";
            while((result=reader.readLine())!=null)
            {
                sb.append(result).append('\n');
            }
            return sb.toString();
        } catch (MalformedURLException e) {

                connection.disconnect();

            try {

                    stream.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //view.setText(s);
        if(dialog.isShowing())
            dialog.dismiss();
        try {
            parser = new JSONParser(s);
            ArrayList<String> val=parser.getArrayList();
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,val);
            view.setAdapter(adapter);
            //Log.d(TAG, "onPostExecute: JSON result"+val);
        }catch (JSONException e)
        {
            //Log.d(TAG, "onPostExecute: "+e.getMessage());
            Toast.makeText(context, "Enter City correctly!", Toast.LENGTH_SHORT).show();
        }catch (NullPointerException e) {
            Toast.makeText(context, "Enter City correctly!", Toast.LENGTH_SHORT).show();
        }finally {
           // Toast.makeText(context, "Enter City correctly!", Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(s);
    }
}
