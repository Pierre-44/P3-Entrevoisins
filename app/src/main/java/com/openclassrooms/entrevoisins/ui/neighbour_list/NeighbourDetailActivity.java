package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.OnClick;

public class NeighbourDetailActivity extends AppCompatActivity {

    // a déplacer dans un fragment
    private ImageView mAvatar;
    private FloatingActionButton mFavoriteButton;
    private TextView mName;
    private TextView mAddress;
    private TextView mPhone;
    private TextView mWeblink;
    private TextView mAboutMeText;
    private TextView mToolbarName;
    private Toolbar mToolbar;

    private NeighbourApiService mApiService;
    public Neighbour profil;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        mApiService = DI.getNeighbourApiService();

        settingFavoriteButton();

        //widgets connection
        mAvatar = findViewById(R.id.image_avatar_detail);
        mFavoriteButton = findViewById(R.id.button_favoris);
        mName = findViewById(R.id.text_name_detail);
        mAddress = findViewById(R.id.text_adress_detail);
        mPhone = findViewById(R.id.text_phone_number_detail);
        mWeblink = findViewById(R.id.text_web_link_detail);
        mAboutMeText = findViewById(R.id.text_about_me_text_content_detail);
        mToolbar = findViewById(R.id.toolbar_detail);

        initView();
        configureToolbar();
    }

    // Initialisation of view
    private void initView() {

        //Get clicked neighbour from Extra
        Neighbour neighbour = (Neighbour) getIntent().getSerializableExtra("Neighbour");

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mAvatar);
        mName.setText(neighbour.getName());
        mAddress.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mWeblink.setText(neighbour.getName());
        mAboutMeText.setText(neighbour.getAboutMe());
    }

    private void configureToolbar() {
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        //Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        //Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        //Disable Title

    }



    //Favorites button control

    @SuppressLint("ResourceAsColor")
    private void setFavoriteButtonDisable() {
        mFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        ImageViewCompat.setImageTintList(mFavoriteButton, ColorStateList.valueOf(R.color.colorStarYellow));
    }

    @SuppressLint("ResourceAsColor")
    private void setFavoriteButtonEnable() {
        mFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        ImageViewCompat.setImageTintList(mFavoriteButton, ColorStateList.valueOf(R.color.colorStarYellow));
    }

    private void settingFavoriteButton() {
        if (!profil.getIsFavoris()) {
            setFavoriteButtonDisable();
        } else
            setFavoriteButtonEnable();
        mFavoriteButton.setOnClickListener(this::onClick);
    }

    private void onClick(View v) {

        if (!profil.getIsFavoris()) {
            mApiService.addNeighbourOnFavoris(profil);
            setFavoriteButtonEnable();
        } else {
            mApiService.removeNeighbourOnFavoris(profil);
            setFavoriteButtonDisable();
        }
    }
}