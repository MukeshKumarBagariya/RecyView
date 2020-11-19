package com.example.recyview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CardAdapter.OnItemClickListener {
    private RecyclerView mrecyclerView;
    private RecyclerView.Adapter madapter;
    private CardAdapter cardAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecyclerView = findViewById(R.id.recyclerView);
        Button submit = findViewById(R.id.submit);
        final TextView data = findViewById(R.id.text);
        Button addmore = findViewById(R.id.addmore);
        items = populateList();
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(new Item());
                cardAdapter = new CardAdapter(MainActivity.this, items);
                mrecyclerView.setAdapter(cardAdapter);
                cardAdapter.setOnItemClickListener(MainActivity.this);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < CardAdapter.mItems.size(); i++){
                    Toast.makeText(MainActivity.this, ""+CardAdapter.mItems.get(i).getPrice(), Toast.LENGTH_LONG).show();
                    data.append(CardAdapter.mItems.get(i).getPrice());
                }
            }
        });

    }

    @Override
    public void onUpdateClick(int position, String pri, String wei, String dis) {

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(MainActivity.this, "Yaya", Toast.LENGTH_LONG).show();
    }
    private ArrayList<Item> populateList(){

        ArrayList<Item> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            Item editModel = new Item();
            editModel.setPrice(String.valueOf(i));
            list.add(editModel);
        }

        return list;
    }
}