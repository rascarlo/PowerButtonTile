package com.rascarlo.power.button.tile.tiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.text.TextUtils;

import androidx.preference.PreferenceManager;

import com.rascarlo.power.button.tile.PowerButtonAccessibilityService;
import com.rascarlo.power.button.tile.utils.PowerButtonTileConstants;
import com.rascarlo.power.button.tile.utils.PowerButtonTileUtils;
import com.rascarlo.power.button.tile.R;

public class SystemPowerDialogTile extends BaseTileService {

    @Override
    public void onClick() {
        handleActionSystemPowerDialog();
        super.onClick();
    }

    @Override
    protected void updateTileResources() {
        if (SystemPowerDialogTile.this.getQsTile() != null) {
            Tile tile = SystemPowerDialogTile.this.getQsTile();
            tile.setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.ic_system_power_dialog_white_24dp));
            tile.setLabel(getString(R.string.system_power_dialog_tile_label));
            tile.setState(Tile.STATE_INACTIVE);
            tile.updateTile();
        }
    }

    private void handleActionSystemPowerDialog() {
        if (isCollapseAndOpenSystemPowerDialog()) {
            sendBroadcastToCloseSystemDialogs();
        }
        performAccessibilityServiceSystemPowerDialog();
    }

    private void performAccessibilityServiceSystemPowerDialog() {
        if (isAccessibilityServiceEnabled()) {
            try {
                startService(new Intent(this, PowerButtonAccessibilityService.class)
                        .setAction(PowerButtonTileConstants.ACCESSIBILITY_SERVICE_PERFORM_GLOBAL_ACTION_POWER_DIALOG));
            } catch (Exception e) {
                catchException(e);
                showSomethingWentWrong();
            }
        } else {
            showAccessibilityServiceNotEnabled();
        }
    }

    private boolean isCollapseAndOpenSystemPowerDialog() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SystemPowerDialogTile.this);
        String action = sharedPreferences.getString(getString(R.string.key_system_power_dialog_tile_action), getString(R.string.key_system_power_dialog_tile_action_collapse_show_dialog));
        return TextUtils.equals(action, getString(R.string.key_system_power_dialog_tile_action_collapse_show_dialog));
    }

}