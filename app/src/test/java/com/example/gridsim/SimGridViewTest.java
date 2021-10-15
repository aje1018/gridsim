package com.example.gridsim;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.widget.GridView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mock;


public class SimGridViewTest {
    private final String basicGrid = "{\"grid\":[[0,1000,1000,1000,0,1000,2003,1000,1000,1000,2002,0,2003,0,0,0],[0,1000,3000,3000,0,1000,0,1000,1000,1000,3000,0,3000,0,0,0],[0,1500,1000,1000,0,1000,0,1000,1000,1000,0,0,0,0,0,0],[0,0,1000,1000,0,1000,0,0,0,1000,2003,0,0,0,0,0],[0,0,3000,0,0,0,0,0,0,1000,0,0,0,0,0,3000],[0,0,2002,0,0,0,0,0,0,0,0,0,1001000,0,2003,0],[0,0,0,0,0,0,2003,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,10018672,0,0,9999264,0,0],[0,0,0,0,3000,3000,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,999176],[0,0,0,0,0,3000,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,3000,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,2003,0,0,0,0,0,0,2002,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,999672,2003],[0,0,10010516,2003,0,0,3000,3000,0,0,3000,0,0,0,0,0]]}";

    @Mock
    MessageEvent event;
    @Mock
    Context mContext;
    @Mock
    GridView gview = (GridView) findViewById(R.id.gridview);

    @Test
    public void test() {
        assertNotNull(mContext);
        mContext.
        SimGridView simGridView = new SimGridView(mContext);


        try {
            JSONArray grid = new JSONArray(basicGrid);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
