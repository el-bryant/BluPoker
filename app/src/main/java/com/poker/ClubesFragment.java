package com.poker;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.poker.adapters.ClubesAdapter;
import com.poker.pojos.ClsClub;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.poker.Principal.codigo_de_usuario;

public class ClubesFragment extends Fragment {
    RecyclerView recyclerClubes;
    ArrayList<ClsClub> clubes;
    ClubesAdapter clubesAdapter;
    private Button btnNuevoClub, btnBuscarClub;
    public static TextView tvNuevoClub, tvBuscarClub;
    private static final String TAG = "ClubesFragment";
    String id_club;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clubes, container, false);
        recyclerClubes = (RecyclerView) v.findViewById(R.id.recyclerClubesPrincipal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerClubes.setLayoutManager(linearLayoutManager);

        btnNuevoClub = (Button) v.findViewById(R.id.btnNuevoClub);
        tvNuevoClub = v.findViewById(R.id.tvNuevoClubFragment);
        btnNuevoClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new NuevoClub();
                dialogFragment.show(getFragmentManager(), "NuevoClub");
            }
        });

        tvNuevoClub.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.equals("")) {
                    Log.i("depurar", "click");
                    Thread tr = new Thread() {
                        @Override
                        public void run() {
                            final String result = enviarDatosGet();
                            Log.i("depurar", result);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int r = obtDatosJson(result);
                                    if (r > 0) {
                                        try {
                                            JSONArray json = new JSONArray(result);
                                            for (int i = 0; i < json.length(); i++) {
                                                id_club = json.getJSONObject(0).getString("id_club");
                                            }
                                            InsertarClub(id_club, tvNuevoClub.getText().toString());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                    };
                    tr.start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnBuscarClub = (Button) v.findViewById(R.id.btnBuscarClub);
        tvBuscarClub = v.findViewById(R.id.tvBuscarClubFragment);
        btnBuscarClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragmentBuscar = new BuscarClub();
                dialogFragmentBuscar.show(getFragmentManager(), "BuscarClub");
            }
        });

        tvBuscarClub.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.equals("")) {
                    Log.i("nombre_club", s.toString());
                    createData(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    public String enviarDatosGet() {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/obtener_ultimo_id_club.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

    public void InsertarClub(final String id_club, final String nombre) {
        Log.i("depurar", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarClub(id_club, nombre);
                Log.i("depurar", result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtClub(result);
                        if (r > 0) {
                            InsertarUsuario_club(id_club, codigo_de_usuario);
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarClub(String id_club, String nombre) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/registrar_club.php?id_club=" + id_club + "&nombre=" + nombre);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

    public int obtClub(String response) {
        int res = 0;
        try {
            if (!response.equals("")) {
                res = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void InsertarUsuario_club(final String id_club, final String id_usuario) {
        Log.i("depurar", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarUsuarioClub(id_usuario, id_club);
                Log.i("depurar", result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtUsuarioClub(result);
                        if (r > 0) {
                            createData(codigo_de_usuario);
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarUsuarioClub(String usuario, String id_club) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/registrar_usuario_club.php?id_usuario=" + usuario + "&id_club=" + id_club);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

    public int obtUsuarioClub(String response) {
        int res = 0;
        try {
            if (!response.equals("")){
                res = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createData("");
    }

    public void createData(final String nombre_club) {
        Log.i("depurar", "click");
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String result = enviarDatosGet1(nombre_club);
                Log.i("depurar", result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r = obtDatosJson1(result);
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(result);
                                clubes = new ArrayList<>();
                                DecimalFormat df = new DecimalFormat("000000000");
                                for (int i = 0; i < json.length(); i++) {
                                    clubes.add(new ClsClub(json.getJSONObject(i).getString("nombre"),
                                            df.format(Integer.parseInt(json.getJSONObject(i).getString("id_club")))));
                                }
                                clubesAdapter = new ClubesAdapter(getActivity(), clubes);
                                recyclerClubes.setAdapter(clubesAdapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), "No se ha encontrado el nombre especificado", Toast.LENGTH_LONG).show();
                            tvBuscarClub.setText("");
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarDatosGet1(String nombre_club) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/buscar_clubes.php?nombre_club=" + nombre_club);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

    public int obtDatosJson1(String response) {
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
}
