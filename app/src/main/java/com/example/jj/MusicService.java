package com.example.jj;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public class MusicService extends Service {
    private MediaPlayer player;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            //player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            player = new MediaPlayer();
            AssetFileDescriptor afd = getAssets().openFd("cherry.mpeg");
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.setLooping(true);
            afd.close();  // Close the AssetFileDescriptor after use

            player.prepare();  // Prepare the media player
        } catch (Exception e) {
            Log.e("meow", "failed playing");
        }
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();  // Always release the MediaPlayer when done
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}