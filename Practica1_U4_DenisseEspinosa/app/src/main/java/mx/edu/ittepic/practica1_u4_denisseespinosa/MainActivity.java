package mx.edu.ittepic.practica1_u4_denisseespinosa;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int ronda,jugador,d1,d2,total;
    String ganador;
    int jugador1[]={0,0,0,0,0},jugador2[]={0,0,0,0,0},jugador3[]={0,0,0,0,0};
    Button play;
    Thread tirojugador1,tirojugador2,tirojugador3;
    ImageView d1j1,d2j1,d1j2,d2j2,d1j3,d2j3;
    TextView rond,turnoj,t1j1,t2j1,t3j1,t4j1,totalj1,t1j2,t2j2,t3j2,t4j2,totalj2,t1j3,t2j3,t3j3,t4j3,totalj3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rond = findViewById(R.id.ronda);
        turnoj = findViewById(R.id.turnoj);

        d1j1 = findViewById(R.id.dado1j1);
        d2j1 = findViewById(R.id.dado2j1);
        d1j2 = findViewById(R.id.dado1j2);
        d2j2 = findViewById(R.id.dado2j2);
        d1j3 = findViewById(R.id.dado1j3);
        d2j3 = findViewById(R.id.dado2j3);

        t1j1 = findViewById(R.id.j1t1);
        t2j1 = findViewById(R.id.j1t2);
        t3j1 = findViewById(R.id.j1t3);
        t4j1 = findViewById(R.id.j1t4);
        totalj1 = findViewById(R.id.j1t);

        t1j2 = findViewById(R.id.j2t1);
        t2j2 = findViewById(R.id.j2t2);
        t3j2 = findViewById(R.id.j2t3);
        t4j2 = findViewById(R.id.j2t4);
        totalj2 = findViewById(R.id.j2t);

        t1j3 = findViewById(R.id.j3t1);
        t2j3 = findViewById(R.id.j3t2);
        t3j3 = findViewById(R.id.j3t3);
        t4j3 = findViewById(R.id.j3t4);
        totalj3 = findViewById(R.id.j3t);

        play = findViewById(R.id.iniciar);


    }

    private void jugador1(){
        tirojugador1 = new Thread(){
            public void run(){
                while(ronda < 5 && !tirojugador1.isInterrupted()){

                    try {
                        sleep(3000); //Milisegundo a dormir (hilo), esto evita que se vuelva inestable
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    total = 0;
                    jugador = 1;
                    tiro();
                    tiros();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rondatiroj();
                            imgdados();
                            mostrartiros();
                        }
                    });
                    jugador2();
                    Thread.currentThread().interrupt();

                }//fin while
            }
        };
        tirojugador1.start();
    }

    private void jugador2(){
        tirojugador2 = new Thread(){
            public void run(){
                while (!tirojugador2.isInterrupted()){

                    try {
                        sleep(3000); //Milisegundo a dormir (hilo), esto evita que se vuelva inestable
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    total = 0;
                    jugador = 2;
                    tiro();
                    tiros();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rondatiroj();
                            imgdados();
                            mostrartiros();
                        }
                    });
                    jugador3();
                    Thread.currentThread().interrupt();

                }//Fin while
            }
        };
        tirojugador2.start();
    }

    private void jugador3(){
        tirojugador3 = new Thread(){
            public void run(){
                while (!tirojugador3.isInterrupted()){

                    try {
                        sleep(3000); //Milisegundo a dormir (hilo), esto evita que se vuelva inestable
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    total = 0;
                    jugador = 3;
                    tiro();
                    tiros();
                    ronda ++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rondatiroj();
                            imgdados();
                            mostrartiros();
                            if(finjuego()){
                                AlertDialog.Builder alertaGanador = new AlertDialog.Builder(MainActivity.this);
                                alertaGanador.setTitle("¡GANADOR!").setMessage(ganador).show();
                                //turno=1;
                                //btnTirarDados.setEnabled(true);
                            }
                        }
                    });
                    jugador1();
                    Thread.currentThread().interrupt();

                }//Fin while
            }
        };
        tirojugador3.start();
    }

    public void iniciar(View v){
        play.setClickable(false);
        for (int x = 0; x < 5; x++) {
            jugador1[x] = 0;
            jugador2[x] = 0;
            jugador3[x] = 0;
            fin();
        }
        ganador = "";
        ronda = 1;
        mostrartiros();
        jugador1();

    }

    private void tiro(){
        d1 = (int) (Math.random() * 6) + 1;
        d2 = (int) (Math.random() * 6) + 1;
        total = d1+d2;
    }

    private void fin(){
        d1j1.setVisibility(View.INVISIBLE);
        d2j1.setVisibility(View.INVISIBLE);
        d1j2.setVisibility(View.INVISIBLE);
        d2j2.setVisibility(View.INVISIBLE);
        d1j3.setVisibility(View.INVISIBLE);
        d2j3.setVisibility(View.INVISIBLE);
    }

    private void imgdados(){
        if (jugador == 1){
            if (d1 == 1){
                d1j1.setImageResource(R.drawable.baseline_looks_one_black_48dp);
            }else if (d1 == 2){
                d1j1.setImageResource(R.drawable.baseline_looks_two_black_48dp);
            }else if (d1 == 3){
                d1j1.setImageResource(R.drawable.baseline_looks_3_black_48dp);
            }else if (d1 == 4){
                d1j1.setImageResource(R.drawable.baseline_looks_4_black_48dp);
            }else if (d1 == 5){
                d1j1.setImageResource(R.drawable.baseline_looks_5_black_48dp);
            }else if (d1 == 6){
                d1j1.setImageResource(R.drawable.baseline_looks_6_black_48dp);
            }

            if (d2 == 1){
                d2j1.setImageResource(R.drawable.baseline_looks_one_black_48dp);
            }else if (d2 == 2){
                d2j1.setImageResource(R.drawable.baseline_looks_two_black_48dp);
            }else if (d2 == 3){
                d2j1.setImageResource(R.drawable.baseline_looks_3_black_48dp);
            }else if (d2 == 4){
                d2j1.setImageResource(R.drawable.baseline_looks_4_black_48dp);
            }else if (d2 == 5){
                d2j1.setImageResource(R.drawable.baseline_looks_5_black_48dp);
            }else if (d2 == 6){
                d2j1.setImageResource(R.drawable.baseline_looks_6_black_48dp);
            }
            d1j1.setVisibility(View.VISIBLE);
            d2j1.setVisibility(View.VISIBLE);
        }else if (jugador == 2){
            if (d1 == 1){
                d1j2.setImageResource(R.drawable.baseline_looks_one_black_48dp);
            }
            if (d1 == 2){
                d1j2.setImageResource(R.drawable.baseline_looks_two_black_48dp);
            }
            if (d1 == 3){
                d1j2.setImageResource(R.drawable.baseline_looks_3_black_48dp);
            }
            if (d1 == 4){
                d1j2.setImageResource(R.drawable.baseline_looks_4_black_48dp);
            }
            if (d1 == 5){
                d1j2.setImageResource(R.drawable.baseline_looks_5_black_48dp);
            }
            if (d1 == 6){
                d1j2.setImageResource(R.drawable.baseline_looks_6_black_48dp);
            }
            if (d2 == 1){
                d2j2.setImageResource(R.drawable.baseline_looks_one_black_48dp);
            }
            if (d2 == 2){
                d2j2.setImageResource(R.drawable.baseline_looks_two_black_48dp);
            }
            if (d2 == 3){
                d2j2.setImageResource(R.drawable.baseline_looks_3_black_48dp);
            }
            if (d2 == 4){
                d2j2.setImageResource(R.drawable.baseline_looks_4_black_48dp);
            }
            if (d2 == 5){
                d2j2.setImageResource(R.drawable.baseline_looks_5_black_48dp);
            }
            if (d2 == 6){
                d2j2.setImageResource(R.drawable.baseline_looks_6_black_48dp);
            }
            d1j2.setVisibility(View.VISIBLE);
            d2j2.setVisibility(View.VISIBLE);
        }else if (jugador == 3){
            if (d1 == 1){
                d1j3.setImageResource(R.drawable.baseline_looks_one_black_48dp);
            }
            if (d1 == 2){
                d1j3.setImageResource(R.drawable.baseline_looks_two_black_48dp);
            }
            if (d1 == 3){
                d1j3.setImageResource(R.drawable.baseline_looks_3_black_48dp);
            }
            if (d1 == 4){
                d1j3.setImageResource(R.drawable.baseline_looks_4_black_48dp);
            }
            if (d1 == 5){
                d1j3.setImageResource(R.drawable.baseline_looks_5_black_48dp);
            }
            if (d1 == 6){
                d1j3.setImageResource(R.drawable.baseline_looks_6_black_48dp);
            }
            if (d2 == 1){
                d2j3.setImageResource(R.drawable.baseline_looks_one_black_48dp);
            }
            if (d2 == 2){
                d2j3.setImageResource(R.drawable.baseline_looks_two_black_48dp);
            }
            if (d2 == 3){
                d2j3.setImageResource(R.drawable.baseline_looks_3_black_48dp);
            }
            if (d2 == 4){
                d2j3.setImageResource(R.drawable.baseline_looks_4_black_48dp);
            }
            if (d2 == 5){
                d2j3.setImageResource(R.drawable.baseline_looks_5_black_48dp);
            }
            if (d2 == 6){
                d2j3.setImageResource(R.drawable.baseline_looks_6_black_48dp);
            }
            d1j3.setVisibility(View.VISIBLE);
            d2j3.setVisibility(View.VISIBLE);
        }
    }

    private void tiros(){
        if (ronda == 1){
            if (jugador == 1){
                jugador1[0]=total;
                jugador1[4]=jugador1[4]+total;
            }else if(jugador == 2){
                jugador2[0]=total;
                jugador2[4]=jugador2[4]+total;
            }else if (jugador == 3){
                jugador3[0]=total;
                jugador3[4]=jugador3[4]+total;
            }
        }else if (ronda == 2){
            if (jugador == 1){
                jugador1[1]=total;
                jugador1[4]=jugador1[4]+total;
            }else if(jugador == 2){
                jugador2[1]=total;
                jugador2[4]=jugador2[4]+total;
            }else if (jugador == 3){
                jugador3[1]=total;
                jugador3[4]=jugador3[4]+total;
            }
        }else if (ronda == 3){
            if (jugador == 1){
                jugador1[2]=total;
                jugador1[4]=jugador1[4]+total;
            }else if(jugador == 2){
                jugador2[2]=total;
                jugador2[4]=jugador2[4]+total;
            }else if (jugador == 3){
                jugador3[2]=total;
                jugador3[4]=jugador3[4]+total;
            }
        }else if(ronda == 4){
            if (jugador == 1){
                jugador1[3]=total;
                jugador1[4]=jugador1[4]+total;
            }else if(jugador == 2){
                jugador2[3]=total;
                jugador2[4]=jugador2[4]+total;
            }else if (jugador == 3){
                jugador3[3]=total;
                jugador3[4]=jugador3[4]+total;
            }
        }
    }

    private void mostrartiros(){
        t1j1.setText(jugador1[0]+"");
        t2j1.setText(jugador1[1]+"");
        t3j1.setText(jugador1[2]+"");
        t4j1.setText(jugador1[3]+"");
        totalj1.setText(jugador1[4]+"");

        t1j2.setText(jugador2[0]+"");
        t2j2.setText(jugador2[1]+"");
        t3j2.setText(jugador2[2]+"");
        t4j2.setText(jugador2[3]+"");
        totalj2.setText(jugador2[4]+"");

        t1j3.setText(jugador3[0]+"");
        t2j3.setText(jugador3[1]+"");
        t3j3.setText(jugador3[2]+"");
        t4j3.setText(jugador3[3]+"");
        totalj3.setText(jugador3[4]+"");
    }

    private void rondatiroj(){

        if (ronda == 5){
            turnoj.setText(jugador+"");
        }else {
            turnoj.setText(jugador+"");
            rond.setText(ronda+"");
        }

    }

    private boolean finjuego(){
        if(ronda < 5) {return false;}
        if(jugador1[4] > jugador2[4] && jugador2[4] > jugador3[4]){ganador = "¡Jugador 1!";}
        if(jugador2[4] > jugador1[4] && jugador1[4] > jugador3[4]){ganador = "¡Jugador 2!";}
        if(jugador3[4] > jugador1[4] && jugador1[4] > jugador2[4]){ganador = "¡Jugador 3!";}
        play.setClickable(true);
        return true;
    }

}
