package com.polinema.meme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class MyNotificationManager {
    private Context mCtx;
    private static MyNotificationManager mInstance;

    private MyNotificationManager(Context context){ mCtx = context;}
    public static synchronized MyNotificationManager getmInstance(Context context){
        if (mInstance == null){
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }
    public void displayNotification(String title, String body)
    {
        String CHANNEL_ID = "my_channel_01";

        Intent resultIntent = new Intent(mCtx, FcmActivity.class);

        PendingIntent pandingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);
        FcmActivity.tit = title; //menampilkan isi dari pesan, ketika notif diklick
        FcmActivity.cont = body;

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name) //logo notifikasi
                .setContentTitle(title) //ini menampilkan judul yang mengirim pesan kalau dipostmannya title dan sebaliknya,
                .setContentText(body)//tapi ditampilkan terbalik
                .setAutoCancel(true)
                .setSound(defaultSoundUri) //memberi notifikasi berupa suara ketika pesan dikirim melalui postman
                .setContentIntent(pandingIntent);
        NotificationManager mNotivyMgr = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        if(mNotivyMgr !=null){
            mNotivyMgr.notify(1, mBuilder.build());
        }


    }

}
