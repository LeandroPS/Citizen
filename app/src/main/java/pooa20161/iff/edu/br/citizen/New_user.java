package pooa20161.iff.edu.br.citizen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import pooa20161.iff.edu.br.citizen.Models.Cidade;
import pooa20161.iff.edu.br.citizen.Models.Usuario;

public class New_user extends AppCompatActivity {
    public static final String PREFS_NAME = "Preferences";
    String photoPath = "00";
    private String array_spinner[];
    List<Cidade> cidades;

    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        cidades = Cidade.listAll(Cidade.class);
        array_spinner=new String[cidades.size()];
        Integer count = 0;
        for (Cidade c : cidades) {
            array_spinner[count] = c.getNome();
            count = count + 1;
        }

        final Spinner user_cidade = (Spinner) findViewById(R.id.new_user_cidade);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        user_cidade.setAdapter(adapter);

        Button criar = (Button) findViewById(R.id.criar);

        criar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.nome);
                EditText mail = (EditText)findViewById(R.id.mail);
                EditText senha = (EditText)findViewById(R.id.Senha);
                EditText rsenha = (EditText)findViewById(R.id.rSenha);
                TextView feedback = (TextView) findViewById(R.id.feedback);

                String tname = name.getText().toString();
                String tmail = mail.getText().toString();
                String tsenha = senha.getText().toString();
                String trsenha = rsenha.getText().toString();
                String ucidade = user_cidade.getSelectedItem().toString();

                List<Cidade> cidades = Cidade.find(Cidade.class, "nome = ?", ucidade);

                Integer cidade_id = (cidades.get(0).getId()).intValue();

                if(!(tname.equals("") || tmail.equals("") || tsenha.equals("") || trsenha.equals(""))) {
                    if (tsenha.equals(trsenha)) {
                        Usuario usuario = new Usuario(tname, tmail, tsenha, photoPath, cidade_id);
                        usuario.save();

                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("usuario_logado", usuario.getId().toString());
                        editor.commit();

                        startActivity(new Intent(New_user.this, city2.class));
                    } else {

                        feedback.setText("As senhas não combinam!");
                    }
                }else{

                    feedback.setText("Todos os campos precisam ser preenchidos!");
                }
            }
        });

        Button nova_cidade = (Button) findViewById(R.id.nova_cidade);

        nova_cidade.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 startActivityForResult(new Intent(New_user.this ,new_cidade.class),PICK_CIDADE);
                 finish();

             }
         });

        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setClipToOutline(true);

        photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pickImage();
            }
        });


    }

    final int PICK_PHOTO_FOR_AVATAR = 1;
    final int PICK_CIDADE = 2;

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }
    /////////

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_PHOTO_FOR_AVATAR) {
                Uri selectedImageUri = data.getData();
                String s = getRealPathFromURI(selectedImageUri);

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setPadding(0,0,0,0);
                photo.setImageURI(selectedImageUri);
                photo.setImageBitmap(BitmapFactory.decodeFile(s));
                photoPath = s;
            }else if (resultCode == PICK_CIDADE){

                cidades = Cidade.listAll(Cidade.class);
                array_spinner=new String[cidades.size()];
                Integer count = 0;
                for (Cidade c : cidades) {
                    array_spinner[count] = c.getNome();
                    count = count + 1;
                }



                adapter.add("teste");
                final Spinner user_cidade = (Spinner) findViewById(R.id.new_user_cidade);
                //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
                adapter.notifyDataSetChanged();
                //user_cidade.setAdapter(adapter);


                //////
                //Log.d(TAG, "onActivityResult: "+data.getStringExtra("nome"));
                Spinner cidade = (Spinner) findViewById(R.id.new_user_cidade);
                cidade.setSelection(Arrays.asList(array_spinner).indexOf(data.getStringExtra("nome")));
            }
        }
    }

    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /**
     * helper to retrieve the path of an image URI
     */


    //////////
    /*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }

            Context context = getApplicationContext();

            //InputStream inputStream = context.getContentResolver().openInputStream(data.getData());
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }
    */


}
