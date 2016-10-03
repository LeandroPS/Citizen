package pooa20161.iff.edu.br.citizen;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pooa20161.iff.edu.br.citizen.Adapter.CausaAdapter;
import pooa20161.iff.edu.br.citizen.Models.Causa;
import pooa20161.iff.edu.br.citizen.Models.Usuario;

public class Minhas_causas extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_causas);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String userid = settings.getString("usuario_logado", "");
        //Usuario usuario = Usuario.findById(Usuario.class, Long.parseLong(userid));

        final List<Causa> causas = Causa.find(Causa.class, "id_autor=?", userid);
        //final List<Causa> causas = Causa.listAll(Causa.class);
        ListView list = (ListView) findViewById(R.id.minhas_causas_list);
        final ArrayAdapter<Causa> adapter = new CausaAdapter(this, (ArrayList<Causa>) causas);
        try{
            list.setAdapter(adapter);
        }catch(Exception e){
            Toast.makeText(this, "Failed to load causas"+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
