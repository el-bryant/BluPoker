package com.poker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.poker.adapters.MesasAdapter;
import com.poker.pojos.ClsMesa;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Mesas extends AppCompatActivity {
    RecyclerView recyclerMesas;
    ArrayList<ClsMesa> mesas;
    MesasAdapter mesasAdapter;
    public static TextView tvClub, tvNombreClubMesas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvClub = (TextView) findViewById(R.id.tvIdClubMesas);
        tvNombreClubMesas = (TextView) findViewById(R.id.tvNombreClubMesas);
        String recup_id_club = getIntent().getStringExtra("id_club");
        String recup_nombre_club = getIntent().getStringExtra("nombre_club");
        tvClub.setText(recup_id_club);
        tvNombreClubMesas.setText(recup_nombre_club);

        createData(tvClub.getText().toString());

        recyclerMesas = (RecyclerView) findViewById(R.id.recyclermesas);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerMesas.setLayoutManager(layoutManager);

        tvClub.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.equals("")) {
                    createData(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void createData(final String id_club) {
        Log.i("depurar", "click");
        mesas = new ArrayList<>();
        mesas.add(new ClsMesa("", "","","","","",""));
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarDatosGet(id_club);
                Log.i("depurar", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJson(result);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result);
                                for (int i = 0; i < json.length(); i++) {
                                    mesas.add(new ClsMesa(json.getJSONObject(i).getString("id_mesa"),
                                    json.getJSONObject(i).getString("entrada_minima"),
                                            json.getJSONObject(i).getString("modalidad"),
                                            json.getJSONObject(i).getString("ciega_minima"),
                                            json.getJSONObject(i).getString("ciega_maxima"),
                                            json.getJSONObject(i).getString("usuarios_activos"),
                                            json.getJSONObject(i).getString("cantidad_usuarios")));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mesasAdapter = new MesasAdapter(Mesas.this, mesas);
                        recyclerMesas.setAdapter(mesasAdapter);
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarDatosGet(String id_club) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://gosmart.pe/poker/buscar_mesas.php?id_club=" + id_club);
            HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
            respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public int obtDatosJson(String response) {
        int res = 0;
        try {
            JSONArray json = new JSONArray(response);
            if (json.length() > 0) {
                res = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void Volver(View view) {
        finish();
    }
}
