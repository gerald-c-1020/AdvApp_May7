package com.example.marc.fragmenttests2;

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


}