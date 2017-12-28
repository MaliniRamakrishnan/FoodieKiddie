package com.example.srivikashini.notificationtrial;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.content.Context;
import android.widget.Button;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
Button doit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doit = (Button)findViewById(R.id.button);

       doit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


                   Notification notification = new NotificationCompat.Builder(MainActivity.this)
                           .setSmallIcon(android.R.drawable.ic_menu_report_image)
                           .setContentTitle("hey")
                           .setContentText("hello")
                           .setAutoCancel(true)
                           .build();

                   NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                   notificationManager.notify(0, notification);
               }

       });


    }



}
