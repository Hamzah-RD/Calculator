package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import  org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    TextView tv_solution, tv_result;
    MaterialButton btn_divid, btn_mutiply, btn_subtrack, btn_plus;
    MaterialButton btn_openbreak, btn_close,btn_c, btn_equal,btn_ac;
    MaterialButton btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,btn_dot,btn_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        tv_solution = findViewById(R.id.tv_solution);
        tv_result = findViewById(R.id.tv_result);


        btn_1 = assignId(R.id.btn_1);
        btn_2 = assignId(R.id.btn_2);
        btn_3 = assignId(R.id.btn_3);
        btn_4 = assignId(R.id.btn_4);
        btn_5 = assignId(R.id.btn_5);
        btn_6 = assignId(R.id.btn_6);
        btn_7 = assignId(R.id.btn_7);
        btn_8 = assignId(R.id.btn_8);
        btn_9 = assignId(R.id.btn_9);
        btn_0 = assignId(R.id.btn_0);
        btn_dot = assignId(R.id.btn_dot);
        btn_openbreak = assignId(R.id.btn_openbreak);
        btn_close = assignId(R.id.btn_close);
        btn_equal = assignId(R.id.btn_equal);
        btn_divid = assignId(R.id.btn_divid);
        btn_mutiply = assignId(R.id.btn_mutiply);
        btn_subtrack = assignId(R.id.btn_subtrack);
        btn_plus = assignId(R.id.btn_plus);
        btn_ac = assignId(R.id.btn_ac);
        btn_c = assignId(R.id.btn_c);


    }

    MaterialButton assignId(int id) {
        MaterialButton    btn = findViewById(id);
        btn.setOnClickListener(this);
        return btn;
    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttonText=button.getText().toString();
        String  datatTocalcute=tv_solution.getText().toString();


        if(buttonText.equals("AC")){
            tv_solution.setText("");
            tv_result.setText("0");
            return;
        }

        if(buttonText.equals("=")) {
            tv_solution.setText(tv_result.getText());
        return;
        }
        if(buttonText.equals("C")){
            if (!datatTocalcute.isEmpty()) {
                datatTocalcute = datatTocalcute.substring(0, datatTocalcute.length() - 1);
            }

        }else
        {
            datatTocalcute = datatTocalcute + buttonText;
        }

        tv_solution.setText(datatTocalcute);


        String finalResult=getResult(datatTocalcute);
        if(!finalResult.equals("Err")){
            tv_result.setText(finalResult);
        }
    }

    String getResult(String data)
    {
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }

       return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}
