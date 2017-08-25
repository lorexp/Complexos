package com.example.vinicius.complexos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.Format;
import java.text.NumberFormat;

/**
 * Created by vinicius on 24/08/17.
 */

public class inicialActivity extends Activity {

    private RadioButton retangular,polar;
    private EditText valor1,valor2;
    private TextView view1,view2,respostas,resposta;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.inicial);

        retangular = (RadioButton) findViewById(R.id.retangular);
        polar = (RadioButton) findViewById(R.id.polar);
        valor1 = (EditText) findViewById(R.id.valor1);
        valor2 = (EditText) findViewById(R.id.valor2);
        view1 = (TextView) findViewById(R.id.texto1);
        view2 = (TextView) findViewById(R.id.texto2);
        respostas = (TextView) findViewById(R.id.respostas);
        resposta = (TextView) findViewById(R.id.resposta);

        retangular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view1.setText(R.string.valorA);
                view2.setText(R.string.valorB);
                retangular.setSelected(true);
                polar.setSelected(false);


            }
        });

        polar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.setText(R.string.valorZ);
                view2.setText(R.string.valorAngulo);
                polar.setSelected(true);
                retangular.setSelected(false);
            }
        });

    }

    public void Calcular(View view) {
        if (valor1.getText().length() == 0) {
            valor1.setError("Campo Vazio");
        } else {
            if (valor2.getText().length() == 0) {
                valor2.setError("Campo Vazio");
            } else {
                respostas.setText(R.string.resposta);
                NumberFormat format = NumberFormat.getInstance();
                format.setMaximumFractionDigits(4);
                format.setMinimumFractionDigits(2);
                format.setMaximumIntegerDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                Double Z, valorB, a, b, angulo;
                if (polar.isSelected()) {
                    Z = Double.valueOf(valor1.getText().toString());
                    valorB = Double.valueOf(valor2.getText().toString());

                    angulo = (Math.PI / 180) * valorB;
                    a = Z * Math.cos(angulo);
                    b = Z * Math.sin(angulo);

                    if (b >= 0) {

                        resposta.setText(format.format(a) + " +" + format.format(b) + "j");
                    } else {
                        resposta.setText(format.format(a) + " " + format.format(b) + "j");
                    }


                } else {
                    a = Double.valueOf(valor1.getText().toString());
                    b = Double.valueOf(valor2.getText().toString());

                    valorB = (b / a);

                    Z = Math.sqrt((a * a) + (b * b));
                    angulo = Math.toDegrees(Math.atan(valorB));

                    resposta.setText(format.format(Z) + "  Ângulo de " + format.format(angulo) + " º");
                }

            }
        }
    }
    public void LimparCampos(View view){
        resposta.setText(null);
        valor1.setText(null);
        valor2.setText(null);
        respostas.setText(null);
        valor1.requestFocus();

    }


}
