package pooa20161.iff.edu.br.citizen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import pooa20161.iff.edu.br.citizen.Models.Causa;
import pooa20161.iff.edu.br.citizen.Models.Usuario;


public class new_issue extends AppCompatActivity implements LocationListener {
    String issue_photo = "00";
    String coordenadas = "00.00000, 00.00000";
    String latitude = "00.00000";
    String longitude = "00.0000";
    public static final String PREFS_NAME = "Preferences";
    final int PICK_PLACE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_issue);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final Integer userid = Integer.parseInt(settings.getString("usuario_logado", ""));

        final Usuario usuario = Usuario.findById(Usuario.class, Long.parseLong(userid.toString()));

        /////////

        ////////

        Button criar = (Button) findViewById(R.id.criar_issue);

        criar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String titulo = ((EditText) findViewById(R.id.issue_titulo)).getText().toString();
                String descricao = ((EditText) findViewById(R.id.issue_descricao)).getText().toString();

                Causa causa = new Causa(userid, titulo, descricao, latitude, longitude, "", issue_photo, usuario.getCidadeId());
                causa.save();
                startActivity(new Intent(new_issue.this, city2.class));
            }
        });

        ImageView issue_image = (ImageView) findViewById(R.id.issue_imageview);

        issue_image.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 pickImage();

             }
         });

        LinearLayout place = (LinearLayout) findViewById(R.id.placepicker);

        place.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(context), PICK_PLACE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView txtLat = (TextView) findViewById(R.id.coord);
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    final int PICK_PHOTO_FOR_AVATAR = 1;

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

                ImageView photo = (ImageView) findViewById(R.id.issue_imageview);
                photo.setPadding(0,0,0,0);
                photo.setImageURI(selectedImageUri);
                photo.setImageBitmap(BitmapFactory.decodeFile(s));
                issue_photo = s;
            }
        }else if(requestCode == PICK_PLACE){

            if (resultCode == RESULT_OK) {
                Place selectedPlace = PlacePicker.getPlace(data, this);
                LatLng coord = selectedPlace.getLatLng();

                latitude = String.valueOf(coord.latitude);
                longitude = String.valueOf(coord.longitude);
                coordenadas = coord.latitude + "," + coord.longitude;
                // Do something with the place
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

}
