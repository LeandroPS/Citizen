package pooa20161.iff.edu.br.citizen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import pooa20161.iff.edu.br.citizen.Models.Cidade;
import pooa20161.iff.edu.br.citizen.Models.Usuario;

public class new_cidade extends AppCompatActivity {
    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cidade);
        array_spinner=new String[5];
        array_spinner[0]="AM";
        array_spinner[1]="PE";
        array_spinner[2]="RJ";
        array_spinner[3]="SC";
        array_spinner[4]="SP";


        Spinner select_estado = (Spinner) findViewById(R.id.cidade_estado);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        select_estado.setAdapter(adapter);

        Button criar = (Button) findViewById(R.id.cidade_criar);
        criar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String c_name = ((EditText) findViewById(R.id.cidade_nome)).getText().toString();
                String c_estado = ((Spinner) findViewById(R.id.cidade_estado)).getSelectedItem().toString();

                Cidade cidade = new Cidade(c_name, c_estado);
                cidade.save();

                //Intent data = getIntent();
                Intent data = new Intent(new_cidade.this, New_user.class);
                data.putExtra("id",cidade.getId());
                data.putExtra("nome",cidade.getNome());
                setResult(RESULT_OK, data);
                startActivity(data);
                finish();
            }
        });
    }
}
