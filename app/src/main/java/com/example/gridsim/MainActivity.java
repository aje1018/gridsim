package com.example.gridsim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.gridsim.MESSAGE";

    private final SimGridView simGrid = new SimGridView(this);
    private static final String TAG = "gridView";

    static final String STATE_DATA = "curCell";
    private String cell = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but1 = (Button) findViewById(R.id.buttonLeft);
        Button but2 = (Button) findViewById(R.id.buttonRight);

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
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // left button, log.d history?
                newActivity();
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // right button, pause grid updating but continue updating history
                simGrid.togglePause();
            }
        });

        Poller poller = Poller.getInstance();
        poller.start(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        cell = simGrid.getString();
        savedInstanceState.putString(STATE_DATA, cell);

        super.onSaveInstanceState(savedInstanceState);
    }

    // new activity code help from https://developer.android.com/training/basics/firstapp/starting-activity
    public void newActivity() {
        Intent intent = new Intent(this, NewActivity.class);
        String history = simGrid.printHistory();

        intent.putExtra(EXTRA_MESSAGE, history);
        startActivity(intent);
    }
}