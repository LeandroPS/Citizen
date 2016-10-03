package pooa20161.iff.edu.br.citizen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pooa20161.iff.edu.br.citizen.Adapter.CausaAdapter;
import pooa20161.iff.edu.br.citizen.Models.Causa;
import pooa20161.iff.edu.br.citizen.Models.Usuario;

public class city2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String PREFS_NAME = "Preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city2);

/*
        List<Causa> causas = Causa.listAll(Causa.class);
        String arrayLista[] = new String[causas.size()];
        Integer count = 0;
        for (Causa c : causas) {
            arrayLista[count] = c.getTitulo();
            count = count + 1;
        }

        final ListView user_cidade = (ListView) findViewById(R.id.causas_city2);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, arrayLista);
        user_cidade.setAdapter(adapter);
        */
        final List<Causa> causas = Causa.listAll(Causa.class);
        ListView list = (ListView) findViewById(R.id.causas_city2);
        final ArrayAdapter <Causa> adapter = new CausaAdapter(this, (ArrayList<Causa>) causas);
        try{
            list.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this, "Failed to load causas"+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //Causa yourData = causas.get(position);
                /*
                Intent intent = new Intent(getBaseContext(), Issue.class);
                intent.putExtra("CAUSA_ID", position);
                startActivity(intent);
                */
                //startActivity(new Intent(city2.this, Issue.class));

                String dataToPass= ""+(position+1);
                Intent intent = new Intent(city2.this, Issue.class);
                intent.putExtra("CAUSA_ID", dataToPass);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ImageView nav = (ImageView) findViewById(R.id.drawerbutton);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(city2.this,new_issue.class));
            }
        });


        nav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String userid = settings.getString("usuario_logado", "");
        Usuario usuario = Usuario.findById(Usuario.class, Long.parseLong(userid));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView nav_username = (TextView) header.findViewById(R.id.nav_username);

        navigationView.getMenu().getItem(0).setChecked(true);

        nav_username.setText(usuario.getNome());

        TextView city2_cidade = (TextView) findViewById(R.id.city2_cidade);

        city2_cidade.setText(usuario.getCidade().getNome());

        ImageView photo = (ImageView) header.findViewById(R.id.single_isuue_photo);
        photo.setClipToOutline(true);
        photo.setPadding(0,0,0,0);
        photo.setImageBitmap(BitmapFactory.decodeFile(usuario.getFoto()));
/////////////

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.city3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cidade) {
            startActivity(new Intent(city2.this,city2.class));
        } else if (id == R.id.nav_meus) {
            startActivity(new Intent(city2.this,Minhas_causas.class));
        } else if (id == R.id.nav_off) {
            startActivity(new Intent(city2.this,LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
