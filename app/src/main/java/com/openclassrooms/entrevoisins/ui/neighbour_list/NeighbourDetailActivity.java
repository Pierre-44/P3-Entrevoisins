package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
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
    private Toolbar mToolbar;

    private NeighbourApiService mApiService;
    public Neighbour profil;
    private boolean mNameStartVowel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        mApiService = DI.getNeighbourApiService();
        profil = (Neighbour) getIntent().getSerializableExtra("Neighbour");

        //widgets connection
        mAvatar = findViewById(R.id.image_avatar_detail);
        mFavoriteButton = findViewById(R.id.button_favoris);
        mName = findViewById(R.id.text_name_detail);
        mAddress = findViewById(R.id.text_adress_detail);
        mPhone = findViewById(R.id.text_phone_number_detail);
        mWeblink = findViewById(R.id.text_web_link_detail);
        mAboutMeText = findViewById(R.id.text_about_me_text_content_detail);
        mToolbar = findViewById(R.id.toolbar_detail);

        configureToolbar();
        settingFavoriteButton();
        initView();
    }

    // Initialisation of view
    private void initView() {

        //Get clicked neighbour from Extra
        Glide.with(this)
                .load(profil.getAvatarUrl())
                .into(mAvatar);
        mName.setText(profil.getName());
        mAddress.setText(profil.getAddress());
        mPhone.setText(profil.getPhoneNumber());
        mWeblink.setText(profil.getWeblink());
        mAboutMeText.setText(profil.getAboutMe());
    }

    /**
     * Configure toolbar
     */
    private void configureToolbar() {
        //Set the toolbar
        setSupportActionBar(mToolbar);
        //Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Set name on toolbar
        getSupportActionBar().setTitle(profil.getName());

    }

    //Favorites button control
    private void settingFavoriteButton() {
        if (!profil.getIsFavoris()) {
            setFavoriteButtonDisable();
        } else
            setFavoriteButtonEnable();
        mFavoriteButton.setOnClickListener(this::onClick);
    }

    private void setFavoriteButtonDisable() {
        mFavoriteButton.setImageResource(R.drawable.ic_star_grey_24dp);
    }

    private void setFavoriteButtonEnable() {
        mFavoriteButton.setImageResource(R.drawable.ic_star_yellow_24dp);
    }


    private void onClick(View v) {
        if (!profil.getIsFavoris()) {
            profil.setFavoris(true);
            mApiService.addNeighbourOnFavoris(profil);
            addNeighbourOnFavoris(v);
            setFavoriteButtonEnable();
        } else {
            profil.setFavoris(false);
            mApiService.removeNeighbourOnFavoris(profil);
            removeNeighbourOnFavoris(v);
            setFavoriteButtonDisable();
        }
    }

    /**
     * Add neighbour in favorites, set message and pass it to method to display a snackBar and call method to properly set FAB
     *
     * @param v the view
     */
    private void addNeighbourOnFavoris(View v) {
        //Set boolean to adjust message depending on firstname first letter
        mNameStartVowel = "A E I O U Y H".contains(Character.toString(mName.getText().toString().charAt(0))) || "a e i o u y h".contains(Character.toString(mName.getText().toString().charAt(0)));
        profil.setFavoris(true);
        String toastThis;
        if (mNameStartVowel) {
            toastThis = "Ajout d'" + mName.getText() + " aux favoris.";
        } else {
            toastThis = "Ajout de " + mName.getText() + " aux favoris.";
        }
        snackBarThis(toastThis, v);
        setFavoriteButtonEnable();
        mApiService.addNeighbourOnFavoris(profil);
    }

    /**
     * Remove neighbour from favorites, set msg and pass it to method to display a snackBar and call method to properly set FAB
     *
     * @param v the view
     */
    private void removeNeighbourOnFavoris(View v) {
        profil.setFavoris(false);
        String toastThis;
        if (mNameStartVowel) {
            toastThis = "Retrait d'" + mName.getText() + " des favoris.";
        } else {
            toastThis = "Retrait de " + mName.getText() + " des favoris.";
        }
        snackBarThis(toastThis, v);
        setFavoriteButtonDisable();
        mApiService.removeNeighbourOnFavoris(profil);
    }

    /**
     * Display snackBar to confirm action on favorite button on neighbour
     *
     * @param toastThis this msg to display
     * @param v         the view
     */

    private void snackBarThis(String toastThis, View v) {
        Snackbar.make(v, toastThis, Snackbar.LENGTH_SHORT)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(getResources().getColor(R.color.colorAccent))
                .setTextColor(getResources().getColor(R.color.colorAppBarText))
                .show();
    }
}