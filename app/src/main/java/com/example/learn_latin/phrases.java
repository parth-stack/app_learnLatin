package com.example.learn_latin;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class phrases extends AppCompatActivity {
    ArrayList<word> words=new ArrayList<word>();
    wordadapter itemsAdapter;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        words.add(new word("one","kénne",R.raw.phrase_are_you_coming));
        words.add(new word("two","ʔóṣṣa",R.raw.phrase_come_here));
        words.add(new word("three","tel(l)éeka",R.raw.phrase_how_are_you_feeling));
        words.add(new word("four","húja",R.raw.phrase_im_coming));
        words.add(new word("five","kenékkuh",R.raw.phrase_im_feeling_good));
        words.add(new word("six","pácciṭak",R.raw.phrase_lets_go));
        words.add(new word("seven","ṣeelóowih",R.raw.phrase_my_name_is));
        words.add(new word("eight","ʔóṣṣuwah",R.raw.phrase_what_is_your_name));
        words.add(new word("nine","kénnekotoh",R.raw.phrase_where_are_you_going));
        words.add(new word("ten","kíccih",R.raw.phrase_yes_im_coming));
        itemsAdapter = new wordadapter(getApplicationContext(), words);
        ListView listView =findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clear_resource();     //agar resource clear nhi kiya toh 2 click krne par 2 file ek saath bajengi
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
        });
    }

    @Override
    protected void onStop() {// clear resource if avtivity imideatly stopped
        super.onStop();
        clear_resource();
    }

    private void clear_resource(){
        if(mp!=null){
            mp.release();
            mp=null;
        }
    }
}