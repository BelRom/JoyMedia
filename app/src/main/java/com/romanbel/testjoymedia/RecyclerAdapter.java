package com.romanbel.testjoymedia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by roman on 20.01.18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static List<String> mList;
    private static Context mContext;

    public Context getContext() {
        return mContext;
    }

    public RecyclerAdapter(List<String> list, Context context) {
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

        TextView firstItemColumn = holder.mFirstItemColumn;
        firstItemColumn.setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mFirstItemColumn;

        public ViewHolder(View itemView) {
            super(itemView);
            mFirstItemColumn = (TextView) itemView.findViewById(R.id.txt_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mList.get(getAdapterPosition()),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}

