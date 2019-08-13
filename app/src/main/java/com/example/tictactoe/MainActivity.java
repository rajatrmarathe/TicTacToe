package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player=0;
    int [] box_empty={2,2,2,2,2,2,2,2,2};
    int [][] winning_conditions={{0,3,6},{1,4,7},{2,5,8},
            {0,1,2},{3,4,5},{6,7,8},
            {0,4,8},{2,4,6}};
   boolean game_active=true;

    public void appear(View view)
    {
        ImageView counter=(ImageView) view;
        int tap_counter=Integer.parseInt(counter.getTag().toString());

      if((box_empty[tap_counter]==2) && game_active) {

          box_empty[tap_counter]=player;

          if (player == 0) {
              counter.setImageResource(R.drawable.oo);
              player = 1;
          } else {
              counter.setImageResource(R.drawable.redx);
              player = 0;
          }
      }



     counter.animate().rotation(360f).setDuration(400);

      for(int winning_condition[]: winning_conditions)
      {
          if (box_empty[winning_condition[0]] == box_empty[winning_condition[1]] &&
                  box_empty[winning_condition[1]]==box_empty[winning_condition[2]] &&
                          box_empty[winning_condition[0]]!=2)
          {
              game_active=false;
              String won="Circle";
              if(box_empty[winning_condition[0]]==1)
              {
                  won="Cross";
              }

              TextView winnermsg=findViewById(R.id.winnermsg);
              LinearLayout winner=findViewById(R.id.playagainlayout);
              winnermsg.setText(won+" has won");
              winner.setVisibility(view.VISIBLE);



          }
          else
          {
              boolean game_over=true;
              for(int counterstate:box_empty)
              {
                  if(counterstate==2)
                  {
                      game_over=false;
                  }
              }
              if(game_over)
              {
                  TextView winnermsg=findViewById(R.id.winnermsg);
                  LinearLayout winner=findViewById(R.id.playagainlayout);
                  winnermsg.setText("It's a Draw");
                  winner.setVisibility(view.VISIBLE);
              }

          }
      }


    }
    public void playAgain(View view)
    {
        LinearLayout winner=findViewById(R.id.playagainlayout);
        winner.setVisibility(view.INVISIBLE);

        player=0;
        game_active=true;

        for(int i=0;i<box_empty.length;i++)
        {
            box_empty[i]=2;
        }

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout2);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {


            ( (ImageView)gridLayout.getChildAt(i)).setImageResource(0);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
