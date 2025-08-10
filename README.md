# Pinky Downloader - A Cute YouTube Downloader for Android

A full-featured YouTube downloader for Android with a cute, pink-themed UI. Built with Kotlin, Chaquopy, yt-dlp, and ExoPlayer.

## ✨ Features

*   **Paste & Go:** Paste any YouTube URL to get started.
*   **Clipboard Monitoring:** Automatically detects YouTube URLs in your clipboard.
*   **Format Selection:** Choose from a wide range of video, audio, and subtitle formats.
*   **Background Downloads:** Reliable downloads powered by WorkManager, with notifications for progress.
*   **Built-in Players:** Play your downloaded videos and audio directly within the app using ExoPlayer.
*   **File Manager:** Manage your downloaded files, rename, delete, and share.
*   **Cute UI:** A unique "kawaii" pink theme with a dark mode variant.
*   **And much more:** Playlist/channel downloads, pause/resume, concurrent download controls, and more are planned!

## 📸 Screenshots

*(Screenshots will be added here soon!)*

## 🛠️ How to Build Locally

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Open in Android Studio:**
    *   Open Android Studio (latest stable version recommended).
    *   Click on `File > Open` and select the cloned repository folder.
    *   Android Studio will automatically sync the project and download the necessary dependencies.
3.  **Python Setup:**
    *   The project uses [Chaquopy](https://chaquo.com/chaquopy/) to run Python. You may need a local Python installation (version 3.8+ recommended) for the Gradle plugin to use during the build process. Ensure it's available in your system's PATH.
4.  **Run the app:**
    *   Connect an Android device (Android 8.0+) or start an emulator.
    *   Click the "Run" button (▶️) in Android Studio.

## 🚀 Continuous Integration (CI)

This project uses GitHub Actions to automatically build the application.

*   On every push to the `main` branch, a new Debug APK is built.
*   You can find the latest compiled APK in the "Artifacts" section of the latest workflow run on the [Actions tab](https://github.com/your-username/your-repo/actions).
*   The workflow also creates a new GitHub Release tagged `build-<run_number>` with the APK attached.

### Release Signing

To create a signed Release APK, you need to configure the following secrets in your GitHub repository settings (`Settings > Secrets and variables > Actions`):

*   `RELEASE_KEYSTORE`: The base64-encoded content of your Java Keystore file.
    *   To generate this, run: `base64 -w 0 my-release-key.keystore > release.keystore.base64` and copy the contents.
*   `RELEASE_KEY_ALIAS`: The alias for your key.
*   `RELEASE_KEY_PASSWORD`: The password for your key.
*   `RELEASE_KEYSTORE_PASSWORD`: The password for the keystore.

## 🔒 Privacy & Security Note

Pinky Downloader downloads content directly from YouTube's servers to your device's local storage. The app does not collect or transmit any personal data. Users are responsible for complying with YouTube's terms of service and any applicable copyright laws in their region.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
