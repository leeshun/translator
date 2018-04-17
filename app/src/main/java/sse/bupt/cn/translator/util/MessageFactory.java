package sse.bupt.cn.translator.util;

import android.os.Bundle;
import android.os.Message;

import java.util.Iterator;
import java.util.Map;

public class MessageFactory {

    public static Message getMessage(int messageType) {
        Message message = new Message();
        message.what = messageType;
        return message;
    }

    public static Message getMessage(int messageType, Map<String, String> map) {
        Message message = new Message();
        message.what = messageType;
        Bundle bundle = new Bundle();
        Iterator<String> itr = map.keySet().iterator();
        String key;
        while (itr.hasNext()) {
            key = itr.next();
            bundle.putString(key, map.get(key));
        }
        message.setData(bundle);
        return message;
    }

    public static Message getMessage(int messageType, String value) {
        Message message = new Message();
        message.what = messageType;
        message.obj = value;
        return message;
    }

}
