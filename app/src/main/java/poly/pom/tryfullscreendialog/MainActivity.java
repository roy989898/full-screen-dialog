package poly.pom.tryfullscreendialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button hibuttonClick = (Button) findViewById(R.id.button);
        Button btDialog = (Button) findViewById(R.id.btDialog);
        hibuttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Dialog","ButtonClick");
                int[] location = new int[2];
                hibuttonClick.getLocationInWindow(location);
                Toast.makeText(MainActivity.this, "x:" + location[0] + " " + "y:" + location[1], Toast.LENGTH_SHORT).show();
            }
        });
        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialog
                FragmentManager fragmentManager = getSupportFragmentManager();
                CustomDialogFragment newFragment = CustomDialogFragment.newInstance(hibuttonClick);
                // The device is smaller, so show the fragment fullscreen
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // For a little polish, specify a transition animation
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // To make it fullscreen, use the 'content' root view as the container
                // for the fragment, which is always the root view for the activity
                transaction.add(android.R.id.content, newFragment)
                        .addToBackStack(null).commit();

            }
        });
    }
}
