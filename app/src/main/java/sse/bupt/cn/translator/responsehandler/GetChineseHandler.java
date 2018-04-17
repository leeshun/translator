package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.util.Log;

import com.android.volley.VolleyError;


import sse.bupt.cn.translator.network.handler.StringRequestHandler;

public class GetChineseHandler implements StringRequestHandler {
    private static final String TAG = "GetChineseHandler";

    private Handler handler;

    public GetChineseHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(String response) {
        Log.i(TAG, "---" + response + "---");
        //TODO(leeshun) update chinese response

    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---chinese text get error---");
    }
}
