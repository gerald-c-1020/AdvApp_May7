package com.example.marc.fragmenttests2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PictureFragment extends Fragment {

    private int[] pictureArray;
    ImageView picture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_fragment, container, false);
        //for loop to add all the pictures we have in the drawable folder
        pictureArray = new int[20];//should be the size of the number of pictures associated with story beats
        pictureArray[0] = R.drawable.storypic101;
        pictureArray[1] = R.drawable.storypic102;
        pictureArray[2] = R.drawable.storypic103;
        pictureArray[3] = R.drawable.storypic104;
        pictureArray[4] = R.drawable.storypic105;
        pictureArray[5] = R.drawable.storypic106;
        pictureArray[6] = R.drawable.storypic107;
        pictureArray[7] = R.drawable.storypic108;
        pictureArray[8] = R.drawable.storypic109;
        pictureArray[9] = R.drawable.storypic110;
        pictureArray[10] = R.drawable.storypic111;
        pictureArray[11] = R.drawable.storypic112;
        pictureArray[12] = R.drawable.storypic113;
        pictureArray[13] = R.drawable.storypic201;
        pictureArray[14] = R.drawable.storypic202;
        pictureArray[15] = R.drawable.storypic203;
        pictureArray[16] = R.drawable.storypic204;
        pictureArray[17] = R.drawable.storypic205;
        pictureArray[18] = R.drawable.storypic206;
        pictureArray[19] = R.drawable.storypic207;
        picture = (ImageView)view.findViewById(R.id.pic);
        return view;
    }

    public void setPicture(int adventure, int story)//there should be some parameters based on how the story progression works
    {
        if (story == -1)
            picture.setImageResource(R.drawable.storymenu);
        else if (adventure ==0){
            switch (story){
                case(0):{
                    picture.setImageResource(pictureArray[0]);
                    break;
                }
                case(1):{
                    picture.setImageResource(pictureArray[1]);
                    break;
                }
                case(2):{
                    picture.setImageResource(pictureArray[2]);
                    break;
                }
                case(3):{
                    picture.setImageResource(pictureArray[3]);
                    break;
                }
                case(4):{
                    picture.setImageResource(pictureArray[4]);
                    break;
                }
                case(5):{
                    picture.setImageResource(pictureArray[5]);
                    break;
                }
                case(6):{
                    picture.setImageResource(pictureArray[6]);
                    break;
                }
                case(7):{
                    picture.setImageResource(pictureArray[7]);
                    break;
                }
                case(8):{
                    picture.setImageResource(pictureArray[8]);
                    break;
                }
                case(9):{
                    picture.setImageResource(pictureArray[9]);
                    break;
                }
                case(10):{
                    picture.setImageResource(pictureArray[10]);
                    break;
                }
                case(11):{
                    picture.setImageResource(pictureArray[11]);
                    break;
                }
                case(12):{
                    picture.setImageResource(pictureArray[12]);
                    break;
                }
            }
        }
        else if (adventure ==1) {
            switch (story) {
                case (0): {
                    picture.setImageResource(pictureArray[13]);
                    break;
                }
                case (1): {
                    picture.setImageResource(pictureArray[14]);
                    break;
                }
                case (2): {
                    picture.setImageResource(pictureArray[15]);
                    break;
                }
                case (3): {
                    picture.setImageResource(pictureArray[16]);
                    break;
                }
                case (4): {
                    picture.setImageResource(pictureArray[17]);
                    break;
                }
                case (5): {
                    picture.setImageResource(pictureArray[18]);
                    break;
                }
                case (6): {
                    picture.setImageResource(pictureArray[19]);
                    break;
                }
            }
        }
        else if (adventure ==2){

        }
        else if (adventure==3){

        }
    }


}
