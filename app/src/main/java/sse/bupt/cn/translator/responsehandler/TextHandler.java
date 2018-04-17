package sse.bupt.cn.translator.responsehandler;

import android.os.Handler;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sse.bupt.cn.translator.model.Text;
import sse.bupt.cn.translator.network.handler.StringRequestHandler;

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
            //TODO(leeshun) add json parse error message
            return;
        }

        try {
            JSONArray values = root.getJSONArray("text");
            List<Text> texts = new ArrayList<>();
            int size = values.length();
            JSONObject object;
            for (int index = 0;index < size;index++) {
                object = values.getJSONObject(index);
                Text text = new Text();
                text.setParaId(Integer.parseInt(object.getString("id")));
                text.setPictureUrl(object.getString("url"));
                text.setEnglishText(object.getString("english"));
                text.setChineseText(object.getString("chinese"));
                texts.add(text);
            }
            //TODO(leeshun) dispatch this list to main activity
        } catch (JSONException e) {
            Log.i(TAG, "---json don't contain menu key---");
            //TODO(leeshun) add json don't contain menu key
            return;
        }
    }

    @Override
    public void onFail(VolleyError error) {

    }
}
