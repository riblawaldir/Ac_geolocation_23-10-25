package com.tuempresa.ap_geolocation_23_10_25;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    private View infoCard;
    private TextView placeTitle, placeDescription;
    private ImageView placeImage;

    private final HashMap<Marker, Place> markerInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        infoCard = findViewById(R.id.infoCard);
        placeTitle = findViewById(R.id.placeTitle);
        placeDescription = findViewById(R.id.placeDescription);
        placeImage = findViewById(R.id.placeImage);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        LatLng santaCruz = new LatLng(-17.7833, -63.1821);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santaCruz, 12));

        // Agregamos los lugares turísticos
        addTouristPoint("Plaza 24 de Septiembre",
                "Centro histórico y corazón de la ciudad de Santa Cruz, rodeado de cafés y la Catedral.",
                new LatLng(-17.7833, -63.1821),
                R.drawable.plaza);

        addTouristPoint("Biocentro Güembé",
                "Complejo turístico natural con mariposario, lagunas, aves exóticas y piscinas.",
                new LatLng(-17.7505, -63.2601),
                R.drawable.guembe);

        addTouristPoint("Jardín Botánico",
                "Espacio verde ideal para caminatas, picnic y contacto con la naturaleza.",
                new LatLng(-17.7961, -63.0925),
                R.drawable.jardin);

        addTouristPoint("Ventura Mall",
                "El centro comercial más grande del país, con tiendas, cines y restaurantes.",
                new LatLng(-17.7654, -63.1961),
                R.drawable.ventura);

        // Mostrar tarjeta al tocar marcador
        myMap.setOnMarkerClickListener(marker -> {
            Place place = markerInfo.get(marker);
            if (place != null) {
                placeTitle.setText(place.title);
                placeDescription.setText(place.description);
                placeImage.setImageResource(place.imageRes);
                infoCard.setVisibility(View.VISIBLE);
            }
            return true; // Detenemos el movimiento automático de la cámara
        });

        // Ocultar tarjeta al tocar el mapa
        myMap.setOnMapClickListener(latLng -> infoCard.setVisibility(View.GONE));
    }

    private void addTouristPoint(String title, String desc, LatLng position, int imageRes) {
        // Crear un bitmap pequeño para usar como ícono del marcador
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), imageRes);
        Bitmap smallBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, false);

        Marker marker = myMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromBitmap(smallBitmap)));

        markerInfo.put(marker, new Place(title, desc, imageRes));
    }

    private static class Place {
        String title;
        String description;
        int imageRes;

        Place(String title, String description, int imageRes) {
            this.title = title;
            this.description = description;
            this.imageRes = imageRes;
        }
    }
}
