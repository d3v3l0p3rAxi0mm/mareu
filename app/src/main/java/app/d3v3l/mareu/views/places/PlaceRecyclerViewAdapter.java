package app.d3v3l.mareu.views.places;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.model.Place;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceRecyclerViewAdapter extends RecyclerView.Adapter<PlaceRecyclerViewAdapter.ViewHolder> {

    private final List<Place> mPlaces;

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
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
