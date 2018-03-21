package com.example.reddit.redditentries.activities.fragments;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reddit.redditentries.data.network.api.ApiInteractor;
import com.example.reddit.redditentries.data.network.api.models.Children;
import com.example.reddit.redditentries.data.network.api.models.DataChild;
import com.example.reddit.redditentries.data.network.api.models.TopResponse;
import com.example.reddit.redditentries.activities.adapters.ListItemAdapter;
import com.example.reddit.redditentries.R;

import java.util.ArrayList;

/**
 * Created by Diego Volantino on 18/3/18.
 *
 * Fragment for list
 */

public class FragmentEntriesList extends Fragment
        implements ListItemAdapter.OnItemClickListener {

    private Context context;
    private Fragment fragment;
    private EscuchaFragmento escucha;
    private RecyclerView listRecycler;
    private ListItemAdapter itemAdapter;
    private ArrayList<Children> childrenList;
    private static DataChild dataChild;


    public FragmentEntriesList() {

    }

    public static FragmentEntriesList crear() {
        return new FragmentEntriesList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        fragment = this;

        if (getArguments() != null) {
            // Manejo de argumentos
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entries_list, container, false);

        listRecycler = v.findViewById(R.id.reciclador);

        ApiInteractor interactor = new ApiInteractor();

        // Get top
        interactor.getTop(new ApiInteractor.ResponseCallback() {
            @Override
            public void onDataReady(TopResponse response) {
                if(response != null && response.getData() != null)
                    childrenList = response.getData().getChildrenList();
                    fillList();
            }

            @Override
            public void onError(Throwable t) {
                Log.e("error"," =>"+t.getMessage());
            }
        });

        return v;
    }


    private void fillList() {
        //Reddit uses Strings as ID, so this FOR creates number IDs
        for(int i=0; i<childrenList.size(); i++) {
            childrenList.get(i).getData().setId(i);
            childrenList.get(i).getData().setViewed(false);
        }
        LinearLayoutManager mLinearManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listRecycler.setLayoutManager(mLinearManager);
        listRecycler.setHasFixedSize(false);
        itemAdapter = new ListItemAdapter(childrenList, new ListItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(ListItemAdapter.ViewHolder viewHolder, int id) {
                Log.d("artcl","=>"+childrenList.get(id).getData().getTitle());
                if (escucha != null) {
                    escucha.alSeleccionarItem(id);
                    dataChild = childrenList.get(id).getData();
                    childrenList.get(id).getData().setViewed(true);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        }, context, fragment);
        listRecycler.setAdapter(itemAdapter);
    }

    public static DataChild getDataChild() {
        return dataChild;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EscuchaFragmento) {
            escucha = (EscuchaFragmento) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " debes implementar EscuchaFragmento");
        }
    }

    public void onResume() {
        super.onResume();
        if(listRecycler != null && itemAdapter != null) {
            itemAdapter.notifyDataSetChanged();
            listRecycler.setAdapter(itemAdapter);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        escucha = null;
    }

    public void removeItem(int position) {
        childrenList.remove(position);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(ListItemAdapter.ViewHolder viewHolder, int id) {
        loadDetail(id);
        dataChild.setViewed(true);
    }

    public void loadDetail(int id) {
        if (escucha != null) {
            escucha.alSeleccionarItem(id);
        }
    }

    public interface EscuchaFragmento {
        void alSeleccionarItem(int id);
    }
}