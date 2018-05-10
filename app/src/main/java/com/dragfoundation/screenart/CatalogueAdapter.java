package com.dragfoundation.screenart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vikas on 22-12-2017.
 */


public class CatalogueAdapter extends RecyclerView.Adapter<CatalogueAdapter.ViewHolder> {

    private List<Catalogue> mDataset;
    Context x;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CatalogueAdapter(List<Catalogue> mDataset,Context ctx) {
        this.x=ctx;
        this.mDataset = mDataset;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView proimg;
        public TextView product;
        public TextView price;
        public TextView quantity;
        Button view;

        public ViewHolder(View v) {
            super(v);
            proimg=(ImageView)v.findViewById(R.id.catImg);
            product=(TextView)v.findViewById(R.id.catPro);
            price=(TextView)v.findViewById(R.id.catPrice);
            quantity=(TextView)v.findViewById(R.id.catq);
            view=(Button)v.findViewById(R.id.viewCatalogue);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CatalogueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catalogue, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Catalogue cat=mDataset.get(position);
        holder.proimg.setImageResource(cat.getId());
        holder.product.setText(cat.getProduct());
        holder.price.setText("Starts from "+cat.getPrice());
        holder.quantity.setText(cat.getQuantity()+" Units");

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send=new Intent(x,ComingSoon.class);
                x.startActivity(send);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
