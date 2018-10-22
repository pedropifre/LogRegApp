package com.example.pedropifre.logregapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class graficodelinha extends AppCompatActivity {
    private ProgressDialog pd;

    ArrayList<String> y;
    ArrayList<Entry> x;
    BarEntry values;
    private LineChart graph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_line_chart);
        setData();


        }


    private void setData() {
        String url = "http://54.94.143.174/login_dados2.php";
        x = new ArrayList<Entry>();
        y = new ArrayList<String>();
        graph = (LineChart) findViewById(R.id.linechar);

        final ArrayList<Entry> entries = new ArrayList<Entry>();
        String tag_string_req = "req_chart";

        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonobject = jsonArray.getJSONObject(i);

                                int value = jsonobject.getInt("DADO1");
                                String date = jsonobject.getString("DADO3");
                                x.add(new Entry(value, i));
                                y.add(date);

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LineDataSet set1 = new LineDataSet(x, "NAV Data Value");
                        set1.setLineWidth(1.5f);
                        set1.setCircleSize(4f);
                        LineData data = new LineData(y, set1);
                        graph.setData(data);
                        }
                }, new  Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error != null){

                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                }
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq);


        };



    // sort by x-value

}