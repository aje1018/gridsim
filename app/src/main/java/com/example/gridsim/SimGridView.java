package com.example.gridsim;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;

import Model.GardenerItem;
import Model.GridCell;
import Model.SimulationGrid;

public class SimGridView {
    private String[][] cellInfo = new String[16][16];

    private static final String TAG = "gridView";

    private boolean isPaused;

    private final Context mcontext;
    public SimulationGrid simGrid;
    private TextView tview;
    private GridView gview;
    private String cell;
    private int clickPos;
    public SimGridView(Context context) {
        isPaused = false;
        mcontext = context;
        simGrid = new SimulationGrid();
    }

    public String getString() {
        return cell;
    }
    public void setString(String str) {
        cell = str;
    }

    public void attach(TextView tview, GridView gview, EventBus eventBus, GridAdapter ga) {
        // tells SimGridView what TextView and GridView to
        // hook up to the other parts, ideally should be called from
        // the Activity's onStart method rather than from the constructor,
        // for use with EventBus later on
        if (eventBus == null) {
            EventBus.getDefault().register(this);
        } else {
            eventBus.register(this);
        }


        this.tview = tview;
        if(ga == null) {
            GridAdapter gridAdapter = new GridAdapter(mcontext, simGrid);
            gview.setAdapter(gridAdapter);
        } else {
            gview.setAdapter(ga);
        }

        this.gview = gview;
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                if (simGrid.getCell(position) == null) {
                    tview.setText("NULL value");
                } else {
                    clickPos = position;
                    GridCell gc = simGrid.getCell(position);
                    cell = gc.getCellInfo();
                    if(!isPaused) {
                        tview.setText(cell);
                        updateCells();
                    } else {
                        tview.setText(cellInfo[position / 16][position % 16]);
                    }

                    Log.d(TAG, "" + position);
                }
            }
        });
    }

    public void detach(EventBus eventBus) {
        // unsubscribe
        if(eventBus == null) {
            EventBus.getDefault().unregister(this);
        } else {
            eventBus.unregister(this);
        }
    }
    // all eventbus code with help of https://greenrobot.org/eventbus/documentation/how-to-get-started/
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        try {
            // line that was originally in code that moved to poller
            setUsingJSON(event.object.getJSONArray("grid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setUsingJSON(JSONArray arr) {
        // invokes the same-named method on the active simulationGrid, calls
        // invalidateViews on the GridView, and makes sure the selected
        // item information is up-to-date
        simGrid.setUsingJSON(arr);
        GridCell gc = simGrid.getCell(clickPos);
        cell = gc.getCellInfo();
        tview.setText(cell);

        gview.invalidateViews();
    }

    public String printHistory() {
        GridCell gi = simGrid.getCell(clickPos);
        //Log.d("gridView", gi.getCellInfo());
        return gi.getCellInfo();
    }
    public boolean getPauseValue() {
        return isPaused;
    }
    public void togglePause() {
        isPaused = !isPaused;
        simGrid.togglePause();
    }


    public void updateCells() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                cellInfo[i][j] = simGrid.getCell((16 * i) + j).getCellInfo();
            }
        }
    }
}

