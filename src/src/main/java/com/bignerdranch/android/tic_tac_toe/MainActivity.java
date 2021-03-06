package com.bignerdranch.android.tic_tac_toe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //the buttions of the board
    private Button[][] mButtons = new Button[3][3];

   //thie textview tells you whos turnit s
    TextView  playerturn;


    boolean p1turn = true;

    //the player
    String p1;
    String p2;

      int round = 0;//the round you are on


    //create the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.tic_tac_toe_menu, menu);

    return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//checks what the user selected in the menu
switch (item.getItemId()){
    case R.id.reset_subtitle:
        reset();//reset the board
        return true;
    case R.id.name_change:
        showInputDialog();//chand the name of the players

        return true;
default:
    return super.onOptionsItemSelected(item);
}



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1= getResources().getString(R.string.player_1);
        p2= getResources().getString(R.string.player_2);
        playerturn = findViewById(R.id.player_turn);
        changeturnview();

        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons[i].length; j++) {
                String bid = "button_"+ i+j;
                int id = getResources().getIdentifier(bid,"id",getPackageName());
                mButtons[i][j] = findViewById(id);
                mButtons[i][j].setOnClickListener(this);

            }
        }

    }

    public void onClick(View v ){


        //check if the user has pressed the button
        if (!((Button)v ).getText().toString().equals("")){
            return;
        }
        //if it is the first player's turn it change the button text to x
        if (p1turn){
            ((Button)v).setText("X");
        }
        else if(!p1turn){
            ((Button)v).setText("O");
        }

        round++;//adds 1 to the round


//checks if a player has won
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
        changeturnview();//changes the textview
    }
    /*
    checks to see if the player has won the game
     */
    boolean check(){

        String[][] let = new String[3][3];

        //put the text of button into a string 2d array
        for (int i=0;i<mButtons.length;i++){
            for (int j=0;j<mButtons[i].length;j++){
                let[i][j]= mButtons[i][j].getText().toString();
            }
        }
        //check to see if player 1 or player 2 got 3 in a row
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
    /*
    display a message saying the player has won the game then reset the board
     */
    void win(String name)
    {
        Toast.makeText(this, name+" has won", Toast.LENGTH_SHORT).show();
        reset();

    }
    /*
    display a message saying the players have draw the reset the board
     */
    void draw(){
        Toast.makeText(this, "it was a draw", Toast.LENGTH_SHORT).show();
        reset();
    }

/*
reset the board
 */
    void reset(){
        //sets all button text to blank
        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons[i].length; j++) {
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


        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.name_diolog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText1 = (EditText) promptView.findViewById(R.id.editp1);
        final EditText editText2 = (EditText) promptView.findViewById(R.id.editp2);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (!editText1.getText().toString().equals("")){
                            p1= editText1.getText().toString();

                        }
                        if (!editText2.getText().toString().equals("")){

                            p2 = editText2.getText().toString();

                        }
                        changeturnview();
                    }

                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
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
