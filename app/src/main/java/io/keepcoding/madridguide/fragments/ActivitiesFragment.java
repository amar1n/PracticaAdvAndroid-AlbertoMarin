package io.keepcoding.madridguide.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.adapters.ActivitiesAdapter;
import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.util.OnElementClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    private RecyclerView activitiesRecyclerView;
    private ActivitiesAdapter adapter;
    private Activities activities;
    private OnElementClick<Activity> listener;

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        activitiesRecyclerView = (RecyclerView) view.findViewById(R.id.activities_recycler_view);
        activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
        updateUI();
    }

    private void updateUI() {
        adapter = new ActivitiesAdapter(activities, getActivity());
        activitiesRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<Activity>() {
            @Override
            public void clickedOn(@NonNull Activity element, int position) {
                if (listener != null) {
                    listener.clickedOn(element, position);
                }
            }
        });

    }

    public OnElementClick<Activity> getListener() {
        return listener;
    }

    public void setListener(OnElementClick<Activity> listener) {
        this.listener = listener;
    }
}
