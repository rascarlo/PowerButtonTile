package com.rascarlo.power.button.tile;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.rascarlo.power.button.tile.ui.MessageFragment;
import com.rascarlo.power.button.tile.ui.SettingsFragment;
import com.rascarlo.power.button.tile.utils.PowerButtonTileConstants;
import com.rascarlo.power.button.tile.utils.PowerButtonTileUtils;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_FRAGMENT_TAG = "MESSAGE_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // always inflate SettingsFragment
        SettingsFragment settingsFragment = new SettingsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_frame_layout, settingsFragment)
                .commit();
        // check if any message fragment needs to be inflated
        if (getIntent().getStringExtra(PowerButtonTileConstants.MESSAGE_CONSTANT) != null
                && !TextUtils.isEmpty(getIntent().getStringExtra(PowerButtonTileConstants.MESSAGE_CONSTANT))) {
            inflateTileSettingsFragment(getIntent().getStringExtra(PowerButtonTileConstants.MESSAGE_CONSTANT));
        }
    }

    private void inflateTileSettingsFragment(String stringExtra) {
        MessageFragment messageFragment = MessageFragment.newInstance(stringExtra);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_frame_layout, messageFragment, MESSAGE_FRAGMENT_TAG)
                .addToBackStack(null)
                .commit();
    }
}