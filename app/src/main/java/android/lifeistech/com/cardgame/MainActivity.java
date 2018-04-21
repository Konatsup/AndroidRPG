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

    int nextPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerHPText = (TextView) findViewById(R.id.playerHPText);
        enemyHPText = (TextView) findViewById(R.id.enemyHPText);
        listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < 6; i++) {
            int id = getResources().getIdentifier("characterP" + (i + 1), "id", getPackageName());
            playerAPText[i] = (TextView) findViewById(id).findViewById(R.id.APTextView);
            playerBPText[i] = (TextView) findViewById(id).findViewById(R.id.BPTextView);
        }

        for (int i = 0; i < 4; i++) {
            int id = getResources().getIdentifier("characterE" + (i + 1), "id", getPackageName());
            enemyAPText[i] = (TextView) findViewById(id).findViewById(R.id.APTextView);
            enemyBPText[i] = (TextView) findViewById(id).findViewById(R.id.BPTextView);
        }


        p1Character[0] = new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター");
        p1Character[1] = new Character(5, 3, 2, 1, R.drawable.drop, "水のキャラクター");
        p1Character[2] = new Character(5, 3, 2, 1, R.drawable.tree, "木のキャラクター");
        p1Character[3] = new Character(5, 3, 2, 1, R.drawable.light, "光のキャラクター");
        p1Character[4] = new Character(5, 3, 2, 1, R.drawable.dark, "のキャラクター");
        p1Character[5] = new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター");

        p2Character[0] = new Character(10, 5, 4, 1, R.drawable.fire, "炎のキャラクター");
        p2Character[1] = new Character(5, 3, 2, 1, R.drawable.drop, "水のキャラクター");
        p2Character[2] = new Character(10, 5, 4, 1, R.drawable.tree, "木のキャラクター");
        p2Character[3] = new Character(5, 3, 2, 1, R.drawable.light, "光のキャラクター");
        p2Character[4] = new Character(10, 5, 4, 1, R.drawable.dark, "のキャラクター");
        p2Character[5] = new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター");

        player1 = new Player(1, sumPlayerHP(1), 1);
        player2 = new Player(2, sumPlayerHP(2), 2);

        nextPlayer = 1;


        renderText();

    }

    int sumPlayerHP(int id) {
        int sum = 0;
        if (id == 1) {
            for (int i = 0; i < 6; i++) {
                sum += p1Character[i].getHP();
            }
        } else {
            for (int i = 0; i < 6; i++) {
                sum += p2Character[i].getHP();
            }
        }
        return sum;
    }


    void renderText() {

        if (nextPlayer == 1) {
            for (int i = 0; i < 6; i++) {
                playerAPText[i].setText(p1Character[i].getAP() + "");
                playerBPText[i].setText(p1Character[i].getBP() + "");
                if (i < 4) {
                    enemyAPText[i].setText(p2Character[i].getAP() + "");
                    enemyBPText[i].setText(p2Character[i].getBP() + "");
                }
            }
            playerHPText.setText(player1.getHP()+"");
            enemyHPText.setText(player2.getHP()+"");
        } else {
            for (int i = 0; i < 6; i++) {
                playerAPText[i].setText(p2Character[i].getAP() + "");
                playerBPText[i].setText(p2Character[i].getBP() + "");
                if (i < 4) {
                    enemyAPText[i].setText(p1Character[i].getAP() + "");
                    enemyBPText[i].setText(p1Character[i].getBP() + "");
                }
            }
            playerHPText.setText(player2.getHP()+"");
            enemyHPText.setText(player1.getHP()+"");
        }
    }


}

