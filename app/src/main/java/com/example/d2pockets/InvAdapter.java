package com.example.d2pockets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InvAdapter extends RecyclerView.Adapter<InvAdapter.InvViewHolder> {

    Context context;
    List<String> weaponNames;

    public InvAdapter(Context ct, List<String> wNames) {
        context = ct;
        weaponNames = wNames;
    }

    @NonNull
    @Override
    public InvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_layout, parent, false);
        return new InvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvViewHolder holder, int position) {
        holder.tv.setText(weaponNames.get(position));
    }

    @Override
    public int getItemCount() {
        return weaponNames.size();
    }

    public class InvViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public InvViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.weapon_name);
        }
    }
}
