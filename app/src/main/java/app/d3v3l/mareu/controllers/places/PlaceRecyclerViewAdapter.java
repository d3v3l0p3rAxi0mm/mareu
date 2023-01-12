package app.d3v3l.mareu.controllers.places;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.model.Place;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceRecyclerViewAdapter extends RecyclerView.Adapter<PlaceRecyclerViewAdapter.ViewHolder> {

    private final List<Place> mPlaces;
    private PlaceDetailsFragment detailsFragment;

    public PlaceRecyclerViewAdapter(List<Place> items) {
        mPlaces = items;
    }

    @NonNull
    @Override
    public PlaceRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Place place = mPlaces.get(position);
        holder.mPlaceName.setText(place.getName());
        holder.mPlacePhoto.setImageResource(place.getPhoto());
        String capacityOfPlace = place.getCapacity() + " seats";
        holder.mPlaceCapacity.setText(capacityOfPlace);

        // Detect the orientation of device
        if (holder.mPlaceMainLayout.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            holder.mPlaceMainLayout.setOnClickListener(view -> {
                FragmentManager manager = ((AppCompatActivity) holder.mPlaceMainLayout.getContext()).getSupportFragmentManager();
                detailsFragment = PlaceDetailsFragment.newInstance(place.getId());
                manager.beginTransaction()
                        .replace(R.id.container_details, detailsFragment)
                        .commit();
            });
        } else {
            holder.mPlaceMainLayout.setOnClickListener(view -> {
                Intent intent = new Intent(holder.mPlaceMainLayout.getContext(), PlaceDetailsActivity.class);
                intent.putExtra("placeId", place.getId());
                holder.mPlaceMainLayout.getContext().startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.placeFragment_mainLayout)
        public LinearLayout mPlaceMainLayout;
        @BindView(R.id.placeFragment_placePhoto)
        public ImageView mPlacePhoto;
        @BindView(R.id.placeFragment_place)
        public TextView mPlaceName;
        @BindView(R.id.placeFragment_numberOfParticipants)
        public TextView mPlaceCapacity;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
