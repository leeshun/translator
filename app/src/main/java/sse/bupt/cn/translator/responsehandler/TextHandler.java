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
import sse.bupt.cn.translator.model.Text;
import sse.bupt.cn.translator.network.handler.StringRequestHandler;
import sse.bupt.cn.translator.util.MessageFactory;

public class TextHandler implements StringRequestHandler {
    private static final String TAG = "TextHandler";

    private Handler handler;

    public TextHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onSuccess(String response) {
        JSONObject root;
        try {
            root = new JSONObject(response);
        } catch (JSONException e) {
            Log.i(TAG, "---json parse error---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_RESPONSE_DO_NOT_CONTAIN_JSON_OBJECT, e.getMessage());
            handler.sendMessage(message);
            return;
        }

        try {
            JSONArray values = root.getJSONArray("text");
            List<Text> texts = new ArrayList<>();
            int size = values.length();
            JSONObject object;
            for (int index = 0; index < size; index++) {
                object = values.getJSONObject(index);
                Text text = new Text();
                text.setParaId(Integer.parseInt(object.getString("id")));
                text.setPictureUrl(object.getString("url"));
                text.setEnglishText(object.getString("english"));
                text.setChineseText(object.getString("chinese"));
                texts.add(text);
            }
            Log.i(TAG, "---read text success from internet " + texts.size() + "---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_RESPONSE_SUCCESS);
            message.obj = texts;
            handler.sendMessage(message);
        } catch (JSONException e) {
            Log.i(TAG, "---json don't contain menu key---");
            Message message = MessageFactory.getMessage(MessageType.TEXT_RESPONSE_PARSE_ERROR, e.getMessage());
            handler.sendMessage(message);
            return;
        }
    }

    @Override
    public void onFail(VolleyError error) {
        Message message = MessageFactory.getMessage(MessageType.TEXT_REQUEST_INTERNET_ERROR, error.getMessage());
        handler.sendMessage(message);
    }
}
