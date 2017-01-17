package br.com.gabricio.mosquito.engine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.concurrent.TimeUnit;

import br.com.gabricio.mosquito.R;
import br.com.gabricio.mosquito.RestartGameActivity;
import br.com.gabricio.mosquito.elementos.EnxameMosquitos;
import br.com.gabricio.mosquito.elementos.Pontuacao;
import br.com.gabricio.mosquito.elementos.Raquete;


/**
 * Created by Gabricio on 02/02/2016.
 */
public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private final SurfaceHolder holder = getHolder();
    private boolean estaRodando = true;
    private Tela tela;
    private Bitmap background;
    private EnxameMosquitos enxameMosquitos;
    private Raquete raquete1;
    private Raquete raquete2;
    private Pontuacao pontuacao;
    private boolean mostraRaqueteUm = false;
    private boolean mostraRaqueteDois = false;
    private Tempo tempo;
    private MediaPlayer m = null;

    public Game(Context context) {
        super(context);
        tela = new Tela(context);
        setOnTouchListener(this);
        inicializarElementos();
    }

    private void inicializarElementos() {
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.pontuacao = new Pontuacao();
        this.tempo = new Tempo();
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);
        this.enxameMosquitos = new EnxameMosquitos(getContext(), tela, pontuacao, tempo);
        this.raquete1 = new Raquete(getContext(), tela, R.drawable.raquete2);
        this.raquete2 = new Raquete(getContext(), tela, R.drawable.raquete);
        this.m = MediaPlayer.create(getContext(),R.raw.mosquito_voando);
    }

    @Override
    public void run() {
        long timeMillis = System.currentTimeMillis();
        long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
        while (estaRodando) {
            if (!holder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = holder.lockCanvas();

            this.tempo.aumentaEDecrementaTempo();

            canvas.drawBitmap(background, 0, 0, null);
            enxameMosquitos.desenharNo(canvas);
            enxameMosquitos.voar();


            pontuacao.desenhaNo(canvas);

            if (mostraRaqueteUm) {
                raquete1.desenharNo(canvas);
            }
            if (mostraRaqueteDois) {
                raquete2.desenharNo(canvas);
            }

            if (this.pontuacao.getErros() >= 5) {
                Intent intent = new Intent(getContext(), RestartGameActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("mensagem", "Game Over");
                intent.putExtras(mBundle);
                getContext().startActivity(intent);
                cancela();
            }

            if (this.pontuacao.getAcertos() == EnxameMosquitos.QTDE_MOSQUITOS){
                long timeMillisFim = System.currentTimeMillis();
                long timeSecondsFim = TimeUnit.MILLISECONDS.toSeconds(timeMillisFim);
                int tempoDeJogo = (int) (timeSecondsFim - timeSeconds);

                Intent intent = new Intent(getContext(), RestartGameActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("mensagem", "Você venceu em " + String.valueOf(tempoDeJogo) + " segundos.");
                intent.putExtras(mBundle);
                getContext().startActivity(intent);
                cancela();
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void cancela() {
        this.estaRodando = false;
        m.stop();
    }

    public void inicia() {
        this.estaRodando = true;
        m.start();
        m.setLooping(true);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mostraRaqueteUm = true;
                raquete1.setX((int) motionEvent.getX());
                raquete1.setY((int) motionEvent.getY());
                return true;
            case MotionEvent.ACTION_UP:
                mostraRaqueteUm = false;
                mostraRaqueteDois = true;
                raquete2.setX((int) motionEvent.getX());
                raquete2.setY((int) motionEvent.getY());
                enxameMosquitos.verificarColisao(motionEvent);
                mostraRaqueteUm = false;
                mostraRaqueteDois = false;
                MediaPlayer.create(getContext(),R.raw.batida).start();
                return true;
        }
        return false;
    }
}