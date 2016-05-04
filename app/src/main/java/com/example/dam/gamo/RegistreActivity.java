package com.example.dam.gamo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistreActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        Button btReg = (Button) findViewById(R.id.btRegistre);
        btReg.setOnClickListener(this);
    }
    public void sendByPost(final String[] dades){
      final  String camp1="aaa";
         String tag_json_obj = "json_obj_req";

        String url = "localhost/gamo-web";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Cargando...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("OK", response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERR", "Error: " + error.getMessage());
                pDialog.hide();
            }
        }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", dades[0]);
                params.put("nom", dades[1]);
                params.put("cnom", dades[2]);
                params.put("pass1", dades[3]);
                params.put("pass2", dades[4]);
                return params;
            }
        };

// Añadimos la petición a la cola de peticiones de Volley
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
    @Override
    public void onClick(View v) {

        EditText mail = (EditText) findViewById(R.id.etMail);
        EditText nom = (EditText) findViewById(R.id.etNom);
        EditText cnom = (EditText) findViewById(R.id.etCnom);
        EditText pass = (EditText) findViewById(R.id.etPassword);
        EditText pass2 = (EditText) findViewById(R.id.etPassword2);

        if(mail.getText().toString().equals("") || nom.getText().toString().equals("") || cnom.getText().toString().equals("") || pass.getText().toString().equals("") || pass2.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("Ayy Caramba!!")
                    .setMessage("No has omplert tots els camps!!")
                    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // No fer res
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else{
            String email = mail.getText().toString();
            String pas = nom.getText().toString();
            String fname = cnom.getText().toString();
            String dateob = pass.getText().toString();
            String add12 = pass2.getText().toString();

            startActivity(new Intent(RegistreActivity.this, LoginActivity.class));
        }
    }
}
