package br.com.concretesolutions.popmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.concretesolutions.popmovies.R;
import br.com.concretesolutions.popmovies.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, MainFragment.newInstance(0))
                .commit();
    }
}
