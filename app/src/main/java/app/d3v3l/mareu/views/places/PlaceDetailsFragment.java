package app.d3v3l.mareu.views.places;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.utils.OnButtonClickedListener;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceDetailsFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;

    // UI Components
    @BindView(R.id.placeDetails_photo)
    ImageView mPhoto;
    @BindView(R.id.placeDetails_name)
    TextView mPlaceName;
    @BindView(R.id.placeDetails_availableSeats)
    TextView mSeats;

    private Place mPlace;
    private int placeId;

    // Call apiService
    MaReuApiService mApiService = DI.getMaReuApiService();
    private static final String PLACEID = "placeId";

    public PlaceDetailsFragment() {
        // Required empty public constructor
    }

    public static PlaceDetailsFragment newInstance(int placeId) {
        PlaceDetailsFragment fragment = new PlaceDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(PLACEID, placeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            placeId = getArguments().getInt(PLACEID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_details, container, false);
        view.findViewById(R.id.back).setOnClickListener(this);
        ButterKnife.bind(this, view);
        mPlace = mApiService.getPlaceById(placeId);
        mPhoto.setBackgroundResource(mPlace.getPhoto());
        mPlaceName.setText(mPlace.getName());
        String capacity = String.valueOf(mPlace.getCapacity()) + " seats";
        mSeats.setText(capacity);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    @Override
    public void onClick(View view) {
        mCallback.onButtonClicked(view);
    }

    // Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e + " must implement OnButtonClickedListener");
        }
    }
}