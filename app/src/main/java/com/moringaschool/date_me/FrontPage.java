package com.moringaschool.date_me;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FrontPage extends AppCompatActivity {
    @BindView(R.id.move) Button mVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        ButterKnife.bind(this);

        mVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animate =
                        AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.fade);
                mVisit.startAnimation(animate);

            }
            public void fade(View view){
                Button button = (Button) findViewById(R.id.move);

            }

        });


    }
}
