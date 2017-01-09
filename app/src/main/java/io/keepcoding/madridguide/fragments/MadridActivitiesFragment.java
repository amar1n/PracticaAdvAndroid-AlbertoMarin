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
import io.keepcoding.madridguide.adapters.MadridActivitiesAdapter;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.util.OnElementClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MadridActivitiesFragment extends Fragment {

    private RecyclerView madridActivitiesRecyclerView;
    private MadridActivitiesAdapter adapter;
    private MadridActivities madridActivities;
    private OnElementClick<MadridActivity> listener;

    public MadridActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_madridactivities, container, false);

        madridActivitiesRecyclerView = (RecyclerView) view.findViewById(R.id.madridactivities_recycler_view);
        madridActivitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void setMadridActivities(MadridActivities madridActivities) {
        this.madridActivities = madridActivities;
        updateUI();
    }

    private void updateUI() {
        adapter = new MadridActivitiesAdapter(madridActivities, getActivity());
        madridActivitiesRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<MadridActivity>() {
            @Override
            public void clickedOn(@NonNull MadridActivity element, int position) {
                if (listener != null) {
                    listener.clickedOn(element, position);
                }
            }
        });

    }

    public OnElementClick<MadridActivity> getListener() {
        return listener;
    }

    public void setListener(OnElementClick<MadridActivity> listener) {
        this.listener = listener;
    }
}
