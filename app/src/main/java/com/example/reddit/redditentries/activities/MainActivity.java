package com.example.reddit.redditentries.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.example.reddit.redditentries.activities.fragments.FragmentEntriesList;
import com.example.reddit.redditentries.R;
import com.example.reddit.redditentries.activities.fragments.FragmentItemDetail;
import com.example.reddit.redditentries.data.network.api.models.DataChild;
import com.facebook.stetho.Stetho;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public class MainActivity extends AppCompatActivity
        implements FragmentEntriesList.EscuchaFragmento {

    private boolean dosPaneles;
    private DataChild dataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initialize( Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

        if (findViewById(R.id.contenedor_detalle_articulo) != null) {
            // If so, then confirm Master-Detail mode
            dosPaneles = true;

            cargarFragmentoDetalle(0);
        }

        // Add fragment to the list
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, FragmentEntriesList.crear())
                .commit();

    }

    private void cargarFragmentoDetalle(int id) {
        Bundle arguments = new Bundle();
        arguments.putInt(FragmentItemDetail.ID_ARTICULO, id);
        FragmentItemDetail fragment = new FragmentItemDetail();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_detalle_articulo, fragment)
                .commit();
    }


    @Override
    public void alSeleccionarItem(int idArticulo) {
        if (dosPaneles) {
            cargarFragmentoDetalle(idArticulo);
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(FragmentItemDetail.ID_ARTICULO, idArticulo);

            startActivity(intent);
        }
    }
}