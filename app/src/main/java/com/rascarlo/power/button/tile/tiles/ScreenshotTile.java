package com.rascarlo.power.button.tile.tiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.service.quicksettings.Tile;
import android.text.TextUtils;

import androidx.preference.PreferenceManager;

import com.rascarlo.power.button.tile.PowerButtonAccessibilityService;
import com.rascarlo.power.button.tile.utils.PowerButtonTileConstants;
import com.rascarlo.power.button.tile.utils.PowerButtonTileUtils;
import com.rascarlo.power.button.tile.R;

public class ScreenshotTile extends BaseTileService {

    @Override
    public void onClick() {
        handleActionScreenshot();
        super.onClick();
    }

    @Override
    protected void updateTileResources() {
        if (ScreenshotTile.this.getQsTile() != null) {
            Tile tile = ScreenshotTile.this.getQsTile();
            tile.setIcon(Icon.createWithResource(getApplicationContext(), R.drawable.ic_screenshot_white_24dp));
            tile.setLabel(getString(R.string.screenshot_tile_label));
            tile.setState(Tile.STATE_INACTIVE);
            tile.updateTile();
        }
    }

    private void handleActionScreenshot() {
        if (isCollapseAndTakeScreenshot()) {
            sendBroadcastToCloseSystemDialogs();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    performAccessibilityServiceScreenshot();
                }
            }, getResources().getInteger(R.integer.delay_1500));
        } else {
            performAccessibilityServiceScreenshot();
        }
    }

    private void performAccessibilityServiceScreenshot() {
        if (isAccessibilityServiceEnabled()) {
            try {
                startService(new Intent(this, PowerButtonAccessibilityService.class)
                        .setAction(PowerButtonTileConstants.ACCESSIBILITY_SERVICE_PERFORM_GLOBAL_ACTION_SCREENSHOT));
            } catch (Exception e) {
                catchException(e);
                showSomethingWentWrong();
            }
        } else {
            showAccessibilityServiceNotEnabled();
        }
    }

    private boolean isCollapseAndTakeScreenshot() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ScreenshotTile.this);
        String action = sharedPreferences.getString(getString(R.string.key_screenshot_tile_action), getString(R.string.key_screenshot_tile_action_collapse_take_screenshot));
        return TextUtils.equals(action, getString(R.string.key_screenshot_tile_action_collapse_take_screenshot));
    }

}
