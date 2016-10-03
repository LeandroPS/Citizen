package pooa20161.iff.edu.br.citizen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pooa20161.iff.edu.br.citizen.Adapter.CausaAdapter;
import pooa20161.iff.edu.br.citizen.Adapter.ComentarioAdapter;
import pooa20161.iff.edu.br.citizen.Models.Apoio;
import pooa20161.iff.edu.br.citizen.Models.Causa;
import pooa20161.iff.edu.br.citizen.Models.Comentario;
import pooa20161.iff.edu.br.citizen.Models.Usuario;

public class Issue extends AppCompatActivity {
//aaaa
    private String PREFS_NAME = "Preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final String userid = settings.getString("usuario_logado", "");
        final Usuario usuario = Usuario.findById(Usuario.class, Long.parseLong(userid));

        final String causaid;
        Intent intent = getIntent();
        causaid = intent.getStringExtra("CAUSA_ID");

        final LinearLayout apoiolinear = (LinearLayout) findViewById(R.id.issue_apoio);

        final Causa causa = Causa.findById(Causa.class, Long.parseLong(causaid));

        final TextView apoios_number = (TextView) findViewById(R.id.apoios_number);

        TextView issue_titulo = (TextView) findViewById(R.id.issue_titulo);
        TextView issue_descricao = (TextView) findViewById(R.id.issue_descricao);

        issue_titulo.setText(causa.getTitulo());
        issue_descricao.setText(causa.getDescricao());

        final List<Apoio> meuApoio = Apoio.findWithQuery(Apoio.class, "Select * from Apoio where id_Causa = ? and id_Usuario = ?", causa.getId().toString(), usuario.getId().toString());
        final TextView vcApoia = (TextView) findViewById(R.id.voce_apoia);
        if(!meuApoio.isEmpty()){
            vcApoia.setText("Você já apoia!");
            apoiolinear.setBackgroundColor(Color.GREEN);
        }


        final List<Apoio> apoios = Apoio.find(Apoio.class, "id_Causa=?", causa.getId().toString());



        apoios_number.setText(""+(apoios.size()));

        apoiolinear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(meuApoio.isEmpty()) {
                    Apoio apoio = new Apoio(usuario.getId().intValue(), causa.getId().intValue());
                    apoio.save();
                    vcApoia.setText("Você já apoia!");
                    apoiolinear.setBackgroundColor(Color.GREEN);
                    apoios_number.setText(""+(apoios.size()+1));
                }
            }
        });

        Button comentar = (Button) findViewById(R.id.btn_comentar);

        comentar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText comentario = ((EditText) findViewById(R.id.comentario_text));

                //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();

                Comentario comment = new Comentario(Integer.parseInt(userid), Integer.parseInt(causaid), comentario.getText().toString(), date);

                comment.save();

            }
        });


        final List<Comentario> comentarios = Comentario.find(Comentario.class, "id_causa=?", causaid);
        ListView list = (ListView) findViewById(R.id.comentario_list);
        final ArrayAdapter<Comentario> adapter = new ComentarioAdapter(this, (ArrayList<Comentario>) comentarios);
        try{
            list.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this, "Failed to load causas"+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(this, "causa Titulo"+causa.getTitulo(), Toast.LENGTH_LONG).show();
    }
}
