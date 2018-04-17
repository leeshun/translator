package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.VolleyError;

import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.network.handler.StringRequestHandler;
import sse.bupt.cn.translator.util.MessageFactory;

public class SendChineseHandler implements StringRequestHandler {
    private static final String TAG = "SendChineseHandler";

    private Handler handler;

    public SendChineseHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(String response) {
        if (response.toLowerCase().equals("true")) {
            Message message = MessageFactory.getMessage(MessageType.SEND_CHINESE_TEXT_AND_SUCCESS);
            handler.sendMessage(message);
        } else if (response.toLowerCase().equals("false")) {
            Message message = MessageFactory.getMessage(MessageType.SEND_CHINESE_TEXT_AND_FAIL);
            handler.sendMessage(message);
        }
    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---" + error.getMessage() + "---");
        Message message = MessageFactory.getMessage(MessageType.SEND_CHINESE_TEXT_AND_INTERNET_ERROR, error.getMessage());
        handler.sendMessage(message);
    }
}
