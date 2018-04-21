package android.lifeistech.com.cardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView playerHPText, enemyHPText;
    TextView[] playerAPText = new TextView[6];
    TextView[] playerBPText = new TextView[6];
    TextView[] enemyAPText = new TextView[4];
    TextView[] enemyBPText = new TextView[4];

    ListView listView;

    Player player1, player2;
    Character[] p1Character = new Character[6];
    Character[] p2Character = new Character[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerHPText = (TextView) findViewById(R.id.playerHPText);
        enemyHPText = (TextView) findViewById(R.id.enemyHPText);
        listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i <= 6; i++) {
            int id = getResources().getIdentifier("characterP" + (i+1), "id", getPackageName());
            playerAPText[i] = (TextView) findViewById(id).findViewById(R.id.APTextView);
            playerBPText[i] = (TextView) findViewById(id).findViewById(R.id.BPTextView);
        }

        for (int i = 0; i <= 6; i++) {
            int id = getResources().getIdentifier("characterE" + (i+1), "id", getPackageName());
            enemyAPText[i] = (TextView) findViewById(id).findViewById(R.id.APTextView);
            enemyBPText[i] = (TextView) findViewById(id).findViewById(R.id.BPTextView);
        }



    }



}

