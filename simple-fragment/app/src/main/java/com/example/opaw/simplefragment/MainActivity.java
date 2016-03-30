package com.example.opaw.simplefragment;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.opaw.simplefragment.fragment.ExampleFragment;
import com.example.opaw.simplefragment.fragment.ExampleFragment2;


public class MainActivity extends AppCompatActivity implements
        ExampleFragment.OnFragmentInteractionListener,
        ExampleFragment2.OnFragmentInteractionListener {

    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout) findViewById(R.id.container);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new ExampleFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
