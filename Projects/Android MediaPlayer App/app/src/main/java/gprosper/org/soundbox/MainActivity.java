package gprosper.org.soundbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private Button prevButton;
    private Button playButton;
    private Button nextButton;
    private TextView artistName;
    private TextView songTitle;
    private TextView songAlbum;
    private ImageView songCover;
    private SeekBar songSeekBar;

    private MediaPlayer mediaPlayer;
    private Timer timer;

    private String[] playlist = {"jcole1","jcole2","jcole3"};
    private int playlistIndex;
    private boolean songEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        setUpMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }

    private void setUpUI(){
        prevButton = (Button) findViewById(R.id.prevButton);
        prevButton.setOnClickListener(this);

        playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(this);

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

        artistName = (TextView) findViewById(R.id.artistName);
        songTitle = (TextView) findViewById(R.id.songTitle);
        songAlbum = (TextView) findViewById(R.id.songAlbum);
        songCover = (ImageView) findViewById(R.id.artistImage);

        songSeekBar = (SeekBar) findViewById(R.id.songSeekBar);
        songSeekBar.setOnSeekBarChangeListener(this);
    }

    private void setUpMediaPlayer(){
        playlistIndex = 0;
        initSong();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        songEnded = true;
        nextMusic();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prevButton:
                prevMusic();
                break;
            case R.id.playButton:
                if (mediaPlayer.isPlaying()){
                    pauseMusic();
                }
                else{
                    playMusic();
                }
                break;
            case R.id.nextButton:
                nextMusic();
                break;
        }
    }

    private void playMusic(){
        if (mediaPlayer != null){
            mediaPlayer.start();
            startProgressTracker();
            playButton.setBackground(getDrawable(android.R.drawable.ic_media_pause));
        }
    }

    private void setAlbumCover() {
        SongMetaData songInfo = getSongMetaData(playlist[playlistIndex]);
        artistName.setText(songInfo.artist);
        songTitle.setText(songInfo.title);
        songAlbum.setText(songInfo.album);
        songCover.setImageDrawable(new BitmapDrawable(getResources(), songInfo.cover));
    }

    private void pauseMusic(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            stopProgressTracker();
            playButton.setBackground(getDrawable(android.R.drawable.ic_media_play));
        }
    }

    private void prevMusic(){
        if (mediaPlayer.getCurrentPosition() > 2 * 1000){
            mediaPlayer.seekTo(0);
        }
        else {
            if (playlistIndex > 0){
                playlistIndex--;
                initSong();
            }
        }
    }

    private void nextMusic(){
        if (playlistIndex >= playlist.length - 1 || playlistIndex < 0){
            playlistIndex = 0;
            initSong();
        }
        else {
            playlistIndex++;
            initSong();
        }
    }

    private void initSong() {

        boolean wasPlaying = false;

        //Check if song was playing before current song is initialized
        if (mediaPlayer != null){
            wasPlaying = mediaPlayer.isPlaying();
            mediaPlayer.release();
        }

        //initialize song
        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(playlist[playlistIndex], "raw", getPackageName()));
        mediaPlayer.setOnCompletionListener(this);
        songSeekBar.setMax(mediaPlayer.getDuration());
        setAlbumCover();

        //if last song was playing or ended while playing. current song should play
        if (wasPlaying || songEnded){
            playMusic();
            songEnded = false;
        }
        else {
            pauseMusic();
        }
    }

    private SongMetaData getSongMetaData(String name){
        MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
        Uri song = Uri.parse("android.resource://" + getPackageName() + "/" + "raw/" + name);
        metaRetriever.setDataSource(this, song);

        String title = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist = metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        String album =  metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        Bitmap cover = null;

        byte[] coverData = metaRetriever.getEmbeddedPicture();
        if (coverData != null){
            cover = BitmapFactory.decodeByteArray(coverData, 0, coverData.length);
        }

        return new SongMetaData(title,artist,album,cover);
    }

    private void startProgressTracker(){
        timer = new Timer();
        timer.schedule(new ProgressBarTracker(), 0, 1000);
    }

    private void stopProgressTracker(){
        timer.cancel();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaPlayer.seekTo(seekBar.getProgress());
    }

    private static class SongMetaData {
        String title;
        String artist;
        String album;
        Bitmap cover;

        public SongMetaData(String title, String artist, String album, Bitmap cover) {
            this.title = title;
            this.artist = artist;
            this.album = album;
            this.cover = cover;
        }
    }

    private class ProgressBarTracker extends TimerTask{
        @Override
        public void run() {
            if (mediaPlayer != null){
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        songSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                });
            }
        }
    }


}
