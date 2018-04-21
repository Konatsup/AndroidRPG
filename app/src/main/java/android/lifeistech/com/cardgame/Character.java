package android.lifeistech.com.cardgame;

/**
 * Created by Owner on 2018/04/21.
 */

public class Character {
    int HP;//体力
    int AP;//攻撃力
    int BP;//バリア数
    int propID;//属性
    int resID;//画像のID
    String name;//キャラの名前
    boolean canTouch;

    Character(int HP, int AP, int BP, int propID, int resID, String name, boolean canTouch) {
        this.HP = HP;
        this.AP = AP;
        this.BP = BP;
        this.propID = propID;
        this.resID = resID;
        this.name = name;
        this.canTouch = canTouch;
    }


    public int getHP() {
        return this.HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAP() {
        return this.AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getBP() {
        return this.BP;
    }

    public void setBP(int BP) {
        this.BP = BP;
    }

    public int getPropID() {
        return this.propID;
    }

    public void setPropID(int propID) {
        this.propID = propID;
    }

    public int getResID() {
        return this.resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCanTouch(){
        return this.canTouch;
    }

    public void setCanTouch(boolean canTouch){
        this.canTouch = canTouch;
    }


}
