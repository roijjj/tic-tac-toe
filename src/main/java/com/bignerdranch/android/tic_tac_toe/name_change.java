package com.bignerdranch.android.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class name_change extends AppCompatActivity {

    private Button mButton,mButton2;
    private EditText namep2;
    private  EditText namep1;

    TextView playerturn;
    //the players name
  public   String p1;
    String p2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_change);
        Bundle player = getIntent().getExtras();
        playerturn = findViewById(R.id.textView);

        p1 = player.getString("Player1");
        p2 = player.getString("Player2");
        namep1 = (EditText) findViewById(R.id.editp1);
        namep2 = (EditText) findViewById(R.id.editp2);


        mButton = (Button) findViewById(R.id.button_ok);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the user does not type anything in the textbox the name will stay the same
                if (!namep1.getText().toString().equals("")) {
                    p1 = namep1.getText().toString();

                }
                if (!namep2.getText().toString().equals("")) {

                   p2 = namep2.getText().toString();


                }
                //p2 = namep2.getText().toString();
               // playerturn = findViewById(R.id.textView);
                //playerturn.setText(namep1.getText().toString());
                sendback();
            }
        });

        mButton2 = (Button) findViewById(R.id.button_cancel);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendback();
            }

        });


    }
    /*
    sends back the names
     */
    private void sendback(){
        //playerturn.setText(p2);
        Intent i = new Intent();
        Bundle bundle = new Bundle();

        bundle.putString("Player1",p1);
        bundle.putString("Player2",p2);

        i.putExtras(bundle);
        setResult(RESULT_OK,i);


        finish();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putString("p1",p1);
        outState.putString("p2",p2);

    }

    @Override
    public void onRestoreInstanceState(Bundle SaveInstanceState){
        super.onRestoreInstanceState(SaveInstanceState);

        p1 = SaveInstanceState.getString("p1");
        p2 = SaveInstanceState.getString("p2");

    }
}
