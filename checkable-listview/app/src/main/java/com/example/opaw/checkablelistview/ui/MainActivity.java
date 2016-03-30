package com.example.opaw.checkablelistview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.opaw.checkablelistview.R;
import com.example.opaw.checkablelistview.domain.Item;
import com.example.opaw.checkablelistview.ui.adapter.ListItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvItem;
    ListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = new ArrayList<>();

        Item item1 = new Item();
        item1.setTitle("Banana");
        item1.setSubtitle("The banana is an edible fruit, botanically a berry, produced by several kinds of large herbaceous flowering plants in the genus Musa.");

        Item item2 = new Item();
        item2.setTitle("Apple");
        item2.setSubtitle("The apple tree (Malus domestica) is a deciduous tree in the rose family best known for its sweet, pomaceous fruit, the apple. It is cultivated worldwide as a fruit tree, and is the most widely grown species in the genus Malus.");

        Item item3 = new Item();
        item3.setTitle("Grape");
        item3.setSubtitle("A grape is a fruiting berry of the deciduous woody vines of the botanical genus Vitis.");

        items.add(item1);
        items.add(item2);
        items.add(item3);

        lvItem = (ListView) findViewById(R.id.lv_item);
        adapter = new ListItemAdapter(getApplicationContext(), items);

        lvItem.setAdapter(adapter);
        lvItem.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Item item = (Item) lvItem.getAdapter().getItem(position);
                item.setSelected(checked);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_list_item, menu);
                mode.setTitle("Select Items");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_submit:
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < lvItem.getAdapter().getCount(); i++){
                            Item x = (Item) lvItem.getAdapter().getItem(i);
                            if(x.isSelected()){
                                sb.append(x.getTitle());
                                sb.append(", ");
                            }
                        }
                        String text = sb.toString();
                        text = text.substring(0, text.length() - 2);
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                        mode.finish();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Clicked " + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                adapter.unselectAllItems();
            }
        });


    }
}
