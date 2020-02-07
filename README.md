<img src="https://github.com/rascarlo/PowerButtonTile/blob/master/fastlane/metadata/android/en-US/images/featureGraphic.png" width="640" height="333" />

# PowerButtonTile
<p>Simple application to add up to 3 quick settings tiles to the quick settings layout.</p>
<p>The app makes use of the accessibility service to provide quick settings tiles to perform global actions: screenshot (API level 28), sleep (API level 28) and system power dialog (API level 21).</p>
<p>System power dialog (GLOBAL_ACTION_POWER_DIALOG) has been added in API level 21 (https://developer.android.com/reference/android/accessibilityservice/AccessibilityService#GLOBAL_ACTION_POWER_DIALOG).</p>
<p>Sleep (GLOBAL_ACTION_LOCK_SCREEN) has been added in API level 28 (https://developer.android.com/reference/android/accessibilityservice/AccessibilityService#GLOBAL_ACTION_LOCK_SCREEN).</p>
<p>Screenshot (GLOBAL_ACTION_TAKE_SCREENSHOT) has been added in API level 28 (https://developer.android.com/reference/android/accessibilityservice/AccessibilityService#GLOBAL_ACTION_TAKE_SCREENSHOT).<br>

___
### Screenshots

<img src="https://github.com/rascarlo/PowerButtonTile/blob/master/fastlane/metadata/android/en-US/phoneScreenshots/01.png" width="333" height="640" /> <img src="https://github.com/rascarlo/PowerButtonTile/blob/master/fastlane/metadata/android/en-US/phoneScreenshots/02.png" width="333" height="640" />

___
## Android Developers Documentation</p>
- Accessibility service: https://developer.android.com/reference/android/accessibilityservice/AccessibilityService
- Tile: https://developer.android.com/reference/android/service/quicksettings/Tile
- TileService: https://developer.android.com/reference/android/service/quicksettings/TileService
