package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by student on 04.04.2017.
 */

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    ArrayList<Integer> numbersInt = new ArrayList<>();
    int sum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("arrayNumbers")) {
            String arrayNumbers = intent.getStringExtra("arrayNumbers");
            String[] numbers = arrayNumbers.split("\\+");
            for(String number : numbers){
                int nr = Integer.parseInt(number);
                numbersInt.add(Integer.parseInt(number));
                sum += nr;
            }
        }

        Log.d("sum", "Suma numerelor este: " + sum);
        setResult(sum, null);


        if(sum > 10){
            Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
            intent2.putExtra("sum", sum);
            getApplicationContext().startService(intent);

        }
        finish();
    }
}
