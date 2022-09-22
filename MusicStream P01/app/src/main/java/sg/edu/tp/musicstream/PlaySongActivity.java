package sg.edu.tp.musicstream;

import android.media.MediaPlayer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {
    //attributes -- 5 attributes are for the song info
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private int drawable;
    private int currentIndex = -1;//refers to the index position value in the array
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    SeekBar seekbar;
    Handler handler = new Handler();
    //useful to retrieve the song object when you have the index

    private SongCollection songCollection = new SongCollection();
    private SongCollection originalSongCollection = new SongCollection();
    List<Song> shuffleList = Arrays.asList(songCollection.songs);


    Button repeatBtn;
    Button shuffleBtn;
    Boolean repeatFlag = false;
    Boolean shuffleFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        //-----
        btnPlayPause = findViewById(R.id.btnPlayPause2);
        seekbar = findViewById(R.id.seekBar2);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });
        repeatBtn = findViewById(R.id.repeatBtn);
        shuffleBtn = findViewById(R.id.shuffleBtn);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");//get the index of the song
        Log.d("temasek", "Retrieved position is :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }//end of onCreate
    Runnable bar = new Runnable() {
        @Override
        public void run() {
            seekbar.setProgress(player.getCurrentPosition());
            handler.postDelayed(this, 1000);
        }
    };
    public void displaySongBasedOnIndex(int selectedIndex) {
        Song song = songCollection.getCurrentSong(currentIndex);
        title = song.getTitle();
        artiste = song.getArtiste();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();
        TextView txtTitle = findViewById(R.id.txtSongTitle2);
        txtTitle.setText(title);
        TextView txtArtiste = findViewById(R.id.txtArtist2);
        txtArtiste.setText(artiste);
        ImageView iCoverArt = findViewById(R.id.imgCoverArt2);
        iCoverArt.setImageResource(drawable);
    }//end of displaySongBasedOnIndex
    public void playSong(String songUrl) {
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            seekbar.setMax(player.getDuration());
            handler.removeCallbacks(bar);
            handler.postDelayed(bar, 1000);
            gracefullyStopsWhenMusicEnds();
            btnPlayPause.setText("PAUSE");
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of playSong
    //onClick event for Play button
    public void playOrPauseMusic(View view) {
        if (player.isPlaying()) { //if player is playing
            player.pause();
            btnPlayPause.setText("PLAY");
        } else {
            player.start();
            btnPlayPause.setText("PAUSE");
        }
    }//end of playOrPauseMusic
    private void gracefullyStopsWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                if (repeatFlag)
                {
                    playOrPauseMusic(null);
                }
                else
                {
                }
                btnPlayPause.setText("PLAY");
            }
        });// end of gracefullyStopsWhenMusicEnds
    }
    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        Toast.makeText(this, "After clicking playPrevious,\nthe current index of this song\nin the SongCollection array is now :" + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playPrevious, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    public void playNext(View view) {
        currentIndex = songCollection.getNextSong(currentIndex);
        Toast.makeText(this, "After clicking playNext,\nthe current index of this song\nin the SongCollection array is now :" + currentIndex, Toast.LENGTH_LONG).show();
        Log.d("temasek", "After playNext, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }
    public void repeatSong(View view) {
        if (repeatFlag)
        {
            repeatBtn.setBackgroundResource(R.drawable.repeat_off);
        }
        else
        {
            repeatBtn.setBackgroundResource(R.drawable.repeat_on);
        }
        repeatFlag = !repeatFlag;
    }
    public void shuffleSong(View view) {
        if (shuffleFlag)
        {
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_on);
            songCollection = new SongCollection();
        }
        else
        {
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_off);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }
        shuffleFlag = !shuffleFlag;
    }
}
//end of class


