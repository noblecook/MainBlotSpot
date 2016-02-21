package com.example.patri_000.mainblotspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SurveyPage extends AppCompatActivity {


    private static RadioGroup radioGroup;
    private static RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private String getRadioButtonText()
    {
        // needed to create an "id" attribute for the RadioGroup in
        // order to get the id
        radioGroup = (RadioGroup)findViewById(R.id.SportsRadioGroup);
        int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedRadioButtonID);
        return radioButton.getText().toString();
    }

    public void onOkButtonClick(View myView)
    {
        String button = getRadioButtonText();
        String thankYouText = "I see you selected " + button + " Good Choice!";

        Toast.makeText(SurveyPage.this,thankYouText, Toast.LENGTH_LONG).show();


        // TODO:  ADD ERROR Condition for button when not selected.
         if(myView.getId() == R.id.buttonOk)
        {
            Intent myIntent = new Intent(SurveyPage.this, TwitterPage.class);
            myIntent.putExtra("pkgName", button);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(myIntent);
        }


    }

}
