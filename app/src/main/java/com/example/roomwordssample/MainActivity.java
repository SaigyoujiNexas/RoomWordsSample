package com.example.roomwordssample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String>mList = new ArrayList<>();
    private worldviewmodel mWorldviewmodel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launcher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result ->{
                    if(RESULT_OK == result.getResultCode()){
                        var text = result.getData().getStringExtra(MainActivity2.EXTRA_REPLY);
                        mWorldviewmodel.insert(text);
                    }
                }));
        var adapter = new WordViewAdapter(new WordViewAdapter.WordDiff());
        mWorldviewmodel = new ViewModelProvider(this).get(worldviewmodel.class);

        mWorldviewmodel.getAllWords().observe(this, (adapter::submitList));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            Log.d("nihao","q");
            launcher.launch(intent);
        });
    }

}