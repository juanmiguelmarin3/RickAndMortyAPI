package com.jm.rickandmortyapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jm.rickandmortyapi.models.RyM;

import java.util.ArrayList;

public class ListaPersonajesAdapter extends RecyclerView.Adapter<ListaPersonajesAdapter.ViewHolder> {

    private ArrayList<RyM> dataset;
    private Context context;

    public ListaPersonajesAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rym, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RyM r = dataset.get(position);
        holder.textViewName.setText(r.getName());

        Glide.with(context)
                .load("https://rickandmortyapi.com/api/character/avatar/" + r.getNumber() + ".jpeg")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgCharacter);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPersonajes(ArrayList<RyM> listaPersonajes) {
        dataset.addAll(listaPersonajes);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCharacter;
        private TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);

            imgCharacter = (ImageView) itemView.findViewById(R.id.imgCharacter);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }
    }

}
