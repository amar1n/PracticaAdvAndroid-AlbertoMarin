package io.keepcoding.madridguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.util.Constants;

public class MadridActivityDetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_madridactivity_detail_madridactivity_name_text)
    TextView madridActivityNameText;

    @BindView(R.id.activity_madridactivity_detail_madridactivity_logo_image)
    ImageView madridActivityLogoImage;

    MadridActivity madridActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madridactivity_detail);

        ButterKnife.bind(this);

        getDetailMadridActivityFromCallingIntent();

        updateUI();
    }

    private void getDetailMadridActivityFromCallingIntent() {
        Intent i = getIntent();
        if (i != null) {
            madridActivity = (MadridActivity) i.getSerializableExtra(Constants.INTENT_KEY_MADRIDACTIVITY_DETAIL);
        }
    }

    private void updateUI() {
        madridActivityNameText.setText(madridActivity.getName());
        Picasso.with(this)
                .load(madridActivity.getLogoImgUrl())
                .into(madridActivityLogoImage);
    }
}
