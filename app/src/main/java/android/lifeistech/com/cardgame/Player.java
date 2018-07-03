package android.lifeistech.com.cardgame;

/**
 * Created by Owner on 2018/04/21.
 */

public class Player {
    private int ID;
    private int HP;
    private int skillID;

    Player(int ID, int HP, int skillID) {
        this.ID = ID;
        this.HP = HP;
        this.skillID = skillID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHP() {
        return this.HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSkillID() {
        return this.skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }


}
