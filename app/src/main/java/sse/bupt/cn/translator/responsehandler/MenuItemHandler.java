package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.VolleyError;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.model.MenuItem;
import sse.bupt.cn.translator.network.handler.StringRequestHandler;
import sse.bupt.cn.translator.util.MessageFactory;

public class MenuItemHandler implements StringRequestHandler {
    private static final String TAG = "MenuItemHandler";

    private Handler handler;

    public MenuItemHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(String response) {
        JSONObject root;
        try {
            root = new JSONObject(response);
        } catch (JSONException e) {
            Log.i(TAG, "---json parse error---");
            Message message = MessageFactory.getMessage(MessageType.MENU_RESPONSE_DO_NOT_CONTAIN_JSON_OBJECT, e.getMessage());
            handler.sendMessage(message);
            return;
        }
        try {
            JSONArray values = root.getJSONArray("menu");
            List<MenuItem> items = new ArrayList<>();
            int size = values.length();
            for (int index = 0; index < size; index++) {
                MenuItem item = new MenuItem();
                item.setMenuName(values.getString(index));
                items.add(item);
            }
            Message message = MessageFactory.getMessage(MessageType.MENU_RESPONSE_SUCCESS);
            message.obj = items;
            handler.sendMessage(message);
        } catch (JSONException e) {
            Log.i(TAG, "---json don't contain menu key---");
            Message message = MessageFactory.getMessage(MessageType.MENU_RESPONSE_PARSE_ERROR);
            handler.sendMessage(message);
            return;
        }
    }

    @Override
    public void onFail(VolleyError error) {
        Log.i(TAG, "---" + error.getMessage() + "---");
        Message message = MessageFactory.getMessage(MessageType.MENU_REQUEST_INTERNET_ERROR, error.getMessage());
        handler.sendMessage(message);
    }
}
