package com.example.aliothman.mutimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;

public class Affine extends AppCompatActivity {

    EditText m_input;
    EditText k_input;
    EditText plain_input;
    Button En;
    Button De;
    TextView m_inv_output;
    TextView cipher_output;
    TextView mainPlain_output;

    int m_key;
    int k_key;
    public static int module = 26;

    public String encryption(String input) {
        m_key = Integer.parseInt(m_input.getText().toString());
        k_key = Integer.parseInt(k_input.getText().toString());

        StringBuilder builder = new StringBuilder();
        for (int in = 0; in < input.length(); in++) {
            char character = input.charAt(in);
            //1- check gcd between (m and 26)
            if (gcd(m_key, module) == 1)
                continue;
            //low of encyption
            character = (char) ((m_key * (character - 'a') + k_key) % module + 'a');
            builder.append(character);
        }
        return builder.toString();
    }

    public int gcd(int m, int n) {
        m_key = m;
        module = n;
        int gcd = 1; // Initial gcd is 1
        int k = 2; // Possible gcd
        while (k <= m_key && k <= module) {
            if (m_key % k == 0 && module % k == 0)
                gcd = k; // Update gcd
                k++; // Next possible gcd
        }
        return gcd;
    }

    String decrypt(String input) {
        StringBuilder builder = new StringBuilder();
        // compute m(dash): multiblicative inverse
        BigInteger inverse = BigInteger.valueOf(m_key).modInverse(BigInteger.valueOf(module));
        m_inv_output.setVisibility(View.VISIBLE);
        m_inv_output.setText("" + inverse);
        // perform  decryption
        for (int in = 0; in < input.length(); in++) {
            char character = input.charAt(in);
            int decoded = inverse.intValue() * (character - 'a' - k_key + module);
            character = (char) (decoded % module + 'a');
            builder.append(character);
        }
        return builder.toString();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affine);
        m_input = (EditText) findViewById(R.id.m);
        k_input = (EditText) findViewById(R.id.k);
        plain_input = (EditText) findViewById(R.id.input);
        En = (Button) findViewById(R.id.Encrypt);
        De = (Button) findViewById(R.id.DEcrypt);
        m_inv_output = (TextView) findViewById(R.id.m_inverse);
        cipher_output = (TextView) findViewById(R.id.Cipher);
        mainPlain_output = (TextView) findViewById(R.id.Plain_Cipher);
        cipher_output.setVisibility(View.INVISIBLE);
        m_inv_output.setVisibility(View.INVISIBLE);
        mainPlain_output.setVisibility(View.INVISIBLE);

        En.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Res_Cipher = encryption(plain_input.getText().toString());
                cipher_output.setText(Res_Cipher);
                cipher_output.setVisibility(View.VISIBLE);

            }
        });

        De.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Res_Plain2 = decrypt(cipher_output.getText().toString());
                mainPlain_output.setText(Res_Plain2);
                mainPlain_output.setVisibility(View.VISIBLE);

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
