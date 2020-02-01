package com.poker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A login screen that offers login via email/password.
 */
public class Acceder extends AppCompatActivity{

    // UI references.

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public static String id_usuario, nom_usuario, perfil, fichas;
    private ImageView googleSignInButton;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "Acceder";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceder);

        firebaseAuth = FirebaseAuth.getInstance();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.actvUsuarioAcceder);
        mPasswordView = (EditText) findViewById(R.id.etPwdAcceder);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        googleSignInButton = (ImageView) findViewById(R.id.google_signin_button);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        /*mGoogleSignInClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new
                GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(Acceder.this, "Connection to Google Sign In failed...", Toast.LENGTH_LONG).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();*/

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            /*if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                Toast.makeText(this, "Please wait while we are getting your auth result...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Can't get Auth result", Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(Acceder.this, Principal.class));
        } catch (ApiException e) {
            Log.w("Google Sign In Error: ", "Sign In Result: failed code" + e.getStatusCode());
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    protected void onStart() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            startActivity(new Intent(Acceder.this, Principal.class));
        }
        super.onStart();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            SendUserToMainActivity();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            String message = task.getException().toString();
                            SendUserToLoginActivity();
                            Toast.makeText(Acceder.this, "Not Authenticated: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SendUserToMainActivity() {
        Intent intent = new Intent(this, Principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void SendUserToLoginActivity() {
        Intent intent = new Intent(this, Acceder.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    public void Login(View view) {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.actvUsuarioAcceder);
        mPasswordView = (EditText) findViewById(R.id.etPwdAcceder);
        String usuario = "", pwd = "";
        usuario = mEmailView.getText().toString();
        pwd = mPasswordView.getText().toString();
        if (usuario.equals("")) {
            mEmailView.setError("Ingrese su nombre de usuario");
        } else {
            if (pwd.equals("")) {
                mPasswordView.setError("Ingrese su contraseña");
            } else {
                /*firebaseAuth.signInWithEmailAndPassword(usuario, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {*/
                                showProgress(true);
                                Log.i("depurar", "click");
                                Thread tr = new Thread() {
                                    @Override
                                    public void run() {
                                        mEmailView = (AutoCompleteTextView) findViewById(R.id.actvUsuarioAcceder);
                                        mPasswordView = (EditText) findViewById(R.id.etPwdAcceder);
                                        final String result = enviarDatosGet(mEmailView.getText().toString(), mPasswordView.getText().toString());
                                        Log.i("depurar", result);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                int r = obtDatosJson(result);
                                                if (r > 0) {
                                                    MostrarData(result);
                                                } else {
                                                    Intent intent = new Intent(Acceder.this, Acceder.class);
                                                    startActivity(intent);
                                                }
                                            }
                                        });
                                    }
                                };
                                tr.start();
                            /*} else {
                                mPasswordView.setError("Usuario y/o contraseña incorrectos");
                                mPasswordView.requestFocus();
                            }
                        } else {
                            Log.i("TAG", "signInError:", task.getException());
                        }
                    }
                });*/
            }
        }
    }

    public String enviarDatosGet(String usuario, String pwd) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;
        try {
            url = new URL("http://www.gosmart.pe/poker/acceder.php?usuario=" + usuario + "&pwd=" + pwd);
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

    public void MostrarData(String response) {
        try {
            JSONArray json = new JSONArray(response);
            if (json.length() > 0) {
                nom_usuario = json.getJSONObject(0).getString("usuario");
                Toast.makeText(this, "Bienvenido " + nom_usuario, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Principal.class);
                id_usuario = json.getJSONObject(0).getString("id_usuario");
                perfil = json.getJSONObject(0).getString("perfil");
                fichas = json.getJSONObject(0).getString("fichas");
                intent.putExtra("id_usuario", id_usuario);
                intent.putExtra("nombre", nom_usuario);
                intent.putExtra("perfil", perfil);
                intent.putExtra("fichas", fichas);
                startActivity(intent);
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
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

    public void Registrarme(View view) {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
        finish();
    }

    public void onOlvidarPwd(View view) {
    }

    public void onNecesitasAyuda(View view) {
    }
}

