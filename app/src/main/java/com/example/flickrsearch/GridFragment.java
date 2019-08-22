package com.example.flickrsearch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GridFragment extends Fragment {

    final String APIKEY = "949e98778755d1982f537d56236bbb42";
    final String GETRECENT = "flickr.photos.getRecent";
    final String SEARCH = "flickr.photos.search";

    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private List<Photo> photos = new ArrayList<Photo>();

    public GridFragment() {
        // Required empty public constructor
    }

    public static GridFragment newInstance() {
        GridFragment fragment = new GridFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRecent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        recyclerView = view.findViewById(R.id.recycler);

        final InfoFragment infoFragment = new InfoFragment();
        PhotoAdapter.EventListener listener = new PhotoAdapter.EventListener() {
            @Override
            public void onClickPhoto(Photo photo) {
                FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                trans.remove(GridFragment.this);
                trans.add(infoFragment, "");
                trans.addToBackStack(null);
                trans.commit();
            }
        };
        adapter = new PhotoAdapter(photos, listener);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void getRecent() {
        FlickrAPI flickrAPI = RetrofitManager.getInstance().getAPI();
        Call<Data> call = flickrAPI.getRecent(GETRECENT, APIKEY, "json", "1");
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.d("Call", response.body().getPhotos().getPhoto().get(0).getURL());
                adapter.photos = response.body().getPhotos().getPhoto();
                Log.d("Photos", String.valueOf(photos.size()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("Call", "Fail");
            }
        });
    }

    public void search(final String keyword) {
        FlickrAPI flickrAPI = RetrofitManager.getInstance().getAPI();
        Call<Data> call = flickrAPI.search(GETRECENT, APIKEY, keyword, "json", "1");
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.d("Call", response.body().getPhotos().getPhoto().get(0).getURL());
                Log.d("Keyword", keyword);
                adapter.photos = response.body().getPhotos().getPhoto();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("Call", "Fail");
            }
        });
    }

    public void setSpanCount(Integer count) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), count);
        recyclerView.setLayoutManager(layoutManager);
    }

}
