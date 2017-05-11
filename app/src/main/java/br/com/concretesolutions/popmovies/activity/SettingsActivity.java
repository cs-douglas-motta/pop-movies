package br.com.concretesolutions.popmovies.activity;

import android.app.Activity;
import android.os.Bundle;

import br.com.concretesolutions.popmovies.fragment.SettingsFragment;

/**
 * Created by douglasmotta on 09/05/17.
 */

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
