package com.romanbel.testjoymedia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.romanbel.testjoymedia.model.Shop;

import java.util.List;

/**
 * Created by roman on 20.01.18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static List<Shop> mList;
    private static Context mContext;

    public Context getContext() {
        return mContext;
    }

    public RecyclerAdapter(List<Shop> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View wordView = inflater.inflate(R.layout.item_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(wordView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView name = holder.mTxtName;
        name.setText(mList.get(position).getName());
        TextView address = holder.mTxtAddress;
        address.setText(mList.get(position).getAddress());
        TextView hours = holder.mTxtHours;
        hours.setText(mList.get(position).getOpeningHours());
        TextView distance = holder.mTxtDistance;
        distance.setText(mList.get(position).getDistance().toString());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtName, mTxtAddress, mTxtHours, mTxtDistance;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtName = (TextView) itemView.findViewById(R.id.txt_name);
            mTxtAddress = itemView.findViewById(R.id.txt_address);
            mTxtHours = itemView.findViewById(R.id.txt_hours);
            mTxtDistance = itemView.findViewById(R.id.txt_distance);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mList.get(getAdapterPosition()).getName(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}

