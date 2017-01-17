package br.com.gabricio.mosquito.engine;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Tempo {

    private double tempoAtual;

    public double atual() {
        return tempoAtual;
    }

    public void passa() {
        tempoAtual += 0.1;
    }

    public void renicia(){
        tempoAtual = 0;
    }
}
