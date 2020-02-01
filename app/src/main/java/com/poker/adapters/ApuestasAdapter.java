package com.poker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.poker.R;
import com.poker.pojos.ClsApuesta;

import java.util.List;

public class ApuestasAdapter extends RecyclerView.Adapter<ApuestasAdapter.ViewHolder>{
    Context context;
    List<ClsApuesta> clsApuestas;

    public ApuestasAdapter(Context context, List<ClsApuesta> clsApuestas) {
        this.context = context;
        this.clsApuestas = clsApuestas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_apuestas, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvId_usuario_mesa.setText(clsApuestas.get(position).getId_usuario_mesa());
        holder.tvCantidad_fichas.setText(clsApuestas.get(position).getCantidad_fichas());
        holder.tvRonda.setText(clsApuestas.get(position).getRonda());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvId_usuario_mesa, tvCantidad_fichas, tvRonda;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview_apuestas);
            tvId_usuario_mesa = (TextView) itemView.findViewById(R.id.tvId_usuario_mesa);
            tvCantidad_fichas = (TextView) itemView.findViewById(R.id.tvCantidad_fichas);
            tvRonda = (TextView) itemView.findViewById(R.id.tvRonda);
        }
    }
}
