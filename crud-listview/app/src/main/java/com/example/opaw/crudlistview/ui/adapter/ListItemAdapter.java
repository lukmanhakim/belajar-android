package com.example.opaw.crudlistview.ui.adapter;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.opaw.crudlistview.R;
import com.example.opaw.crudlistview.domain.Item;
import com.example.opaw.crudlistview.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by opaw on 3/30/16.
 */
public class ListItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;


    AlertDialog.Builder addNewItemDialogBuilder = null;
    AlertDialog addNewItemDialog = null;
    View promptsView;
    TextInputLayout lblTitle, lblSubtitle;
    EditText txtTitle, txtSubtitle;

    public ListItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        if(items == null)
            return -1;
        else
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);

        final Item item = items.get(position);

        TextView title = (TextView) convertView.findViewById(R.id.list_item_title);
        TextView subtitle = (TextView) convertView.findViewById(R.id.list_item_subtitle);

        title.setText(item.getTitle());
        subtitle.setText(item.getSubtitle());

        Button delete = (Button) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });

        Button edit = (Button) convertView.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showEditDialog(position, item);
            }
        });

        return convertView;
    }

    public void addItem(Item item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        items.remove(position);
        notifyDataSetChanged();
    }

    public void editItem(int position, Item item){
        items.set(position, item);
        notifyDataSetChanged();
    }

}
