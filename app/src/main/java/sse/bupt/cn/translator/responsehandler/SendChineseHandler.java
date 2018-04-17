package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.util.Log;

import com.android.volley.VolleyError;

import sse.bupt.cn.translator.network.handler.StringRequestHandler;

public class SendChineseHandler implements StringRequestHandler {
    private static final String TAG = "SendChineseHandler";

    private Handler handler;

    public SendChineseHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(String response) {
        if (response.toLowerCase().equals("true")) {
            //TODO
        } else if (response.toLowerCase().equals("false")) {
            //TODO
        } else {
            //TODO
        }
    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---" + error.getMessage() + "---");
        //TODO(leeshun) handle the error message
    }
}
