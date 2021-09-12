package com.example.gridsim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.chromium.base.Log;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //private Button but1;
    //private Button but2;
    private int[][] data = new int[16][16];
    private static final String TAG = "gridView";

    static final String STATE_DATA = "curCell";
    private String cell = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = (TextView)findViewById(R.id.textView1);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            cell = savedInstanceState.getString(STATE_DATA);
            tv1.setText(cell);
            //Toast.makeText(MainActivity.this, cell, Toast.LENGTH_SHORT).show();
        } else {
            // Probably initialize members with default values for a new instance
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        // create new adapter
        GridAdapter ga = new GridAdapter(this);
        gridview.setAdapter(ga);

        // Milestone I code unused for this project

        /*but1 = (Button) findViewById(R.id.buttonLeft);
        but2 = (Button) findViewById(R.id.buttonRight);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        R.string.popup1,
                        Toast.LENGTH_SHORT).show();
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        R.string.popup2,
                        Toast.LENGTH_SHORT).show();
            }
        });*/

        // Some code used from https://stackoverflow.com/questions/20191914/how-to-add-gridview-setonitemclicklistener
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(data[position/16][position%16] == 0) {
                    //Toast.makeText(MainActivity.this, "Zero value at " + position, Toast.LENGTH_SHORT).show();
                    // Save gamestate
                    cell = "Zero value at " + position;
                } else {
                    //Toast.makeText(MainActivity.this, "Nonzero value at " + position, Toast.LENGTH_SHORT).show();
                    cell = "Nonzero value at " + position;
                }
                tv1.setText(cell);
                // Code from https://developer.android.com/studio/debug/am-logcat
                Log.d(TAG, "" + position);
            }
        });

        // Code from https://developer.android.com/training/volley/requestqueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Code from https://developer.android.com/training/volley/request
        String url = "http://stman1.cs.unh.edu:6191/games";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int[][] array = new int[16][16];
                            // Grab 2d JSON Array via tag (grid)
                            JSONArray twoD = response.getJSONArray("grid");
                            for (int i = 0; i < 16; i++) {
                                // Grab 1d JSON Array via index (0-15)
                                JSONArray oneD = twoD.getJSONArray(i);
                                for(int j = 0; j < 16; j++) {
                                    array[i][j] = oneD.getInt(j);
                                }
                            }
                            data = array;
                            ga.setData(array);
                            gridview.invalidateViews();
                        } catch (Exception e) {
                            System.out.println("Oops");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_DATA, cell);

        super.onSaveInstanceState(savedInstanceState);
    }
}