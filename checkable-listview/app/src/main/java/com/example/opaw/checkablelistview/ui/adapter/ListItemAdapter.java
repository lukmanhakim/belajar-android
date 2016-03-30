package com.example.opaw.checkablelistview.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opaw.checkablelistview.R;
import com.example.opaw.checkablelistview.domain.Item;

import java.util.List;

/**
 * Created by opaw on 3/30/16.
 */
public class ListItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;

    public ListItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);

        final Item item = items.get(position);

        TextView mTitle = (TextView) convertView.findViewById(R.id.list_item_title);
        TextView mSubtitle = (TextView) convertView.findViewById(R.id.list_item_subtitle);
        Button btnAction1 = (Button) convertView.findViewById(R.id.btn_action_1);
        Button btnAction2 = (Button) convertView.findViewById(R.id.btn_action_2);

        mTitle.setText(item.getTitle());
        mSubtitle.setText(item.getSubtitle());

        btnAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        btnAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getSubtitle(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public void unselectAllItems(){
        for (int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            item.setSelected(false);
        }
    }
}
