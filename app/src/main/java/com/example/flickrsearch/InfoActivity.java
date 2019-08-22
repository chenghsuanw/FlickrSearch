package com.example.flickrsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class InfoActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Log.d("InfoActivity", "hello");
        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra("photo");
        fragmentManager = getSupportFragmentManager();
        infoFragment = InfoFragment.newInstance(photo);
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(R.id.info_frag_container, infoFragment);
        trans.commit();
    }
}
