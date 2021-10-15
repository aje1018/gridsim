package com.example.gridsim;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.widget.GridView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
public class SimGridViewTest {
    private final String basicGrid = "{\"grid\":[[0,1000,1000,1000,0,1000,2003,1000,1000,1000,2002,0,2003,0,0,0],[0,1000,3000,3000,0,1000,0,1000,1000,1000,3000,0,3000,0,0,0],[0,1500,1000,1000,0,1000,0,1000,1000,1000,0,0,0,0,0,0],[0,0,1000,1000,0,1000,0,0,0,1000,2003,0,0,0,0,0],[0,0,3000,0,0,0,0,0,0,1000,0,0,0,0,0,3000],[0,0,2002,0,0,0,0,0,0,0,0,0,1001000,0,2003,0],[0,0,0,0,0,0,2003,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,10018672,0,0,9999264,0,0],[0,0,0,0,3000,3000,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,999176],[0,0,0,0,0,3000,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,3000,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,2003,0,0,0,0,0,0,2002,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,999672,2003],[0,0,10010516,2003,0,0,3000,3000,0,0,3000,0,0,0,0,0]]}";

    private JSONObject jsonObject;
    private Context mContext;
    private SimGridView simGridView;
    @Mock GridView gview;
    @Mock TextView tview;
    @Mock EventBus eventBus;

    @Before
    public void init() throws JSONException {
        simGridView = new SimGridView(mContext);
        gview = new GridView(mContext);
        tview = new TextView(mContext);
        eventBus = new EventBus();
        jsonObject = new JSONObject(basicGrid);
    }

    @Test
    public void test() {
        // inject an EventBus (?)
        simGridView.attach(tview, gview, eventBus);

        verify(eventBus).post(new MessageEvent(jsonObject));
    }
}
