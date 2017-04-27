package edu.cecyt9.ipn.practica_13_hilos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText entrada;
    private TextView salida;
    private TextView salidaDos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
        salidaDos=(TextView)findViewById(R.id.txtFib);
    }

    public void calcularOperacion(View view) {
        try {
            int n = Integer.parseInt(entrada.getText().toString());
            salida.append(n + "! = ");
            //MiThread hilo = new MiThread(n);
            //hilo.run();
            new MiTarea().execute(n);
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        //int res = factorial(n);
        //salida.append(res + "\n");
    }

    public int factorial(int n) {
        int res=1;
        for (int i=1; i<=n; i++){
            res*=i;
           SystemClock.sleep(500);
        }

        return res;

    }

    class MiThread extends Thread {
        private int n, res;

        public MiThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            res = factorial(n);
            salida.append(res + "\n");
        }

//        @Override
//        public void run() {
//            res = factorial(n);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    salida.append(res + "\n");
//                }
//            });
//        }
    }

//    public void calcularOperacion(View view) {
//        int n = Integer.parseInt(entrada.getText().toString());
//        salida.append(n + "! = ");
//        MiThread thread = new MiThread(n);
//        thread.start();
//    }


//    ejemplo AsyncTask
    class MiTarea extends AsyncTask<Integer, Void, String> {


        String numA;
        String numAA;
        String resultadoFact="";
        String resultado="";
        int fibIteracion=0;
        @Override

        protected String doInBackground(Integer... n) {
            fibIteracion=n[0];
            resultadoFact=""+factorial(n[0]);
            resultado= fibonacci("01",0);

            return ""+resultado+" \n"+n[0]+"!="+resultadoFact;
        }

        public String fibonacci(String cadena,int ciclo)
        {   int producto;
            if(ciclo<fibIteracion)
            {
                numA=""+cadena.charAt(cadena.length()-1);
                numAA=""+cadena.charAt(cadena.length()-2);
                producto=Integer.parseInt(numA)+Integer.parseInt(numAA);
                Log.d("Error:",""+ciclo);
                cadena+=producto;
                cadena=fibonacci(cadena,ciclo+1);
            }
            Log.d("Error:"+ciclo,cadena);
            return cadena;

        }

        @Override

        protected void onPostExecute(String res) {

            salida.setText("Factorial: " +resultadoFact);
            salidaDos.setText("Fibonacci: "+resultado + "\n");
            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
        }

    }

//        public void calcularOperacion(View view) {
//        int n = Integer.parseInt(entrada.getText().toString());
//        salida.append(n + "! = ");
//        MiTarea tarea = new MiTarea();
//        tarea.execute(n);
//
//        }

    //    ejemplo AsyncTask whit progressdialog
//    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
//
//        private ProgressDialog progreso;
//
//        @Override protected void onPreExecute() {
//
//            progreso = new ProgressDialog(MainActivity.this);
//
//            progreso.setProgressStyle(ProgressDialog.
//                    STYLE_HORIZONTAL);
//
//            progreso.setMessage("Calculando...");
//
//            progreso.setCancelable(false);
//
//            progreso.setMax(100);
//
//            progreso.setProgress(0);
//
//            progreso.show();
//
//        }
//
//        @Override protected Integer doInBackground(Integer... n) {
//
//            int res = 1;
//
//            for (int i = 1; i <= n[0]; i++) {
//
//                res *= i;
//
//                SystemClock.sleep(1000);
//
//                publishProgress(i*100 / n[0]);
//
//            }
//
//            return res;
//
//        }
//
//        @Override protected void onProgressUpdate(Integer... porc) {
//
//            progreso.setProgress(porc[0]);
//
//        }
//
//        @Override protected void onPostExecute(Integer res) {
//
//            progreso.dismiss();
//
//            salida.append(res + "\n");
//
//        }
//
//    }

    //    ejemplo AsyncTask whit progressdialog cancel
//    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
//
//        private ProgressDialog progreso;
//
//        @Override protected void onPreExecute() {
//
//            progreso = new ProgressDialog(MainActivity.this);
//
//            progreso.setProgressStyle(ProgressDialog.
//                    STYLE_HORIZONTAL);
//
//            progreso.setMessage("Calculando...");
//
//            progreso.setCancelable(true);
//
//            progreso.setOnCancelListener(new OnCancelListener() {
//
//                @Override
//                public void onCancel(DialogInterface dialog) {
//
//                    MiTarea.this.cancel(true);
//
//                }
//
//            });
//
//            progreso.setMax(100);
//
//            progreso.setProgress(0);
//
//            progreso.show();
//
//        }
//
//        @Override protected Integer doInBackground(Integer... n) {
//
//            int res = 1;
//
//            for (int i = 1; i <= n[0] && !isCancelled(); i++) {
//                res *= i;
//
//                SystemClock.sleep(1000);
//
//                publishProgress(i*100 / n[0]);
//
//            }
//
//            return res;
//
//        }
//
//        @Override protected void onProgressUpdate(Integer... porc) {
//
//            progreso.setProgress(porc[0]);
//
//        }
//
//        @Override protected void onPostExecute(Integer res) {
//
//            progreso.dismiss();
//
//            salida.append(res + "\n");
//
//        }
//
//        @Override protected void onCancelled() {
//
//            salida.append("cancelado\n");
//
//        }
//
//    }

}