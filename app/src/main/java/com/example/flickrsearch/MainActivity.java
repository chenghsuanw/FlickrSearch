package com.example.flickrsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Switch switcher;
    private FragmentManager fragmentManager;
    private GridFragment gridFragment;
    private Button btn_search;
    private EditText ed_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_search = findViewById(R.id.btn_search);
        ed_search = findViewById(R.id.ed_search);
        switcher = findViewById(R.id.switcher);

        gridFragment = GridFragment.newInstance();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(R.id.fragment_container, gridFragment);
        trans.commit();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = ed_search.getText().toString();
                if (!keyword.isEmpty()) {
                    gridFragment.search(keyword);
                } else {
                    Log.d("Keyword", "empty");
                }
            }
        });

        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Log.d("Switch", "List");
                    gridFragment.setSpanCount(1);
                } else {
                    Log.d("Switch", "Grid");
                    gridFragment.setSpanCount(2);
                }
            }
        });
    }
}
