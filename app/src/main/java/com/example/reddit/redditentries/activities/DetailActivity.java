package com.example.reddit.redditentries.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.reddit.redditentries.R;
import com.example.reddit.redditentries.activities.fragments.FragmentItemDetail;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Verificación: ¿La app se está ejecutando en un teléfono?
        /*if (!getResources().getBoolean(R.bool.esTablet)) {
            // Mostrar Up Button
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }*/

        if (savedInstanceState == null) {
            // Add DetailFragment
            Bundle arguments = new Bundle();
            arguments.putString(FragmentItemDetail.ID_ARTICULO,
                    getIntent().getStringExtra(FragmentItemDetail.ID_ARTICULO));
            FragmentItemDetail fragment = new FragmentItemDetail();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_detalle_articulo, fragment)
                    .commit();
        }
    }
}
