package com.poker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import static com.poker.Acceder.fichas;
import static com.poker.Acceder.id_usuario;
import static com.poker.Acceder.nom_usuario;
import static com.poker.Principal.foto_de_perfil;

public class PerfilFragment extends Fragment {
    TextView tvIdUsuario, tvNombreUsuario, tvCantidadFichas;
    ImageView imvPerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        if (v != null) {
            tvIdUsuario = (TextView) v.findViewById(R.id.tvIdUsuarioPerfil);
            String recup_idusuario = id_usuario;
            DecimalFormat decimalFormat = new DecimalFormat("000000000");
            tvIdUsuario.setText("Usuario ID: " + decimalFormat.format(Integer.parseInt(recup_idusuario)));

            tvNombreUsuario = (TextView) v.findViewById(R.id.tvNombreUsuarioPerfil);
            String nombre = nom_usuario;
            tvNombreUsuario.setText(nombre);

            imvPerfil = (ImageView) v.findViewById(R.id.imvUsuarioPerfil);
            String imagen = foto_de_perfil;
            switch (imagen) {
                case "pjh01.png":
                    imvPerfil.setImageResource(R.drawable.pjh01);
                    break;
                case "pjh02.png":
                    imvPerfil.setImageResource(R.drawable.pjh02);
                    break;
                case "pjh03.png":
                    imvPerfil.setImageResource(R.drawable.pjh03);
                    break;
                case "pjh04.png":
                    imvPerfil.setImageResource(R.drawable.pjh04);
                    break;
                case "pjh05.png":
                    imvPerfil.setImageResource(R.drawable.pjh05);
                    break;
                case "pjh06.png":
                    imvPerfil.setImageResource(R.drawable.pjh06);
                    break;
                case "pjh07.png":
                    imvPerfil.setImageResource(R.drawable.pjh07);
                    break;
                case "pjh08.png":
                    imvPerfil.setImageResource(R.drawable.pjh08);
                    break;
                case "pjh09.png":
                    imvPerfil.setImageResource(R.drawable.pjh09);
                    break;
                case "pjh10.png":
                    imvPerfil.setImageResource(R.drawable.pjh10);
                    break;
                case "pjm01.png":
                    imvPerfil.setImageResource(R.drawable.pjm01);
                    break;
                case "pjm02.png":
                    imvPerfil.setImageResource(R.drawable.pjm02);
                    break;
                case "pjm03.png":
                    imvPerfil.setImageResource(R.drawable.pjm03);
                    break;
                case "pjm04.png":
                    imvPerfil.setImageResource(R.drawable.pjm04);
                    break;
                case "pjm05.png":
                    imvPerfil.setImageResource(R.drawable.pjm05);
                    break;
                case "pjm06.png":
                    imvPerfil.setImageResource(R.drawable.pjm06);
                    break;
                default:
                    imvPerfil.setImageResource(0);
                    break;
            }

            tvCantidadFichas = (TextView) v.findViewById(R.id.tvCantidad_fichasPerfil);
            String cantidad_fichas = fichas;
            tvCantidadFichas.setText("Cantidad de fichas: " + cantidad_fichas);
        }

        return v;
    }
}
