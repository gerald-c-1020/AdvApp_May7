/*package com.example.marc.fragmenttests2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class GameEnvironment extends ActionBarActivity implements ChoicesFragment.ChoicesListener{

    private int adv; // Added by Gerald
    private int story; // Added by Gerald

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_environment);
    }

//registers the selection and passes it over to the log fragment
    @Override
    public void choice(int decision) {
        // Major change made here: decision can still be 0-3, but it can now possibly be -4 <= x <= 3 OR 20
        //               Reason: Sending 20 (it's just an arbitrary number, no meaning) is a sign to
        //                       clear out the log fragment
        //                       Sending -4 <= x <= -1 is a sign that we need a script different than
        //                       what's currently being used for Strings
        LogFragment log = (LogFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);
        if (decision == 20)
        {
            // This will clear out log fragment for adventure choice
            adv = -1;
            log.clearText();
        }
        else if (decision < 0)
        {
            // This will tell the log fragment to scan a different text file for the proper script
            adv = decision + 4;
            story = 0;
            decision += 4;
            log.addText(adv, story , decision);
        }
        else
        {
            // Works as Marc intended, and moves the story along. Note: "advanceStory" is a method
            //    added to keep track of which script we're using and where we are within the script.
            //    It's here so that we can pass the proper information to the log fragment (For story
            //    development) and the picture fragment (For the correct picture use). Furthermore,
            //    "adv" and "story" can also be used as a means of knowing which music to play.
            advanceStory( adv , decision );
            log.addText( story , decision);
        }
    }


    public void advanceStory( int adventure , int choice ) {
        // This is a new method added specifically to adjust private variable "story"
        if (adventure == 0) // Adventure #1's progression (Gerald's Script)
        {
            switch (story)
            {
                case 0:
                    if (choice == 0)
                        story = 1;
                    else if (choice == 1)
                        story = 2;
                    else if (choice == 2)
                        story = 3;
                    break;
                case 1:
                    if (choice == 0)
                        story = 4;
                    else if (choice == 1)
                        story = 5;
                    else if (choice == 2)
                        story = 6;
                    break;
                case 2:
                    if (choice == 0)
                        story = 7;
                    else if (choice == 1)
                        story = 8;
                    else if (choice == 2)
                        story = 9;
                    break;
                case 3:
                    if (choice == 0)
                        story = 10;
                    else if (choice == 1)
                        story = 11;
                    else if (choice == 2)
                        story = 12;
                    break;
                case 4:
                    story = -1;
                    break;
                case 5:
                    story = -1;
                    break;
                case 6:
                    story = -1;
                    break;
                case 7:
                    story = -1;
                    break;
                case 8:
                    story = -1;
                    break;
                case 9:
                    story = -1;
                    break;
                case 10:
                    story = -1;
                    break;
                case 11:
                    story = -1;
                    break;
                case 12:
                    story = -1;
                    break;
            }
        }
        else if (adventure == 1) // Adventure #2's progression (Taiwo's Script)
        {
            switch (story)
            {
                case 0:
                    if (choice == 0)
                        story = 1;
                    else if (choice == 1)
                        story = 2;
                    break;
                case 1:
                    if (choice == 0)
                        story = 3;
                    else if (choice == 1)
                        story = 4;
                    break;
                case 2:
                    if (choice == 0)
                        story = 5;
                    else if (choice == 1)
                        story = 6;
                    break;
                case 3:
                    story = -1;
                    break;
                case 4:
                    story = -1;
                    break;
                case 5:
                    story = -1;
                    break;
                case 6:
                    story = -1;
                    break;
            }
        }
    }


}*/



package com.example.marc.fragmenttests2;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;


public class GameEnvironment extends ActionBarActivity implements ChoicesFragment.ChoicesListener, AdapterView.OnItemClickListener{

    private int adv; // Added by Gerald
    private int story; // Added by Gerald
    //Added by Kevin
    private MediaPlayer mp;
    private LogFragment log;
    private ChoicesFragment choicesfrag;
    private PictureFragment pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_environment);
        //mp = MediaPlayer.create(getApplicationContext(), R.raw.a1);
        //mp.start();
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        getSupportActionBar().setTitle("Adventures");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position) {
            case 0:
            {
                //resets log and choices fragments to the story menu
                choicesfrag = (ChoicesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
                choice(20);
                choicesfrag.adv = -1;
                choicesfrag.setButtonText();
                break;
            }
            case 1:
            {
                //brings user back to beautiful title screen
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    //registers the selection and passes it over to the log fragment
    @Override
    public void choice(int decision) {
        // Major change made here: decision can still be 0-3, but it can now possibly be -4 <= x <= 3 OR 20
        //               Reason: Sending 20 (it's just an arbitrary number, no meaning) is a sign to
        //                       clear out the log fragment
        //                       Sending -4 <= x <= -1 is a sign that we need a script different than
        //                       what's currently being used for Strings
        LogFragment log = (LogFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);
        pic = (PictureFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (decision == 20)
        {
            // This will clear out log fragment for adventure choice
            adv = -1;
            log.clearText();
            story = -1;
            pic.setPicture(adv, story);
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            //mp = MediaPlayer.create(getApplicationContext(), R.raw.a1);
            //mp.start();
        }
        else if (decision < 0)
        {
            // This will tell the log fragment to scan a different text file for the proper script
            adv = decision + 4;
            story = 0;
            decision += 4;
            log.addText(adv, story , decision);
            pic.setPicture(adv, story);
            //if (mp != null && mp.isPlaying()) {
            //    mp.stop();
            //    mp.release();
            //}
            if (decision < 2) { // We only have two adventures
                mp = MediaPlayer.create(getApplicationContext(), R.raw.a1);
                mp.start();
            }
        }
        else
        {
            // Works as Marc intended, and moves the story along. Note: "advanceStory" is a method
            //    added to keep track of which script we're using and where we are within the script.
            //    It's here so that we can pass the proper information to the log fragment (For story
            //    development) and the picture fragment (For the correct picture use). Furthermore,
            //    "adv" and "story" can also be used as a means of knowing which music to play.
            advanceStory(adv, decision);
            log.addText( story , decision);
            pic.setPicture(adv, story);
        }
    }


    public void advanceStory( int adventure , int choice ) {
        // This is a new method added specifically to adjust private variable "story"
        if (adventure == 0) // Adventure #1's progression (Gerald's Script)
        {
            switch (story)
            {
                case 0:
                    if (choice == 0) {
                        story = 1;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a2);
                        mp.start();
                    }
                    else if (choice == 1) {
                        story = 2;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a3);
                        mp.start();

                    }
                    else if (choice == 2) {
                        story = 3;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a4);
                        mp.start();
                    }
                    break;
                case 1:
                    if (choice == 0) {
                        story = 4;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a3);
                        mp.start();
                    }
                    else if (choice == 1) {
                        story = 5;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a6);
                        mp.start();
                    }
                    else if (choice == 2) {
                        story = 6;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a8);
                        mp.start();
                    }
                    break;
                case 2:
                    if (choice == 0) {
                        story = 7;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a4);
                        mp.start();
                    }
                    else if (choice == 1) {
                        story = 8;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a7);
                        mp.start();
                    }
                    else if (choice == 2) {
                        story = 9;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a10);
                        mp.start();
                    }
                    break;
                case 3:
                    if (choice == 0) {
                        story = 10;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a5);
                        mp.start();
                    }
                    else if (choice == 1) {
                        story = 11;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a7);
                        mp.start();
                    }
                    else if (choice == 2) {
                        story = 12;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a9);
                        mp.start();
                    }
                    break;
                case 4:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a6);
                    mp.start();
                    break;
                case 5:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a7);
                    mp.start();
                    break;
                case 6:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a10);
                    mp.start();
                    break;
                case 7:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a9);
                    mp.start();
                    break;
                case 8:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a4);
                    mp.start();
                    break;
                case 9:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a5);
                    mp.start();
                    break;
                case 10:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a8);
                    mp.start();
                    break;
                case 11:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a2);
                    mp.start();
                    break;
                case 12:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a3);
                    mp.start();
                    break;
            }
        }
        else if (adventure == 1) // Adventure #2's progression (Taiwo's Script)
        {
            switch (story)
            {
                case 0:
                    if (choice == 0) {
                        story = 1;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a4);
                        mp.start();
                    }
                    else if (choice == 1) {
                        story = 2;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a9);
                        mp.start();
                    }
                    break;
                case 1:
                    if (choice == 0) {
                        story = 3;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a10);
                        mp.start();
                    }
                    else if (choice == 1) {
                        story = 4;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a2);
                        mp.start();
                    }
                    break;
                case 2:
                    if (choice == 0) {
                        story = 5;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a5);
                        mp.start();
                    }
                    else if (choice == 1) {
                        story = 6;
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.a7);
                        mp.start();
                    }
                    break;
                case 3:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a6);
                    mp.start();
                    break;
                case 4:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a8);
                    mp.start();
                    break;
                case 5:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a2);
                    mp.start();
                    break;
                case 6:
                    story = -1;
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.a10);
                    mp.start();
                    break;
            }
        }
    }


}