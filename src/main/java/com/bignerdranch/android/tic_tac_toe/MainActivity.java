package com.bignerdranch.android.tic_tac_toe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] mButtons = new Button[3][3];

    TextView  playerturn;


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
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.tic_tac_toe_menu, menu);

    return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.editp1){
            reset();

        }*/
switch (item.getItemId()){
    case R.id.reset_subtitle:
        reset();
        return true;
    case R.id.name_change:
        showInputDialog();

        return true;
default:
    return super.onOptionsItemSelected(item);
}



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerturn = findViewById(R.id.player_turn);
        changeturnview();

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


        }
        else if(!p1turn){
            ((Button)v).setText("O");


        }

        if (check()){
            if(p1turn){
                win(p1);
            }
            else{
                win(p2);
            }
        }
        else if (round == 8){
            draw();
        }
        else {
            p1turn = !p1turn;
        }
        round++;
        changeturnview();
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
        Toast.makeText(this, name+" has won", Toast.LENGTH_SHORT).show();
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
        changeturnview();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("round", round);
        outState.putBoolean("p1Turn", p1turn);
        outState.putString("p1",p1);
        outState.putString("p2",p2);

    }

    @Override
    public void onRestoreInstanceState(Bundle SaveInstanceState){
        super.onRestoreInstanceState(SaveInstanceState);
        round = SaveInstanceState.getInt("round");
        p1turn = SaveInstanceState.getBoolean("p1turn");
        p1 = SaveInstanceState.getString("p1");
        p2 = SaveInstanceState.getString("p2");

    }
    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.name_diolog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText1 = (EditText) promptView.findViewById(R.id.editp1);
        final EditText editText2 = (EditText) promptView.findViewById(R.id.editp2);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (!editText1.getText().toString().equals("")){
                            p1= editText1.getText().toString();

                        }
                        if (!editText2.getText().toString().equals("")){

                            p2 = editText2.getText().toString();

                        }
                    }

                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        changeturnview();
    }
    public void changeturnview(){
        if(p1turn){

            playerturn.setText("it is "+p1+"'s turn");
        }
        else {

            playerturn.setText("it is "+p2+"'s turn");
        }
    }

}
