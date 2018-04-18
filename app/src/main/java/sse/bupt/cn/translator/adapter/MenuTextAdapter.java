package sse.bupt.cn.translator.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sse.bupt.cn.translator.model.MenuItem;

public class MenuTextAdapter extends BaseAdapter {
    private static final String TAG = "MenuTextAdapter";

    private List<MenuItem> items;

    private Context context;

    private LayoutInflater inflater;

    public MenuTextAdapter(List<MenuItem> items, Context context) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        Log.i(TAG, "---count is " + items.size() + "---");
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "---get item is " + items.get(position).toString() + "---");
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, "---position is " + position + "---");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItemHolder holder = null;
        if (convertView == null) {

        } else {
            holder = (MenuItemHolder) convertView.getTag();
        }
        return convertView;
    }

    private class MenuItemHolder {
        ImageView imageView;
        TextView textView;
    }
}
