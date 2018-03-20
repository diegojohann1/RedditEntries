package com.example.reddit.redditentries.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.reddit.redditentries.activities.fragments.FragmentEntriesList;
import com.example.reddit.redditentries.R;
import com.example.reddit.redditentries.data.network.api.models.TopResponseData;
import com.facebook.stetho.Stetho;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public class MainActivity extends AppCompatActivity
        implements FragmentEntriesList.EscuchaFragmento {

    private TopResponseData topResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initialize( Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

        // Add fragment to the list
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, FragmentEntriesList.crear())
                .commit();

    }


    @Override
    public void alSeleccionarItem(String idArticulo) {

    }
}
