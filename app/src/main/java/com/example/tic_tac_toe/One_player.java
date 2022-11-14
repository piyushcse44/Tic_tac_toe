package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class One_player extends AppCompatActivity {
  Button[] buttons = new Button[9] ;
  TextView txt_extra,txt_declaration;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int number = bundle.getInt("which");
        Toast.makeText(this, ""+number, Toast.LENGTH_SHORT).show();

        txt_extra=findViewById(R.id.txt_extra);
        txt_declaration=findViewById(R.id.txt_declaration);
       final int []x={0};
        buttons[x[0]] =findViewById(R.id.ind_11);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_12);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_13);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_21);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_22);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_23);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_31);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_32);
        x[0]++;
        buttons[x[0]] =findViewById(R.id.ind_33);
        x[0]++;
        final int[] vis=new int[9] ;
        for(int i=0;i<9;i++)
        {
            vis[i]=-1;
        }
         final int[] kon = {1,2};
   final boolean[] is_over ={false},is_free={true};
        final int[] player = {(int) (Math.random() * 2)};
        if(number==0 && player[0] ==0)
        {
            int num = (int)(Math.random()*(9))+1,final_bot = 0;
            for(int i=0;i<9;i++)
            {
                if(vis[i]==-1)
                {
                    if(num==1)
                    {
                        final_bot=i;
                        break;
                    }
                    else
                        num--;
                }
            }

            vis[final_bot]=1- player[0];
                buttons[final_bot].setText("X");

        }
        final int[] cur_player={0};
        if(number==0) {
            if (player[0] == 0)
                txt_extra.setText("Player Turn (O)");
            else
                txt_extra.setText("Player Turn (X)");
        }
        else
        {
            if (player[0] == 0) {
                txt_extra.setText("Player1 Turn (X)");
                kon[0]=2;
                kon[1]=1;
                cur_player[0]=1;
            }
            else {
                txt_extra.setText("Player2 Turn (X)");
                cur_player[0]=1;
            }
        }

        for(int i=0;i<9;i++)
        {
            int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(vis[finalI]==-1 && !is_over[0] && is_free[0]) {
                        if (number == 0)
                            vis[finalI] = player[0];
                        else {
                            vis[finalI] = cur_player[0];
                        }
                        if (number == 0) {
                            if (player[0] == 0)
                                buttons[finalI].setText("O");
                            else
                                buttons[finalI].setText("X");
                        } else {
                            if (cur_player[0] == 0)
                                buttons[finalI].setText("O");
                            else
                                buttons[finalI].setText("X");
                        }

                        if(number==0){
                        if (is_win(vis, finalI, player[0])) {
                            Toast.makeText(One_player.this, "PLAYER WINS", Toast.LENGTH_SHORT).show();
                            txt_declaration.setText("Player wins");
                            txt_declaration.setBackgroundResource(R.color.win);
                            is_over[0] = true;

                        } else {
                            int ct = 0;
                            for (int i = 0; i < 9; i++)
                                if (vis[i] == -1) ct++;
                            if (ct == 0) {
                                Toast.makeText(One_player.this, "DRAW", Toast.LENGTH_SHORT).show();
                                is_over[0] = true;
                                txt_declaration.setText("Game Draw");
                                txt_declaration.setBackgroundResource(R.color.Draw);

                            } else {
                                is_free[0]=false;
     //  //  //   // *****************************************************************************************************
                           final Handler handler = new Handler();

                                int finalCt = ct;
                                handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   if(player[0]==0)
                                       txt_extra.setText("Player Turn (O)");
                                   else
                                       txt_extra.setText(("Player Turn(X)"));

                                   int num = (int) (Math.random() * (finalCt)) + 1, final_bot = 0;
                                   for (int i = 0; i < 9; i++) {
                                       if (vis[i] == -1) {
                                           if (num == 1) {
                                               final_bot = i;
                                               break;
                                           } else
                                               num--;
                                       }
                                   }
                                   vis[final_bot] = 1 - player[0];
                                   if (player[0] == 1)
                                       buttons[final_bot].setText("O");
                                   else
                                       buttons[final_bot].setText("X");
                                   if (is_win(vis, final_bot, 1 - player[0])) {
                                       Toast.makeText(One_player.this, "BOT WINS", Toast.LENGTH_SHORT).show();
                                       txt_declaration.setText("BOT Wins");
                                       txt_declaration.setBackgroundResource(R.color.lose);
                                       is_over[0] = true;
                                   } else {
                                       if (finalCt == 1) {
                                           Toast.makeText(One_player.this, "DRAW", Toast.LENGTH_SHORT).show();
                                           is_over[0] = true;
                                           txt_declaration.setText("Game Draw");
                                           txt_declaration.setBackgroundResource(R.color.Draw);
                                       }

                                   }
                                   is_free[0]=true;

                               }

                           },500);




                            }
                        }
                    }
                    else {
                            if (is_win(vis, finalI, cur_player[0])) {
                                Toast.makeText(One_player.this, "PLAYER"+kon[cur_player[0]]+" WINS", Toast.LENGTH_SHORT).show();
                                txt_declaration.setText("PLAYER"+kon[cur_player[0]]+" WINS");
                                txt_declaration.setBackgroundResource(R.color.win);
                                is_over[0] = true;

                            }
                            else {
                                int ct = 0;
                                for (int i = 0; i < 9; i++)
                                    if (vis[i] == -1) ct++;
                                if (ct == 0) {
                                    Toast.makeText(One_player.this, "DRAW", Toast.LENGTH_SHORT).show();
                                    is_over[0] = true;
                                    txt_declaration.setText("Game Draw");
                                    txt_declaration.setBackgroundResource(R.color.Draw);

                                }
                            }


                    }


                    }
                    if(number==0) {
                        if (player[0] == 0)
                            txt_extra.setText("Bot Turn (X)");
                        else
                            txt_extra.setText("Bot Turn (O)");
                    }
                    else{

                        cur_player[0]=1-cur_player[0];
                          if(cur_player[0]==0)
                            txt_extra.setText("Player"+kon[cur_player[0]]+" Turn (O)");
                          else
                              txt_extra.setText("Player"+kon[cur_player[0]]+" Turn (X)");

                    }
                    if(is_over[0])
                    {
                        txt_extra.setText("Click to Try Again");
                    }


                }
            });
            txt_extra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(is_over[0])
                    {
                        txt_declaration.setText("");
                        is_over[0]=false;
                        for(int i =0;i<9;i++)
                            buttons[i].setText("");
                        for(int i=0;i<9;i++)
                        {
                            vis[i]=-1;
                        }
                         player[0] = (int)(Math.random()*2);
                        if(number==0 && player[0] ==0)
                        {
                            int num = (int)(Math.random()*(9))+1,final_bot = 0;
                            for(int i=0;i<9;i++)
                            {
                                if(vis[i]==-1)
                                {
                                    if(num==1)
                                    {
                                        final_bot=i;
                                        break;
                                    }
                                    else
                                        num--;
                                }
                            }

                            vis[final_bot]=1- player[0];
                            buttons[final_bot].setText("X");

                        }
                        if(number==0) {
                            if (player[0] == 0)
                                txt_extra.setText("Player Turn (O)");
                            else
                                txt_extra.setText("Player Turn (X)");
                        }
                        else
                        {
                            if (player[0] == 0) {
                                txt_extra.setText("Player1 Turn (X)");
                                kon[0]=2;
                                kon[1]=1;
                                cur_player[0]=1;
                            }
                            else {
                                txt_extra.setText("Player2 Turn (X)");
                                cur_player[0]=1;
                            }
                        }

                    }
                }
            });
        }






    }
    public boolean is_win(final int[] vis,int ind,int turn)
    {
        // diagonal
        if(vis[0]==turn && vis[4]==turn && vis[8]==turn)
            return true;
        if(vis[2]==turn && vis[4] == turn && vis[6]==turn)
            return true;
       // vertical
        for(int i=0;i<3;i++)
           if(vis[i]==turn && vis[i+3]==turn && vis[i+6]==turn)
               return true;
        // horizontal
        for(int i=0;i<3;i++)
            if(vis[3*i]==turn && vis[i*3+1]==turn && vis[i*3+2]==turn)
                return true;

        return(false);



    }
}