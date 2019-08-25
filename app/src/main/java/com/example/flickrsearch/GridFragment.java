package com.example.flickrsearch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GridFragment extends Fragment {

    private static final String TAG = "GridFragment";
    private static final String API_KEY = "949e98778755d1982f537d56236bbb42";
    private static final String GET_RECENT = "flickr.photos.getRecent";
    private static final String SEARCH = "flickr.photos.search";

    private RecyclerView recyclerView;
    private PhotoAdapter adapter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        recyclerView = view.findViewById(R.id.recycler);

        PhotoEventListener listener = new PhotoEventListener() {
            @Override
            public void onClick(Photo photo) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("photo", photo);
                startActivity(intent);
            }
        };
        adapter = new PhotoAdapter(listener);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        getRecent();
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
        Call<Data> call = flickrAPI.getRecent(GET_RECENT, API_KEY, "json", "1");
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                List<Photo> photoList = response.body().getPhotos().getPhoto();
                adapter.setPhotos(photoList);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d(TAG, "Fail");
            }
        });
    }

    public void search(final String keyword) {
        FlickrAPI flickrAPI = RetrofitManager.getInstance().getAPI();
        Call<Data> call = flickrAPI.search(SEARCH, API_KEY, keyword, "json", "1");
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                List<Photo> photoList = response.body().getPhotos().getPhoto();
                adapter.setPhotos(photoList);
                if (photoList.size() > 0) {
                    Log.d(TAG, photoList.get(0).getURL());
                } else {
                    new AlertDialog.Builder(getContext())
                            .setMessage("Please change the keyword.")
                            .setTitle("There is no related photo.")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d(TAG, "Fail");
            }
        });
    }

    public void setSpanCount(int count) {
        ((GridLayoutManager)recyclerView.getLayoutManager()).setSpanCount(count);
        adapter.notifyDataSetChanged();
    }

}
