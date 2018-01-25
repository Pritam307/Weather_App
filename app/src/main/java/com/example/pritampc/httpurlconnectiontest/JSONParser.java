package com.example.pritampc.httpurlconnectiontest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pritamPC on 1/21/2018.
 */

public class JSONParser {
    JSONObject object;
    ArrayList<String> arrayList=new ArrayList<>();
    int city_id;
    int city_code;
    String city_name;
    private static final String TAG="Handler";
    public JSONParser(String json_result) throws JSONException {
        if(json_result!=null) {
            object = new JSONObject(json_result);
        }
    }

    public void result_main(){
        try {
            JSONObject main = object.getJSONObject("main");
            arrayList.add("Temperature: "+main.getLong("temp"));
            arrayList.add("Pressure: "+main.getInt("pressure"));
            arrayList.add("Humidity: "+main.getInt("humidity"));
            arrayList.add("Temperature Min: "+main.getLong("temp_min"));
            arrayList.add("Temperature Max: "+main.getLong("temp_max"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void result_weather()
    {
        try {
            JSONArray weather=object.getJSONArray("weather");
            for(int i=0;i<weather.length();i++)
            {
                JSONObject c=weather.getJSONObject(i);
                arrayList.add("Status: "+c.getString("description"));
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void location()
    {

        try {
             city_id=object.getInt("id");
             city_name=object.getString("name");
             city_code=object.getInt("cod");
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
            arrayList.add("City Id: "+ city_id);
            arrayList.add("City Name : "+city_name);
            arrayList.add("City Code : "+city_code);
    }

    public ArrayList<String> getArrayList() {
        location();
        result_main();
        result_weather();
        return arrayList;
    }
}
