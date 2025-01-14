package com.ranbiraeir.ques3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Sort sort;
    private boolean isBound = false;
    private ArrayList<Integer> integerList;
    private ArrayAdapter<Integer> integerArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        integerList = new ArrayList<>();
        integerList.add(6);
        integerList.add(45);
        integerList.add(78);
        integerList.add(2);
        integerList.add(470);
        integerList.add(423);
        integerList.add(169);
        integerList.add(420);
        integerList.add(380);

        integerArrayAdapter = new ArrayAdapter<>(this, R.layout.r_int, integerList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(integerArrayAdapter);

        Intent intent = new Intent(this, Sort.class);
        intent.putIntegerArrayListExtra("data", integerList);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void sortNumbers(View view) {
        integerList = sort.sortNumbers();
        integerArrayAdapter = new ArrayAdapter<>(this, R.layout.r_int, integerList);
        listView.setAdapter(integerArrayAdapter);
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Sort.LocalBinder binder = (Sort.LocalBinder) service;
            sort = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

}