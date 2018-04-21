package android.lifeistech.com.cardgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView playerHPText, enemyHPText;
    FrameLayout[] playerLayoutList = new FrameLayout[6];
    TextView[] playerAPTextList = new TextView[6];
    TextView[] playerBPTextList = new TextView[6];
    ImageView[] playerImageList = new ImageView[6];
    LinearLayout[] playerShadowList = new LinearLayout[6];

    FrameLayout[] enemyLayoutList = new FrameLayout[4];
    TextView[] enemyAPTextList = new TextView[4];
    TextView[] enemyBPTextList = new TextView[4];
    ImageView[] enemyImageList = new ImageView[4];
    LinearLayout[] enemyShadowList = new LinearLayout[4];

    ListView listView;
    ArrayAdapter<String> adapter;

    Player player1, player2;
    Character[] p1CharacterList = new Character[6];
    Character[] p2CharacterList = new Character[6];


    int[] p1TapHistory = new int[4];
    int[] p2TapHistory = new int[4];
    int nextPlayer;

    ArrayList<String> logList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerHPText = (TextView) findViewById(R.id.playerHPText);
        enemyHPText = (TextView) findViewById(R.id.enemyHPText);
        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter(this, R.layout.log);

        listView.setAdapter(adapter);
        for (int i = 0; i < 6; i++) {
            int id = getResources().getIdentifier("characterP" + (i + 1), "id", getPackageName());
            playerLayoutList[i] = (FrameLayout) findViewById(id);
            playerAPTextList[i] = (TextView) findViewById(id).findViewById(R.id.APTextView);
            playerBPTextList[i] = (TextView) findViewById(id).findViewById(R.id.BPTextView);
            playerImageList[i] = (ImageView) findViewById(id).findViewById(R.id.imageView);
            playerShadowList[i] = (LinearLayout) findViewById(id).findViewById(R.id.shadow);

        }

        for (int i = 0; i < 4; i++) {
            int id = getResources().getIdentifier("characterE" + (i + 1), "id", getPackageName());
            enemyLayoutList[i] = (FrameLayout) findViewById(id);
            enemyAPTextList[i] = (TextView) findViewById(id).findViewById(R.id.APTextView);
            enemyBPTextList[i] = (TextView) findViewById(id).findViewById(R.id.BPTextView);
            enemyImageList[i] = (ImageView) findViewById(id).findViewById(R.id.imageView);
            enemyShadowList[i] = (LinearLayout) findViewById(id).findViewById(R.id.shadow);

        }

        //インスタンス生成
        p1CharacterList[0] = new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター");
        p1CharacterList[1] = new Character(5, 3, 2, 2, R.drawable.drop, "水のキャラクター");
        p1CharacterList[2] = new Character(5, 3, 2, 3, R.drawable.tree, "木のキャラクター");
        p1CharacterList[3] = new Character(5, 3, 2, 4, R.drawable.light, "光のキャラクター");
        p1CharacterList[4] = new Character(5, 3, 2, 5, R.drawable.dark, "のキャラクター");
        p1CharacterList[5] = new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター");

        p2CharacterList[0] = new Character(10, 5, 4, 1, R.drawable.fire, "炎のキャラクター");
        p2CharacterList[1] = new Character(5, 3, 2, 2, R.drawable.drop, "水のキャラクター");
        p2CharacterList[2] = new Character(10, 5, 4, 3, R.drawable.tree, "木のキャラクター");
        p2CharacterList[3] = new Character(5, 3, 2, 4, R.drawable.light, "光のキャラクター");
        p2CharacterList[4] = new Character(10, 5, 4, 5, R.drawable.dark, "のキャラクター");
        p2CharacterList[5] = new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター");

        player1 = new Player(1, sumPlayerHP(1), 1);
        player2 = new Player(2, sumPlayerHP(2), 2);

        nextPlayer = 1;


        renderText();

        logList.add("aaaaaaa");
        logList.add("iiiiiii");
        logList.add("uuuuuuu");
        logList.add("eeeeeee");
        logList.add("ooooooo");
        logList.add("aaaaaaa");
        logList.add("iiiiiii");
        logList.add("uuuuuuu");
        logList.add("eeeeeee");
        logList.add("ooooooo");


        for (String str: logList){
            // ArrayAdapterにitemを追加する
            adapter.add(str);
        }

        for (int i = 0; i < 6; i++) {
            final int num = i;

            playerLayoutList[num].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    playerAPTextList[num].setText("334");
                    if(num >= 4){
                        //入れ替えの処理
                    }
                    addLog("334");
                    nextPlayer = nextPlayer==0? 1:0;
                    renderText();

                }
            });

            //shadowがOnの時は常にtrueにしタッチできないようにする
            playerShadowList[num].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });

        }

    }

    int sumPlayerHP(int id) {
        int sum = 0;
        if (id == 1) {
            for (int i = 0; i < 6; i++) {
                sum += p1CharacterList[i].getHP();
            }
        } else {
            for (int i = 0; i < 6; i++) {
                sum += p2CharacterList[i].getHP();
            }
        }
        return sum;
    }


    void renderText() {

        if (nextPlayer == 1) {
            for (int i = 0; i < 6; i++) {
                playerAPTextList[i].setText(p1CharacterList[i].getAP() + "");
                playerBPTextList[i].setText(p1CharacterList[i].getBP() + "");
                playerImageList[i].setImageResource(p1CharacterList[i].getResID());
                playerImageList[i].setBackgroundColor(getPropColor(p1CharacterList[i].getPropID()));
//                for (int j = 0; j < 6; j++) {
//                    final int num = j;
//
//                    if (p1TapHistory[num] == 1) {
//                        playerShadowList[num].setVisibility(View.VISIBLE);
//                    }
//
//                    playerLayoutList[num].setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            playerAPTextList[num].setText("334");
//                        }
//                    });
//
//
//
//                }


                if (i < 4) {
                    enemyAPTextList[i].setText(p2CharacterList[i].getAP() + "");
                    enemyBPTextList[i].setText(p2CharacterList[i].getBP() + "");
                    enemyImageList[i].setImageResource(p2CharacterList[i].getResID());
                    enemyImageList[i].setBackgroundColor(getPropColor(p2CharacterList[i].getPropID()));

                }
            }
            playerHPText.setText(player1.getHP() + "");
            enemyHPText.setText(player2.getHP() + "");
        } else {
            for (int i = 0; i < 6; i++) {
                playerAPTextList[i].setText(p2CharacterList[i].getAP() + "");
                playerBPTextList[i].setText(p2CharacterList[i].getBP() + "");
                playerImageList[i].setImageResource(p2CharacterList[i].getResID());
                playerImageList[i].setBackgroundColor(getPropColor(p2CharacterList[i].getPropID()));
                if (i < 4) {
                    enemyAPTextList[i].setText(p1CharacterList[i].getAP() + "");
                    enemyBPTextList[i].setText(p1CharacterList[i].getBP() + "");
                    enemyImageList[i].setImageResource(p1CharacterList[i].getResID());
                    enemyImageList[i].setBackgroundColor(getPropColor(p1CharacterList[i].getPropID()));

                }
            }
            playerHPText.setText(player2.getHP() + "");
            enemyHPText.setText(player1.getHP() + "");
        }
    }

    int getPropColor(int propId) {
        int color;
        switch (propId) {
            case 1:
                color = Color.parseColor("#FF0000");
                break;
            case 2:
                color = Color.parseColor("#0000FF");
                break;
            case 3:
                color = Color.parseColor("#00FF00");
                break;
            case 4:
                color = Color.parseColor("#FFFF00");
                break;
            case 5:
                color = Color.parseColor("#FF00FF");

                break;
            default:
                color = Color.parseColor("#000000");
                break;
        }
        return color;
    }

    void addLog(String text) {
        adapter.add(text);
        listView.setSelection(logList.size());
    }


}

