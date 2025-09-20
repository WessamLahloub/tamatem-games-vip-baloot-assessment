# Appium Automation Project (tamatem vip_baloot)

# This README explains how to prepare the environment and run the tests in this codebase "Reach out to the owner: Wessam Lahloub (wessam.lahloub1@gmail.com)" for any additional help
- The following instructions are only applicable to Windows users

## Prerequisites

- Java 11 (JDK 11) installed and on PATH.
- Maven 3.6+ installed and on PATH.
- Android SDK and platform-tools (adb) installed and on PATH.
- An Android emulator or physical device available.
- Appium server installed (Appium v2) and accessible.

## Project notes (important)

- Tests use UiAutomator2 (Appium capability) and read several values from system properties:
  - appium.server (default: http://127.0.0.1:4723/wd/hub)
  - device.name (default: emulator-5554)
  - app.package and app.activity (optional — set to force launching a specific activity)
  - noReset (default: true)
  - newCommandTimeout (seconds, default: 60)
- The test code will try to probe common Appium endpoints; if your server uses a different base path, pass -Dappium.server.

## Setup (one-time)

1. Ensure adb sees your device/emulator:
   - Start emulator (Android Studio AVD Manager) or connect device.
   - Run: `adb devices`
   - Example output should include `emulator-5554`.

2. If you want Appium Inspector or to ensure exact server base path:
   - Start Appium server:
     - Common: `appium --allow-cors` You MUST alloe CORS to use appium inspector.
     - If Appium is configured with a base path use that URL later via -Dappium.server.

3. APK / installation:
   - pre-install the APK on the emulator/device by drag and drop it from the PC to the emulator or install directly on the emulator using UpToDown store
   - you can also install the app using it's path on your PC
     - `adb -s emulator-5554 install -r "D:\Tamatem_games\vip_baloot.apk"` (my own path, adjust as yours)
   - Alternatively set appPackage/appActivity as system properties (app won't launch without this).

## How to run tests

-  normal run (uses defaults set in BaseTest):
  ```bash
  mvn -DskipTests=false test
  ```

- clean env, re-initialize maven and then run
   ```bash
  mvn -U clean install
  ```


## Troubleshooting

- **App opens Uptodown or installer instead of the app**
  - Pre-install APK with `adb install -r` to avoid the device launching the installer UI.

- **Element not found / slow loading**
  - Ensure emulator has enough resources and animations/overlays disabled for stability.
  - You can increase waits via system property newCommandTimeout or adjust sleep/wait values in page object.

## Where to look for results and logs

- Surefire reports: `target/surefire-reports/`
- Console logs show logger.info messages during test execution.

