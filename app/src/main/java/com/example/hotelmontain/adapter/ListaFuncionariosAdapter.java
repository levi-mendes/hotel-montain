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
import com.example.hotelmontain.activities.cadastros.FuncionarioActivity;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.FuncionarioDao;
import com.example.hotelmontain.database.entity.Funcionario;
import com.example.hotelmontain.util.AlertUtil;
import com.example.hotelmontain.util.ToastUtil;

import java.util.List;

import static java.lang.String.valueOf;

public class ListaFuncionariosAdapter extends
        RecyclerView.Adapter<ListaFuncionariosAdapter.ViewHolder> {

    private final List<Funcionario> localDataSet;
    private final OnFuncionarioDelete onFuncionarioDelete;
    private final Context mContext;

    public ListaFuncionariosAdapter(Context context, List<Funcionario> dataSet, OnFuncionarioDelete onFuncionarioDelete) {
        localDataSet = dataSet;
        this.onFuncionarioDelete = onFuncionarioDelete;
        mContext = context;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNome, tvCargo, tvTelefone;
        private final ImageView ivEdit, ivApagar;

        public ViewHolder(View view) {
            super(view);

            tvNome = view.findViewById(R.id.tv_nome);
            tvCargo = view.findViewById(R.id.tv_cargo);
            tvTelefone = view.findViewById(R.id.tv_telefone);

            ivEdit = view.findViewById(R.id.iv_edit);
            ivApagar = view.findViewById(R.id.iv_apagar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_funcionario, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Funcionario funcionario = localDataSet.get(position);

        viewHolder.tvNome.setText(funcionario.nome);
        viewHolder.tvCargo.setText(funcionario.cargo);
        viewHolder.tvTelefone.setText(valueOf(funcionario.telefone));
        viewHolder.ivEdit.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, FuncionarioActivity.class);
            intent.putExtra("funcionario", funcionario);
            mContext.startActivity(intent);
        });
        viewHolder.ivApagar.setOnClickListener(v -> removeDeletion(funcionario));
    }

    private void removeDeletion(Funcionario funcionario) {
        new AlertDialog.Builder(mContext)
                .setMessage("Confirma a EXCLUSAO do funcionário ?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    try {
                        FuncionarioDao dao = HotelMontainDatabase.getInstance(mContext).funcionarioDao();
                        dao.remover(funcionario);
                        onFuncionarioDelete.remover(funcionario);
                        ToastUtil.show(mContext, "Funcionário excluido com sucesso");

                    } catch (Exception e) {
                        AlertUtil.showAlert(mContext, "Erro ao tentar remover funcionário");
                    }

                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnFuncionarioDelete {

        void remover(Funcionario funcionario);
    }
}