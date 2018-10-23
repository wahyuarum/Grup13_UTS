package com.polinema.meme;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM_Dimdim";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN", s);
    }
    // token = punya nilai id unik, ketika apk diuninstall dan dilaunch lagi, token akan berubah lagi

    public void newToken()
    {
        // tokon = butten yang punya nilai id unik, ketika apk diuninstall dan dilounc lagi, token akan berubah lagi
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Pengirim: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG,"Pesennya si dia: " + remoteMessage.getData().get("body"));
        }

        MyNotificationManager.getmInstance(this).displayNotification(
                remoteMessage.getData().get("body"), //isi dari pesan
                remoteMessage.getData().get("title") //judul pesan
        );

    }









}
