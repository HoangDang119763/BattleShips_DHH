import java.io.*;
import java.util.ArrayList;

public class ListUser {
    private ArrayList<User> listUser;

    public ListUser() {
        this.listUser = new ArrayList<>();
    }

    public ListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public void addUser(User userName) {
        this.listUser.add(userName);
    }

    public void removeUser(User userName) {
        this.listUser.remove(userName);
    }

    //Kiểm tra rỗng
    /*public boolean checkNull() {
        return this.listUser.isEmpty();
    }*/

    //Số user
    public int numUser() {
        return this.listUser.size();
    }

    //Xóa hết User đã lưu
    public void removeAllUser() {
        this.listUser.removeAll(listUser);
    }

    //Xóa theo số thứ tự
    /*public void removeSTT(int STT) {
        this.listUser.remove(STT - 1);
    }*/

    //Xóa theo nameID
    public void removeNameID(String nameID) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                removeUser(user);
                break;
            }
        }
    }

    //Cập nhật danh sách từ file
    public void updateListUserFromFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
                while (true) {
                    Object obj = objectIn.readObject();
                    if (obj instanceof User) {
                        User user = (User) obj;
                        this.listUser.add(user);
                    }
                }
            } catch (EOFException e) {
                //
            }
            objectIn.close();
            fileIn.close();
            //System.out.println("Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Lỗi khi cập nhật ArrayList!");
        }
    }

    //Lưu danh sách vào file
    public void updateListUserToFile(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (User user : listUser) {
                objectOut.writeObject(user);
            }
            objectOut.close();
            fileOut.close();
            //System.out.println("Đã lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Lưu thất bại!");
        }
    }

    //Kiểm tra tài khoản người dùng nhập có đúng không (nếu đã đăng kí) và chưa đăng kí
    public boolean checkInputUserFromFile(String nameID, String passWord) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkNameID(nameID) && user.checkPasswordID(passWord)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //Kiểm tra tài khoản
    public boolean checknameIDUserFromFile(String nameID) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //Kiểm tra mật khẩu
    public boolean checkPasswordUserByNameIDFromFile(String nameID, String passwordID) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkPasswordID(passwordID)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //Kiểm tra câu hỏi bảo mật
    public boolean checkSecretQuestionUserByNameIdFromFile(String nameID, String secretQuestion) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkSecretQuetion(secretQuestion)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void setPasswordIDByNameIDToFile(String nameID, String passwordID) {
        for (User user : listUser) {
            if (user.getNameID().equals(nameID)) user.setPasswordID(passwordID);
        }
    }

    public void setSecretQuestionByNameIDToFile(String nameID, String secretQuestion) {
        for (User user : listUser) {
            if (user.getNameID().equals(nameID)) user.setSecretQuestion(secretQuestion);
        }
    }

    //Lấy namePlayer từ ListUser
    public String getNamePlayerFromList(String nameID) {
        String temp = "";
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getNamePlayer();
            }
        }
        return temp;
    }

    public String getSecretQuestionFromList(String nameID) {
        String temp = "";
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getSecretQuestion();
            }
        }
        return temp;
    }

    //Trả về số gold của player có TK:..
    public double getGoldPlayerFromListByNameID(String nameID) {
        double temp = 0;
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getGold();
            }
        }
        return temp;
    }

    //Trả về số diamond của player có TK:..
    public double getDiamondPlayerFromListByNameID(String nameID) {
        double temp = 0;
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getDiamond();
            }
        }
        return temp;
    }

    //In thông tin của User chỉ định
    public void printUserInformationByNameID(String nameID) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                System.out.println(user);
                System.out.println();
                break;
            }
        }
    }

    public void printUserGoldByNameID(String nameID) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                System.out.println(user.Gold());
                System.out.println();
                break;
            }
        }
    }

    public void printUserDiamondByNameID(String nameID) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                System.out.println(user.Diamond());
                System.out.println();
                break;
            }
        }
    }

    //Thêm và trừ GOLD của User
    public void addUserGoldByNameID(String nameID, double numGold) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(user.getGold() + numGold);
                break;
            }
        }
    }

    public void subUserGoldByNameID(String nameID, double numGold) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(user.getGold() - numGold);
                break;
            }
        }
    }

    //Thêm và trừ GOLD của User
    public void addUserDiamondByNameID(String nameID, double numDiamond) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setDiamond(user.getDiamond() + numDiamond);
                break;
            }
        }
    }

    public void subUserDiamondByNameID(String nameID, double numDiamond) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setDiamond(user.getDiamond() - numDiamond);
                break;
            }
        }
    }

    //Set form list
    public void setUserGoldByNameID(String nameID, double numGold) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(numGold);
                break;
            }
        }
    }

    public void setUserDiamondByNameID(String nameID, double numDiamond) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(numDiamond);
                break;
            }
        }
    }

    //In danh sách User đã lưu ra màn hình
    public void printAllUserSaved() {
        int stt = 1;
        for (User user : listUser) {
            System.out.println("\nNgười dùng thứ " + stt + ":");
            stt++;
            //In
            System.out.println(user);
        }
    }

}
