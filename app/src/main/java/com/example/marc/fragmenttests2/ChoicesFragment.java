package com.example.marc.fragmenttests2;


import android.app.Activity;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.InputStream;
import java.util.Scanner;


public class ChoicesFragment extends Fragment {

    private Button[] btns;
    private int id;

    protected int adv; // Added by Gerald
    private int story; // Added by Gerald
    private String[][] btnText; // Added by Gerald

    private Scanner s; // Added by Gerald

    ChoicesListener activityCommander;

    public interface ChoicesListener {
        public void choice(int decision);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCommander = (ChoicesListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choices_fragment, container, false);
        id = R.id.yes_button;
        btns = new Button[4]; // Changed to 4 by Gerald ; Reason: Added 2 buttons
        for (int i = 0; i < btns.length; i++) {
            btns[i] = (Button) view.findViewById(id);
            btns[i].setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            buttonClicked(v);
                        }
                    }
            );
            id++;
        }
        return view;
    }

    public void buttonClicked(View view) {
        // Major change made here: Still operates as Marc originally coded, however, also checks the
        //                         String on each button and calls the choice method and sets up button
        //                         text as needed
        for (int i = 0; i < btns.length; i++)
        {
            if (view.equals(btns[i])) {
                // Let's make sure the adventure hasn't changed.
                if(btns[i].getText().toString().length() > 8 &&
                        btns[i].getText().toString().substring(0 , 9).equals("Adventure"))
                {
                    adv = i;
                    setUpBtnText(); // Prepares Button strings (btnText is within the scope of this class)
                    story = 0; // Starts at beginning of script
                    setButtonText();
                    activityCommander.choice(i - 4);
                }
                else if (btns[i].getText().toString().substring(0).equals("Return to Story Menu"))
                {
                    adv = -1;
                    setButtonText();
                    activityCommander.choice(20); // 20 is an arbitrary number, there's no real reason I picked it
                }
                else
                {
                    advanceStory( adv , i );
                    setButtonText();
                    activityCommander.choice(i);
                }
            }
        }
    }

    public void setButtonText()
    {
        // I filled up the method as best I could. Note that "View.GONE" makes the button disappear
        //   and cause the choice fragment (our button fragment) to shrink a bit to adapt. "View.VISIBLE"
        //   does the opposite of this, and will expand our choice fragment layout a bit to adapt. The
        //   thing to notice is that we will have to scroll down to see the buttons if they're made
        //   visible again.

        if (adv == -1) {
            for (int i = 0 ; i < btns.length ; i++) {
                btns[i].setVisibility(View.VISIBLE);
                btns[i].setText("Adventure #" + (i+1));
            }
        }
        else {
            for (int i = 0; i < btns.length; i++) {
                if (story == -1 || btnText[story][0] == null) // When we reach a part of the story with no options
                {
                    btns[0].setText("Return to Story Menu");
                    btns[1].setVisibility(View.GONE);
                    btns[2].setVisibility(View.GONE);
                    btns[3].setVisibility(View.GONE);
                }
                else if (btnText[story][i] != null)
                {
                    btns[i].setVisibility(View.VISIBLE);
                    btns[i].setText(btnText[story][i]);
                }
                else
                    btns[i].setVisibility(View.GONE);
            }
        }

    }

    public void setUpBtnText()
    {
        // This is a new method I added specifically to fix the btnText array of Strings when appropriate.

        InputStream i1, i2;
        i1 = this.getResources().openRawResource(R.raw.main_menu);
        i2 = this.getResources().openRawResource(R.raw.main_menu);

        switch (adv)
        {
            case 0:
                i1 = this.getResources().openRawResource(R.raw.script_g);
                i2 = this.getResources().openRawResource(R.raw.script_g);
                break;
            case 1:
                i1 = this.getResources().openRawResource(R.raw.script_t);
                i2 = this.getResources().openRawResource(R.raw.script_t);
                break;
            case 2:
                ;//For another adventure if we add it
                break;
            case 3:
                ;//For another adventure if we add it
                break;
        }

        s = new Scanner (i1);
        int numStoryLines = 0;
        while (s.hasNextLine())
        {
            if (s.next().charAt(0) == '*')
                numStoryLines++;
            s.nextLine();
        }
        s.close();

        s = new Scanner (i2);
        btnText = new String [numStoryLines][4];
        while (s.hasNextLine())
        {
            String temp = s.nextLine();
            if (temp.substring(0 , 1).charAt(0) == '#')
                btnText[ Integer.parseInt( temp.substring(2 , 4))]
                       [ Integer.parseInt( temp.substring(5 , 7))] = temp.substring(11);
        }
        s.close();
    }

    public void advanceStory( int adventure , int choice ) {
        // This is a new method added specifically to adjust private variable "story", so that
        //    we are looking at the proper place in private variable btnText
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
