package com.dumblabs.co.coviddaily;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterDistrict extends RecyclerView.Adapter<ListAdapterDistrict.ListViewHolderDistrict>{

    private Context contextDistrict;
    private List<ListElementsDistrict> listElementsDistrictList;


    public ListAdapterDistrict(Context contextDistrict, List<ListElementsDistrict> listElementsDistrictList) {
        this.contextDistrict = contextDistrict;
        this.listElementsDistrictList = listElementsDistrictList;
    }

    @NonNull
    @Override
    public ListViewHolderDistrict onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(contextDistrict);
        View view = inflater.inflate(R.layout.list_layout,null);
        return new ListViewHolderDistrict(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolderDistrict holder, final int position) {

        holder.cardView.setAnimation(AnimationUtils.loadAnimation(contextDistrict,R.anim.card_animation));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(contextDistrict,District.class);
                intent.putExtra("Title",listElementsDistrictList.get(position).getList_name_district());
                intent.putExtra("Total",listElementsDistrictList.get(position).getList_total());
                intent.putExtra("totaldelta",listElementsDistrictList.get(position).getDeltaTotalD());
                intent.putExtra("active",listElementsDistrictList.get(position).getActiveD());
                intent.putExtra("activedelta",listElementsDistrictList.get(position).getDeltaActiveD());
                intent.putExtra("recovered",listElementsDistrictList.get(position).getRecoveredD());
                intent.putExtra("recovereddelta",listElementsDistrictList.get(position).getDeltaRecoveredD());
                intent.putExtra("death",listElementsDistrictList.get(position).getDeathD());
                intent.putExtra("deathdelta",listElementsDistrictList.get(position).getDeltaDeathD());

                contextDistrict.startActivity(intent);
            }
        });

        ListElementsDistrict listElementsDistrict= listElementsDistrictList.get(position);
        holder.listNameView.setText(listElementsDistrict.getList_name_district());
        holder.listTotalView.setText(listElementsDistrict.getList_total());
    }

    @Override
    public int getItemCount() {
        return listElementsDistrictList.size();
    }

    class ListViewHolderDistrict extends RecyclerView.ViewHolder{

        TextView listNameView;
        TextView listTotalView;
        CardView cardView;

        public ListViewHolderDistrict(@NonNull View itemView) {
            super(itemView);

            cardView= itemView.findViewById(R.id.cardView);
            listNameView = itemView.findViewById(R.id.listNameView);
            listTotalView = itemView.findViewById(R.id.listTotalView);
        }
    }



}
