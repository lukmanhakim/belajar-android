package com.example.opaw.crudlistview.ui;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.opaw.crudlistview.R;
import com.example.opaw.crudlistview.domain.Item;
import com.example.opaw.crudlistview.ui.adapter.ListItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvItem;
    ListItemAdapter adapter;

    AlertDialog.Builder addNewItemDialogBuilder = null;
    AlertDialog addNewItemDialog = null;
    View promptsView;
    TextInputLayout lblTitle, lblSubtitle;
    EditText txtTitle, txtSubtitle;
    List<Item> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItem = (ListView) findViewById(R.id.lv_item);
        Button addNewItem = (Button) findViewById(R.id.add);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
    }

    public void showAddDialog(){
        if(addNewItemDialogBuilder == null) {
            addNewItemDialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.DialogStyle);
        }

        promptsView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add_new_item, null);

        lblTitle = (TextInputLayout) promptsView.findViewById(R.id.lbl_title);
        lblSubtitle = (TextInputLayout) promptsView.findViewById(R.id.lbl_subtitle);

        txtTitle = (EditText) promptsView.findViewById(R.id.txt_title);
        txtSubtitle = (EditText) promptsView.findViewById(R.id.txt_subtitle);

        Button save = (Button) promptsView.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hasError()){
                    Item item = new Item();
                    item.setTitle(txtTitle.getText().toString());
                    item.setSubtitle(txtSubtitle.getText().toString());

                    if(adapter == null){
                        items.add(item);
                        adapter = new ListItemAdapter(MainActivity.this, items);
                        lvItem.setAdapter(adapter);
                    } else {
                        adapter.addItem(item);
                    }
                    addNewItemDialog.dismiss();
                }
            }
        });

        Button cancel = (Button) promptsView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewItemDialog.dismiss();
            }
        });

        addNewItemDialogBuilder.setView(promptsView);
        addNewItemDialogBuilder.setCancelable(false);
        addNewItemDialog = addNewItemDialogBuilder.create();
        addNewItemDialog.show();
    }

    public void showEditDialog(final int position, Item item){
        if(addNewItemDialogBuilder == null) {
            addNewItemDialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.DialogStyle);
        }

        promptsView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add_new_item, null);

        lblTitle = (TextInputLayout) promptsView.findViewById(R.id.lbl_title);
        lblSubtitle = (TextInputLayout) promptsView.findViewById(R.id.lbl_subtitle);

        txtTitle = (EditText) promptsView.findViewById(R.id.txt_title);
        txtTitle.setText(item.getTitle());
        txtSubtitle = (EditText) promptsView.findViewById(R.id.txt_subtitle);
        txtSubtitle.setText(item.getSubtitle());

        Button save = (Button) promptsView.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hasError()){
                    Item newItem = new Item();
                    newItem.setTitle(txtTitle.getText().toString());
                    newItem.setSubtitle(txtSubtitle.getText().toString());
                    adapter.editItem(position, newItem);
                    addNewItemDialog.dismiss();
                }
            }
        });

        Button cancel = (Button) promptsView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewItemDialog.dismiss();
            }
        });

        addNewItemDialogBuilder.setView(promptsView);
        addNewItemDialogBuilder.setCancelable(false);
        addNewItemDialog = addNewItemDialogBuilder.create();
        addNewItemDialog.show();
    }

    private boolean hasError(){
        boolean isError = false;
        if(TextUtils.isEmpty(txtTitle.getText().toString())){
            isError = true;
            lblTitle.setError("This field is required");
        }
        if(TextUtils.isEmpty(txtSubtitle.getText().toString())){
            isError = true;
            lblSubtitle.setError("This field is required");
        }
        return isError;
    }
}
