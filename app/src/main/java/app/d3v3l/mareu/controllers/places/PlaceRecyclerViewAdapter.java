package app.d3v3l.mareu.controllers.places;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.databinding.FragmentPlaceBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Place;
import app.d3v3l.mareu.service.MaReuApiService;

public class PlaceRecyclerViewAdapter extends RecyclerView.Adapter<PlaceRecyclerViewAdapter.ViewHolder> {

    private final List<Place> mPlaces;
    public PlaceRecyclerViewAdapter(List<Place> items) {
        mPlaces = items;
    }

    @NonNull
    @Override
    public PlaceRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentPlaceBinding fragmentPlaceBinding = FragmentPlaceBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(fragmentPlaceBinding);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Place place = mPlaces.get(position);
        holder.bindView(place);
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentPlaceBinding fragmentPlaceBinding;
        private final MaReuApiService mApiService = DI.getMaReuApiService();

        public ViewHolder(@NonNull FragmentPlaceBinding fragmentPlaceBinding) {
            super(fragmentPlaceBinding.getRoot());
            this.fragmentPlaceBinding = fragmentPlaceBinding;
        }

        public void bindView(Place place) {
            fragmentPlaceBinding.placeFragmentPlacePhoto.setImageResource(place.getPhoto());
            fragmentPlaceBinding.placeFragmentPlace.setText(place.getName());
            String capacityOfPlace = place.getCapacity() + " seats";
            fragmentPlaceBinding.placeFragmentNumberOfParticipants.setText(capacityOfPlace);

            // Detect the orientation of device
            if (fragmentPlaceBinding.placeFragmentMainLayout.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                    fragmentPlaceBinding.placeFragmentMainLayout.getResources().getConfiguration().screenWidthDp >= 600) {
                fragmentPlaceBinding.placeFragmentMainLayout.setOnClickListener(view -> {
                    mApiService.setSelectedPlace(place);
                    FragmentManager manager = ((AppCompatActivity) fragmentPlaceBinding.placeFragmentMainLayout.getContext()).getSupportFragmentManager();
                    PlaceDetailsFragment detailsFragment = PlaceDetailsFragment.newInstance();
                    manager
                            .beginTransaction()
                            .replace(R.id.container_details, detailsFragment)
                            .commit();
                });
            } else {
                fragmentPlaceBinding.placeFragmentMainLayout.setOnClickListener(view -> {
                    mApiService.setSelectedPlace(place);
                    Intent intent = new Intent(fragmentPlaceBinding.placeFragmentMainLayout.getContext(), PlaceDetailsActivity.class);
                    fragmentPlaceBinding.placeFragmentMainLayout.getContext().startActivity(intent);
                });
            }
        }
    }

}
