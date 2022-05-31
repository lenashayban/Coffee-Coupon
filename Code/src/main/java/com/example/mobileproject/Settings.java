package com.example.mobileproject;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends Activity {
    Button silentButton;
    Button normalButton;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        silentButton = (Button) findViewById(R.id.silentBtn);
        normalButton = (Button) findViewById(R.id.normalBtn);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // silent mode
        silentButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // the below code is to check the permission that the access
                // notification policy settings from users device..
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()) {
                    Intent intent1 = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    startActivity(intent1);
                }
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        });

        // normal mode
        normalButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // the below code is to check the permission that the access
                // notification policy settings from users device..
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()) {
                    Intent intent1 = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    startActivity(intent1);
                }
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        });
    }
}

