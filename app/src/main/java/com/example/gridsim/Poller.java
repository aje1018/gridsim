package com.example.gridsim;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Poller {

    // stuff to make it a singleton
    private static Poller pole = new Poller();
    private Poller() {}
    public static Poller getInstance() {
        return pole;
    }

    public void start(Context context) {
        // Code from https://developer.android.com/training/volley/requestqueue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        // Code from https://developer.android.com/training/volley/request
        String url = "http://stman1.cs.unh.edu:6191/games";
        // issue post request
        JsonObjectRequest post = new JsonObjectRequest(Request.Method.POST, url, null, response -> {}, error -> {});
        requestQueue.add(post);

        // ScheduledThreadPoolExecutor
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Runnable periodicTask = new Runnable(){
            @Override
            public void run() {
                try{
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    // post new message for subscribers
                                    Log.d("gridView", "Response Received");
                                    EventBus.getDefault().post(new MessageEvent(response));
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                }
                            });
                    requestQueue.add(jsonObjectRequest);
                }catch(Exception e){
                    System.out.println("oops");
                }
            }

        };

        // periodic, 0 sec initial delay, 0.5 sec periodic delay
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(periodicTask, 500, 500, TimeUnit.MILLISECONDS);

    }
}
