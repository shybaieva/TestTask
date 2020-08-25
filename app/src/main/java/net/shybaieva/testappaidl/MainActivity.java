package net.shybaieva.testappaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstNumber, secondNumber;
    Button multBtn;
    TextView resultTV;
    IMultiply myInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = findViewById(R.id.firstNum);
        secondNumber = findViewById(R.id.secNum);
        multBtn = findViewById(R.id.multBtn);
        resultTV = findViewById(R.id.result);

        multBtn.setOnClickListener(MainActivity.this);

        Intent i = new Intent(MainActivity.this, MultService.class);

        bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            myInterface = IMultiply.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void onClick(View view) {
        int firstNumb = Integer.parseInt(firstNumber.getText().toString());
        int secNumb = Integer.parseInt(secondNumber.getText().toString());

        try {
            int result = myInterface.multiplyTwoValues(firstNumb, secNumb);
            resultTV.setText(result+"");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
