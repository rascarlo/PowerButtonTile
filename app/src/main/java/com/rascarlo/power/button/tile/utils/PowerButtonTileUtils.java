package com.rascarlo.power.button.tile.utils;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.view.accessibility.AccessibilityManager;

import com.rascarlo.power.button.tile.PowerButtonAccessibilityService;

import java.util.List;

public class PowerButtonTileUtils {

    public static boolean isAccessibilityServiceEnabled(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> accessibilityServiceInfoList;
        if (accessibilityManager != null) {
            accessibilityServiceInfoList = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
            for (AccessibilityServiceInfo enabledService : accessibilityServiceInfoList) {
                ServiceInfo enabledServiceInfo = enabledService.getResolveInfo().serviceInfo;
                if (enabledServiceInfo.packageName.equals(context.getPackageName()) && enabledServiceInfo.name.equals(PowerButtonAccessibilityService.class.getName()))
                    return true;
            }
        }
        return false;
    }

    public static boolean isComponentEnabled(Context context, ComponentName componentName) {
        return context.getPackageManager().getComponentEnabledSetting(componentName) ==
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
    }

    public static void setComponentEnabled(Context context, ComponentName componentName, boolean value) {
        context.getPackageManager().setComponentEnabledSetting(componentName,
                value ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}