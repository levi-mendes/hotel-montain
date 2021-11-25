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
import com.example.hotelmontain.activities.cadastros.ReservaActivity;
import com.example.hotelmontain.database.HotelMontainDatabase;
import com.example.hotelmontain.database.dao.ReservaDao;
import com.example.hotelmontain.database.entity.Reserva;
import com.example.hotelmontain.util.AlertUtil;
import com.example.hotelmontain.util.ToastUtil;

import java.util.List;

import static java.lang.String.valueOf;

public class ListaReservasAdapter extends RecyclerView.Adapter<ListaReservasAdapter.ViewHolder> {

    private final List<Reserva> localDataSet;
    private final OnReservaDelete onReservaDelete;
    private final Context mContext;

    public ListaReservasAdapter(Context context, List<Reserva> dataSet, OnReservaDelete onReservaDelete) {
        localDataSet = dataSet;
        this.onReservaDelete = onReservaDelete;
        mContext = context;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNumReserva, tvNumQuarto, tvQtdeHospedes, tvDataHorario;
        private final ImageView ivEdit, ivApagar;

        public ViewHolder(View view) {
            super(view);

            tvNumReserva = view.findViewById(R.id.tv_num_reserva);
            tvNumQuarto = view.findViewById(R.id.tv_num_quarto);
            tvQtdeHospedes = view.findViewById(R.id.tv_qtde_hospedes);
            tvDataHorario = view.findViewById(R.id.tv_data_horario);

            ivEdit = view.findViewById(R.id.iv_edit);
            ivApagar = view.findViewById(R.id.iv_apagar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_reservas, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Reserva reserva = localDataSet.get(position);

        viewHolder.tvNumQuarto.append(valueOf(reserva.numQuarto));
        viewHolder.tvNumReserva.append(valueOf(reserva.numReserva));
        viewHolder.tvQtdeHospedes.append(valueOf(reserva.numHospedes));
        viewHolder.tvDataHorario.append(valueOf(reserva.dataHorario));
        viewHolder.ivEdit.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ReservaActivity.class);
            intent.putExtra("reserva", reserva);
            mContext.startActivity(intent);
        });
        viewHolder.ivApagar.setOnClickListener(v -> removeDeletion(reserva));
    }

    private void removeDeletion(Reserva reserva) {
        new AlertDialog.Builder(mContext)
                .setMessage("Confirma a EXCLUSAO dessa reserva ?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    try {
                        ReservaDao dao = HotelMontainDatabase.getInstance(mContext).reservaDao();
                        dao.remover(reserva);
                        onReservaDelete.remover(reserva);
                        ToastUtil.show(mContext, "Reserva excluido com sucesso");

                    } catch (Exception e) {
                        AlertUtil.showAlert(mContext, "Erro ao tentar remover reserva");
                    }

                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnReservaDelete {

        void remover(Reserva reserva);
    }
}