package com.example.aliothman.mutimedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RSAmain extends AppCompatActivity {

    EditText EDTP;
    EditText EDTQ;
    EditText EDTqprimeWithZ;
    EditText EDTinput;
    Button BUEncrypt;
    TextView TXVPfreq;
    TextView TXVqfreq;
    TextView TXVN;
    TextView TXVZout;
    TextView TXVE;
    TextView TXVD;
    TextView TXVoutputEncryopted;
    TextView TXVoutputDecryptopted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rs);
        EDTP = (EditText) findViewById(R.id.P);
        EDTQ = (EditText) findViewById(R.id.Q);
        EDTqprimeWithZ = (EditText) findViewById(R.id.qprimeWithZ);
        EDTinput = (EditText) findViewById(R.id.input);
        BUEncrypt = (Button) findViewById(R.id.Encrypt);
        TXVPfreq = (TextView) findViewById(R.id.Pfreq);
        TXVqfreq = (TextView) findViewById(R.id.qfreq);
        TXVN = (TextView) findViewById(R.id.N);
        TXVZout = (TextView) findViewById(R.id.Zout);
        TXVE = (TextView) findViewById(R.id.E);
        TXVD = (TextView) findViewById(R.id.D);
        TXVoutputEncryopted = (TextView) findViewById(R.id.outputEncryopted);
        TXVoutputDecryptopted = (TextView) findViewById(R.id.outputDecryptopted);

        TXVPfreq.setVisibility(View.INVISIBLE);
        TXVqfreq.setVisibility(View.INVISIBLE);
        TXVN.setVisibility(View.INVISIBLE);
        TXVZout.setVisibility(View.INVISIBLE);
        TXVE.setVisibility(View.INVISIBLE);
        TXVD.setVisibility(View.INVISIBLE);
        TXVoutputEncryopted.setVisibility(View.INVISIBLE);
        TXVoutputDecryptopted.setVisibility(View.INVISIBLE);



        BUEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p1 = EDTP.getText().toString();
                int p = Integer.parseInt(p1);
                String q1 = EDTQ.getText().toString();
                int q = Integer.parseInt(q1);
                String enterE = EDTqprimeWithZ.getText().toString();
                String pt = EDTinput.getText().toString();

                Rsa rr = new Rsa();
                rr.setpE(p);
                rr.setqE(q);
                rr.setN(p * q);
                rr.setZ((p - 1) * (q - 1));
                rr.setE(Integer.parseInt(enterE));

                int h = 0;
                int b = 0;
                int km = 0;

                ArrayList numbers = new ArrayList();
                ArrayList Denumbers = new ArrayList();

                Denumbers.clear();
                char[] array1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
                String encrypted = "";//to save message
                String encryptedMessage = "";//to save message

                String Decrypted = "";//to save message

                //forloop for plaintext
                for (int i = 0; i < pt.length(); i++) {
                    char y = pt.charAt(i);
                    //forloop for char array
                    for (int j = 0; j < array1.length; j++) {
                        char a = array1[j];
                        //if char in plain text == char in array then get its index then add to listarray
                        if (y == a) {
                            int k = j;
                            int m = k;
                            //add to list array
                            numbers.add(rr.calculateC(m));
                        }
                    }
                }
                for (int kk = 0; kk < numbers.size(); kk++) {
                    encrypted += numbers.get(kk);
                    encryptedMessage += "C(" + pt.charAt(kk) + ")" + numbers.get(kk) + ",";
                }


                for (int de = 0; de < numbers.size(); de++) {
                    int i = (int) numbers.get(de);

                    Decrypted += rr.calculateP(i, (int) rr.calculateD(rr.getE(), rr.getZ())) + ",";

                }

                TXVPfreq.setText("P" + "=" + rr.pE);
                TXVqfreq.setText("Q" + "=" + rr.qE);
                TXVN.setText("N" + "=" + rr.getN());
                TXVZout.setText("Z" + "=" + rr.getZ());
                TXVE.setText("E" + "=" + rr.getE());
                TXVD.setText("D" + "=" + rr.calculateD(rr.getE(), rr.getZ()));
                TXVoutputEncryopted.setText("encrypted Message is :" + "(" + encryptedMessage + ")");
                TXVoutputDecryptopted.setText("Decrypted is " + "(" + Decrypted + ")");

                TXVPfreq.setVisibility(View.VISIBLE);
                TXVqfreq.setVisibility(View.VISIBLE);
                TXVN.setVisibility(View.VISIBLE);
                TXVZout.setVisibility(View.VISIBLE);
                TXVE.setVisibility(View.VISIBLE);
                TXVD.setVisibility(View.VISIBLE);
                TXVoutputEncryopted.setVisibility(View.VISIBLE);
                TXVoutputDecryptopted.setVisibility(View.VISIBLE);

            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
