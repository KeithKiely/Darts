package com.example.xdog.dartsscore;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Jimena on 13/07/2016.
 */
public class Player implements Parcelable{
    private String playerName;
    private int score;
    private int playerNum;

    private ArrayList<Integer> scores = new ArrayList<>();

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

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
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

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
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
