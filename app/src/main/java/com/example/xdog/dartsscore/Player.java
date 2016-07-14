package com.example.xdog.dartsscore;

import java.util.ArrayList;

/**
 * Created by Jimena on 13/07/2016.
 */
public class Player {
    private String playerName;
    private int score;
    private int playerNum;

    private ArrayList<Integer> scores = new ArrayList<Integer>();

    public Player(String playerName, int score, int playerNum) {
        this.playerName = playerName;
        this.score = score;
        this.playerNum = playerNum;
    }

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
        this.score -= score;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }
}
