package io.keepcoding.madridguide.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.util.OnElementClick;
import io.keepcoding.madridguide.views.MadridActivityRowViewHolder;

public class MadridActivitiesAdapter extends RecyclerView.Adapter<MadridActivityRowViewHolder> {

    private MadridActivities madridActivities;
    private LayoutInflater layoutInflater;

    private OnElementClick<MadridActivity> listener;

    public MadridActivitiesAdapter(MadridActivities madridActivities, Context context) {
        this.madridActivities = madridActivities;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MadridActivityRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_madridactivity, parent, false);

        return new MadridActivityRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MadridActivityRowViewHolder holder, final int position) {
        final MadridActivity madridActivity = madridActivities.get(position);

        holder.setMadridActivity(madridActivity);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.clickedOn(madridActivity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int) madridActivities.size();
    }

    public void setOnElementClickListener(@NonNull final OnElementClick<MadridActivity> listener) {
        this.listener = listener;
    }

}
