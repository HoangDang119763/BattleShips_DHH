import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class User implements Serializable {
    private String nameID;//Tài khoản
    private String passwordID;//Mật khẩu
    private String namePlayer;//Tên người dùng
    private String secretQuestion;

    private int numGameWin;

    //Phần tiền tệ
    private double gold;
    private double diamond;


    //Liên quan đến việc dùng tiền tệ

    //Định dạng lại đơn tị tiền tệ
    NumberFormat numf = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

    public User() {
    }

    public User(String nameID, String passwordID, String namePlayer, double gold, double diamond) {
        this.nameID = nameID;
        this.passwordID = passwordID;
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

    public String getPasswordID() {
        return passwordID;
    }

    public void setPasswordID(String passwordID) {
        this.passwordID = passwordID;
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

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public int getNumGameWin() {
        return numGameWin;
    }

    public void setNumGameWin(int numGameWin) {
        this.numGameWin = numGameWin;
    }

    public NumberFormat getNumf() {
        return numf;
    }

    public void setNumf(NumberFormat numf) {
        this.numf = numf;
    }

    //Đăng nhập: Chỉ nhập TK + MK
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
        System.out.print("Mật khẩu: ");
        setPasswordID(sc.nextLine());
    }

    //Nhập tài khoản
    public void inputNameID() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
    }

    //Nhâp mật khẩu
    public void inputPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mật khẩu: ");
        setPasswordID(sc.nextLine());
    }

    //Tạo tài khoản
    public void registerData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tài khoản: ");
        setNameID(sc.nextLine().toLowerCase());
        System.out.print("Mật khẩu: ");
        setPasswordID(sc.nextLine());
        System.out.print("Tên đại diện: ");
        setNamePlayer(sc.nextLine());

        //Mặc định tài khoản luôn sẽ có 500 gold
        setGold(500);
        setDiamond(0);
        setSecretQuestion("null");
    }

    //Kiểm tra tài khoản
    public boolean checkNameID(String nameID) {
        return nameID.equals(getNameID());
    }

    //Kiểm tra mật khẩu
    public boolean checkPasswordID(String passwordID) {
        return passwordID.equals(getPasswordID());
    }

    //Kiểm tra câu hỏi bảo mật
    public boolean checkSecretQuetion(String secretQuestion) {
        return secretQuestion.equals(getSecretQuestion());
    }

    public boolean checkNumGameWin(int numGameWin) {
        return numGameWin == getNumGameWin();
    }

    public boolean checkGold(double gold) {
        return gold == getGold();
    }

    public boolean checkDiamond(double diamond) {
        return diamond == getDiamond();
    }

    @Override
    public String toString() {
        return "User{" +
                "nameID='" + nameID + '\'' +
                ", passWord='" + passwordID + '\'' +
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
        System.out.println("====> **** <====");
        System.out.println("1.Đổi mật khẩu");
        System.out.println("2.Đặt câu hỏi bảo mật");
        System.out.println("3.Bạn bè");
        System.out.println("0.Thoát");
        //System.out.println("Giới hạn lưu GAME hiện tại: " + getSaveGameLimited());
    }
}
