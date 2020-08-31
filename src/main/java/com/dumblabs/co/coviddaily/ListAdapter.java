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

import java.io.Serializable;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;
    private List<ListElements> listElementsList;

    public ListAdapter(Context context, List<ListElements> listElementsList) {
        this.context = context;
        this.listElementsList = listElementsList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_layout,null);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {

        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.card_animation));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,StateActivity.class);
                intent.putExtra("Title",listElementsList.get(position).getList_name_view());
                intent.putExtra("Total",listElementsList.get(position).getList_total_view());
                intent.putExtra("totaldelta",listElementsList.get(position).getDeltaTotal());
                intent.putExtra("active",listElementsList.get(position).getActive());
                intent.putExtra("activedelta",listElementsList.get(position).getDeltaActive());
                intent.putExtra("recovered",listElementsList.get(position).getRecovered());
                intent.putExtra("recovereddelta",listElementsList.get(position).getDeltaRecovered());
                intent.putExtra("death",listElementsList.get(position).getDeath());
                intent.putExtra("deathdelta",listElementsList.get(position).getDeltaDeath());
                intent.putExtra("statecode", listElementsList.get(position).getStatecode());

                context.startActivity(intent);

            }
        });

        ListElements listElements= listElementsList.get(position);
        holder.listNameView.setText(listElements.getList_name_view());
        holder.listTotalView.setText(listElements.getList_total_view());

    }

    @Override
    public int getItemCount() {
        return listElementsList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView listNameView;
        TextView listTotalView;
        CardView cardView;

        public ListViewHolder(View itemView) {
            super(itemView);

            cardView= itemView.findViewById(R.id.cardView);
            listNameView = itemView.findViewById(R.id.listNameView);
            listTotalView = itemView.findViewById(R.id.listTotalView);

        }
    }

}
