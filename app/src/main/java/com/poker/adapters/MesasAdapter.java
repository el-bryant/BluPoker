package com.poker.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poker.CrearMesa;
import com.poker.Juego3;
import com.poker.Juego6;
import com.poker.Juego9;
import com.poker.Modalidad;
import com.poker.R;
import com.poker.pojos.ClsMesa;

import java.util.List;

public class MesasAdapter  extends RecyclerView.Adapter<MesasAdapter.ViewHolder> {
    private Context context;
    private List<ClsMesa> mesas;

    public MesasAdapter(Context context, List<ClsMesa> mesas) {
        this.context = context;
        this.mesas = mesas;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_mesa, parent,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ClsMesa clsMesa = mesas.get(position);
        String entrada_modalidad = clsMesa.getEntrada_minima() + clsMesa.getModalidad();
        String ciega = clsMesa.getCiegaMinima() + clsMesa.getCiegaMaxima();
        String usuarios_activos_cantidad = clsMesa.getUsuariosActivos() + clsMesa.getCantidadUsuarios();
        if (entrada_modalidad != "" && ciega != "" && usuarios_activos_cantidad != "") {
            holder.tvIdMesaCard_mesa.setText(clsMesa.getId_mesa());
            holder.tvEntradaModalidadCard_mesa.setText(clsMesa.getEntrada_minima() + " - " + clsMesa.getModalidad());
            holder.tvCiegaCard_mesa.setText("Ciegas: " + clsMesa.getCiegaMinima() + " - " + clsMesa.getCiegaMaxima());
            holder.tvUsuariosActivosCantidadCard_mesa.setText("Usuarios: " + clsMesa.getUsuariosActivos() + " / " + clsMesa.getCantidadUsuarios());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int largo = holder.tvUsuariosActivosCantidadCard_mesa.getText().length();
                        String cantidad = holder.tvUsuariosActivosCantidadCard_mesa.getText().toString().substring(largo - 1, largo);
                        String codigo_mesa = holder.tvIdMesaCard_mesa.getText().toString();
                        Intent intent;
                        switch (cantidad) {
                            case "3":
                                intent = new Intent(holder.cardView.getContext(), Juego3.class);
                                intent.putExtra("id_mesa_card", codigo_mesa);
                                intent.putExtra("ciega_minima", clsMesa.getCiegaMinima());
                                intent.putExtra("ciega_maxima", clsMesa.getCiegaMaxima());
                                holder.cardView.getContext().startActivity(intent);
                                break;
                            case "6":
                                intent = new Intent(holder.cardView.getContext(), Juego6.class);
                                intent.putExtra("id_mesa_card", codigo_mesa);
                                intent.putExtra("ciega_minima", clsMesa.getCiegaMinima());
                                intent.putExtra("ciega_maxima", clsMesa.getCiegaMaxima());
                                holder.cardView.getContext().startActivity(intent);
                                break;
                            case "9":
                                intent = new Intent(holder.cardView.getContext(), Juego9.class);
                                intent.putExtra("id_mesa_card", codigo_mesa);
                                intent.putExtra("ciega_minima", clsMesa.getCiegaMinima());
                                intent.putExtra("ciega_maxima", clsMesa.getCiegaMaxima());
                                holder.cardView.getContext().startActivity(intent);
                                break;
                        }
                    }
                });
        } else {
            holder.tvEntradaModalidadCard_mesa.setText("");
            holder.tvCiegaCard_mesa.setText("+");
            holder.tvCiegaCard_mesa.setTextSize(50);
            holder.tvUsuariosActivosCantidadCard_mesa.setText("");
            holder.tvUsuariosActivosCantidadCard_mesa.setVisibility(View.GONE);

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
        if (mesas != null) {
            return mesas.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvEntradaModalidadCard_mesa, tvCiegaCard_mesa, tvUsuariosActivosCantidadCard_mesa, tvIdMesaCard_mesa;

        public ViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview_mesas);
            tvEntradaModalidadCard_mesa = (TextView) itemView.findViewById(R.id.tvEntradaModalidadCard_mesa);
            tvCiegaCard_mesa = (TextView) itemView.findViewById(R.id.tvCiegaCard_mesa);
            tvUsuariosActivosCantidadCard_mesa = (TextView) itemView.findViewById(R.id.tvUsuariosActivosCantidadCard_mesa);
            tvIdMesaCard_mesa = (TextView) itemView.findViewById(R.id.tvIdMesaCard_mesa);
        }
    }
}
