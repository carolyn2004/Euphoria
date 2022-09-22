package sg.edu.tp.musicstream;

public class SongCollection {


    //attributes
    Song[] songs = new Song[4];//array of songs
    //default constructor of this class
    public SongCollection() {
        Song theWayYouLookTonight = new Song("S1001",
                "1. The Way You Look Tonight",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66,
                R.drawable.michael_buble_collection);

        Song billieJean = new Song("S1002",
                "2. Billie Jean",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/4eb779428d40d579f14d12a9daf98fc66c7d0be4?cid=2afe87a64b0042dabf51f37318616965",
                4.9,
                R.drawable.billie_jean);
        Song flyMe = new Song("S1003",
                "3. Fly Me To The Moon",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/8b814bc82c930dcac946e75eb49e59079dc74feb?cid=2afe87a64b0042dabf51f37318616965",
                6.19,
                R.drawable.fallinlove);
        Song letsFallinLove = new Song("S1004",
        "4. Let's Fall in Love for the Night",
        "Finneas",
        "https://p.scdn.co/mp3-preview/6c4c568b6c1e8c86ea51333fcf8a1b30d02b4810?cid=2afe87a64b0042dabf51f37318616965",
        3.17,
                R.drawable.letsfallinlove);


        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
        songs[2] = flyMe;
        songs[3] = letsFallinLove;



    }//end of constructor
    //used to search a Song object from the array according to the id in the parameter
    public int searchSongById(String id){
        //you need iterate the songs array to find out the song with the same id in the parameter
        for(int index=0; index < songs.length; index++){
            Song tempSong = songs[index];//store the song into a temp variable
            //compare the id with the tempSong's id, if equal, then we found the song with the id
            //.equals to compare strings, please don't use ==
            if(tempSong.getId().equals(id)){
                return index;//return the position of this song in the songs array
            }
        }
        return -1;//when you could not find a song with the id, then return -1 to indicate cannot find...
    }//end of method searchSongById
    //this method return the song object according to the parameter int
    public Song getCurrentSong(int currentSongId){
        return songs[currentSongId] ;
    }//end of getCurrentSong
    public int getPrevSong(int currentSongIndex){
        if (currentSongIndex <= 0){
            return currentSongIndex;
        }
        else{
            return currentSongIndex-1;
        }
    }//end of getPrevSong
    public int getNextSong(int currentSongIndex){
        if (currentSongIndex >= songs.length-1){
            return currentSongIndex;
        }
        else{
            return currentSongIndex +1;
        }
    }// end of getNextSong

    public Song searchById(String id){
//Song tempSong = null;
        for (int i = 0; i < songs.length; i++) {
            Song tempSong = songs[i];
            String tempId = tempSong.getId();
            if (tempId.equals(id)){
                return tempSong;

            }
        }
        return null;
    }




}//end of class
