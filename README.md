# VolumeWidget

A clean, open-source Android Volume Widget. 

The perfect trustworthy replacement if your phone's physical volume buttons are broken or unreliable. VolumeWidget provides quick access to your volume controls directly from your home screen.

![Volume Widget Preview](widget.jpg)

## Features
- **Simple & Clean:** A modern interface with dark mode support.
- **Interactive:** Custom ripple animations and haptic feedback.
- **Open Source:** Completely transparent and trustworthy.

## Permissions
VolumeWidget requests exactly one permission:

| Permission | Why |
|---|---|
| `VIBRATE` | The haptic click when you tap + or − |

No internet access, no storage access, no analytics, no background services. The tap handler can't be triggered by other apps.

## Installation
You can download the latest APK directly from the [Releases](https://github.com/kawishkamd/volumewidget/releases) page. 

## Upgrading from v2.0 or earlier
Starting with v2.1, the app ID changed from `com.example.volumewidget` to `io.github.kawishkamd.volumewidget`. Android treats this as a new app, so it **won't** update the old one in place:

1. Install the new APK.
2. Remove the old widget from your home screen and add the new one.
3. Uninstall the old "VolumeWidget". Both will appear in your app list until you do.

## Requirements
- Android 7.0 (API 24) or higher.
