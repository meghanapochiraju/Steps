package com.utd.mxp165130.steps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

import static android.widget.GridLayout.VERTICAL;

public class HistoryFragment extends Fragment {

    private static String STEP_COUNTER_DATA = "stepCounterData";
    private static String USER = "user";
    private static String DATA_OBJECT = "dataObject";
    private DataProcessing dataObject;
    private ArrayList<StepCounterInstance> stepCounterData;
    private UserAccount user;

    public static HistoryFragment newInstance(ArrayList<StepCounterInstance> stepCounterData, UserAccount user, DataProcessing dataObject) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(STEP_COUNTER_DATA, stepCounterData);
        args.putParcelable(USER, user);
        args.putParcelable(DATA_OBJECT, dataObject);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        stepCounterData = getArguments().getParcelableArrayList(STEP_COUNTER_DATA);
        user = getArguments().getParcelable(USER);
        dataObject = getArguments().getParcelable(DATA_OBJECT);
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        recyclerView.addOnItemTouchListener(
                new ItemClickListener(getContext(), new ItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(getContext(), SummaryActivity.class);
                        i.putExtra("UserObj", dataObject.getUserData());
                        i.putExtra("StepCounterObj", dataObject.getStepCounterData(position));
                        startActivity(i);
                    }
                })
        );
        Collections.sort(stepCounterData);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(stepCounterData, user);
        recyclerView.setAdapter(adapter);
        ((MainActivity) getActivity()).setAdapter(adapter);

        return recyclerView;
    }
}