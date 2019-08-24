package com.example.flickrsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {
    final String APIKEY = "949e98778755d1982f537d56236bbb42";
    final String GETINFO = "flickr.photos.getInfo";

    private FragmentManager fragmentManager;
    private InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra("photo");
        fragmentManager = getSupportFragmentManager();
        infoFragment = InfoFragment.newInstance(photo);
        FlickrAPI flickrAPI = RetrofitManager.getInstance().getAPI();
        Call<PhotoInfo> call = flickrAPI.getInfo(GETINFO, APIKEY, photo.getId(), "json", "1");
        call.enqueue(new Callback<PhotoInfo>() {
            @Override
            public void onResponse(Call<PhotoInfo> call, Response<PhotoInfo> response) {
                PhotoInfo.Info info = response.body().getInfo();
                infoFragment.setInfo(info);
                Log.d("Info Title", info.getTitle().get_content());
                Log.d("Info Description", info.getDescription().get_content());
                Log.d("Info Username", info.getOwner().getUsername());
                Log.d("Info Realname", info.getOwner().getRealname());

            }

            @Override
            public void onFailure(Call<PhotoInfo> call, Throwable t) {
                Log.d("Info", "Fail");
            }
        });
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(R.id.info_frag_container, infoFragment);
        trans.commit();
    }
}
