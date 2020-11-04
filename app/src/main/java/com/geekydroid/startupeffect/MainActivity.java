package com.geekydroid.startupeffect;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private FloatingActionButton fab;
    private RelativeLayout layoutButtons;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);
        layoutButtons = (RelativeLayout) findViewById(R.id.layoutButtons);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMenu();
            }
        });

    }

    private void viewMenu() {

        if (!isOpen) {

            int x = layoutButtons.getRight();
            int y = layoutButtons.getBottom();

            Log.d(TAG, "viewMenu: x" + x);
            Log.d(TAG, "viewMenu: y" + y);

            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutButtons.getWidth(), layoutButtons.getHeight());

            Log.d(TAG, "viewMenu: end" + endRadius);

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);

            layoutButtons.setVisibility(View.VISIBLE);
            anim.start();

            isOpen = true;

        } else {

            int x = layoutButtons.getRight();
            int y = layoutButtons.getBottom();

            int startRadius = Math.max(layoutButtons.getWidth(), layoutButtons.getHeight());
            int endRadius = 0;


            Animator anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutButtons.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            anim.start();

            isOpen = false;
        }
    }
}
