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
import com.example.hotelmontain.activities.cadastros.HospedeActivity;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.HospedeDao;
import com.example.hotelmontain.database.entity.Hospede;
import com.example.hotelmontain.util.AlertUtil;
import com.example.hotelmontain.util.ToastUtil;

import java.util.List;

import static java.lang.String.valueOf;

public class ListaHospedesAdapter extends
        RecyclerView.Adapter<ListaHospedesAdapter.ViewHolder> {

    private final List<Hospede> localDataSet;
    private final OnHospedeDelete onHospedeDelete;
    private final Context mContext;

    public ListaHospedesAdapter(Context context, List<Hospede> dataSet, OnHospedeDelete onHospedeDelete) {
        localDataSet = dataSet;
        this.onHospedeDelete = onHospedeDelete;
        mContext = context;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNome, tvEmail, tvTelefone;
        private final ImageView ivEdit, ivApagar;

        public ViewHolder(View view) {
            super(view);

            tvNome = view.findViewById(R.id.tv_nome);
            tvEmail = view.findViewById(R.id.tv_email);
            tvTelefone = view.findViewById(R.id.tv_telefone);

            ivEdit = view.findViewById(R.id.iv_edit);
            ivApagar = view.findViewById(R.id.iv_apagar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_hospede, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Hospede hospede = localDataSet.get(position);

        viewHolder.tvNome.setText(hospede.nome);
        viewHolder.tvEmail.setText(hospede.email);
        viewHolder.tvTelefone.setText(valueOf(hospede.telefone));
        viewHolder.ivEdit.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, HospedeActivity.class);
            intent.putExtra("hospede", hospede);
            mContext.startActivity(intent);
        });
        viewHolder.ivApagar.setOnClickListener(v -> removeDeletion(hospede));
    }

    private void removeDeletion(Hospede hospede) {
        new AlertDialog.Builder(mContext)
                .setMessage("Confirma a EXCLUSAO do Hospede ?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    try {
                        HospedeDao dao = HotelMontainDatabase.getInstance(mContext).hospedeDao();
                        dao.remover(hospede);
                        onHospedeDelete.remover(hospede);
                        ToastUtil.show(mContext, "Hospede excluido com sucesso");

                    } catch (Exception e) {
                        AlertUtil.showAlert(mContext, "Erro ao tentar remover Hospede");
                    }

                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnHospedeDelete {

        void remover(Hospede hospede);
    }
}