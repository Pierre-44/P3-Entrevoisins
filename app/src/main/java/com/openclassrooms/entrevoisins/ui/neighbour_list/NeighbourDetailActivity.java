package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class NeighbourDetailActivity extends AppCompatActivity {

    private ImageView mAvatar;
    private FloatingActionButton mFavoriteButton;
    private TextView mName;
    private TextView mAddress;
    private TextView mPhone;
    private TextView mWeb;
    private TextView mAboutMeText;
    private TextView mToolbarName;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //wire widgets
        mAddress = findViewById(R.id.myAdress);
        mAvatar = findViewById(R.id.avatar);
        //TODO : etc...

        initView();

    }

    private void initView (){
        Neighbour neighbour = (Neighbour) getIntent().getSerializableExtra("Neighbour");

        //mName.setText(neighbour.getName());
        //mAddress.setText(neighbour.getAddress());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mAvatar);
        //TODO : etc...

    }


}