package com.josecan.android.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mCampoEstatura = (EditText) findViewById(R.id.campo_estatura);
        final EditText mCampoPeso = (EditText) findViewById(R.id.campo_peso);
        Button mBotonCalcular = (Button) findViewById(R.id.boton_calcular);
        Button mBotonLimpiar = (Button) findViewById(R.id.boton_limpiar);
        final TextView mEtiquetaImc = (TextView) findViewById(R.id.etiqueta_imc);
        final TextView mEtiquetaClasificacion = (TextView) findViewById(R.id.etiqueta_clasificacion);

        mBotonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPeso, strEstatura;
                Double peso = null, estatura = null;
                try{
                    strEstatura = mCampoEstatura.getText().toString();
                    estatura = Double.parseDouble(strEstatura);
                }
                catch (Exception $e)
                {
                    Toast.makeText(getApplicationContext(), "Estatura: " + getResources().getString(R.string.valor_invalido), Toast.LENGTH_SHORT).show();
                    mCampoEstatura.setText("");
                }
                try{
                    strPeso = mCampoPeso.getText().toString();
                    peso = Double.parseDouble(strPeso);
                }
                catch (Exception $e)
                {
                    Toast.makeText(getApplicationContext(), "Peso: " + getResources().getString(R.string.valor_invalido), Toast.LENGTH_SHORT).show();
                    mCampoPeso.setText("");
                }
                if (estatura != null && peso != null){
                    double imc = peso / (double)Math.pow(estatura, 2);
                    mEtiquetaImc.setText(new DecimalFormat("0.00").format(imc));
                    String clasificacion;
                    if (imc >= 40) {
                        clasificacion = getResources().getString(R.string.obesidad_extrema);
                    } else if(imc >= 30) {
                        clasificacion = getResources().getString(R.string.obesidad);
                    } else if(imc >= 25) {
                        clasificacion = getResources().getString(R.string.sobrepeso);
                    } else if(imc > 18.5) {
                        clasificacion = getResources().getString(R.string.peso_normal);
                    } else {
                        clasificacion = getResources().getString(R.string.peso_bajo);
                    }
                    mEtiquetaClasificacion.setText(clasificacion);
                } else {
                    mEtiquetaImc.setText("0.0");
                    mEtiquetaClasificacion.setText(getResources().getString(R.string.clasificacion_indefinida));
                }
            }
        });
        mBotonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCampoEstatura.setText("");
                mCampoPeso.setText("");
                mEtiquetaImc.setText("0.0");
                mEtiquetaClasificacion.setText("");
                mCampoEstatura.requestFocus();
            }
        });
    }
}
