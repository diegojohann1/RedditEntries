package com.example.reddit.redditentries.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.reddit.redditentries.data.network.api.ApiInteractor;
import com.example.reddit.redditentries.R;
import com.facebook.stetho.Stetho;

/**
 * Created by Diego Volantino on 18/3/18.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Stetho.initialize( Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());

        ApiInteractor interactor = new ApiInteractor();

        // Get top
        interactor.getTop();

    }
}
