package com.example.roomwordssample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<stringword>mList = new ArrayList<>();
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
                        var word = new world();
                        assert result.getData() != null;
                        word.worlds = result.getData().getStringExtra(MainActivity2.EXTRA_REPLY);
                        mWorldviewmodel.insert(word);
                    }
                }));
        WordViewHolder adapter = new WordViewHolder(mList);
        mWorldviewmodel = new ViewModelProvider(this).get(worldviewmodel.class);
        mWorldviewmodel.getAllWords().observe(this, new Observer<List<world>>() {
            @Override
            public void onChanged(List<world> worlds) {
                for(int i = 0;i<worlds.size();i++) {
                    stringword stringword = new stringword();
                    String a = worlds.get(i).worlds;
                    stringword.word = a;
                    Log.d("nihao",stringword.word);
                    mList.add(stringword);
                    adapter.notifyDataSetChanged();
                }
            }
        });

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