package com.example.xdog.dartsscore;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Keith Kiely on 22/08/2016.
 * Player: Information related player
 */
public class Player implements Parcelable{
    private String playerName;
    private int score;
    private int playerNum;
    public Player(String playerName, int score, int playerNum) {
        this.playerName = playerName;
        this.score = score;
        this.playerNum = playerNum;
    }

    protected Player(Parcel in) {
        playerName = in.readString();
        score = in.readInt();
        playerNum = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void subtractScore(int score) {
        this.score -= score;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
        dest.writeInt(score);
        dest.writeInt(playerNum);
    }
}
