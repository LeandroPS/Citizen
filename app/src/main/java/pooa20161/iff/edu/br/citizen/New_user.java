package pooa20161.iff.edu.br.citizen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class New_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button criar = (Button) findViewById(R.id.criar);

        criar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(New_user.this,city2.class));
            }
        });

        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setClipToOutline(true);
    }
}
