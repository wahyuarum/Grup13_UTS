package com.polinema.meme;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class PlayBack extends AppCompatActivity {
    VideoView videoView;
    ListView listView;
    ArrayList<String> listVideo;
    ArrayAdapter adpter;
    TextView txtLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_back);

        videoView = (VideoView) findViewById(R.id.videoViewId);
        listView = (ListView) findViewById(R.id.ListViewId);

        listVideo = new ArrayList<>();
        listVideo.add("Cinta rangga ditolak afifah");
        listVideo.add("orangke3");

        adpter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, SinetronModel.drama);
        listView.setAdapter(adpter);

        txtLoading = (TextView) findViewById(R.id.textLoading);
        txtLoading.setVisibility(videoView.INVISIBLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SinetronModel sinetron = SinetronModel.drama[(int) id];
                Uri videoUri = dapatkanMedia(sinetron.getVideoRawId());
                buatPlayer(videoUri);
            }
        });
    }

    private Uri dapatkanMedia(String namaMedia){
        //return Uri.parse("android.resource://"+getPackageName()+"/raw/" + namaMedia);
        if (URLUtil.isValidUrl(namaMedia)){
            return Uri.parse(namaMedia);
        }
        else {
            return Uri.parse("android.resurce://" + getPackageName()+"/raw/"+namaMedia);
        }
    }

    private void buatPlayer(Uri videoUri){

        txtLoading.setVisibility(videoView.VISIBLE);

        videoView.setVideoURI(videoUri);
        MediaController medcon = new MediaController(PlayBack.this);
        videoView.setMediaController(medcon);
        medcon.setAnchorView(videoView);
        videoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        txtLoading.setVisibility(videoView.INVISIBLE);
                        videoView.start();
                    }
                });
        videoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        System.out.println(Toast.makeText(PlayBack.this, "video selesai", Toast.LENGTH_SHORT));
                        videoView.seekTo(0);
                    }
                }
        );
    }



}
