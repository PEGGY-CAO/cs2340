package edu.gatech.cs2340.m4faulttolerance.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.gatech.cs2340.m4faulttolerance.R;
import edu.gatech.cs2340.m4faulttolerance.viewmodels.ServiceViewModel;

public class StartUpActivity extends AppCompatActivity {

    private ServiceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);


        viewModel = ViewModelProviders.of(this).get(ServiceViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNormalCall(View view) {
        String res = viewModel.makeNormalRequest("some url here");
        if (res == null) res = "Exception Happened during call";
        Toast.makeText(this, "Result: " + res, Toast.LENGTH_LONG).show();
    }

    public void onFailCall(View view) {
        String res = viewModel.makeBadRequest("some url here");
        if (res == null) res = "Exception Happened during call";
        Toast.makeText(this, "Result: " + res, Toast.LENGTH_LONG).show();
    }

    public void onSlowCall(View view) {
        String res = viewModel.makeSlowRequest("some url here");
        if (res == null) res = "Exception Happened during call";
        Toast.makeText(this, "Result: " + res, Toast.LENGTH_LONG).show();
    }

    public void onRetryCall(View view) {
        String res = viewModel.makeRetryRequest("Some url here");
        if (res == null) res = "Exception Happened during call";
        Toast.makeText(this, "Result: " + res, Toast.LENGTH_LONG).show();

    }
}
