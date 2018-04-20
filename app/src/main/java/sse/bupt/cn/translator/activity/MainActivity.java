package sse.bupt.cn.translator.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sse.bupt.cn.translator.R;
import sse.bupt.cn.translator.adapter.MenuTextAdapter;
import sse.bupt.cn.translator.constants.MessageType;
import sse.bupt.cn.translator.constants.UrlConstant;
import sse.bupt.cn.translator.model.MenuItem;
import sse.bupt.cn.translator.network.StringRequestWrapper;
import sse.bupt.cn.translator.responsehandler.MenuItemHandler;
import sse.bupt.cn.translator.util.MenuPreferenceReader;
import sse.bupt.cn.translator.util.MenuPreferenceWriter;
import sse.bupt.cn.translator.util.TextInfoHolder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Handler handler;

    private StringRequestWrapper requestHandler;

    private MenuItemHandler itemHandler;

    private MenuPreferenceReader preferenceReader;

    private List<MenuItem> items;

    private MenuTextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();
    }

    private void initialize() {
        setContentView(R.layout.activity_main);

        initializeHandler();

        items = new ArrayList<>();

        initializeListView();

        itemHandler = new MenuItemHandler(handler);

        preferenceReader = new MenuPreferenceReader(this);

        List<MenuItem> preferenceItems = preferenceReader.getItems();

        //get items from the internet
        if (preferenceItems == null || preferenceItems.isEmpty()) {
            requestHandler = new StringRequestWrapper(this);
            requestHandler.sendGetRequest(UrlConstant.GETMENUS, itemHandler);
        } else {
            //clear previous items
            int size = items.size();
            for (int index = 0; index < size; index++) {
                items.remove(index);
            }
            items.addAll(preferenceItems);
            adapter.notifyDataSetChanged();
        }
    }

    private void initializeListView() {
        ListView menuView = findViewById(R.id.menu_list_view);
        initializeAdapter();
        menuView.setAdapter(adapter);
    }


    private void initializeAdapter() {
        adapter = new MenuTextAdapter(items, this);
    }


    private void initializeHandler() {
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int messageType = msg.what;
                switch (messageType) {
                    case MessageType.MENU_RESPONSE_SUCCESS:
                        List<MenuItem> tmpItems = (List<MenuItem>) msg.obj;
                        //clear previous list
                        for (int index = 0; index < items.size(); index++) {
                            items.remove(index);
                        }

                        items.addAll(tmpItems);
                        adapter.notifyDataSetChanged();
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
            }
        };
    }


    @Override
    protected void onResume() {
        Log.i(TAG, "--- resume ----");
        super.onResume();
        Log.i(TAG, "---start receive information---");
        int position = TextInfoHolder.getIndex();
        items.get(position).setLastViewPages(TextInfoHolder.getLastViewPages());
        items.get(position).setLastViewTime(TextInfoHolder.getLastViewTime());
        Log.i(TAG, "---end receive information---");
        Collections.sort(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "--- destroy ---");
        MenuPreferenceWriter writer = new MenuPreferenceWriter(this, items);
        writer.deletePreference();
        /*try {
            writer.work();
        } catch (JSONException e) {
            Log.i(TAG, "---destory cannot write into shared preference with json format---q");
        }*/
    }
}
