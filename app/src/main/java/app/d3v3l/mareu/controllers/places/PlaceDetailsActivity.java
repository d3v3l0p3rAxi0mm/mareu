package app.d3v3l.mareu.controllers.places;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.utils.OnButtonClickedListener;

public class PlaceDetailsActivity extends AppCompatActivity implements OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        this.configureAndShowDetailFragment();
    }

    private void configureAndShowDetailFragment() {
        PlaceDetailsFragment detailsPlaceFragment = PlaceDetailsFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_detailsPlace, detailsPlaceFragment)
                .commit();
    }

    @Override
    public void onButtonClicked(View view) {
        PlaceDetailsActivity.this.finish();
    }
}