package com.ti4e.wira.mediaplayback;

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

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    ListView listView;

    ArrayList<String> listVideo;
    ArrayAdapter adapter;

    TextView textLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView =(VideoView) findViewById(R.id.videoView);
        listView = (ListView) findViewById(R.id.listView);
        textLoading = (TextView) findViewById(R.id.textLoading);
        textLoading.setVisibility(VideoView.INVISIBLE);

        listVideo = new ArrayList<>();
        listVideo.add("ini judul 1");
        listVideo.add("vlog lalala");

//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listVideo);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,SinetronModel.drama);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sample_video_1));
//                        break;
//                    case 1:
//                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sample_video_2));
//                        break;
//                }
//                videoView.setMediaController(new MediaController(MainActivity.this));
//                videoView.requestFocus();
//                videoView.start();

                SinetronModel sinetronModel = SinetronModel.drama[(int) id];
                Uri videoUri = dapatkanMedia(sinetronModel.getVideoRawId());
                buatPlayer(videoUri);
            }
        });
    }

    private Uri dapatkanMedia(String namaMedia){
        if (URLUtil.isValidUrl(namaMedia)) {

            return  Uri.parse(namaMedia);
        }
        else {
        return Uri.parse(("android.resource://" + getPackageName()+"/raw/"+namaMedia));

        }
    }
    private void buatPlayer(Uri videoUri){

        //menampilkan text loading
        textLoading.setVisibility(VideoView.VISIBLE);
        //set resource videonya
        videoView.setVideoURI(videoUri);
        //Menambah controller untuk playback
        MediaController mc = new MediaController(MainActivity.this);
        videoView.setMediaController(mc);
        mc.setAnchorView(videoView);
//        videoView.setMediaController(new MediaController(MainActivity.this));

        //listener untuk event onprepared event(dijalankan saat siap dimainkan)
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //menyembukan tect loading
                textLoading.setVisibility(VideoView.INVISIBLE);
                videoView.start();
            }
        });

        //lisstener untuk event oncompletion(saat selesai dimainkan

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this,"video tamat",Toast.LENGTH_SHORT).show();

                //kembalikan ke posisi frame 0
                videoView.seekTo(0);
            }
        });
        videoView.requestFocus();
        videoView.start();
    }
}
