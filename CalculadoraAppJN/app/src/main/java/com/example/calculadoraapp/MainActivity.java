package com.example.calculadoraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.concurrent.ArrayBlockingQueue;

public class MainActivity extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnAdd,btnEqual,btnClear,btnSin,btnCos,btnTan,btnX,btnDiv,btnRefractor,btnFin;
    TextView txtResult, txtResultado;
    String proceso, op,trig,resultadoSinComa,procesoRad,res;
    Switch switchDegrees;
    double resultado;
    int pulsado,numPulsado;
    Boolean presionado,error;

    public MainActivity(){
        this.resultado = 0;
        this.proceso = "";
        this.op = null;
        this.trig = null;
        this.presionado = false;
        this.error = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAdd = findViewById(R.id.btnAdd);
        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnX = findViewById(R.id.btnX);
        btnDiv = findViewById(R.id.btnDiv);
        btnRefractor = findViewById(R.id.btnRefractor);
        btnFin = findViewById(R.id.btnFin);
        txtResult = findViewById(R.id.txtResult);
        txtResultado = findViewById(R.id.txtResultado);
        switchDegrees = findViewById(R.id.switchDegrees);

       switchDegrees.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(presionado == true)
                    {
                        resultado = 0;
                        numPulsado = 0;
                        op = null;
                        trig = null;
                        int i = 0;
                        if(switchDegrees.isChecked()) {
                            if(proceso.contains("sin")|proceso.contains("cos")|proceso.contains("tan")){
                                for(int j=0;j<proceso.length();j++) {
                                    char c = proceso.charAt(j);
                                    String s = String.valueOf(c);
                                    procesoRad = procesoRad + s;
                                    if (Character.isDigit(c)) {
                                        i = Integer.parseInt(s);
                                        operacionRad(i);
                                    } else {
                                        switch (c) {
                                            case ('+'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "sumar";
                                                break;
                                            case ('-'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "restar";
                                                break;
                                            case ('x'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "multiplicar";
                                                break;
                                            case ('/'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "dividir";
                                                break;
                                            case ('i'):
                                                trig = "sin";
                                                break;
                                            case ('o'):
                                                trig = "cos";
                                                break;
                                            case ('a'):
                                                trig = "tan";
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                                calcularResultado();
                                String resultString=quitarComa();
                                if(resultString.length()>=12){
                                    res= resultString.substring(0,11);
                                    txtResultado.setText(res);
                                }else{
                                    txtResultado.setText(resultString);
                                }
                                trig = null;
                            }else{
                                if(resultadoSinComa.length()>=12){
                                    res= resultadoSinComa.substring(0,11);
                                    txtResultado.setText(res);
                                }else{
                                    txtResultado.setText(resultadoSinComa);
                                }
                            }
                        }else {
                            if(proceso.contains("sin")|proceso.contains("cos")|proceso.contains("tan")){
                                for(int j=0;j<proceso.length();j++) {
                                    char c = proceso.charAt(j);
                                    String s = String.valueOf(c);
                                    procesoRad = procesoRad + s;
                                    if (Character.isDigit(c)) {
                                        i = Integer.parseInt(s);
                                        operacionRad(i);
                                    } else {
                                        switch (c) {
                                            case ('+'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "sumar";
                                                break;
                                            case ('-'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "restar";
                                                break;
                                            case ('x'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "multiplicar";
                                                break;
                                            case ('/'):
                                                if (op == null) {
                                                    resultado = numPulsado;
                                                    numPulsado = 0;
                                                } else {
                                                    calcularResultado();
                                                }
                                                op = "dividir";
                                                break;
                                            case ('i'):
                                                trig = "sin";
                                                break;
                                            case ('o'):
                                                trig = "cos";
                                                break;
                                            case ('a'):
                                                trig = "tan";
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                                calcularResultado();
                                String resultString=quitarComa();
                                if(resultString.length()>=12){
                                    res= resultString.substring(0,11);
                                    txtResultado.setText(res);
                                }else{
                                    txtResultado.setText(resultadoSinComa);
                                }
                                trig = null;
                            }else{
                                if(resultadoSinComa.length()>=12){
                                res= resultadoSinComa.substring(0,11);
                                txtResultado.setText(res);
                            }else{
                                txtResultado.setText(resultadoSinComa);
                            }
                            }
                        }
                    }
           }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResultado.setText("");
                resultado = 0;
                op = null;
                proceso = "";
                txtResult.setText("");
                presionado = false;
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "0");
                    pulsado = 0;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "1");
                    pulsado = 1;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "2");
                    pulsado = 2;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "3");
                    pulsado = 3;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "4");
                    pulsado = 4;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "5");
                    pulsado = 5;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "6");
                    pulsado = 6;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "7");
                    pulsado = 7;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "8");
                    pulsado = 8;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presionado == true)
                {
                    txtResult.setText("");
                    txtResultado.setText("");
                    resultado = 0;
                    op = null;
                    trig = null;
                }else {
                    txtResult.setText(proceso + "9");
                    pulsado = 9;
                    proceso = proceso + pulsado;
                    operacion(pulsado);
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(op==null){
                    resultado = numPulsado;
                }else{
                    calcularResultado();
                }
                txtResult.setText(proceso + "+");
                proceso = proceso + "+";
                op = "sumar";

            }
        });
        btnRefractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(op==null){
                    resultado = numPulsado;
                }else{
                    calcularResultado();
                }
                txtResult.setText(proceso + "-");
                proceso = proceso + "-";
                op = "restar";
            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(op==null){
                    resultado = numPulsado;
                }else{
                    calcularResultado();
                }
                txtResult.setText(proceso + "x");
                proceso = proceso + "x";
                op = "multiplicar";
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(op==null){
                    resultado = numPulsado;
                }else{
                    calcularResultado();
                }
                txtResult.setText(proceso + "/");
                proceso = proceso + "/";
                op = "dividir";
            }
        });
        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText(proceso + "sin(");
                proceso = proceso + "sin(";
                trig = "sin";
            }
        });
        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText(proceso + "cos(");
                proceso = proceso + "cos(";
                trig = "cos";
            }
        });
        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText(proceso + "tan(");
                proceso = proceso + "tan(";
                trig = "tan";
            }
        });
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularResultado();
                txtResult.setText(proceso + ")");
                proceso = proceso + ")";
                trig = null;
            }
        });

        btnEqual.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Character.isDigit(proceso.charAt(proceso.length() - 1))){
                    calcularResultado();
                }
                resultadoSinComa = quitarComa();
                if(error){
                    txtResultado.setText("ERROR");
                    error = false;
                }else{
                    if(resultadoSinComa.length()>=12){
                        res= resultadoSinComa.substring(0,11);
                        txtResultado.setText(res);
                    }else{
                        txtResultado.setText(resultadoSinComa);
                    }
                }
                op = null;
                trig = null;
                presionado =true;
            }
        }));
    }

    public void operacion(int intOp) {
        if (proceso.length() == 1) {
            numPulsado = intOp;
        } else if (Character.isDigit(proceso.charAt(proceso.length() - 2))) {
            numPulsado = numPulsado * 10 + intOp;
        }else{
            numPulsado = intOp;
        }
    }
    public void operacionRad(int intOp) {
        if (procesoRad.length() == 1) {
            numPulsado = intOp;
        } else if (Character.isDigit(procesoRad.charAt(procesoRad.length() - 2))) {
            numPulsado = numPulsado * 10 + intOp;
        }else{
            numPulsado = intOp;
        }
    }

    public void calcularResultado(){

        if(op!=null){
            switch(op){
                case("sumar"):
                    if(trig!=null) {
                        switch (trig) {
                            case ("sin"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado + Math.sin(numPulsado);
                                }else{
                                    resultado = resultado + Math.sin(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("cos"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado + Math.cos(numPulsado);
                                }else{
                                    resultado = resultado + Math.cos(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("tan"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado + Math.tan(numPulsado);
                                }else{
                                    resultado = resultado + Math.tan(Math.toRadians(numPulsado));
                                }
                                break;
                            default:
                                break;
                        }
                    }else{
                            resultado = resultado + numPulsado;
                    }
                    numPulsado = 0;
                    break;
                case("restar"):
                    if(trig!=null) {
                        switch (trig) {
                            case ("sin"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado - Math.sin(numPulsado);
                                }else{
                                    resultado = resultado - Math.sin(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("cos"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado - Math.cos(numPulsado);
                                }else{
                                    resultado = resultado - Math.cos(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("tan"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado - Math.tan(numPulsado);
                                }else{
                                    resultado = resultado - Math.tan(Math.toRadians(numPulsado));
                                }
                                break;
                            default:
                                break;
                        }
                    }else{
                        resultado = resultado - numPulsado;
                    }
                    numPulsado = 0;
                    break;
                case("multiplicar"):
                    if(trig!=null) {
                        switch (trig) {
                            case ("sin"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado * Math.sin(numPulsado);
                                }else{
                                    resultado = resultado * Math.sin(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("cos"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado * Math.cos(numPulsado);
                                }else{
                                    resultado = resultado * Math.cos(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("tan"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado * Math.tan(numPulsado);
                                }else{
                                    resultado = resultado * Math.tan(Math.toRadians(numPulsado));
                                }
                                break;
                            default:
                                break;
                        }
                    }else{
                        resultado = resultado * numPulsado;
                    }
                    numPulsado = 0;
                    break;
                case("dividir"):
                    if(trig!=null) {
                        switch (trig) {
                            case ("sin"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado / Math.sin(numPulsado);
                                }else{
                                    resultado = resultado / Math.sin(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("cos"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado / Math.cos(numPulsado);
                                }else{
                                    resultado = resultado / Math.cos(Math.toRadians(numPulsado));
                                }
                                break;
                            case ("tan"):
                                if(switchDegrees.isChecked()){
                                    resultado = resultado / Math.tan(numPulsado);
                                }else{
                                    resultado = resultado / Math.tan(Math.toRadians(numPulsado));
                                }
                                break;
                            default:
                                break;
                        }
                    }else{
                        if(numPulsado==0){
                            error = true;
                        }else{
                            resultado = resultado / numPulsado;
                        }
                    }
                    numPulsado = 0;
                    break;
                default:
                    break;
            }
        } else if (trig!=null) {
            switch (trig) {
                case ("sin"):
                    if(switchDegrees.isChecked()){
                        resultado = Math.sin(numPulsado);
                    }else{
                        resultado = Math.sin(Math.toRadians(numPulsado));
                    }
                    break;
                case ("cos"):
                    if(switchDegrees.isChecked()){
                        resultado = Math.cos(numPulsado);
                    }else{
                        resultado = Math.cos(Math.toRadians(numPulsado));
                    }
                    break;
                case ("tan"):
                    if(switchDegrees.isChecked()){
                        resultado = Math.tan(numPulsado);
                    }else{
                        resultado = Math.tan(Math.toRadians(numPulsado));
                    }
                    break;
                default:
                    break;
            }
        } else {
            resultado = numPulsado;
            txtResultado.setText("");
        }
    }

    public String quitarComa(){
        if(resultado == (long) resultado)
           return String.format("%d",(long)resultado);
        else
           return String.format("%s",resultado);
    }
}
