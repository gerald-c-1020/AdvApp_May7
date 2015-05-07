package com.example.marc.fragmenttests2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

public class LogFragment extends Fragment {

    private TextView t;
    private String in, stream;

    private int adv; // Added by Gerald
    private int story; // Added by Gerald
    private String[] storyTxt; // Added by Gerald
    private String[][] btnText; // Added by Gerald

    private Scanner s; // Added by Gerald

    public LogFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.text_log_fragment, container, false);
        in = "<";
        t = (TextView) view.findViewById(R.id.log);
        stream = "";
        adv = -1;
        setUpScriptText();
        question();
        return view;
    }

    public void question()
    {
        // Still works as Marc originally wrote it, but now writes script stuff instead
        stream += ">" + storyTxt[story] + "\n";
        t.setText(stream);
    }

    public void addText(int script , int location , int num)
    {
        // NOTE: Had to change addText because more information is needed than a single int, so it's split into two
        //       variations of the method - This variation is for when we're changing "scripts".
        adv = script;
        story = location;
        setUpScriptText();
        stream += in + "Adventure #" + (script + 1) + " chosen!\n";
        question();
    }

    public void addText(int location , int num)
    {
        // This is the second variation on the addText method, used to proceed through a story, and
        //    assumes there's no change in which "script" we're using.
        stream = t.getText().toString();
        stream += in + btnText[story][num] + "\n";
        story = location;
        t.setText(stream);
        question();
    }

    public void clearText()
    {
        // Method added specifically to clear the textView so we can present the user with the chance
        //   to decide which adventure to run through
        stream = "";
        adv = -1;
        story = 0;
        setUpScriptText();
        t.setText(stream);
        question();
    }

    public void setUpScriptText() {
        // This is a new method I added specifically to fix the btnText
        //     and storyTxt arrays of Strings when appropriate.

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
        storyTxt = new String [numStoryLines];
        btnText = new String [numStoryLines][4];
        while (s.hasNextLine())
        {
            String temp = s.nextLine();
            if (temp.substring(0 , 1).charAt(0) == '*')
                storyTxt[ Integer.parseInt( temp.substring(2 , 4))] = temp.substring(5);
            else if (temp.substring(0 , 1).charAt(0) == '#')
                btnText[ Integer.parseInt( temp.substring(2 , 4))]
                       [ Integer.parseInt( temp.substring(5 , 7))] = temp.substring(11);
        }
        s.close();
    }
}
