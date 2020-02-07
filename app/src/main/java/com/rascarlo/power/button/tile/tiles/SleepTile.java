package com.rascarlo.power.button.tile.tiles;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;

import com.rascarlo.power.button.tile.PowerButtonAccessibilityService;
import com.rascarlo.power.button.tile.utils.PowerButtonTileConstants;
import com.rascarlo.power.button.tile.utils.PowerButtonTileUtils;
import com.rascarlo.power.button.tile.R;

public class SleepTile extends BaseTileService {

    @Override
    public void onClick() {
        handleActionSleep();
        super.onClick();
    }

    @Override
    protected void updateTileResources() {
        if (SleepTile.this.getQsTile() != null) {
            Tile tile = SleepTile.this.getQsTile();
            tile.setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.ic_sleep_white_24dp));
            tile.setLabel(getString(R.string.sleep_tile_label));
            tile.setState(Tile.STATE_INACTIVE);
            tile.updateTile();
        }
    }

    private void handleActionSleep() {
        if (isAccessibilityServiceEnabled()) {
            try {
                startService(new Intent(this, PowerButtonAccessibilityService.class)
                        .setAction(PowerButtonTileConstants.ACCESSIBILITY_SERVICE_PERFORM_GLOBAL_ACTION_LOCK_SCREEN));
            } catch (Exception e) {
                catchException(e);
                showSomethingWentWrong();
            }
        } else {
            showAccessibilityServiceNotEnabled();
        }
    }
}