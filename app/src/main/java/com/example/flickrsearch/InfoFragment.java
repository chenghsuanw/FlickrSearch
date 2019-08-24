package com.example.flickrsearch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

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
    final String APIKEY = "949e98778755d1982f537d56236bbb42";
    final String GETINFO = "flickr.photos.getInfo";

    private PhotoInfo.Info info;
    private Photo photo;
    private ImageView imageView;
    private TextView tv_title;
    private TextView tv_description;
    private TextView tv_userName;
    private TextView tv_realName;

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

//        FlickrAPI flickrAPI = RetrofitManager.getInstance().getAPI();
//        Call<PhotoInfo> call = flickrAPI.getInfo(GETINFO, APIKEY, photo.getId(), "json", "1");
//        call.enqueue(new Callback<PhotoInfo>() {
//            @Override
//            public void onResponse(Call<PhotoInfo> call, Response<PhotoInfo> response) {
//                PhotoInfo.Info info = response.body().getInfo();
//                tv_title.setText(info.getTitle().get_content());
//                tv_description.setText(info.getDescription().get_content());
//                tv_userName.setText(info.getOwner().getUsername());
//                tv_realName.setText(info.getOwner().getRealname());
//                Log.d("Info Title", info.getTitle().get_content());
//                Log.d("Info Description", info.getDescription().get_content());
//                Log.d("Info Username", info.getOwner().getUsername());
//                Log.d("Info Realname", info.getOwner().getRealname());
//
//            }
//
//            @Override
//            public void onFailure(Call<PhotoInfo> call, Throwable t) {
//                Log.d("Info", "Fail");
//            }
//        });
        Glide.with(imageView.getContext())
                .load(Uri.parse(photo.getURL()))
                .centerCrop()
                .fitCenter()
                .into(imageView);
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

    public void findView(View view) {
        tv_title = view.findViewById(R.id.info_title);
        tv_description = view.findViewById(R.id.info_description);
        tv_userName = view.findViewById(R.id.info_username);
        tv_realName = view.findViewById(R.id.info_realname);
        imageView = view.findViewById(R.id.info_img);
    }

    public void setInfo(PhotoInfo.Info info) {
        Log.d("Info", "setInfo");
        this.info = info;
        tv_title.setText(info.getTitle().get_content());
        tv_description.setText(info.getDescription().get_content());
        tv_userName.setText(info.getOwner().getUsername());
        tv_realName.setText(info.getOwner().getRealname());
    }
}
