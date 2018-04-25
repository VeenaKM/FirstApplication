package chat.firebase.com.placepickerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


public class MainActivity extends AppCompatActivity {

    private static final String TAG ="Location" ;
    int PLACE_PICKER_REQUEST = 1;
    TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLocation = (TextView) findViewById(R.id.location);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place selectedPlace = PlacePicker.getPlace(data, this);

                Log.d("SelectedLocation",""+selectedPlace);
                // Do something with the place

                String placeName = selectedPlace.getName().toString();
                String latitude = String.valueOf(selectedPlace.getLatLng().latitude);
                String longitude = String.valueOf(selectedPlace.getLatLng().longitude);
                String address = String.format("%s", selectedPlace.getAddress());

                Log.d(TAG, "Place name: " + placeName);
                Log.d(TAG, "Latitude: " + latitude);
                Log.d(TAG, "Longitude: " + longitude);
                Log.d(TAG, "Address: " + address);

                tvLocation.setText("Place name: " + placeName +"\n"+
                        "Latitude: " + latitude +"\n"+
                        "Longitude: " + longitude +"\n"+
                        "Address: " + address +"\n");

            }
        }
    }

    public void selectLocation(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(MainActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.e("Location", "PlayServices Exception : " + e.getMessage());
        }
    }
}
