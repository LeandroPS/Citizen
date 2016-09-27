package pooa20161.iff.edu.br.citizen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import pooa20161.iff.edu.br.citizen.Models.Usuario;

public class New_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button criar = (Button) findViewById(R.id.criar);

        criar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.nome);
                EditText mail = (EditText)findViewById(R.id.mail);
                EditText senha = (EditText)findViewById(R.id.Senha);
                EditText rsenha = (EditText)findViewById(R.id.rSenha);

                String tname = name.getText().toString();
                String tmail = mail.getText().toString();
                String tsenha = senha.getText().toString();
                String trsenha = rsenha.getText().toString();

                if(tsenha.equals(trsenha)) {
                    Usuario usuario = new Usuario(tname, tmail, tsenha, "00");
                    usuario.save();

                    startActivity(new Intent(New_user.this, city2.class));
                }else{
                    TextView feedback = (TextView) findViewById(R.id.feedback);
                    feedback.setText("As senhas não combinam!!!");
                }
            }
        });

        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setClipToOutline(true);
    }
}
