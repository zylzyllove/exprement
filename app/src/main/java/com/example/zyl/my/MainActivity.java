package com.example.zyl.my;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.sql.ClientInfoStatus;
import java.util.Stack;
import java.util.logging.LoggingMXBean;
import java.util.regex.Pattern;

import static com.example.zyl.my.R.id.equal;
import static com.example.zyl.my.R.id.standard;

import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button plus;
    Button minus;
    Button multiple;
    Button equal;
Button back;
    Button divide;
    Button point;
    Button clear;


    TextView result;
    int opflag=0;//运算符标识有没有计算,默认没有按下
    int poflag=0;//小数点有没有按过
    double sum=0, sum1=0, sum2=0,sumpoint=0;
    int operate=0;
boolean allflag=true;//程序是否出错

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        btn0 = (Button) findViewById(R.id.zero);
        btn1 = (Button) findViewById(R.id.one);
        btn2 = (Button) findViewById(R.id.two);
        btn3 = (Button) findViewById(R.id.three);
        btn4 = (Button) findViewById(R.id.four);
        btn5 = (Button) findViewById(R.id.five);
        btn6 = (Button) findViewById(R.id.six);
        btn7 = (Button) findViewById(R.id.seven);
        btn8 = (Button) findViewById(R.id.eight);
        btn9 = (Button) findViewById(R.id.nine);
        point = (Button) findViewById(R.id.point);
        clear = (Button) findViewById(R.id.clear);
        plus = (Button) findViewById(R.id.plus);
        equal = (Button) findViewById(R.id.equal);
        divide = (Button) findViewById(R.id.divide);
        minus = (Button) findViewById(R.id.minus);
        multiple = (Button) findViewById(R.id.multiple);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        equal.setOnClickListener(this);
        plus.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiple.setOnClickListener(this);
        minus.setOnClickListener(this);
        point.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    public void onClick(View w) {
        String s = result.getText().toString();//得到的数字转成字符串
        Button btn = (Button) w;

        if (btn.getId() == R.id.zero || btn.getId() == R.id.one || btn.getId() == R.id.two || btn.getId() == R.id.three || btn.getId() == R.id.four || btn.getId() == R.id.five || btn.getId() == R.id.six || btn.getId() == R.id.seven || btn.getId() == R.id.eight | btn.getId() == R.id.nine || (btn.getId() == R.id.point && opflag == 0)) {
            //表达小数
            if (btn.getId() == R.id.point) {
                if (s == null || s.equals("")) {
                    s += "0" + btn.getText();
                } else {
                    s += btn.getText();
                }
                poflag = 1;
            } else {
                s += btn.getText();
            }
            result.setText(s);
        }
        //运算符
        if (btn.getId() == R.id.plus || btn.getId() == R.id.divide || btn.getId() == R.id.minus || btn.getId() == R.id.multiple) {
            if (s == null || s.equals("")) {
                s = "0";
            }
            if (opflag !=0) {
                sum2 = Double.valueOf(s);
                switch(operate){
                    case 1:
                        sum=sum1+sum2;
                        break;
                    case 2:
                        sum=sum1-sum2;
                        break;
                    case 3:
                        sum=sum1*sum2;
                        break;
                    case 4:
                        if(sum2==0){
                            Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_LONG).show();
                            result.setText("");
                            break;
                        }
                        sum=sum1/sum2;
                        break;

                }

                sum1 = sum;//为了保证二次计算，方便

        }
        if (opflag==0) {
            sum1 = Double.valueOf(s);
        }
        switch (btn.getId()) {
            case R.id.plus:
               opflag = 1;
                break;
            case R.id.minus:
                opflag = 2;
                break;
            case R.id.multiple:
                opflag = 3;
                break;
            case R.id.divide:
                opflag = 4;
                break;
        }
        result.setText("");
    }
        if (btn.getId() == R.id.equal) {
            if (s==null || s.equals("")) {
                s = "0";
            }
            sum2 = Double.valueOf(s);
            switch (opflag) {
                case 1:
                    sum = sum1 + sum2;

                    break;
                case 2:
                    sum = sum1 - sum2;
                    break;
                case 3:
                    sum = sum1 * sum2;
                    break;
                case 4:
                    if (sum2 == 0) {
                        Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_LONG).show();
                        result.setText("0");
                        allflag=false;
                        break;
                    }
                    sum = sum1 / sum2;
                    break;
            }

            s = "" + sum;
          //正确的输出
            if(allflag){
                result.setText(s);
            }
            sum1=Double.valueOf(s);

            opflag=0;
            allflag=true;

        }

        if(btn.getId()==R.id.back){
            int i=s.length();
            String m=(s.toString().substring(0,i-1));

            result.setText(m);
        }
        if(btn.getId()==R.id.clear){
            result.setText("");
            opflag=0;
            allflag=true;
            poflag=0;
//全部清零
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.item,menu);
        return true;
    }
    public boolean onOptionsItemSelected( MenuItem item) {
         int id = item.getItemId();
         //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.standard:
                Toast.makeText(this, "标准计算器",Toast.LENGTH_SHORT).show();

                   break;
                   case R.id.jin:
                       item.setIntent(new Intent(MainActivity.this,Main2Activity.class));
                        Toast.makeText(this, "进制转换",Toast.LENGTH_SHORT).show();
                         break;
        }
          return super.onOptionsItemSelected(item);
    }

}





