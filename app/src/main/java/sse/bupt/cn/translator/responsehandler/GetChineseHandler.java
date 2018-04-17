package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.VolleyError;


import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.network.handler.StringRequestHandler;
import sse.bupt.cn.translator.util.MessageFactory;

public class GetChineseHandler implements StringRequestHandler {
    private static final String TAG = "GetChineseHandler";

    private Handler handler;

    public GetChineseHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(String response) {
        Log.i(TAG, "---" + response + "---");
        Message message = MessageFactory.getMessage(MessageType.GET_CHINESE_TEXT_AND_SHOW_TO_ACTIVITY, response);
        handler.sendMessage(message);
    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---chinese text get error---");
    }
}
