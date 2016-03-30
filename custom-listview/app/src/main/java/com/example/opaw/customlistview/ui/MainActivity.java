package com.example.opaw.customlistview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.opaw.customlistview.R;
import com.example.opaw.customlistview.domain.Item;
import com.example.opaw.customlistview.ui.adapter.ListItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        ListView lvItem = (ListView) findViewById(R.id.lv_item);
        lvItem.setAdapter(new ListItemAdapter(getApplicationContext(), items));

    }
}
