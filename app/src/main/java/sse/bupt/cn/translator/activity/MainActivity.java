package sse.bupt.cn.translator.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import sse.bupt.cn.translator.R;
import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.constants.UrlConstant;
import sse.bupt.cn.translator.model.MenuItem;
import sse.bupt.cn.translator.network.StringRequestWrapper;
import sse.bupt.cn.translator.responsehandler.MenuItemHandler;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Handler handler;

    private StringRequestWrapper requestHandler;

    private MenuItemHandler itemHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeHandler();
        itemHandler = new MenuItemHandler(handler);
        requestHandler = new StringRequestWrapper(this);
        requestHandler.sendGetRequest(UrlConstant.GETMENUS, itemHandler);
    }


    private void initializeHandler() {
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int messageType = msg.what;
                switch (messageType) {
                    case MessageType.MENU_RESPONSE_SUCCESS:
                        List<MenuItem> items = (List<MenuItem>) msg.obj;
                        for (MenuItem item : items) {
                            Log.i(TAG, "---" + item.toString() + "---");
                        }
                        break;
                    case MessageType.MENU_RESPONSE_DO_NOT_CONTAIN_JSON_OBJECT:
                        Log.i(TAG, "menu response do not contain json object");
                        break;
                    case MessageType.MENU_RESPONSE_PARSE_ERROR:
                        Log.i(TAG, "menu response parse error");
                        break;
                    case MessageType.MENU_REQUEST_INTERNET_ERROR:
                        Log.i(TAG, "menu request internet error");
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

}
