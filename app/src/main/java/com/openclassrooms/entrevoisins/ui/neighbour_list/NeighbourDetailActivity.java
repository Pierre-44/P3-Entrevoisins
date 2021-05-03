package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ImageViewCompat;

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
    private TextView mToolbarName;
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
        mWeblink.setText(profil.getName());
        mAboutMeText.setText(profil.getAboutMe());

        //Set Favorite Button
        setFavoriteButtonDisable(profil.getIsFavoris());
        setFavoriteButtonEnable(profil.getIsFavoris());

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

    @SuppressLint("ResourceAsColor")
    private void setFavoriteButtonDisable(boolean isFavoris) {
        mFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
        ImageViewCompat.setImageTintList(mFavoriteButton, ColorStateList.valueOf(R.color.colorStarYellow));
    }

    @SuppressLint("ResourceAsColor")
    private void setFavoriteButtonEnable(boolean isFavoris) {
        mFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        ImageViewCompat.setImageTintList(mFavoriteButton, ColorStateList.valueOf(R.color.colorStarYellow));
    }

    private void settingFavoriteButton() {
        if (!profil.getIsFavoris()) {
            setFavoriteButtonDisable(profil.getIsFavoris());
        } else
            setFavoriteButtonEnable(profil.getIsFavoris());
        mFavoriteButton.setOnClickListener(this::onClick);
    }

    private void onClick(View v) {

        if (!profil.getIsFavoris()) {
            mApiService.addNeighbourOnFavoris(profil);
            setFavoriteButtonEnable(profil.getIsFavoris());
        } else {
            mApiService.removeNeighbourOnFavoris(profil);
            setFavoriteButtonDisable(profil.getIsFavoris());
        }
    }

    // TODO : add methode onClick  pour :

    // addNeighbourOnFavoris()
    // removeNeighbourOnFavoris()
    //


    /**
     * Add neighbour in favorites, set message and pass it to method to display a snackBar and call method to properly set FAB
     */

    private void addNeighbourOnFavoris(View view) {
        //Set boolean to adjust message depending on firstname first letter
        mNameStartVowel = "A E I O U Y".contains(Character.toString(mName.getText().toString().charAt(0))) || "a e i o u y".contains(Character.toString(mName.getText().toString().charAt(0)));

        String toastThis;
        if (mNameStartVowel) {
            toastThis = "Ajout d'" + mName.getText() + " aux favoris !";
        } else {
            toastThis = "Ajout de " + mName.getText() + " aux favoris !";
        }
        snackBarThis(toastThis, view);
        setFavoriteButtonEnable(profil.getIsFavoris());
        mApiService.addNeighbourOnFavoris(profil);
    }

    /**
     * Remove neighbour from favorites, set msg and pass it to method to display a snackBar and call method to properly set FAB
     */
    private void removeNeighbourOnFavoris(View view) {
        String toastThis;
        if (mNameStartVowel) {
            toastThis = "Retrait d'" + mName.getText() + " des favoris.";
        } else {
            toastThis = "Retrait de " + mName.getText() + " des favoris.";
        }
        snackBarThis(toastThis, view);
        setFavoriteButtonDisable(profil.getIsFavoris());
        mApiService.removeNeighbourOnFavoris(profil);
    }

    /**
     * Display snackBar to confirm action on favorite button on neighbour
     *
     * @param toastThis this msg to display
     * @param view
     */
    private void snackBarThis(String toastThis, View view) {
        Snackbar.make(view, toastThis, Snackbar.LENGTH_SHORT).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).setBackgroundTint(getResources().getColor(R.color.colorLightGrey)).show();
    }


}