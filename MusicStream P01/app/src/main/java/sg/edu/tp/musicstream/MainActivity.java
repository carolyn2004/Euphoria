package sg.edu.tp.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //attribute here as a songCollection inside the MainActivity
    SongCollection songCollection = new SongCollection();//the only attribute here
    static ArrayList<Song> favList = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//end of onCreate

    public void handleSelection(View myView) {
        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("temasek", "The index in the array for this song is :" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }

    public void sendDataToActivity(int index) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);

    }

    public void addToFavorite(View view) {
        String songID = view.getContentDescription().toString();
        Song song = songCollection.searchById(songID);
        favList.add(song);
        Toast.makeText(this, "added to Favorites", Toast.LENGTH_SHORT).show();


    }

    public void gotoFavoriteActivity(View view) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.userguide) {

            Intent intent = new Intent(MainActivity.this, UserGuide.class);
            startActivity(intent);


        }
        return true;
    }



    }//end of class


