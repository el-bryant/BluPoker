package com.poker.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poker.Juego3;
import com.poker.Juego6;
import com.poker.Juego9;
import com.poker.Modalidad;
import com.poker.R;
import com.poker.pojos.ClsMesa;
import com.poker.pojos.ClsMesasActivas;

import java.util.List;

public class MesasActivasAdapter extends RecyclerView.Adapter<MesasActivasAdapter.ViewHolder> {
    private Context context;
    private List<ClsMesasActivas> mesasactivas;

    public MesasActivasAdapter(Context context, List<ClsMesasActivas> mesasactivas) {
        this.context = context;
        this.mesasactivas = mesasactivas;
    }

    @NonNull
    @Override
    public MesasActivasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_mesa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MesasActivasAdapter.ViewHolder holder, int position) {
        final ClsMesasActivas clsMesasActivas = mesasactivas.get(position);
        String entrada_modalidad = clsMesasActivas.getEntrada_minima() + clsMesasActivas.getModalidad();
        String ciega = clsMesasActivas.getCiegaMinima() + clsMesasActivas.getCiegaMaxima();
        String usuarios_activos_cantidad = clsMesasActivas.getUsuariosActivos() + clsMesasActivas.getCantidadUsuarios();
        if (entrada_modalidad != "" && ciega != "" && usuarios_activos_cantidad != "") {
            holder.tvIdMesaCard_mesa.setText(clsMesasActivas.getId_mesa());
            holder.tvEntradaModalidadCard_mesa.setText(clsMesasActivas.getEntrada_minima() + " - " + clsMesasActivas.getModalidad());
            holder.tvCiegaCard_mesa.setText("Ciegas: " + clsMesasActivas.getCiegaMinima() + " - " + clsMesasActivas.getCiegaMaxima());
            holder.tvUsuariosActivosCard_mesa.setText("Usuarios: " + clsMesasActivas.getUsuariosActivos() + " / " + clsMesasActivas.getCantidadUsuarios());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int largo = holder.tvUsuariosActivosCard_mesa.getText().length();
                    String cantidad = holder.tvUsuariosActivosCard_mesa.getText().toString().substring(largo - 1, largo);
                    String codigo_mesa = holder.tvIdMesaCard_mesa.getText().toString();
                    Intent intent;
                    switch (cantidad) {
                        case "3":
                            intent = new Intent(holder.cardView.getContext(), Juego3.class);
                            intent.putExtra("id_mesa_card", codigo_mesa);
                            intent.putExtra("ciega_minima", clsMesasActivas.getCiegaMinima());
                            intent.putExtra("ciega_maxima", clsMesasActivas.getCiegaMaxima());
                            holder.cardView.getContext().startActivity(intent);
                            break;
                        case "6":
                            intent = new Intent(holder.cardView.getContext(), Juego6.class);
                            intent.putExtra("id_mesa_card", codigo_mesa);
                            intent.putExtra("ciega_minima", clsMesasActivas.getCiegaMinima());
                            intent.putExtra("ciega_maxima", clsMesasActivas.getCiegaMaxima());
                            holder.cardView.getContext().startActivity(intent);
                            break;
                        case "9":
                            intent = new Intent(holder.cardView.getContext(), Juego9.class);
                            intent.putExtra("id_mesa_card", codigo_mesa);
                            intent.putExtra("ciega_minima", clsMesasActivas.getCiegaMinima());
                            intent.putExtra("ciega_maxima", clsMesasActivas.getCiegaMaxima());
                            holder.cardView.getContext().startActivity(intent);
                            break;
                    }
                }
            });
        } else {
            holder.tvEntradaModalidadCard_mesa.setText("");
            holder.tvCiegaCard_mesa.setText("");
            holder.tvCiegaCard_mesa.setTextSize(50);
            holder.tvUsuariosActivosCard_mesa.setText("");
            holder.tvUsuariosActivosCard_mesa.setVisibility(View.GONE);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.cardView.getContext(), Modalidad.class);
                    holder.cardView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mesasactivas != null) {
            return mesasactivas.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvEntradaModalidadCard_mesa, tvCiegaCard_mesa, tvUsuariosActivosCard_mesa, tvIdMesaCard_mesa;

        public ViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview_mesas);
            tvEntradaModalidadCard_mesa = (TextView) itemView.findViewById(R.id.tvEntradaModalidadCard_mesa);
            tvCiegaCard_mesa = (TextView) itemView.findViewById(R.id.tvCiegaCard_mesa);
            tvUsuariosActivosCard_mesa = (TextView) itemView.findViewById(R.id.tvUsuariosActivosCantidadCard_mesa);
            tvIdMesaCard_mesa = (TextView) itemView.findViewById(R.id.tvIdMesaCard_mesa);
        }
    }
}
