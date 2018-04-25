package android.lifeistech.com.cardgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    //2人で対戦(ローカル)
    public void intentMainActivity(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //2人で対戦(オンライン)
    public void intentOnlineBattleActivity(View v){
        Intent intent = new Intent(this, OnlineBattleActivity.class);
        startActivity(intent);
    }

}
