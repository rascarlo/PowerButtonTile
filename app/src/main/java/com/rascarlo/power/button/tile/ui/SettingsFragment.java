package com.rascarlo.power.button.tile.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.rascarlo.power.button.tile.utils.PowerButtonTileUtils;
import com.rascarlo.power.button.tile.R;
import com.rascarlo.power.button.tile.tiles.ScreenshotTile;
import com.rascarlo.power.button.tile.tiles.SleepTile;
import com.rascarlo.power.button.tile.tiles.SystemPowerDialogTile;

public class SettingsFragment extends PreferenceFragmentCompat {
    private SwitchPreference accessibilityServiceSwitchPreference;
    private SwitchPreference screenshotTileSwitchPreference;
    private SwitchPreference sleepTileSwitchPreference;
    private SwitchPreference systemPowerDialogTileSwitchPreference;

    private ComponentName screenshotTileComponentName;
    private ComponentName sleepTileComponentName;
    private ComponentName systemPowerDialogTileComponentName;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenshotTileComponentName = new ComponentName(getActivity(), ScreenshotTile.class);
        sleepTileComponentName = new ComponentName(getActivity(), SleepTile.class);
        systemPowerDialogTileComponentName = new ComponentName(getActivity(), SystemPowerDialogTile.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        accessibilityServiceSwitchPreference = getPreferenceManager().findPreference(getString(R.string.key_component_accessibility_service));
        if (accessibilityServiceSwitchPreference != null) {
            accessibilityServiceSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                    return false;
                }
            });
        }

        // components category
        PreferenceCategory preferenceCategoryScreenshot = getPreferenceManager().findPreference(getString(R.string.key_settings_category_screenshot));
        PreferenceCategory preferenceCategorySleep = getPreferenceManager().findPreference(getString(R.string.key_settings_category_sleep));
        PreferenceCategory preferenceCategorySystemPowerDialog = getPreferenceManager().findPreference(getString(R.string.key_settings_category_system_power_dialog));

        // screenshot
        screenshotTileSwitchPreference = getPreferenceManager().findPreference(getString(R.string.key_component_screenshot_tile));
        if (screenshotTileSwitchPreference != null) {
            screenshotTileSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean value = (Boolean) newValue;
                    PowerButtonTileUtils.setComponentEnabled(getActivity(), screenshotTileComponentName, value);
                    return true;
                }
            });
        }

        // sleep
        sleepTileSwitchPreference = getPreferenceManager().findPreference(getString(R.string.key_component_sleep_tile));
        if (sleepTileSwitchPreference != null) {
            sleepTileSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean value = (Boolean) newValue;
                    PowerButtonTileUtils.setComponentEnabled(getActivity(), sleepTileComponentName, value);
                    return true;
                }
            });
        }

        // system power dialog
        systemPowerDialogTileSwitchPreference = getPreferenceManager().findPreference(getString(R.string.key_component_system_power_dialog_tile));
        if (systemPowerDialogTileSwitchPreference != null) {
            systemPowerDialogTileSwitchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean value = (Boolean) newValue;
                    PowerButtonTileUtils.setComponentEnabled(getActivity(), systemPowerDialogTileComponentName, value);
                    return true;
                }
            });
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateAccessibilityServiceSwitch();
        updateComponents();
    }

    private void updateComponents() {
        // update api visibilities
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            getPreferenceScreen().removePreference(sleepTileSwitchPreference);
            getPreferenceScreen().removePreference(screenshotTileSwitchPreference);
        }
        screenshotTileSwitchPreference.setChecked(isComponentEnabled(screenshotTileComponentName));
        sleepTileSwitchPreference.setChecked(isComponentEnabled(sleepTileComponentName));
        systemPowerDialogTileSwitchPreference.setChecked(isComponentEnabled(systemPowerDialogTileComponentName));
    }

    private void updateAccessibilityServiceSwitch() {
        accessibilityServiceSwitchPreference.setChecked(PowerButtonTileUtils.isAccessibilityServiceEnabled(getActivity()));
    }

    private boolean isComponentEnabled(ComponentName componentName) {
        return PowerButtonTileUtils.isComponentEnabled(getActivity(), componentName);
    }
}
