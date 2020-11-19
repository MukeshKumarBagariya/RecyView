package com.example.recyview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardAdapterViewHolder> {
    public static ArrayList<Item> mItems;
    private LayoutInflater inflater;
    private static CardAdapter.OnItemClickListener mclickListener;

    public interface OnItemClickListener{
        void onUpdateClick(int position, String pri, String wei, String dis);
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mclickListener = listener;
    }
    @NonNull
    @Override
    public CardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card, parent, false);
        CardAdapter.CardAdapterViewHolder viewHolder = new CardAdapter.CardAdapterViewHolder(v);
        return viewHolder;
    }
    public CardAdapter(Context ctx, ArrayList<Item> items){
        inflater = LayoutInflater.from(ctx);
        this.mItems = items;
    }
    @Override
    public void onBindViewHolder(@NonNull CardAdapterViewHolder holder, int position) {
        holder.price.setText(mItems.get(position).getPrice());
        Log.d("print","yes");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class CardAdapterViewHolder extends RecyclerView.ViewHolder {

        private EditText price;
        private EditText weight;
        private EditText discount;
        private Button update;
        private String sprice, swe, sdis;



        public CardAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            weight = itemView.findViewById(R.id.weight);
            discount = itemView.findViewById(R.id.discount);
            price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mItems.get(getAdapterPosition()).setPrice(price.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mclickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mclickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}