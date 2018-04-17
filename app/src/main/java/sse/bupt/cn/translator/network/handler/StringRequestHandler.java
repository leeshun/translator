package sse.bupt.cn.translator.network.handler;

import com.android.volley.VolleyError;

public interface StringRequestHandler {
    void onSuccess(String response);

    void onFail(VolleyError error);
}
