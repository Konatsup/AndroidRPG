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
    Character(int HP,int AP,int BP,int propID,int resID,String name) {
        this.HP = HP;
        this.AP = AP;
        this.BP = BP;
        this.propID = propID;
        this.resID = resID;
        this.name = name;
    }
}
