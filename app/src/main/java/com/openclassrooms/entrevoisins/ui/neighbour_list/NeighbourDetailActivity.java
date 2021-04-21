package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourDetailActivity extends AppCompatActivity {

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

    private Neighbour profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        mApiService = DI.getNeighbourApiService();

        settingFavoriteButton();

        //widgets connection
        mAvatar = findViewById(R.id.image_avatar_detail);
        mFavoriteButton = findViewById(R.id.button_favoris_text);
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
    private void initView (){

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

    private void configureToolbar(){
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

    private void setFavoriteButtonDisable(){
        mFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
    }

    private void setFavoriteButtonEnable(){
        mFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
    }

    private void settingFavoriteButton() {
        if (!profil.isFavoris()) {
            setFavoriteButtonDisable();
        } else
            setFavoriteButtonEnable();
        mFavoriteButton.setOnClickListener(v -> {

            if (!profil.isFavoris()) {
                mApiService.addNeighbourOnFavoris(profil);
                setFavoriteButtonEnable();
            } else {
                mApiService.removeNeighbourOnFavoris(profil);
                setFavoriteButtonDisable();
            }
        });
    }
}