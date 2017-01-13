package io.keepcoding.madridguide.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    protected boolean bError = false;

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
        openShopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
            }
        });
        openShopsButton.setEnabled(false);

        openMadridActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToMadridActivitiesActivity(MainActivity.this);
            }
        });
        openMadridActivitiesButton.setEnabled(false);
    }

    private void setupData() {
        new GetAllShopsInteractor().execute(getApplicationContext(),
                new GetAllItemsInteractorResponse<Shops>() {
                    @Override
                    public void response(Shops shops) {
                        if (shops == null) {
                            bError = true;
                            updateUI();
                        } else {
                            new CacheAllShopsInteractor().execute(getApplicationContext(),
                                    shops, new CacheAllItemsInteractorResponse() {
                                        @Override
                                        public void response(boolean success) {
                                            bError = !success;
                                            updateUI();
                                        }
                                    });
                        }
                    }
                }
        );

        new GetAllMadridActivitiesInteractor().execute(getApplicationContext(),
                new GetAllItemsInteractorResponse<MadridActivities>() {
                    @Override
                    public void response(MadridActivities madridActivities) {
                        if (madridActivities == null) {
                            bError = true;
                            updateUI();
                        } else {
                            new CacheAllMadridActivitiesInteractor().execute(getApplicationContext(),
                                    madridActivities, new CacheAllItemsInteractorResponse() {
                                        @Override
                                        public void response(boolean success) {
                                            bError = !success;
                                            updateUI();
                                        }
                                    });
                        }
                    }
                }
        );
    }

    private void updateUI() {
        counter += 1;
        if (counter % 2 == 0) {
            viewSwitcher.setDisplayedChild(1);

            if (bError) {
                // Ha habido algún error, se lo notificamos al usuario
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Jorror!!!");
                alertDialog.setMessage("se tiró 3 alguna vaina!!!");
                alertDialog.setPositiveButton("inténtalo again!!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        viewSwitcher.setDisplayedChild(0);
                        counter = 0;
                        setupData();
                    }
                });
                alertDialog.show();
            } else {
                openMadridActivitiesButton.setEnabled(true);
                openShopsButton.setEnabled(true);
            }
        }
    }
}
