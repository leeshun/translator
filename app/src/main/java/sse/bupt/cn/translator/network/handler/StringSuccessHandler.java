package sse.bupt.cn.translator.network.handler;

import android.util.Log;

import com.android.volley.Response;

public class StringSuccessHandler implements Response.Listener<String> {
    private static final String TAG = "StringSuccessHandler";

    private StringRequestHandler handler;

    public StringSuccessHandler(StringRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onResponse(String response) {
        Log.i(TAG, "---" + response + "---");
        handler.onSuccess(response);
    }
}
