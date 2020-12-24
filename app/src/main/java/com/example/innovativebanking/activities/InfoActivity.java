package com.example.innovativebanking.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.innovativebanking.DownloadAsync;
import com.example.innovativebanking.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class InfoActivity extends AppCompatActivity {

    private static final String TAG = DownloadAsync.class.getSimpleName();
    private Button showJson;
    private TextView jsonContent;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        showJson = findViewById(R.id.infoBtn);
        jsonContent = findViewById(R.id.jsonObj);
        progressBar = findViewById(R.id.progressCirc);
        progressBar.setVisibility(View.GONE);

        showJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadAsync downloadAsync = new DownloadAsync() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        jsonContent.setText(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonLibrary = jsonObject.getJSONObject("articole");
                            JSONArray jsonArrayBooks = jsonLibrary.getJSONArray("articol");
                            for (int i = 0; i < jsonArrayBooks.length(); i++) {
                                JSONObject jsonObjectBook = jsonArrayBooks.getJSONObject(i);
//                                String title = jsonObjectBook.getString("title");
//                                Log.d(TAG,"\n Book title: " + title);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            progressBar.setVisibility(View.GONE);
                        }
                    }


                };
                downloadAsync.execute("http://pdm.ase.ro/examen/articole.json.txt");
            }
        });
    }
}