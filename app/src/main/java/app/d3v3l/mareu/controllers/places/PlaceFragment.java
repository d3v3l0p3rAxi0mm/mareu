package app.d3v3l.mareu.controllers.places;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.service.MaReuApiService;

public class PlaceFragment extends Fragment {

    private MaReuApiService mApiService;
    private RecyclerView mRecyclerView;

    public PlaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     */

    public static PlaceFragment newInstance() {
        return new PlaceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMaReuApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        // define one or two columns depending of orientation of Device
        if (
                mRecyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT &&
                mRecyclerView.getResources().getConfiguration().screenWidthDp >= 600
        ) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        return view;

    }

    /**
     * Init the List of Places
     */
    private void initList() {
        mRecyclerView.setAdapter(new PlaceRecyclerViewAdapter(mApiService.getPlaces()));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

}
