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
import java.util.Random;


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

    ArrayList<Character> p1CharacterList = new ArrayList<>();
    ArrayList<Character> p2CharacterList = new ArrayList<>();

    int nowPlayer;
    GameState gameState; //キャラクタータップ時の状態 1~
    int selectCharacterNum;

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


        p1CharacterList.add(new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター", false));
        p1CharacterList.add(new Character(5, 3, 2, 2, R.drawable.drop, "水のキャラクター", false));
        p1CharacterList.add(new Character(5, 3, 2, 3, R.drawable.tree, "木のキャラクター", false));
        p1CharacterList.add(new Character(5, 3, 2, 4, R.drawable.light, "光のキャラクター", false));
        p1CharacterList.add(new Character(5, 3, 2, 5, R.drawable.dark, "闇のキャラクター", false));
        p1CharacterList.add(new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター", false));

        p2CharacterList.add(new Character(10, 5, 2, 1, R.drawable.fire, "炎のキャラクター", false));
        p2CharacterList.add(new Character(5, 3, 2, 2, R.drawable.drop, "水のキャラクター", false));
        p2CharacterList.add(new Character(10, 5, 4, 3, R.drawable.tree, "木のキャラクター", false));
        p2CharacterList.add(new Character(5, 3, 2, 4, R.drawable.light, "光のキャラクター", false));
        p2CharacterList.add(new Character(10, 5, 2, 5, R.drawable.dark, "闇のキャラクター", false));
        p2CharacterList.add(new Character(5, 3, 2, 1, R.drawable.fire, "炎のキャラクター", false));

        player1 = new Player(1, sumPlayerHP(1), 1);
        player2 = new Player(2, sumPlayerHP(2), 2);


        nowPlayer = 1;
        selectCharacterNum = 0;
        gameState = GameState.SELECT_ACTION;

        changeShadowStates(3);
        renderText();


        addLog("戦闘開始");
        addLog("プレイヤー" + nowPlayer + "のターン");


        for (int i = 0; i < 6; i++) {
            final int num = i;

            playerLayoutList[num].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int touchState = getTouchState(num);
                    switch (gameState) {
                        case SELECT_ACTION:

                            selectCharacterNum = num;

                            changeShadowStates(touchState);
                            playerShadowList[num].setVisibility(View.INVISIBLE);

                            gameState = GameState.SELECT_TARGET;
                            break;
                        case SELECT_TARGET:
                            changeShadowStates(3);
                            if (selectCharacterNum == num) {
                                //キャンセル処理
                                renderShadowFromActCompleted();
                            } else {
                                //自陣とベンチの入れ替え処理
                                exchange(num);

                                //次のターンに移行
                                nowPlayer = nowPlayer == 1 ? 2 : 1;
                                renderText();
                                addLog("プレイヤー" + nowPlayer + "のターン");
                            }
                            gameState = GameState.SELECT_ACTION;
                            break;
                        default:
                            break;
                    }
                }
            });

            //shadowがOnの時は常にtrueにしタッチできないようにする
            playerShadowList[num].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });

            if (num < 4) {
                enemyLayoutList[num].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (gameState) {
                            case SELECT_ACTION:
                                break;
                            case SELECT_TARGET:
                                //ダメージ計算
                                damage(num);

                                //次のターンに移行
                                nowPlayer = nowPlayer == 1 ? 2 : 1;
                                changeShadowStates(3);
                                renderShadowFromActCompleted();
                                renderText();
                                addLog("プレイヤー" + nowPlayer + "のターン");
                                gameState = GameState.SELECT_ACTION;
                                break;
                            default:
                                break;
                        }
                    }

                });

                //shadowがOnの時は常にtrueにしタッチできないようにする
                enemyShadowList[num].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });


            }

        }

    }

    int sumPlayerHP(int id) {
        int sum = 0;
        if (id == 1) {
            for (int i = 0; i < 6; i++) {
                sum += p1CharacterList.get(i).getHP();
            }
        } else {
            for (int i = 0; i < 6; i++) {
                sum += p2CharacterList.get(i).getHP();
            }
        }
        return sum;
    }


    void renderText() {

        if (nowPlayer == 1) {
            for (int i = 0; i < 6; i++) {
                playerAPTextList[i].setText(p1CharacterList.get(i).getAP() + "");
                playerBPTextList[i].setText(p1CharacterList.get(i).getBP() + "");
                playerImageList[i].setImageResource(p1CharacterList.get(i).getResID());
                playerImageList[i].setBackgroundColor(getPropColor(p1CharacterList.get(i).getPropID()));

                if (i < 4) {
                    enemyAPTextList[i].setText(p2CharacterList.get(i).getAP() + "");
                    enemyBPTextList[i].setText(p2CharacterList.get(i).getBP() + "");
                    enemyImageList[i].setImageResource(p2CharacterList.get(i).getResID());
                    enemyImageList[i].setBackgroundColor(getPropColor(p2CharacterList.get(i).getPropID()));

                }
            }
            playerHPText.setText(player1.getHP() + "");
            enemyHPText.setText(player2.getHP() + "");
        } else {
            for (int i = 0; i < 6; i++) {
                playerAPTextList[i].setText(p2CharacterList.get(i).getAP() + "");
                playerBPTextList[i].setText(p2CharacterList.get(i).getBP() + "");
                playerImageList[i].setImageResource(p2CharacterList.get(i).getResID());
                playerImageList[i].setBackgroundColor(getPropColor(p2CharacterList.get(i).getPropID()));
                if (i < 4) {
                    enemyAPTextList[i].setText(p1CharacterList.get(i).getAP() + "");
                    enemyBPTextList[i].setText(p1CharacterList.get(i).getBP() + "");
                    enemyImageList[i].setImageResource(p1CharacterList.get(i).getResID());
                    enemyImageList[i].setBackgroundColor(getPropColor(p1CharacterList.get(i).getPropID()));

                }
            }
            playerHPText.setText(player2.getHP() + "");
            enemyHPText.setText(player1.getHP() + "");
        }
    }

    void damage(int num) {
        int result = 0;
        int damage = 0;
        double critical = 1.0;
        double scale; //弱点補正
        Random r = new Random();
        if (r.nextInt(100) < 10) {
            critical *= 1.5;
            addLog("クリティカル発動！");
        }

        if (nowPlayer == 1) {
            scale = calcRelationScale(p1CharacterList.get(selectCharacterNum).getPropID(), p2CharacterList.get(num).getPropID());
            damage = (int) (p1CharacterList.get(selectCharacterNum).getAP() * scale * critical);
            result = p2CharacterList.get(num).getBP() - damage;
            if (result < 0) {
                if (p2CharacterList.get(num).getBP() != 0) {
                    addLog(p2CharacterList.get(num).getName() + "に" + p2CharacterList.get(selectCharacterNum).getBP() + "ダメージ");
                    p2CharacterList.get(num).setBP(0);
                }
                player2.setHP(player2.getHP() + result);
                addLog("プレイヤー2に" + (-1 * result) + "ダメージ");
                if (player2.getHP() <= 0) {
                    addLog("プレイヤー1の勝利！");
                }
            } else {
                addLog(p2CharacterList.get(num).getName() + "に" + damage + "ダメージ");
                p2CharacterList.get(num).setBP(result);
            }
        } else {
            scale = calcRelationScale(p2CharacterList.get(selectCharacterNum).getPropID(), p1CharacterList.get(num).getPropID());
            damage = (int) (p2CharacterList.get(selectCharacterNum).getAP() * scale * critical);
            result = p1CharacterList.get(num).getBP() - damage;
            if (result < 0) {
                if (p1CharacterList.get(num).getBP() != 0) {
                    addLog(p1CharacterList.get(num).getName() + "に" + p1CharacterList.get(selectCharacterNum).getBP() + "ダメージ");
                    p1CharacterList.get(num).setBP(0);
                }
                player1.setHP(player1.getHP() + result);
                addLog("プレイヤー1に" + (-1 * result) + "ダメージ");
                if (player1.getHP() <= 0) {
                    addLog("プレイヤー2の勝利！");
                }
            } else {
                addLog(p1CharacterList.get(num).getName() + "に" + damage + "ダメージ");
                p1CharacterList.get(num).setBP(result);
            }
        }
    }

    void exchange(int num) {

        Character tmp;
        if (nowPlayer == 1) {
            tmp = p1CharacterList.get(selectCharacterNum);
            p1CharacterList.set(selectCharacterNum, p1CharacterList.get(num));
            p1CharacterList.set(num, tmp);
            addLog(p1CharacterList.get(selectCharacterNum).getName() + "が戻り");
            addLog(p1CharacterList.get(num).getName() + "が召喚されました");
        } else {
            tmp = p2CharacterList.get(selectCharacterNum);
            p2CharacterList.set(selectCharacterNum, p2CharacterList.get(num));
            p2CharacterList.set(num, tmp);
            addLog(p2CharacterList.get(selectCharacterNum).getName() + "が戻り");
            addLog(p2CharacterList.get(num).getName() + "が召喚されました");
        }
    }

    double calcRelationScale(int prop1, int prop2) {
        double scale = 1.0;
        switch (prop1) {
            case 1:
                if (prop2 == 2) scale = 0.75;
                else if (prop2 == 3) scale = 1.5;
                break;
            case 2:
                if (prop2 == 3) scale = 0.75;
                else if (prop2 == 1) scale = 1.5;
                break;
            case 3:
                if (prop2 == 1) scale = 0.75;
                else if (prop2 == 2) scale = 1.5;
                break;
            case 4:
                if (prop2 == 5) scale = 1.5;
                break;
            case 5:
                if (prop2 == 4) scale = 1.5;
                break;

        }
        return scale;
    }

    //属性の色を取得する
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

    int getTouchState(int num) {
        int state = 0;
        if (num < 4) {
            state = 1;
        } else {
            state = 2;
        }

        return state;
    }

    void addLog(String text) {
        adapter.add(text);
        logList.add(text);
        listView.setSelection(logList.size());
    }

    void renderShadowFromActCompleted() {
        for (int i = 0; i < 4; i++) {
            boolean flg = (nowPlayer == 1) ? p1CharacterList.get(i).isActCompleted()
                    : p2CharacterList.get(i).isActCompleted();
            if (flg) {
                playerShadowList[i].setVisibility(View.VISIBLE);
            }
        }
    }

    void changeShadowStates(int touchState) {
        //1のとき自陣の戦闘、2のとき自陣のベンチ、3のとき敵
        switch (touchState) {
            case 1:
                for (int i = 0; i < 6; i++) {
                    playerShadowList[i].setVisibility(View.VISIBLE);
                    if (i < 4) {
                        enemyShadowList[i].setVisibility(View.INVISIBLE);
                    }
                }
                break;

            case 2:
                for (int i = 0; i < 6; i++) {
                    if (i < 4) {
                        playerShadowList[i].setVisibility(View.INVISIBLE);
                    } else {
                        playerShadowList[i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case 3:
                for (int i = 0; i < 6; i++) {
                    playerShadowList[i].setVisibility(View.INVISIBLE);
                    if (i < 4) {
                        enemyShadowList[i].setVisibility(View.VISIBLE);
                    }
                }
                break;
            default:
                break;
        }
    }

    public enum GameState {
        SELECT_ACTION,
        SELECT_TARGET
    }
}

