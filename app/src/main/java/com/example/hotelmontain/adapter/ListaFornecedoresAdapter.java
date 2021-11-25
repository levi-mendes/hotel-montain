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
import com.example.hotelmontain.activities.cadastros.FornecedorActivity;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.FornecedorDao;
import com.example.hotelmontain.database.entity.Fornecedor;
import com.example.hotelmontain.util.AlertUtil;
import com.example.hotelmontain.util.ToastUtil;

import java.util.List;

import static java.lang.String.valueOf;

public class ListaFornecedoresAdapter extends
        RecyclerView.Adapter<ListaFornecedoresAdapter.ViewHolder> {

    private final List<Fornecedor> localDataSet;
    private final OnFornecedorDelete onFornecedorDelete;
    private final Context mContext;

    public ListaFornecedoresAdapter(Context context, List<Fornecedor> dataSet, OnFornecedorDelete onFornecedorDelete) {
        localDataSet = dataSet;
        this.onFornecedorDelete = onFornecedorDelete;
        mContext = context;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNome, tvCnpj, tvTelefone;
        private final ImageView ivEdit, ivApagar;

        public ViewHolder(View view) {
            super(view);

            tvNome = view.findViewById(R.id.tv_nome);
            tvCnpj = view.findViewById(R.id.tv_cnpj);
            tvTelefone = view.findViewById(R.id.tv_telefone);

            ivEdit = view.findViewById(R.id.iv_edit);
            ivApagar = view.findViewById(R.id.iv_apagar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_fornecedor, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Fornecedor fornecedor = localDataSet.get(position);

        viewHolder.tvNome.setText(fornecedor.nome);
        viewHolder.tvCnpj.setText(fornecedor.cnpj);
        viewHolder.tvTelefone.setText(valueOf(fornecedor.telefone));
        viewHolder.ivEdit.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, FornecedorActivity.class);
            intent.putExtra("fornecedor", fornecedor);
            mContext.startActivity(intent);
        });
        viewHolder.ivApagar.setOnClickListener(v -> removeDeletion(fornecedor));
    }

    private void removeDeletion(Fornecedor Fornecedor) {
        new AlertDialog.Builder(mContext)
                .setMessage("Confirma a EXCLUSAO do fornecedor ?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    try {
                        FornecedorDao dao = HotelMontainDatabase.getInstance(mContext).fornecedorDao();
                        dao.remover(Fornecedor);
                        onFornecedorDelete.remover(Fornecedor);
                        ToastUtil.show(mContext, "Fornecedor excluido com sucesso");

                    } catch (Exception e) {
                        AlertUtil.showAlert(mContext, "Erro ao tentar remover forncecedor");
                    }

                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnFornecedorDelete {

        void remover(Fornecedor Fornecedor);
    }
}