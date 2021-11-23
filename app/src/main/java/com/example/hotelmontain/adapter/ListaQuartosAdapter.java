package com.example.hotelmontain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmontain.R;
import com.example.hotelmontain.activities.QuartoActivity;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.Quarto;
import com.example.hotelmontain.database.QuartoDao;
import com.example.hotelmontain.util.AlertUtil;
import com.example.hotelmontain.util.ToastUtil;

import java.util.List;

import static java.lang.String.valueOf;

public class ListaQuartosAdapter extends RecyclerView.Adapter<ListaQuartosAdapter.ViewHolder> {

    private List<Quarto> localDataSet;
    private OnQuartoRemovido onQuartoRemovido;
    private Context mContext;

    public ListaQuartosAdapter(Context context, List<Quarto> dataSet, OnQuartoRemovido onQuartoRemovido) {
        localDataSet = dataSet;
        this.onQuartoRemovido = onQuartoRemovido;
        mContext = context;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNumQuarto, tvAndar, tvTelefone;
        private final ImageView ivEdit, ivApagar;

        public ViewHolder(View view) {
            super(view);

            tvNumQuarto = view.findViewById(R.id.tv_num_quarto);
            tvAndar = view.findViewById(R.id.tv_andar);
            tvTelefone = view.findViewById(R.id.tv_telefone);

            ivEdit = view.findViewById(R.id.iv_edit);
            ivApagar = view.findViewById(R.id.iv_apagar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_quarto, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Quarto quarto = localDataSet.get(position);

        viewHolder.tvNumQuarto.append(valueOf(quarto.numQuarto));
        viewHolder.tvAndar.append(valueOf(quarto.andar));
        viewHolder.tvTelefone.append(valueOf(quarto.numTelefone));
        viewHolder.ivEdit.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, QuartoActivity.class);
            intent.putExtra("quarto", quarto);
            mContext.startActivity(intent);
        });
        viewHolder.ivApagar.setOnClickListener(v -> removeDeletion(quarto));
    }

    private void removeDeletion(Quarto quarto) {
        new AlertDialog.Builder(mContext)
                .setMessage("Confirma a exclusao desse quarto ?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    try {
                        QuartoDao dao = HotelMontainDatabase.getInstance(mContext).quartoDao();
                        dao.remover(quarto);
                        onQuartoRemovido.remover(quarto);
                        ToastUtil.show(mContext, "Quarto excluido com sucesso");

                    } catch (Exception e) {
                        AlertUtil.showAlert(mContext, "Erro ao tentar remover o quarto");
                    }

                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnQuartoRemovido {

        void remover(Quarto quarto);
    }
}