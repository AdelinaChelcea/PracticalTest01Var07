package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private EditText editText = null;
    private TextView showText = null;
    private Button addButton = null;
    private Button computeButton = null;
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private int sum = 0;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.addButton:
                    editText = (EditText)findViewById(R.id.edit_text);
                    showText = (TextView)findViewById(R.id.show);
                    int number = Integer.parseInt(editText.getText().toString());
                    if(showText.getText().toString().isEmpty()){

                        showText.setText(String.valueOf(number));
                    }
                    else{
                        String currentString = showText.getText().toString();
                        showText.setText(currentString + "+" + number);
                    }
                    break;
                case R.id.compute:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                    intent.putExtra("arrayNumbers", showText.getText().toString());
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        editText = (EditText)findViewById(R.id.edit_text);
        addButton = (Button)findViewById(R.id.addButton);
        computeButton = (Button)findViewById(R.id.compute);
        addButton.setOnClickListener(buttonClickListener);
        computeButton.setOnClickListener(buttonClickListener);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            sum = resultCode;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("sum", sum);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("sum")) {
            sum = savedInstanceState.getInt("sum");
        } else {
            sum = 0;
        }
        Log.d("sum", "Suma este: " + sum);

    }
}
