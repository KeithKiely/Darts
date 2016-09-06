package com.example.xdog.dartsscore;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keith Kiely on 25/08/2016.
 * Pair class: hold pairs of integers
 */

public class Pair implements Parcelable{
    private final int player1, player2;

    public Pair(int player1, int player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    protected Pair(Parcel in) {
        player1 = in.readInt();
        player2 = in.readInt();
    }

    public static final Creator<Pair> CREATOR = new Creator<Pair>() {
        @Override
        public Pair createFromParcel(Parcel in) {
            return new Pair(in);
        }

        @Override
        public Pair[] newArray(int size) {
            return new Pair[size];
        }
    };

    public int getPlayer1()   {
        return player1;
    }
    public int getPlayer2() {
        return player2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(player1);
        dest.writeInt(player2);
    }
}
