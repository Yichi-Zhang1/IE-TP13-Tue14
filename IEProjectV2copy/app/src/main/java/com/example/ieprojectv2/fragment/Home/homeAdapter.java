package com.example.ieprojectv2.fragment.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ieprojectv2.R;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.homeViewHolder> {
    private ImageView home_iv;
    Context context;

    public homeAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override

    public homeAdapter.homeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new homeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull homeAdapter.homeViewHolder holder, int position) {
        if(position % 2 == 0){
            Glide.with(context).load("https://cdn.pixabay.com/photo/2015/06/19/21/24/the-road-815297__340.jpg").
                    into(home_iv);
        }
        else{
            Glide.with(context).load("https://images.pexels.com/photos/349732/pexels-photo-349732.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500").
                    into(home_iv);
        }


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class homeViewHolder extends RecyclerView.ViewHolder{

        public homeViewHolder(@NonNull View itemView) {
            super(itemView);
            home_iv = itemView.findViewById(R.id.home_iv);
        }
    }
}
