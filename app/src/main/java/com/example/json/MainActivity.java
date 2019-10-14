package com.example.json;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Leitor de Json's");
        setContentView(R.layout.activity_main);
        botaoBuscar();
    }

    private void botaoBuscar() {
        Button botaoBuscar = findViewById(R.id.btn_buscar);
        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acessarJson();
            }
        });
    }

    public void acessarJson(){
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.dados);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine()){
            builder.append(scanner.nextLine());
        }
        apresentarJson(builder.toString());
    }

    private void apresentarJson(String s){

        TextView campoJson = findViewById(R.id.tv_json);
        StringBuilder builder = new StringBuilder();

        try {
            JSONObject root = new JSONObject(s);
            JSONObject student = root.getJSONObject("grade-aulas");
            builder.append("Conteúdo do Json:\n")
                    .append(student.getString("nome")).append("\n\n");
            builder.append("Meio Periodo:\n")
                    .append(student.getBoolean("meio-periodo")).append("\n\n");

            JSONArray materias = student.getJSONArray("materias");

            for(int i = 0; i<materias.length(); i++){
                JSONObject materia = materias.getJSONObject(i);
                builder.append(materia.getString("nome")).append(":\n ").append
                        (materia.getString("nota")).append("\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        campoJson.setText(builder.toString());
    }
}


