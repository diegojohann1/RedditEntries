package com.example.reddit.redditentries.activities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reddit.redditentries.data.network.api.models.Children;
import com.example.reddit.redditentries.R;
import com.example.reddit.redditentries.data.network.api.models.DataChild;

import java.util.ArrayList;

import static android.text.format.DateUtils.getRelativeTimeSpanString;

/**
 * Created by Diego Volantino on 19/3/18.
 */

/**
 * {@link RecyclerView.Adapter} that fills the list with
 * {@link Children} instances
 */
public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

    private Context context;
    // private final TopResponseData topResponseData;
    public ArrayList<Children> valores;

    public ListItemAdapter(ArrayList<Children> items, OnItemClickListener escuchaClicksExterna, Context context) {
        valores = items;
        this.escuchaClicksExterna = escuchaClicksExterna;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // holder.item = valores.get(position);
        holder.tvAuthor.setText(valores.get(position).getData().getAuthor());
        String createdUtc;
        createdUtc = getRelativeTimeSpanString(valores.get(position).getData().getCreated_utc(),
                System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
        holder.tvDate.setText(createdUtc);
        holder.tvTitle.setText(valores.get(position).getData().getTitle());
        Glide.with(holder.itemView.getContext())
                // .load(holder.item.urlMiniatura)
                .load(valores.get(position).getData().getThumbnail())
                .thumbnail(0.1f)
                .centerCrop()
                .into(holder.viewMiniatura);
        holder.tvComments.setText(String.valueOf(valores.get(position).getData().getNum_comments()) + " "
                + context.getResources().getString(R.string.comments));

    }

    @Override
    public int getItemCount() {
        if (valores != null) {
            return valores.size() > 0 ? valores.size() : 0;
        } else {
            return 0;
        }
    }


    /**private String obtenerIdArticulo(int position) {
        if (position != RecyclerView.NO_POSITION) {
            return valores.get(position).id;
        } else {
            return null;
        }
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final ImageView viewViewed;
        public final TextView tvAuthor;
        public final TextView tvDate;
        public final ImageView viewMiniatura;
        public final TextView tvTitle;
        public final ImageView btnDismiss;
        public final TextView tvDismiss;
        public final TextView tvComments;


        // public ModeloArticulos.Articulo item;

        public ViewHolder(View view) {
            super(view);
            view.setClickable(true);
            viewViewed = (ImageView) view.findViewById(R.id.viewed);
            tvAuthor = (TextView) view.findViewById(R.id.author);
            tvDate = (TextView) view.findViewById(R.id.date);
            viewMiniatura = (ImageView) view.findViewById(R.id.miniatura);
            tvTitle = (TextView) view.findViewById(R.id.title);
            btnDismiss = (ImageView) view.findViewById(R.id.btn_dismiss);
            tvDismiss = (TextView) view.findViewById(R.id.dismiss_post);
            tvComments = (TextView) view.findViewById(R.id.comments);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            escuchaClicksExterna.onClick(this, getAdapterPosition());
        }
    }


    public interface OnItemClickListener {
        public void onClick(ViewHolder viewHolder, int id);
    }

    private OnItemClickListener escuchaClicksExterna;
}