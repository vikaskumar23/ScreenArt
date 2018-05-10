package com.dragfoundation.screenart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CompletedOrderAdapter2 extends RecyclerView.Adapter<CompletedOrderAdapter2.OrderViewHolder>{


    ArrayList<OrderStat> orderData;
    String cat,date;
    Context x;

    public CompletedOrderAdapter2(Context ctx, ArrayList<OrderStat> notificationData){
        this.x=ctx;
        this.orderData = notificationData;

    }


    @Override
    public CompletedOrderAdapter2.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list, parent, false);

        return new CompletedOrderAdapter2.OrderViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final CompletedOrderAdapter2.OrderViewHolder holder, final int position) {
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderStat k=orderData.get(position);
                Intent i=new Intent(x,Invoice.class);
                i.putExtra("data",k);
                x.startActivity(i);

            }
        });

        switch (orderData.get(position).getCategory())
        {
            case 1: cat="Visiting cards";
                break;
            case 2: cat="Letter Heads";
                break;
            case 3: cat="Envelopes";
                break;
            case 4: cat="Books";
                break;
            case 5: cat="Marriage Cards";
                break;
        }


        Calendar s=Calendar.getInstance();
        s.setTimeInMillis(orderData.get(position).getDate());
        date="Placed on "+ DateFormat.format("dd MMMM, yyyy", s).toString()+" at "+DateFormat.format("h:mm a", s).toString();


        holder.orderID.setText("Order ID SA-00000"+orderData.get(position).getOrder());
        holder.date.setText(""+date);
        holder.category.setText(""+cat);
        holder.quantity.setText("Quantity : "+orderData.get(position).getQuantity());


    }

    @Override
    public int getItemCount() {
        return orderData.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        public TextView orderID , date , category , quantity ;
        public Button submit ;

        public OrderViewHolder(View itemView) {
            super(itemView);

            orderID = itemView.findViewById(R.id.orderIdsa);
            date = itemView.findViewById(R.id.datesa);
            category = itemView.findViewById(R.id.categorysa);
            quantity = itemView.findViewById(R.id.quantitysa);


            submit = itemView.findViewById(R.id.submitsa);


        }

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
