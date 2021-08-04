package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];

    private boolean player1turn=true;

    private int roundcount;

    private int player1point;
    private int player2point;

    private TextView textviewplayer1;
    private TextView textviewplayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewplayer1=findViewById(R.id.text_view_p1);
        textviewplayer2=findViewById(R.id.text_view_p2);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonid="button_" + i + j;
                int resId=getResources().getIdentifier(buttonid,"id",getPackageName());
                buttons[i][j]=findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }


        Button buttonreset=findViewById(R.id.button_reset);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetboard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(player1turn){
            ((Button) v).setText("x");
        }
        else{
            ((Button) v).setText("o");
        }
        roundcount++;
        if(checkforwin()){
            if(player1turn){
                player1wins();
            }
            else{
                player2wins();
            }
        }
        else if(roundcount==9){
            draw();
        }else{
            player1turn=!player1turn;
        }
    }
    private boolean checkforwin(){
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!field[i][0].equals("")){
                return true;
            }
        }
        for(int j=0;j<3;j++){
            if(field[0][j].equals(field[1][j])&&field[0][j].equals(field[2][j])&&!field[0][j].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&!field[0][0].equals("")){
            return true;
        }
        if(field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])&&!field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1wins(){
        player1point++;
        Toast.makeText(this,"Player 1 wins", Toast.LENGTH_SHORT).show();
        updatepointstext();
        resetboard();
    }
    private void player2wins(){
        player2point++;
        Toast.makeText(this,"Player 2 wins", Toast.LENGTH_SHORT).show();
        updatepointstext();
        resetboard();
    }
    private void draw(){
        Toast.makeText(this,"Draw", Toast.LENGTH_SHORT).show();
        resetboard();
    }

    private void updatepointstext(){
        textviewplayer1.setText("Player 1:"+player1point);
        textviewplayer2.setText("Player 2:" +player2point);
    }
    private void resetboard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;
    }

}
