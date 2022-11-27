package com.example.roomwordssample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class worldviewmodel extends AndroidViewModel {
    private WordRepository mRepository;

    private final LiveData<List<world>> mAllWords;
    public worldviewmodel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllworlds();
    }
    LiveData<List<String>> getAllWords() {
        return Transformations.map(mAllWords, (items -> {
            return items.stream()
                    .map(item -> item.worlds)
                    .collect(Collectors.toList());
        }));
    }

    public void insert(String text) {
        var word = new world();
        word.worlds = text;
        mRepository.insert(word);
    }
}
