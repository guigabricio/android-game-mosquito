package br.com.gabricio.mosquito.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.gabricio.mosquito.engine.Cores;


/**
 * Created by Gabricio on 02/02/2016.
 */
public class Pontuacao {

    private static final Paint BRANCO = Cores.getCorDaPontuacaoAcertos();
    private static final Paint VERMELHO = Cores.getCorDaPontuacaoErros();
    private int acertos = 0;
    private int erros = 0;

    public Pontuacao(){
    }

    public int getAcertos() {
        return acertos;
    }

    public int getErros() {
        return erros;
    }

    public void aumentarAcertos() {
        this.acertos++;
    }

    public void aumentarErros() {
        this.erros++;
    }

    public void desenhaNo(Canvas canvas) {
        canvas.drawText( String.valueOf(acertos),10 ,100,BRANCO);

        canvas.drawText( String.valueOf(erros),300 ,100 ,VERMELHO);
    }
}
