package com.romanbel.testjoymedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 20.01.18.
 */

public class Fragment1 extends Fragment {
    CardView mBtnCard;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, null);
        Log.d("TAG", "onCreateView");
        mBtnCard = view.findViewById(R.id.btn_card);
        mBtnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button click", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "click");
            }
        });
        return view;
    }
}
