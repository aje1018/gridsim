package com.example.gridsim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //private Button but1;
    //private Button but2;
    private final SimGridView simGrid = new SimGridView(this);
    private static final String TAG = "gridView";

    static final String STATE_DATA = "curCell";
    private String cell = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = (TextView)findViewById(R.id.textView1);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        simGrid.attach(tv1, gridView);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            cell = savedInstanceState.getString(STATE_DATA);
            simGrid.setString(cell);
            tv1.setText(cell);
        } else {
            // Probably initialize members with default values for a new instance
        }

        int[][] array = new int[16][16];
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

        // Code from https://developer.android.com/training/volley/requestqueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Code from https://developer.android.com/training/volley/request
        String url = "http://stman1.cs.unh.edu:6191/games";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            simGrid.setUsingJSON(response.getJSONArray("grid"));
                        } catch (JSONException e){
                            System.out.println("oops");
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
        cell = simGrid.getString();
        savedInstanceState.putString(STATE_DATA, cell);

        super.onSaveInstanceState(savedInstanceState);
    }
}