# Installation
If you want to just use the app you may be better of by just installing by APK.
If you want to debug or develop the app you have to install it via Source code.

**TL;DR: Latest version availalbe for [download](https://github.com/davidkroell/docker2go/releases/latest)**

## Prerequisites
- Device with Android 5.1 Lollipop or newer (API level 22)
- Host with Docker daemon installed
- A user with SSH access on this host and enough rights to access Docker's unix socket `/var/run/docker.sock`. This is granted by adding the user to the group `docker` (or using root, but is not recommended)

## From APK
Right now, the app is only available by downloading the APK from the GitHub release section.
It is unclear if the app is ever published in Google's Playstore.
Make sure you permit the installation of non-official Android application by enabling `Unknown sources` in the security settings.

The latest stable release is found on [GitHub](https://github.com/davidkroell/docker2go/releases).

## From Source
The app may also be installed from source. For this you have to install Android Studio on your laptop or PC.

- Download the code either via zip or tar from GitHubs webpage or as git clone. We will be using Git here.

```bash
git clone https://github.com/davidkroell/docker2go.git
```

- Open up the directory in Android Studio and deploy the app onto your mobile.

This will also work in an Android emulator (for developers).
