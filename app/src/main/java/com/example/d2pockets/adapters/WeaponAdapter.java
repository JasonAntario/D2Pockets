package com.example.d2pockets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d2pockets.R;

import java.util.List;

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.InvViewHolder> {

    Context context;
    List<String> weaponNames;

    public WeaponAdapter(Context ct, List<String> wNames) {
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
        holder.tvItemName.setText(weaponNames.get(position));
    }

    @Override
    public int getItemCount() {
        return weaponNames.size();
    }

    public class InvViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName;

        public InvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.weapon_name);
        }
    }
}
