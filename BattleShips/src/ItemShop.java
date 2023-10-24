import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ItemShop implements Serializable,FileName {
    private String nameID;
    private int savedGameLimited;

    private String nickName;

    public ItemShop() {
    }

    public ItemShop(String nameID, int saveGameLimited) {
        this.nameID = nameID;
        this.savedGameLimited = saveGameLimited;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public int getSavedGameLimited() {
        return savedGameLimited;
    }

    public void setSavedGameLimited(int savedGameLimited) {
        this.savedGameLimited = savedGameLimited;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setDefaultItemByNameID(String nameID) {
        setNameID(nameID);
        setNickName(nickNameRank[0]);
        setSavedGameLimited(5);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "nameID='" + nameID + '\'' +
                ", saveGameLimited='" + savedGameLimited + '\'' +
                '}';
    }

    public boolean checkNameID(String nameID) {
        return getNameID().equals(nameID);
    }
}
