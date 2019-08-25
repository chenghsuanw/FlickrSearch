package com.example.flickrsearch;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfoFragment extends Fragment {
    private static final String TAG = "InfoFragment";
    private static final String API_KEY = "949e98778755d1982f537d56236bbb42";
    private static final String GET_INFO = "flickr.photos.getInfo";

    private Photo photo;
    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvUserName;
    private TextView tvRealName;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(Photo photo) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putSerializable("photo", photo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photo = (Photo) getArguments().getSerializable("photo");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        findView(view);

        FlickrAPI flickrAPI = RetrofitManager.getInstance().getAPI();
        Call<PhotoInfo> call = flickrAPI.getInfo(GET_INFO, API_KEY, photo.getId(), "json", "1");
        call.enqueue(new Callback<PhotoInfo>() {
            @Override
            public void onResponse(Call<PhotoInfo> call, Response<PhotoInfo> response) {
                PhotoInfo.Info info = response.body().getInfo();
                tvTitle.setText(info.getTitle().get_content());
                tvDescription.setText(info.getDescription().get_content());
                tvUserName.setText(info.getOwner().getUserName());
                tvRealName.setText(info.getOwner().getRealName());
                Log.d(TAG, photo.getURL());
                Log.d(TAG, "Title:"+info.getTitle().get_content());
                Log.d(TAG, "Description:"+info.getDescription().get_content());
                Log.d(TAG, "User name:"+info.getOwner().getUserName());
                Log.d(TAG, "Real name:"+info.getOwner().getRealName());

            }

            @Override
            public void onFailure(Call<PhotoInfo> call, Throwable t) {
                Log.d(TAG, "Fail");
            }
        });
        Glide.with(imageView.getContext())
                .load(Uri.parse(photo.getURL()))
                .centerCrop()
                .fitCenter()
                .override(400, 400)
                .into(imageView);
        return view;
    }

    public void findView(View view) {
        tvTitle = view.findViewById(R.id.info_title);
        tvDescription = view.findViewById(R.id.info_description);
        tvUserName = view.findViewById(R.id.info_username);
        tvRealName = view.findViewById(R.id.info_realname);
        imageView = view.findViewById(R.id.info_img);
    }
}
