package com.romanbel.testjoymedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 20.01.18.
 */

public class Fragment2 extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, null);

        List<String> list = new ArrayList<String>();
        list.add("Январь");
        list.add("Февраль");
        list.add("Март");
        list.add("Апрель");
        list.add("Май");
        list.add("Июнь");
        list.add("Январь");
        list.add("Февраль");
        list.add("Март");
        list.add("Апрель");
        list.add("Май");
        list.add("Июнь");
        list.add("Январь");
        list.add("Февраль");
        list.add("Март");
        list.add("Апрель");
        list.add("Май");
        list.add("Июнь");

        RecyclerAdapter mRecyclerAdapter = new RecyclerAdapter(list, getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mRecyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        return view;
    }
}
