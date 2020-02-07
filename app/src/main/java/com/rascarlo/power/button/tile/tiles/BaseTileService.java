package com.rascarlo.power.button.tile.tiles;

import android.content.Intent;
import android.service.quicksettings.TileService;
import android.util.Log;

import com.rascarlo.power.button.tile.MainActivity;
import com.rascarlo.power.button.tile.utils.PowerButtonTileConstants;
import com.rascarlo.power.button.tile.utils.PowerButtonTileUtils;

abstract class BaseTileService extends TileService {
    protected abstract void updateTileResources();

    @Override
    public void onTileAdded() {
        updateTileResources();
        super.onTileAdded();
    }

    @Override
    public void onStartListening() {
        updateTileResources();
        super.onStartListening();
    }

    boolean isAccessibilityServiceEnabled() {
        return PowerButtonTileUtils.isAccessibilityServiceEnabled(BaseTileService.this);
    }

    void sendBroadcastToCloseSystemDialogs() {
        try {
            sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        } catch (Exception e) {
            catchException(e);
        }
    }

    void catchException(Exception e) {
        Log.e(getClass().getSimpleName(), e.getMessage() != null ? e.getMessage() : e.toString());
    }

    void showSomethingWentWrong() {
        Intent intent = new Intent(BaseTileService.this, MainActivity.class);
        intent.putExtra(PowerButtonTileConstants.MESSAGE_CONSTANT, PowerButtonTileConstants.MESSAGE_SOMETHING_WENT_WRONG);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityAndCollapse(intent);
    }

    void showAccessibilityServiceNotEnabled() {
        Intent intent = new Intent(BaseTileService.this, MainActivity.class);
        intent.putExtra(PowerButtonTileConstants.MESSAGE_CONSTANT, PowerButtonTileConstants.MESSAGE_ACCESSIBILITY_SERVICE_DISABLED);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivityAndCollapse(intent);
    }
}
