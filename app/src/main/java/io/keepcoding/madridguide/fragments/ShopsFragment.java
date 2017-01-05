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
import io.keepcoding.madridguide.adapters.ShopsAdapter;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.util.OnElementClick;

public class ShopsFragment extends Fragment {
    private RecyclerView notesRecyclerView;
    private ShopsAdapter adapter;
    private Shops shops;
    private OnElementClick<Shop> listener;


    public ShopsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);

        notesRecyclerView = (RecyclerView) view.findViewById(R.id.shops_recycler_view);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void updateUI() {
        adapter = new ShopsAdapter(shops, getActivity());
        notesRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(@NonNull Shop element, int position) {
                if (listener != null) {
                    listener.clickedOn(element, position);
                }
            }
        });

    }

    public void setShops(Shops shops) {
        this.shops = shops;
        updateUI();
    }

    public OnElementClick<Shop> getListener() {
        return listener;
    }

    public void setListener(OnElementClick<Shop> listener) {
        this.listener = listener;
    }

}
