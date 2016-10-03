package pooa20161.iff.edu.br.citizen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pooa20161.iff.edu.br.citizen.Models.Usuario;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "Preferences";

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button entrare = (Button) findViewById(R.id.entrar);

        entrare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            if(!(mEmailView.getText().toString().equals("") || mPasswordView.getText().toString().equals(""))) {
                List<Usuario> usuarios = Usuario.find(Usuario.class, "email = ? and senha = ?", mEmailView.getText().toString(), mPasswordView.getText().toString());

                if (!usuarios.isEmpty()) {
                    Usuario usuario = usuarios.get(0);

                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("usuario_logado", usuario.getId().toString());
                    editor.commit();

                    //((Citizen) getApplication()).setUsuario(usuario);
                    startActivity(new Intent(LoginActivity.this, city2.class));

                } else {
                    TextView feedbacklogin = (TextView) findViewById(R.id.feedbacklogin);
                    feedbacklogin.setText("o usuário não existe");
                }
            }else{
                TextView feedbacklogin = (TextView) findViewById(R.id.feedbacklogin);
                feedbacklogin.setText("Preencha o email e a senha");

            }
            }
        });

    }


}

