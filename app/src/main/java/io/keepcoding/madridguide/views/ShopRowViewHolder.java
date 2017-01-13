package io.keepcoding.madridguide.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.model.Shop;

public class ShopRowViewHolder extends RecyclerView.ViewHolder {
    TextView nameTextView;
    ImageView logoImageView;
    WeakReference<Context> context;

    public ShopRowViewHolder(View rowNote) {
        super(rowNote);

        context = new WeakReference<Context>(rowNote.getContext());
        nameTextView = (TextView) rowNote.findViewById(R.id.row_shop_name);
        logoImageView = (ImageView) rowNote.findViewById(R.id.row_shop_logo);
    }

    public void setShop(@Nullable Shop shop) {
        if (shop == null) {
            return;
        }
        this.nameTextView.setText(shop.getName());
        Picasso.with(context.get())
                .load(shop.getLogoImgUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(logoImageView);
    }
}
