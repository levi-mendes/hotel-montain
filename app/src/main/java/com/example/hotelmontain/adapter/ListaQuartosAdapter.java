package com.example.hotelmontain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.QuartoActivity;
import com.example.hotelmontain.database.Quarto;

import java.util.List;

public class ListaQuartosAdapter extends RecyclerView.Adapter<ListaQuartosAdapter.ViewHolder> {

    private List<Quarto> localDataSet;

    public ListaQuartosAdapter(List<Quarto> dataSet) {
        localDataSet = dataSet;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNumQuarto, tvAndar, tvTelefone;
        private final ImageView ivEdit;

        public ViewHolder(View view) {
            super(view);

            tvNumQuarto = view.findViewById(R.id.tv_num_quarto);
            tvAndar = view.findViewById(R.id.tv_num_quarto);
            tvTelefone = view.findViewById(R.id.tv_num_quarto);

            ivEdit = view.findViewById(R.id.iv_edit);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_quarto, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Context context = viewHolder.itemView.getContext();
        Quarto quarto = localDataSet.get(position);

        viewHolder.tvNumQuarto.setText("" + quarto.numQuartos);
        viewHolder.tvAndar.setText("" + quarto.andar);
        viewHolder.tvTelefone.setText("" + quarto.numTelefone);
        viewHolder.ivEdit.setOnClickListener(v ->
                context.startActivity(new Intent(context, QuartoActivity.class)));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}