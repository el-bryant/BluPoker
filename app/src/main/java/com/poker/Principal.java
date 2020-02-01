package com.poker;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    PerfilFragment perfil;
    ClubesFragment clubesF;
    StoreFragment store;
    BonusFragment bonus;
    BottomNavigationView mMainNav;
    FrameLayout mMainFrame;
    public static String nombre_de_usuario, codigo_de_usuario, foto_de_perfil;
    GoogleSignInClient mGoogleSignInClient;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_perfil:
                    setFragment(perfil);
                    return true;
                case R.id.nav_clubes:
                    setFragment(clubesF);
                    return true;
                case R.id.nav_store:
                    setFragment(store);
                    return true;
                case R.id.nav_bonus:
                    setFragment(bonus);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        codigo_de_usuario = getIntent().getStringExtra("id_usuario");
        nombre_de_usuario = getIntent().getStringExtra("usuario");
        foto_de_perfil = getIntent().getStringExtra("perfil");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mMainFrame = (FrameLayout) findViewById(R.id.container);
        mMainNav = (BottomNavigationView) findViewById(R.id.nav_view);
        perfil = new PerfilFragment();
        clubesF = new ClubesFragment();
        store = new StoreFragment();
        bonus = new BonusFragment();

        mMainNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setFragment(perfil);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_perfil:
                        setFragment(perfil);
                        return true;
                    case R.id.nav_clubes:
                        setFragment(clubesF);
                        return true;
                    case R.id.nav_store:
                        setFragment(store);
                        return true;
                    case R.id.nav_bonus:
                        setFragment(bonus);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    public void nuevoClub(View view) {
        DialogFragment dialogFragment = new NuevoClub();
        dialogFragment.show(getFragmentManager(), "Nuevo Club");
    }
}
