package gprosper.org.videoplayer;

import android.media.MediaPlayer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener {

    private VideoView videoView;
    private ProgressBar progressBar;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Up UI
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        videoView = (VideoView) findViewById(R.id.videoView);

        //Set Up VideoView
        videoView.setOnInfoListener(this);
        videoView.setOnPreparedListener(this);

        //Set Up MediaController
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //Set video src
        videoView.setVideoPath("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_50mb.mp4");

    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch(what){
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:

                return true;
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                progressBar.setVisibility(View.VISIBLE);
                return true;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                progressBar.setVisibility(View.INVISIBLE);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        progressBar.setVisibility(View.INVISIBLE);
        videoView.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int position = savedInstanceState.getInt("position");
        videoView.seekTo(position);
    }
}
