package br.com.gabricio.mosquito;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import br.com.gabricio.mosquito.engine.Game;


public class MainActivity extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        this.game = new Game(this);
        container.addView(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.game.cancela();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.game.inicia();
        new Thread(this.game).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.game.cancela();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.game.cancela();
    }
}
