package com.example.reddit.redditentries.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.reddit.redditentries.R;
import com.example.reddit.redditentries.data.network.api.models.DataChild;

/**
 * Created by Diego Volantino on 19/3/18.
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentItemDetail extends Fragment {
    // EXTRA
    public static final String ID_ARTICULO = "extra.idArticulo";

    // Artículo al cual está ligado la UI
    private DataChild itemDetallado;

    public FragmentItemDetail() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ID_ARTICULO)) {
            // Cargar modelo según el identificador
            itemDetallado = (DataChild) FragmentEntriesList.getDataChild(); // getArguments().getInt(ID_ARTICULO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_detail, container, false);

        if (itemDetallado != null) {
            ((TextView) v.findViewById(R.id.author)).setText(itemDetallado.getAuthor());
            Glide.with(this)
                    .load(itemDetallado.getThumbnail())
                    .thumbnail(0.1f)
                    .centerCrop()
                    .into((ImageView) v.findViewById(R.id.imagen));
            ((TextView) v.findViewById(R.id.title)).setText(itemDetallado.getTitle());

        }

        return v;
    }
}
