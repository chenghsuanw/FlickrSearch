package com.example.flickrsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra("photo");
        FragmentManager fragmentManager = getSupportFragmentManager();
        InfoFragment infoFragment = InfoFragment.newInstance(photo);

        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(R.id.info_frag_container, infoFragment);
        trans.commit();
    }
}
