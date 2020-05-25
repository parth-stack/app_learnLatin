package com.example.learn_latin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class numbers extends AppCompatActivity {
    ArrayList<word> words=new ArrayList<word>();
    wordadapter itemsAdapter;
    MediaPlayer mp;
    AudioManager am;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        am=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        words.add(new word("one","kénne",R.drawable.number_one,R.raw.number_one));
        words.add(new word("two","ʔóṣṣa",R.drawable.number_two,R.raw.number_two));
        words.add(new word("three","tel(l)éeka",R.drawable.number_three,R.raw.number_three));
        words.add(new word("four","húja",R.drawable.number_four,R.raw.number_four));
        words.add(new word("five","kenékkuh",R.drawable.number_five,R.raw.number_five));
        words.add(new word("six","pácciṭak",R.drawable.number_six,R.raw.number_six));
        words.add(new word("seven","ṣeelóowih",R.drawable.number_seven,R.raw.number_seven));
        words.add(new word("eight","ʔóṣṣuwah",R.drawable.number_eight,R.raw.number_eight));
        words.add(new word("nine","kénnekotoh",R.drawable.number_nine,R.raw.number_nine));
        words.add(new word("ten","kíccih",R.drawable.number_ten,R.raw.number_ten));
        itemsAdapter = new wordadapter(getApplicationContext(), words);
        ListView listView =findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clear_resource();     //agar resource clear nhi kiya toh 2 click krne par 2 file ek saath bajengi

                int result=am.requestAudioFocus(afChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mp=MediaPlayer.create(getApplicationContext(),itemsAdapter.getItem(position).getPlayresource());
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            clear_resource();//      after completion  resource clear karne ke liye
                        }
                    });
                    Log.v("mylog ||"+getApplicationContext().toString(),"|| "+itemsAdapter.getItem(position).toString());

                }
                else if(result==AudioManager.AUDIOFOCUS_REQUEST_FAILED){

                }

            }
        });
    }

    @Override
    protected void onStop() {                        // clear resource if avtivity imideatly stopped
        super.onStop();
        clear_resource();
    }

    private void clear_resource(){
        if(mp!=null){
            mp.release();
            mp=null;
        }
    }

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                clear_resource();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // Pause playback
                mp.pause();
                mp.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Lower the volume, keep playing
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Your app has been granted audio focus again
                // Raise volume to normal, restart playback if necessary
                mp.start();
            }
        }
    };
}