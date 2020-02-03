package com.example.mydaser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SaveActivity extends AppCompatActivity {
    private  TextView saveTextView;
    private RecyclerView recyclerView;
    private List<Timer> listTimer=new ArrayList<>();
    private SaveAdabter adabter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        saveTextView=findViewById(R.id.saveTextView);
        recyclerView=findViewById(R.id.recyclerView);
        Intent intent =getIntent();
        String name= intent.getStringExtra("name");
//        saveTextView.setText(name);
        listTimer.add(new Timer(name));
        adabter=new SaveAdabter(listTimer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adabter);
        adabter.setOnTimerClickLisner(new SaveAdabter.OnTimerClickLisner() {
            @Override
            public void onLongTimerClick(int position) {
                delet(position);
            }
            @Override
            public void onTimerClick(int position) {
                Toast.makeText(SaveActivity.this, getString(R.string.info)+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void delet(int position){
        listTimer.remove(position);
        adabter.notifyDataSetChanged();
    }
}

