package com.un.app.mynanodegreeapps;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button mybuttton1,mybuttton2,mybuttton3,mybuttton4,mybuttton5,mybuttton6;
    Toast toast1,toast2,toast3,toast4,toast5,toast6;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buttonToast1(View v){

        //code for the Spotify Streamer

        mybuttton1 = (Button)v;
        context = getApplicationContext();
        toast1 = Toast.makeText(context, "This button will launch my spotify streamer app ", Toast.LENGTH_SHORT);
        toast1.show();

    }
    public void buttonToast2(View v){

        //code for the Scores App

        mybuttton2 = (Button)v;
        context = getApplicationContext();
        toast2 = Toast.makeText(context, "This button will launch my scores app", Toast.LENGTH_SHORT);
        toast2.show();

    }
    public void buttonToast3(View v){

        //code for the Library App

        mybuttton3 = (Button)v;
        context = getApplicationContext();
        toast3 = Toast.makeText(context, "This button will launch my library app", Toast.LENGTH_SHORT);
        toast3.show();

    }
    public void buttonToast4(View v){

        //code for the Build It Bigger App

        mybuttton4 = (Button)v;
       context = getApplicationContext();
        toast4 = Toast.makeText(context, "This button will launch my build it bigger app", Toast.LENGTH_SHORT);
        toast4.show();

    }
    public void buttonToast5(View v){

        //code for the XYZ Reader App

        mybuttton5 = (Button)v;
        context = getApplicationContext();
        toast5 = Toast.makeText(context, "This button will launch my xyz reader app", Toast.LENGTH_SHORT);
        toast5.show();

    }
    public void buttonToast6(View v){

        //code for the the Capstone App

        mybuttton6 = (Button)v;
         context = getApplicationContext();
        toast6 = Toast.makeText(context, "This button will launch my capstone app", Toast.LENGTH_SHORT);
        toast6.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
