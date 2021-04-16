package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;

public class DetailsActivity extends AppCompatActivity {

    protected ImageView myAvatar;
    protected FloatingActionButton myfavorisButton;
    protected TextView myName;
    protected TextView myAdresse;
    protected TextView myPhone;
    protected TextView myWeb;
    protected TextView aboutMeText;
    protected TextView myNameWhite;

    /*@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details_neighbour, container, true);

        this.myAvatar = view.findViewById(R.id.myAvatar);
        this.myfavorisButton = view.findViewById(R.id.myfavorisButton);
        this.myName = view.findViewById(R.id.myName);
        this.myAdresse = view.findViewById(R.id.myAdresse);
        this.myPhone = view.findViewById(R.id.myPhone);
        this.myWeb = view.findViewById(R.id.myWeb);
        this.aboutMeText = view.findViewById(R.id.aboutMeText);
        this.myNameWhite = view.findViewById(R.id.myNameWhite);

        myName.setTextSize(20);
        myName.setTypeface(null, Typeface.BOLD);
        aboutMeText.setTypeface(null,Typeface.BOLD);
        aboutMeText.setTextSize(20);
        myNameWhite.setTextSize(30);
        myNameWhite.setTextColor(Color.WHITE);
        aboutMeText.setGravity(Gravity.CENTER_VERTICAL);

        return view;


    }
*/

}