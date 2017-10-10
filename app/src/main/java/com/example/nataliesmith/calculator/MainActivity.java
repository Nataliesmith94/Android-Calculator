package com.example.nataliesmith.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    EditText inputDeposit; // Global variables
    EditText input_interest;
    Button calculate_bt;
    TextView Year1;
    TextView Year2;
    TextView Year3;
    TextView Year4;
    TextView Year5;
    TextView errorText;

    public void calculation(String depositInput, String interestInput) //passing in data from OnClick
    {
        double Deposit = Double.parseDouble(depositInput);  //convert string to double
        double Interest = Double.parseDouble(interestInput);

        double year1 = calculateCompountInterest(Deposit, Interest, 1); //calculate compound interest
        double year2 = calculateCompountInterest(Deposit, Interest, 2);
        double year3 = calculateCompountInterest(Deposit, Interest, 3);
        double year4 = calculateCompountInterest(Deposit, Interest, 4);
        double year5 = calculateCompountInterest(Deposit, Interest, 5);

        String Result_yr1 = Double.toString(year1); //convert back to string
        String Result_yr2 = Double.toString(year2);
        String Result_yr3 = Double.toString(year3);
        String Result_yr4 = Double.toString(year4);
        String Result_yr5 = Double.toString(year5);

        Year1.setText(String.format(getString(R.string.calculation_format_output), 1, Result_yr1)); //print Results
        Year2.setText(String.format(getString(R.string.calculation_format_output), 2, Result_yr2));
        Year3.setText(String.format(getString(R.string.calculation_format_output), 3, Result_yr3));
        Year4.setText(String.format(getString(R.string.calculation_format_output), 4, Result_yr4));
        Year5.setText(String.format(getString(R.string.calculation_format_output), 5, Result_yr5));
        errorText.setText("");
    }

    private double calculateCompountInterest(double Deposit, double Interest, int numberOfYears) {
        double yearInterest = (Deposit * Math.pow(1 + (Interest / 100), numberOfYears));
        return (double) Math.round(yearInterest * 100.0) / 100.0;
    }

    public void EmptyFields()
    {
        errorText.setText("Please fill in both Initial Deposit and Interest rate");
        Year1.setText(""); //print
        Year2.setText("");
        Year3.setText("");
        Year4.setText("");
        Year5.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //creates the layout

        inputDeposit = (EditText) findViewById(R.id.inputtext_deposit); //reference to the Global variables
        input_interest = (EditText) findViewById(R.id.inputtext_interest);
        calculate_bt = (Button) findViewById(R.id.button_calculate);
        Year1 = (TextView) findViewById(R.id.text_output1yr);
        Year2 = (TextView) findViewById(R.id.text_output2yr);
        Year3 = (TextView) findViewById(R.id.text_output3yr);
        Year4 = (TextView) findViewById(R.id.text_output4yr);
        Year5 = (TextView) findViewById(R.id.text_output5yr);
        errorText = (TextView) findViewById(R.id.text_Default);

        calculate_bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String depositInput = inputDeposit.getText().toString().trim(); //Reading String from text box
                final String interestInput = input_interest.getText().toString().trim();

                if (depositInput.length() > 0  && interestInput.length() > 0) //if both text boxes are filled in
                {
                    calculation(depositInput, interestInput); //passing in the text read from text box
                }

                else  //if one of the text boxes have not been filled in
                {
                    EmptyFields();
                }

            }

        });


    }

}

