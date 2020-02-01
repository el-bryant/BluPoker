package com.poker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.poker.pojos.ClsUsuarios;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A login screen that offers login via email/password.
 */
public class Registro extends AppCompatActivity {
    private EditText actvUsuarioRegistro;
    private EditText etPwdRegistro;
    private EditText actvApellidosRegistro;
    private EditText actvNombresRegistro;
    private EditText actvCelularRegistro;
    String usuario = "";
    String pwd = "";
    public static int perfil;
    RadioButton rbtV, rbtM;
    public static ImageButton imbPerfil;
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        actvUsuarioRegistro = (EditText) findViewById(R.id.actvUsuarioRegistro);
        etPwdRegistro = (EditText) findViewById(R.id.etPwdRegistro);

        rbtV = (RadioButton) findViewById(R.id.rbtVaronRegistro);
        rbtM = (RadioButton) findViewById(R.id.rbtMujerRegistro);
        imbPerfil = (ImageButton) findViewById(R.id.imbPerfilRegistro);

        mLoginFormView = findViewById(R.id.sign_in_form);
        mProgressView = findViewById(R.id.sign_in_progress);

        actvApellidosRegistro = (EditText) findViewById(R.id.actvApellidosRegistro);
        actvNombresRegistro = (EditText) findViewById(R.id.actvNombresRegistro);
        actvCelularRegistro = (EditText) findViewById(R.id.actvCelularRegistro);
    }

    public void Registrar(View view) {
        mEmailView = (EditText) findViewById(R.id.actvUsuarioRegistro);
        mPasswordView = (EditText) findViewById(R.id.etPwdRegistro);
        actvApellidosRegistro = (EditText) findViewById(R.id.actvApellidosRegistro);
        actvNombresRegistro = (EditText) findViewById(R.id.actvNombresRegistro);
        actvCelularRegistro = (EditText) findViewById(R.id.actvCelularRegistro);
        rbtV = (RadioButton) findViewById(R.id.rbtVaronRegistro);
        rbtM = (RadioButton) findViewById(R.id.rbtMujerRegistro);
        imbPerfil = (ImageButton) findViewById(R.id.imbPerfilRegistro);
        String usuario = "", pwd = "", apellidos = "", nombres = "", perfil = "", sexo = "", celular = "";
        usuario = mEmailView.getText().toString();
        pwd = mPasswordView.getText().toString();
        apellidos = actvApellidosRegistro.getText().toString();
        nombres = actvNombresRegistro.getText().toString();
        perfil = imbPerfil.getTag().toString();
        if (rbtM.isChecked()) {
            sexo = "F";
        } else {
            if (rbtV.isChecked()) {
                sexo = "M";
            }
        }
        celular = actvCelularRegistro.getText().toString();
        if (usuario.equals("")) {
            mEmailView.setError("Ingrese su nombre de usuario");
        } else {
            if (pwd.equals("")) {
                mPasswordView.setError("Ingrese su contraseña");
            } else {
                final String finalUsuario = usuario;
                final String finalPwd = pwd;
                //final String finalApellidos = apellidos;
                //final String finalNombres = nombres;
                //final String finalPerfil = perfil;
                //final String finalSexo = sexo;
                //final String finalCelular = celular;
                /*firebaseAuth.createUserWithEmailAndPassword(finalUsuario, finalPwd).addOnCompleteListener(this, new
                        OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(Registro.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
                //Firestore
                //comprobar si el usuario ya existe
                /*db.collection("usuarios").whereEqualTo("usuario", finalUsuario).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.size() > 0) {
                            actvUsuarioRegistro.setError("El nombre de usuario no está disponible");
                        } else {*/
                            showProgress(true);
                            Log.i("depurar", "click");
                            Thread tr = new Thread() {
                                @Override
                                public void run() {
                                    final String result = enviarDatosGet(finalUsuario, finalPwd, " ", " ", " ", " ", " ");
                                    Log.i("depurar", result);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            int r = obtDatosJson(result);
                                            if (r > 0) {
                                                MostrarData();
                                            }
                                        }
                                    });
                                }
                            };
                            tr.start();

                            //realizar la consulta de inserción
                            /*db.collection("usuarios").add(new ClsUsuarios(finalApellidos, finalCelular, 1000, finalNombres,
                                    finalPwd, finalSexo, finalUsuario)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent intent = new Intent(Registro.this, Principal.class);
                                    Acceder.id_usuario = documentReference.getId();
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });*/
            }
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            //Almacenar la información del usuario en el Firestore
            Intent intent = new Intent(Registro.this, Principal.class);
            intent.putExtra("id_usuario", "1");
            intent.putExtra("usuario", "Bryant");
            intent.putExtra("perfil", "pjh01.png");
            startActivity(intent);
        } else {
            etPwdRegistro.setError("Algunos datos no son correctos");
            etPwdRegistro.requestFocus();
        }
    }

    public String enviarDatosGet(String usuario, String pwd, String apellidos, String nombres, String perfil, String sexo, String celular) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/registro.php?usuario=" + usuario + "&pwd=" + pwd + "&apellidos=" + apellidos +
                    "&nombres=" + nombres + "&perfil=" + perfil + "&sexo=" + sexo + "&celular=" + celular);
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
        if (response != "") {
            res = 1;
        }
        return res;
    }

    public void MostrarData() {
        usuario = actvUsuarioRegistro.getText().toString();
        Intent intent = new Intent(Registro.this, Juego9.class);
        intent.putExtra("n_dni", usuario);
        startActivity(intent);
        Toast.makeText(this,"Bienvenido " + usuario, Toast.LENGTH_SHORT);
        finish();
    }

    public void habilitar(View view) {
        CheckBox chkAceptar = (CheckBox) findViewById(R.id.chkAceptar);
        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        if (chkAceptar.isChecked()) {
            btnRegistrar.setEnabled(true);
        } else {
            btnRegistrar.setEnabled(false);
        }
    }

    public void Avatar(View view) {
        rbtV = (RadioButton) findViewById(R.id.rbtVaronRegistro);
        rbtM = (RadioButton) findViewById(R.id.rbtMujerRegistro);
        if (rbtV.isChecked()) {
            Intent intent = new Intent(this,  Avatares.class);
            intent.putExtra("sexo", "M");
            startActivity(intent);
        } else {
            if (rbtM.isChecked()) {
                Intent intent = new Intent(this, Avatares.class);
                intent.putExtra("sexo", "F");
                startActivity(intent);
            }
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

