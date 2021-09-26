package com.example.gridsim;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import Model.SimulationGrid;

public class GridAdapter extends BaseAdapter {
    private Context context;
    //private int[][] data = new int[16][16];
    private SimulationGrid simGrid;
    public GridAdapter(Context c, SimulationGrid simGrid) {
        context = c;
        this.simGrid = simGrid;
    }

    public int getCount() {
        return 256;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    /*public void setData(int[][] j) {
        data = j;
    }*/
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        /*if data[position] == x non zero
            imageView.setImageResource(R.mipmap.image1);
        if (data[position/16][position%16] != 0) {
            imageView.setImageResource(R.mipmap.image1);
        } else {
            imageView.setImageResource(R.mipmap.image2);
        }*/

        if (simGrid.getCell(position) == null) {
            imageView.setImageResource(R.mipmap.blank);
        } else {
            imageView.setImageResource(simGrid.getCell(position).getResourceID());
        }
        return imageView;
    }

}
