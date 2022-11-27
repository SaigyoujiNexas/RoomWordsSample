package com.example.roomwordssample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class WordViewAdapter extends ListAdapter<String, WordViewAdapter.WordViewHolder> {
    public WordViewAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class WordDiff extends DiffUtil.ItemCallback<String> {

        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    }

    static class WordViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView ;
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
        }
        void bind(java.lang.String text){
            mTextView.setText(text);
        }
        static WordViewHolder create(ViewGroup parent){
            var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            return new WordViewHolder(view);
        }
    }
}
