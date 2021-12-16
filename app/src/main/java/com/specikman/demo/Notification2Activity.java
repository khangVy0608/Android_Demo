package com.specikman.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;

import com.specikman.demo.databinding.ActivityNotification1Binding;
import com.specikman.demo.databinding.ActivityNotification2Binding;

public class Notification2Activity extends AppCompatActivity {
    private ActivityNotification2Binding binding = null;
    private final String CHANNEL_ID = "ChannelID";
    private final String CHANNEL_NAME = "ChannelName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotification2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Create channel
        createNotificationChannel();
        //Create and show notification when click button
        binding.btnShow.setOnClickListener(e->{
            String title = binding.etContentTitle.getText().toString();
            String content = binding.etContentText.getText().toString();
            String subtext = binding.etSubtext.getText().toString();
            showNotification(createNotification(
                    title, //title
                    content, //content
                    subtext, //subtext
                    R.drawable.ic_notification, //small icon
                    NotificationCompat.PRIORITY_DEFAULT //priority
            ));
        });

        binding.btnNext.setOnClickListener(e->{
            Intent intent = new Intent(this, Notification3Activity.class);
            startActivity(intent);
        });
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }

    private Notification createNotification(String title, String content, String subtext, int icon, int priority) {
        String content_show = "1\n2\n3\n4\n5\n\6\n";
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(content)
                .setSubText(subtext)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(content_show))
                .setPriority(priority)
                .build();
    }

    private void showNotification(Notification notification) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(0, notification);
    }
}