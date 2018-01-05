package com.example.aliothman.mutimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Transposition extends AppCompatActivity {

    EditText msgString;
    EditText keyString;
    TextView CipherString;
    Button EncString;

    EditText CipheString;
    EditText keyPString;
    TextView PlainString;
    Button DECString;

    String key;
    String message;
    String encryptedMessage;


    String key2;
    String cypherText;
    String decryptedCypherText;


    //ArrayList<Character> duplicate = new ArrayList<Character>();

    public boolean getKey2() {
        key2 = keyPString.getText().toString();
        if (!isKeyOk2()) {
            return false;
        }
        return true;
    }

    public boolean isKeyOk2() {
        key2 = key2.toUpperCase();

        // check if key textbox is empty
        if (key2.length() == 0) {
            Toast.makeText(getApplicationContext(), "WARNING: KEY textbox is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        // check for spaces in the key
        for (int i = 0; i < key2.length(); i++) {
            if (key2.charAt(i) == ' ') {
                Toast.makeText(getApplicationContext(), "WARNING: space characters in KEY!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // check if key-length is < 2
        if (key2.length() < 2) {
            Toast.makeText(getApplicationContext(), "WARNING: length of KEY less than 2!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // check for duplicate letters in the key
        for (int i = 0; i < key2.length() - 1; i++) {
            for (int j = i + 1; j < key2.length(); j++) {
                if (key2.charAt(i) == key2.charAt(j)) {
//                     duplicate.add(key2.charAt(j));
//                    duplicate.clear();
                    Toast.makeText(getApplicationContext(), "WARNING: duplicate letters in KEY!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets cyphertext to decrypt from the user
     */
    public boolean getCypherText() {
        cypherText = CipheString.getText().toString();
        if (!isCypherTextOk()) {
            return false;
        }
        return true;
    }

    public boolean isCypherTextOk() {
        // check if the cyphertext textbox is empty
        if (cypherText.length() == 0) {
            Toast.makeText(getApplicationContext(), "WARNING: CYPHERTEXT textbox is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // check for spaces in the cyphertext
        for (int i = 0; i < cypherText.length(); i++) {
            if (cypherText.charAt(i) == ' ') {
                Toast.makeText(getApplicationContext(), "WARNING: space characters in CYPHERTEXT!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // check if cyphertext length is okay
//        if (cypherText.length() % key2.length() != 0) {
//            Toast.makeText(getApplicationContext(), "WARNING: check if the cyphertext is okay!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    public void decryptCypherText() {
        decryptedCypherText = "";

        String cypherBlock[] = new String[key2.length()];

        String rearrangedKey = sortAscending(key2);

        // rearrange the key in ascending order
        for (int i = 0; i < cypherBlock.length; i++) {
            cypherBlock[i] = rearrangedKey.substring(i, i + 1);
        }

        // length of string from cypherText to be appended to cypherBlock
        int blockLength = cypherText.length() / key2.length();

        // append each block of size blockLength to cypherBlock[n]
        {
            int i = 0;
            int j = 0;

            while (true) {
                cypherBlock[i] += cypherText.substring(j, j + blockLength);
                i++;
                if (i == cypherBlock.length) {
                    break;
                }

                j += blockLength;
            }

        }

        // extract characters from cypherBlock to form the cleartext
        for (int k = 1; k < blockLength + 1; k++) {
            for (int i = 0; i < key2.length(); i++) {
                for (int j = 0; j < cypherBlock.length; j++) {
                    if (key2.charAt(i) == cypherBlock[j].charAt(0)) {
                        decryptedCypherText += cypherBlock[j].substring(k, k + 1);
                    }
                }
            }
        }

    }

    public void showDecryptedCypherText() {
        PlainString.setText(decryptedCypherText);
    }

    /**
     * Arranges the letters of the key in ascending order
     */
    public String sortAscending(String s) {
        char charArray[] = s.toCharArray();
        Arrays.sort(charArray);
        s = "";
        // convert the char array to string
        for (int i = 0; i < charArray.length; i++) {
            s += charArray[i] + "";
        }

        return s;
    }


    public boolean getKeyEncrypt() {
        key = keyString.getText().toString();
        if (!checkKeyEncrypt()) {
            return false;
        }
        return true;
    }

    private boolean checkKeyEncrypt() {
        key = key.toUpperCase();

        // check if key textbox is empty
        if (key.length() == 0) {
            Toast.makeText(getApplicationContext(), "WARNING: KEY textbox is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // check for spaces in the key
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == ' ') {
                Toast.makeText(getApplicationContext(), "WARNING: space characters in KEY!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // check if key-length is <2
        if (key.length() < 2) {
            Toast.makeText(getApplicationContext(), "WARNING: length of KEY less than 2!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public boolean getPlaintext() {
        message = msgString.getText().toString();
        if (!checkPlaintext()) {
            return false;
        }
        toUpper();
        return true;
    }

    private boolean checkPlaintext() {
        if (message.length() == 0) {
            Toast.makeText(getApplicationContext(), "WARNING: MESSAGE textbox is empty!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void toUpper() {
        message = message.toUpperCase();
        StringBuffer temp = new StringBuffer();

        for (int i = 0; i < message.length(); i++) {
            if (!(message.charAt(i) == ' ')) {
                temp.append(message.charAt(i));
            }
        }

        message = temp.toString();
    }

    public void encryptMessage() {
        // clear the contents of encryptedMessage
        encryptedMessage = "";

        String temp[] = new String[key.length()];

        // populate the array
        for (int l = 0; l < key.length(); l++) {
            temp[l] = "";
        }

        int k = 0;
        int j = 0;
        // fill list with key
        for (int p = 0; p < key.length(); p++) {
            temp[p] = key.substring(p, p + 1);
            j = k;
            for (; j < message.length(); j += key.length()) {
                temp[p] += message.substring(j, j + 1);
            }
            ++k;
        }

        int matched = 0;

        //complex part
        for (int i = 65; i <= 90; i++) {
            for (j = 0; j < key.length(); j++) {
                if ((char) i == temp[j].charAt(0)) {
                    encryptedMessage += temp[j].substring(1);
                    ++matched;
                }
            }
            if (matched >= key.length()) {
                break;
            }
        }
    }

    public void showEncryptedMessage() {
        CipherString.setText(encryptedMessage);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transposition);
        msgString = (EditText) findViewById(R.id.MsgTV);
        keyString = (EditText) findViewById(R.id.KeyTV);
        CipherString = (TextView) findViewById(R.id.Cipher);
        Button EncString = (Button) findViewById(R.id.Encrypt);

        CipheString = (EditText) findViewById(R.id.cypherText);
        keyPString = (EditText) findViewById(R.id.KeyCi);
        PlainString = (TextView) findViewById(R.id.Plain);
        Button DECString = (Button) findViewById(R.id.Decrypt);

        CipherString.setVisibility(View.INVISIBLE);
        PlainString.setVisibility(View.INVISIBLE);
        EncString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getKeyEncrypt()) {
                    return;
                }
                if (!getPlaintext()) {
                    return;
                }
                encryptMessage();
                showEncryptedMessage();
                CipherString.setVisibility(View.VISIBLE);
            }
        });


        DECString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getKey2()) {
                    return;
                }
                if (!getCypherText()) {
                    return;
                }
                decryptCypherText();
                showDecryptedCypherText();
                PlainString.setVisibility(View.VISIBLE);
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
