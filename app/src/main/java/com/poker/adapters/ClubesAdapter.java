package com.poker.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.poker.Mesas;
import com.poker.R;
import com.poker.pojos.ClsClub;

import java.util.List;

public class ClubesAdapter extends RecyclerView.Adapter<ClubesAdapter.ViewHolder>{
    private Context context;
    private List<ClsClub> clubes;
    public static String id_clubes = "";

    public ClubesAdapter(Context context, List<ClsClub> clubes) {
        this.context = context;
        this.clubes = clubes;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_clubes, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ClsClub clsClub = clubes.get(position);
        holder.nombreClub.setText(clsClub.getNombre());
        holder.tvClub.setText(clsClub.getId_club());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.cardView.getContext(), Mesas.class);
                intent.putExtra("id_club", holder.tvClub.getText().toString());
                intent.putExtra("nombre_club", holder.nombreClub.getText().toString());
                id_clubes = holder.tvClub.getText().toString();
                holder.cardView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (clubes != null) {
            return clubes.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nombreClub, tvClub;

        public ViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview_clubes);
            nombreClub = (TextView) itemView.findViewById(R.id.tvNombreClubClubes);
            tvClub = (TextView) itemView.findViewById(R.id.tvIdClubClubes);
        }
    }
}
