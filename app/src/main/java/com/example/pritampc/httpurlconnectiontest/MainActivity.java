package com.example.pritampc.httpurlconnectiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button hit;
    ListView result;
    JSONHandler handler;
    EditText ed;
    private static final String TAG="Handler";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hit=findViewById(R.id.button);
        result=findViewById(R.id.listView);
        ed=findViewById(R.id.enter_city);
        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=ed.getText().toString();
                String url="http://api.openweathermap.org/data/2.5/weather?q="+name+"&appid=1b5dda390a434c315d55a3ac5906fdf0";
                Log.d(TAG, "onClick: "+url);
                handler= (JSONHandler) new JSONHandler(MainActivity.this,result).execute(url);
            }
        });
    }
}


