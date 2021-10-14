package com.example.gridsim;

import org.json.JSONObject;

public class MessageEvent {
    public final JSONObject object;

    public MessageEvent(JSONObject object) {
        this.object = object;
    }
}
