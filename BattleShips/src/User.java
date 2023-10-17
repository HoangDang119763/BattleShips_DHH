import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class User implements Serializable {
    private String nameID;//Tài khoản
    private String passWord;//Mật khẩu
    private String namePlayer;//Tên người dùng

    //Phần tiền tệ
    private double gold;
    private double diamond;


    //Liên quan đến việc dùng tiền tệ

    //Định dạng lại đơn tị tiền tệ
    NumberFormat numf = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

    public User() {
    }

    public User(String nameID, String passWord, String namePlayer, double gold, double diamond) {
        this.nameID = nameID;
        this.passWord = passWord;
        this.namePlayer = namePlayer;
        this.gold = gold;
        this.diamond = diamond;
    }

    public String getNameID() {
        return nameID;
    }

    public void setNameID(String nameID) {
        this.nameID = nameID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getDiamond() {
        return diamond;
    }

    public void setDiamond(double diamond) {
        this.diamond = diamond;
    }

    //Đăng nhập: Chỉ nhập TK + MK
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
        System.out.print("Mật khẩu: ");
        setPassWord(sc.nextLine());
    }

    //Tạo tài khoản
    public void registerData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
        System.out.print("Mật khẩu: ");
        setPassWord(sc.nextLine());
        System.out.print("Tên đại diện: ");
        setNamePlayer(sc.nextLine());

        //Mặc định tài khoản luôn sẽ có 500 gold
        setGold(500);
        setDiamond(0);
    }

    //Kiểm tra tài khoản
    public boolean checkNameID(String nameID) {
        return nameID.equals(getNameID());
    }

    //Kiểm tra mật khẩu
    public boolean checkPassWord(String passWord) {
        return passWord.equals(getPassWord());
    }

    @Override
    public String toString() {
        return "User{" +
                "nameID='" + nameID + '\'' +
                ", passWord='" + passWord + '\'' +
                ", namePlayer='" + namePlayer + '\'' +
                ", gold=" + gold +
                ", diamond=" + diamond +
                '}';
    }

    public String Gold() {
        return numf.format(this.getGold());
    }

    public String Diamond() {
        return numf.format(this.getDiamond());
    }
    public void printInformationUser() {
        System.out.println("====> INFORMATION <====");
        System.out.println("Biệt danh: " + this.getNamePlayer());
        System.out.println("TK: " + this.getNameID());
        System.out.println("MK: ********");
        System.out.println("Gold: " + Gold());
        System.out.println("Diamond: " + Diamond());
        //System.out.println("Giới hạn lưu GAME hiện tại: " + getSaveGameLimited());
    }
}
