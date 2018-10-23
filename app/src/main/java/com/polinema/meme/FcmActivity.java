package com.polinema.meme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FcmActivity extends AppCompatActivity {

    public static String tit,cont;
    public TextView title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);

        MyFirebaseMessagingService ser = new MyFirebaseMessagingService();

        ser.newToken();
        //untuk memanggil body dan title yang ada dipostmen selanjutnya dikirim ke activity.xml
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        if(tit!=null || cont !=null)
        {
            title.setText(tit);
            content.setText(cont);
        }

    }
}
