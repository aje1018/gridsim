package com.example.gridsim;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gridsim.GridAdapter;
import com.example.gridsim.R;

import org.json.JSONArray;

import Model.GridCell;
import Model.SimulationGrid;

public class SimGridView {
    private static final String TAG = "gridView";

    private Context mcontext;
    public SimulationGrid simGrid;
    private TextView tview;
    private GridView gview;
    private GridAdapter gridAdapter;
    private String cell;
    public SimGridView(Context context) {
        mcontext = context;
        simGrid = new SimulationGrid();
    }

    public void attach(TextView tview, GridView gview) {
        // tells SimGridView what TextView and GridView to
        // hook up to the other parts, ideally should be called from
        // the Activity's onStart method rather than from the constructor,
        // for use with EventBus later on
        this.tview = tview;
        this.gview = gview;
        gridAdapter = new GridAdapter(mcontext, simGrid);
        gview.setAdapter(gridAdapter);


        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                GridCell gc = simGrid.getCell(position);
                cell = gc.getCellInfo();
                tview.setText(cell);

                Log.d(TAG, "" + position);
            }
        });
    }

    public void detach() {
        // does nothing yet, but here to get ready for use with EventBus later
        // (called from the Activity's onStop)

    }

    public void setUsingJSON(JSONArray arr) {
        // invokes the same-named method on the active simulationGrid, calls
        // invalidateViews on the GridView, and makes sure the selected
        // item information is up-to-date
        simGrid.setUsingJSON(arr);
        gview.invalidateViews();
    }
}

