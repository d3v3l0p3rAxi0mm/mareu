package app.d3v3l.mareu.controllers.places;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.d3v3l.mareu.databinding.FragmentPlaceDetailsBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.controllers.utils.OnButtonClickedListener;

public class PlaceDetailsFragment extends Fragment implements View.OnClickListener {

    private OnButtonClickedListener mCallback;
    private FragmentPlaceDetailsBinding binding;
    // Call apiService
    MaReuApiService mApiService = DI.getMaReuApiService();

    public PlaceDetailsFragment() {
        // Required empty public constructor
    }

    public static PlaceDetailsFragment newInstance() {
        return new PlaceDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlaceDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Place place = mApiService.getSelectedPlace();
        binding.placeDetailsPhoto.setImageDrawable(binding.placeDetailsPhoto.getResources().getDrawable(place.getPhoto()));
        binding.placeDetailsName.setText(place.getName());
        String capacity = place.getCapacity() + " seats";
        binding.placeDetailsAvailableSeats.setText(capacity);
        binding.back.setOnClickListener(this);

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