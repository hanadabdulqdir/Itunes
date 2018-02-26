package com.example.hanad.itunesapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanad.itunesapplication.Itunes.Collection.classicDetails.model.ClassicMusicFragment;
import com.example.hanad.itunesapplication.Itunes.Collection.popDetails.model.PopMusicFragment;
import com.example.hanad.itunesapplication.Itunes.Collection.rockDetails.model.RockMusicFragment;
import com.example.hanad.itunesapplication.Pulse.pulseLive.PulseLiveFragment;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private static FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        PulseLiveFragment pulseLiveFragment = new PulseLiveFragment();
        RockMusicFragment rockMusicFragment = new RockMusicFragment();
        ClassicMusicFragment classicMusicFragment = new ClassicMusicFragment();
        PopMusicFragment popMusicFragment = new PopMusicFragment();


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_Pulse);
                    Toast.makeText(getApplicationContext(), "Pulse Live", Toast.LENGTH_LONG).show();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new PulseLiveFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_Rock);
                    Toast.makeText(getApplicationContext(), "Rock", Toast.LENGTH_LONG).show();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new RockMusicFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_Classic);
                    Toast.makeText(getApplicationContext(), "classic", Toast.LENGTH_LONG).show();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new ClassicMusicFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
                case R.id.navigation_other:
                    mTextMessage.setText(R.string.title_Pop);
                    Toast.makeText(getApplicationContext(), "Pop", Toast.LENGTH_LONG).show();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new PopMusicFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        //if (savedInstanceState == null) {
        PulseLiveFragment pulseLiveFragment = new PulseLiveFragment();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, new PulseLiveFragment())
                .addToBackStack(null)
                .commit();

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
