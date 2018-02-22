package com.bignerdranch.android.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] mButtons = new Button[3][3];

    TextView  playerturn; //= findViewById(R.id.player_turn);


    boolean p1turn = true;

    String p1="player 1";
    String p2="player 2";

    int round = 0;

/*
    @Override
    public void OnCreateOptionMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.tic_tac_toe_menu, menu);
        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);

    }
@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.tic_tac_toe_menu, menu);
    MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerturn = findViewById(R.id.player_turn);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String bid = "button_"+ i+j;
                int id = getResources().getIdentifier(bid,"id",getPackageName());
                mButtons[i][j] = findViewById(id);
                mButtons[i][j].setOnClickListener(this);

            }
        }

    }
    public void onClick(View v ){


        if (!((Button)v ).getText().toString().equals("")){
            return;

        }
        if (p1turn){
            ((Button)v).setText("X");
           playerturn.setText("it is"+p2+"turn");

        }
        else if(!p1turn){
            ((Button)v).setText("O");
            playerturn.setText("it is"+p1+"turn");

        }

        if (check()){
            if(p1turn){
                win(p1);
            }
            else{
                win(p2);
            }
        }
        else if (round == 9){
            draw();
        }
        else {
            p1turn = !p1turn;
        }
        round++;
    }
    boolean check(){

        String[][] let = new String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                let[i][j]= mButtons[i][j].getText().toString();
            }
        }
        if ((let[0][0].equals("X") && let[0][1].equals("X") && let[0][2].equals("X"))
            ||(let[1][0].equals("X") && let[1][1].equals("X")&&let[1][2].equals("X"))
        ||(let[2][0].equals("X") && let[2][1].equals("X")&&let[2][2].equals("X"))
        ||(let[0][0].equals("X") && let[1][0].equals("X")&&let[2][0].equals("X"))
        ||(let[0][1].equals("X") && let[1][1].equals("X")&&let[2][1].equals("X"))
        ||(let[0][2].equals("X") && let[1][2].equals("X")&&let[2][2].equals("X"))
        ||(let[0][0].equals("X") && let[1][1].equals("X")&&let[2][2].equals("X"))
        ||(let[0][2].equals("X") && let[1][1].equals("X")&&let[2][0].equals("X"))
        ){
            return true;
        }
        else if ((let[0][0].equals("O") && let[0][1].equals("O") && let[0][2].equals("O"))
                ||(let[1][0].equals("O") && let[1][1].equals("O") &&let[1][2].equals("O"))
                ||(let[2][0].equals("O") && let[2][1].equals("O")&&let[2][2].equals("O"))
                ||(let[0][0].equals("O") && let[1][0].equals("O")&&let[2][0].equals("O"))
                ||(let[0][1].equals("O") && let[1][1].equals("O") &&let[2][1].equals("O"))
                ||(let[0][2].equals("O") && let[1][2].equals("O") &&let[2][2].equals("O"))
                ||(let[0][0].equals("O") && let[1][1].equals("O") &&let[2][2].equals("O"))
                ||(let[0][2].equals("O") && let[1][1].equals("O") &&let[2][0].equals("O"))){
            return true;

        }
        else {
            return false;
        }

    }
    void win(String name)
    {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        reset();

    }
    void draw(){
        Toast.makeText(this, "it was a draw", Toast.LENGTH_SHORT).show();
        reset();
    }


    void reset(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mButtons[i][j].setText("");
            }
        }

        round = 0;
        p1turn = true;
    }

    @Override    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", round);
        outState.putBoolean("player1Turn", p1turn);
        outState.putString("player1",p1);
        outState.putString("player2",p2);

    }

}
