package com.rascarlo.power.button.tile;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;

import com.rascarlo.power.button.tile.utils.PowerButtonTileConstants;
import com.rascarlo.power.button.tile.utils.PowerButtonTileUtils;

public class PowerButtonAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null && !TextUtils.isEmpty(intent.getAction())) {
            if (TextUtils.equals(PowerButtonTileConstants.ACCESSIBILITY_SERVICE_PERFORM_GLOBAL_ACTION_POWER_DIALOG, intent.getAction())) {
                handleGlobalActionPowerDialog();
            } else if (TextUtils.equals(PowerButtonTileConstants.ACCESSIBILITY_SERVICE_PERFORM_GLOBAL_ACTION_LOCK_SCREEN, intent.getAction())) {
                handleGlobalActionLockScreen();
            } else if (TextUtils.equals(PowerButtonTileConstants.ACCESSIBILITY_SERVICE_PERFORM_GLOBAL_ACTION_SCREENSHOT, intent.getAction())) {
                handleGlobalActionScreenshot();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    // screenshot, API>=P
    private void handleGlobalActionScreenshot() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            performGlobalAction(AccessibilityService.GLOBAL_ACTION_TAKE_SCREENSHOT);
        }
    }

    // sleep, API >=P
    private void handleGlobalActionLockScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            performGlobalAction(AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN);
        }
    }

    // system power dialog
    private void handleGlobalActionPowerDialog() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_POWER_DIALOG);
    }
}
