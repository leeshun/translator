package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.VolleyError;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
        try {
            response = new String(response.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "---" + response + "---");
        List<String> result = new ArrayList<>();
        //TODO(leeshun) parse json string here
        Message message = MessageFactory.getMessage(MessageType.GET_CHINESE_TEXT_AND_SHOW_TO_ACTIVITY);
        message.obj = result;
        handler.sendMessage(message);
    }

    @Override
    public void onFail(VolleyError error) {
        Message message = MessageFactory.getMessage(MessageType.GET_CHINESE_ERROR);
        handler.sendMessage(message);
        Log.i(TAG, "---chinese text get error---");
    }
}
