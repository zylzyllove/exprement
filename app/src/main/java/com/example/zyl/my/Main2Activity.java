package com.example.zyl.my;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final EditText textView=(EditText) findViewById(R.id.text);
        final TextView put=(TextView)findViewById(R.id.put);
        RadioButton binary=(RadioButton)findViewById(R.id.binary);
        binary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = Integer.toBinaryString(Integer.valueOf(textView.getText().toString()));
                put.setText(str);
            }
        });

        RadioButton octal=(RadioButton)findViewById(R.id.octal);
        octal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = Integer.toOctalString(Integer.valueOf(textView.getText().toString()));
                put.setText(str);
            }
        });

        RadioButton hex=(RadioButton)findViewById(R.id.hex);
        hex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = Integer.toHexString(Integer.valueOf(textView.getText().toString()));
                put.setText(str);
            }
        });
    }
}
