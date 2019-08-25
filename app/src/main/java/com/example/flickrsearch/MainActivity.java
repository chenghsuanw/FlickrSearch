package com.example.flickrsearch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String LIST = "List";
    private static final String GRID = "Grid";
    private Button switcher;
    private FragmentManager fragmentManager;
    private GridFragment gridFragment;
    private Button btnSearch;
    private EditText edtTxtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        View abView = getSupportActionBar().getCustomView();
        btnSearch = abView.findViewById(R.id.btn_search);
        edtTxtSearch = abView.findViewById(R.id.ed_search);
        switcher = findViewById(R.id.switcher);

        gridFragment = GridFragment.newInstance();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(R.id.fragment_container, gridFragment);
        trans.commit();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = edtTxtSearch.getText().toString();
                if (!keyword.replaceAll(" ", "").isEmpty()) {
                    gridFragment.search(keyword);
                } else {
                    gridFragment.getRecent();
                }
            }
        });

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("List".equals(switcher.getText().toString())) {
                    gridFragment.setSpanCount(1);
                    switcher.setText(GRID);
                } else {
                    gridFragment.setSpanCount(2);
                    switcher.setText(LIST);
                }
            }
        });

    }
}
