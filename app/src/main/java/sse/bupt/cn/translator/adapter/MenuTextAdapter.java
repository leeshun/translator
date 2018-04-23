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

import sse.bupt.cn.translator.R;
import sse.bupt.cn.translator.model.MenuItem;

public class MenuTextAdapter extends BaseAdapter {
    private static final String TAG = "MenuTextAdapter";

    private List<MenuItem> items;

    private LayoutInflater inflater;

    public MenuTextAdapter(List<MenuItem> items, Context context) {
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItemHolder holder;
        if (convertView == null) {
            holder = new MenuItemHolder();
            convertView = inflater.inflate(R.layout.menu_items, parent, false);

            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.textView = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (MenuItemHolder) convertView.getTag();
        }

        holder.textView.setText(items.get(position).getMenuName());

        return convertView;
    }

    private class MenuItemHolder {
        ImageView imageView;
        TextView textView;
    }
}
