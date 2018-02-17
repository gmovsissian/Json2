package com.example.student.json2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview);
        button=findViewById(R.id.button);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/lsv/fifa-" + "worldcup-2018/master/data.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonfile = null;
                        try {
                            jsonfile = new JSONObject(response);
                            JSONArray jsonArray = jsonfile.getJSONArray("stadiums");
                            StringBuilder builder = new StringBuilder();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String city = jsonObject.getString("city");
                                builder.append(name + "\n");
                                builder.append(city + "\n");
                            }
                            textView.setText(builder);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } 
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(
                MainActivity.this
        );
        requestQueue.add(stringRequest);

    }

}
