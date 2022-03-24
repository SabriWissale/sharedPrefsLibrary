package com.example.preferencesscreensettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_PREF_LANGUAGE = "language";
    public String languagePref_ID;
    ConstraintLayout layout;
    TextView text;

    public void setLocale(Locale locale) {
        Locale.setDefault(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        recreate();
        //finish();
        //startActivity(getIntent());
    }

    public void setLocaleOnCreate(Locale locale) {
        Locale.setDefault(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Paramètres");
        layout =  (ConstraintLayout) findViewById(R.id.Clayout);
        text =  findViewById(R.id.welcomeText);


        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean performSync=prefs.getBoolean("veille",true);
        String syncInterval=prefs.getString("veille_intervale","20");
        String fullName=prefs.getString("full_name","");
        String email=prefs.getString("email_address","");
        Boolean mode =prefs.getBoolean("themeMode",false);

        //layout.setBackgroundColor(ContextCompat.getColor(this, R.color.Orange));

       if(mode)
       {
           ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#36454F"));

            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
            text.setTextColor(ContextCompat.getColor(this, R.color.white));

       }
       else
       {
           ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#DAF7A6"));

           // Set BackgroundDrawable
           actionBar.setBackgroundDrawable(colorDrawable);
           layout.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
           text.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));

       }


        String language=prefs.getString("language","none");

        Toast.makeText(this,performSync+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,syncInterval+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,fullName+ "",Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,email+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"theme: " + mode+ "",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"language: " + language+ "",Toast.LENGTH_SHORT).show();
        //prefs.edit().putBoolean("shouldwe",true).apply();


       languagePref_ID = prefs.getString("language", "en-US");
        switch (languagePref_ID) {
            case "en-US":
                Locale localeEN = new Locale("en");
                setLocaleOnCreate(localeEN);
                break;
            case "fr-FR":
                Locale localeHU = new Locale("fr");
                setLocaleOnCreate(localeHU);
                break;

        }

        

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String languagePref_ID_RES = sharedPref.getString(KEY_PREF_LANGUAGE, "en-US");
        if(!languagePref_ID.equals(languagePref_ID_RES)){
            languagePref_ID_RES = languagePref_ID;
            switch (languagePref_ID_RES) {
                case "en-US":
                    Locale localeEN = new Locale("en");
                    setLocale(localeEN);
                    break;
                case "fr-FR":
                    Locale localeHU = new Locale("fr");
                    setLocale(localeHU);
                    break;

            }
        }

        ActionBar actionBar=getSupportActionBar();
        Boolean mode =sharedPref.getBoolean("themeMode",false);
        if(mode)
        {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#36454F"));

            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
            text.setTextColor(ContextCompat.getColor(this, R.color.white));

        }
        else
        {
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#DAF7A6"));

            // Set BackgroundDrawable
            actionBar.setBackgroundDrawable(colorDrawable);
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            text.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settings:
                startActivity(new Intent(this, Settings.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
   }
