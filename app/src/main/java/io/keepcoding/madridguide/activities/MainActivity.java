package io.keepcoding.madridguide.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.interactors.CacheAllItemsInteractorResponse;
import io.keepcoding.madridguide.interactors.CacheAllMadridActivitiesInteractor;
import io.keepcoding.madridguide.interactors.CacheAllShopsInteractor;
import io.keepcoding.madridguide.interactors.GetAllItemsInteractorResponse;
import io.keepcoding.madridguide.interactors.GetAllMadridActivitiesInteractor;
import io.keepcoding.madridguide.interactors.GetAllShopsInteractor;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.navigator.Navigator;

public class MainActivity extends AppCompatActivity {

    protected int counter = 0;

    protected ViewSwitcher viewSwitcher;

    @BindView(R.id.button_activity_main_open_shops)
    Button openShopsButton;

    @BindView(R.id.button_activity_main_open_madridactivities)
    Button openMadridActivitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        setupSwitcher();
        setupShopsButton();
        setupData();
    }

    private void setupSwitcher() {
        viewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);
        viewSwitcher.setInAnimation(this, android.R.anim.fade_in);
        viewSwitcher.setOutAnimation(this, android.R.anim.fade_out);
    }

    private void setupShopsButton() {
        this.openShopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
            }
        });

        this.openMadridActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToMadridActivitiesActivity(MainActivity.this);
            }
        });
    }

    private void setupData() {
        // Esto puede ir a MainActivity para controlar la barra de carga
        new GetAllShopsInteractor().execute(getApplicationContext(),
                new GetAllItemsInteractorResponse<Shops>() {
                    @Override
                    public void response(Shops shops) {
                        new CacheAllShopsInteractor().execute(getApplicationContext(),
                                shops, new CacheAllItemsInteractorResponse() {
                                    @Override
                                    public void response(boolean success) {
                                        updateUI();
                                    }
                                });
                    }
                }
        );

        new GetAllMadridActivitiesInteractor().execute(getApplicationContext(),
                new GetAllItemsInteractorResponse<MadridActivities>() {
                    @Override
                    public void response(MadridActivities madridActivities) {
                        new CacheAllMadridActivitiesInteractor().execute(getApplicationContext(),
                                madridActivities, new CacheAllItemsInteractorResponse() {
                                    @Override
                                    public void response(boolean success) {
                                        updateUI();
                                    }
                                });
                    }
                }
        );
    }

    private void updateUI() {
        counter += 1;
        if (counter % 2 == 0) {
            viewSwitcher.setDisplayedChild(1);
        }
    }
}
