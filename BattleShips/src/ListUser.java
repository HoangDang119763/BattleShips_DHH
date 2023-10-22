import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListUser implements FileName {
    Scanner sc = new Scanner(System.in);
    private ArrayList<User> listUser;

    public ListUser() {
        this.listUser = new ArrayList<>();
    }

    public ListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public void addUser(User userName) {
        this.listUser.add(userName);
    }

    public void removeUser(User userName) {
        this.listUser.remove(userName);
    }

    //Kiểm tra rỗng
    public boolean checkNull() {
        return this.listUser.isEmpty();
    }

    //Số user
    public int numUser() {
        return this.listUser.size();
    }

    //Xóa hết User đã lưu
    public void removeAllUser() {
        this.listUser.removeAll(listUser);
    }

    //Xóa theo số thứ tự
    public void removeSTT(int STT) {
        this.listUser.remove(STT - 1);
    }

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
    public boolean checkInputUserFromList(String nameID, String passWord) {
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
    public boolean checknameIDUserFromList(String nameID) {
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
    public boolean checkPasswordIDByNameIDFromList(String nameID, String passwordID) {
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
    public boolean checkSecretQuestionByNameIDFromList(String nameID, String secretQuestion) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkSecretQuetion(secretQuestion)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean checkNumGameWinByNameIDFromList(String nameID, int numGameWin) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkNumGameWin(numGameWin)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean checkGoldPlayerByNameIDFromList(String nameID, double gold) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkGold(gold)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean checkDiamondPlayerByNameIDFromList(String nameID, double diamond) {
        boolean flag = false;
        for (User user : listUser) {
            if (user.checkGold(diamond)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    //Lấy namePlayer từ ListUser
    public String getNamePlayerByNameIDFromList(String nameID) {
        String temp = "";
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getNamePlayer();
            }
        }
        return temp;
    }

    public String getPasswordIDByNameIDFromList(String nameID) {
        String temp = "";
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getPasswordID();
            }
        }
        return temp;
    }

    public String getSecretQuestionByNameIDFromList(String nameID) {
        String temp = "";
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getSecretQuestion();
            }
        }
        return temp;
    }

    public int getNumGameWinByNameIDFromList(String nameID) {
        int temp = 0;
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getNumGameWin();
            }
        }
        return temp;
    }

    //Trả về số gold của player có TK:..
    public double getGoldPlayerByNameIDFromList(String nameID) {
        double temp = 0;
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getGold();
            }
        }
        return temp;
    }

    //Trả về số diamond của player có TK:..
    public double getDiamondPlayerByNameIDFromList(String nameID) {
        double temp = 0;
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                temp = user.getDiamond();
            }
        }
        return temp;
    }

    //Thêm và trừ GOLD của User
    public void addUserGoldByNameIDToList(String nameID, double numGold) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(user.getGold() + numGold);
                break;
            }
        }
    }

    public void subUserGoldByNameIDToList(String nameID, double numGold) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(user.getGold() - numGold);
                break;
            }
        }
    }

    //Thêm và trừ GOLD của User
    public void addUserDiamondByNameIDToList(String nameID, double numDiamond) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setDiamond(user.getDiamond() + numDiamond);
                break;
            }
        }
    }

    public void subUserDiamondByNameIDToList(String nameID, double numDiamond) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setDiamond(user.getDiamond() - numDiamond);
                break;
            }
        }
    }

    //Set vào list
    public void setPasswordIDByNameIDToList(String nameID, String passwordID) {
        for (User user : listUser) {
            if (user.getNameID().equals(nameID)) user.setPasswordID(passwordID);
        }
    }

    public void setPasswordIDByNameIDToFile(String fileName, String nameID, String passwordID) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            if (checkNull()) {
                System.out.println("Người dùng này chưa đăng kí!");
            } else {
                setPasswordIDByNameIDToList(nameID, passwordID);
                updateListUserToFile(fileName);
                System.out.println("Đổi mật khẩu thành công !!!");
            }
        } else if (!checkNull() && fileExist(fileName)){
            setPasswordIDByNameIDToList(nameID, passwordID);
            updateListUserToFile(fileName);
            System.out.println("Đổi mật khẩu thành công !!!");
        }
    }

    public void setSecretQuestionByNameIDToList(String nameID, String secretQuestion) {
        for (User user : listUser) {
            if (user.getNameID().equals(nameID)) user.setSecretQuestion(secretQuestion);
        }
    }

    public void setSecretQuestionByNameIDToFile(String fileName, String nameID, String secretQuestion) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            if (checkNull()) {
                System.out.println("Người dùng này chưa đăng kí!");
            } else {
                if (!getSecretQuestionByNameIDFromList(nameID).equals("null")) System.out.println("Đã cài câu hỏi bảo mật!");
                else {
                    setSecretQuestionByNameIDToList(nameID, secretQuestion);
                    updateListUserToFile(fileName);
                    System.out.println("Đặt câu hỏi bảo mật thành công !!!");
                }
            }
        } else if (!checkNull() && fileExist(fileName)){
            setSecretQuestionByNameIDToList(nameID, secretQuestion);
            updateListUserToFile(fileName);
            System.out.println("Đặt câu hỏi bảo mật thành công !!!");
        }
    }

    public void setNumGameWinByNameIDToList(String nameID, int numGameWin) {
        for (User user : listUser) {
            if (user.getNameID().equals(nameID)) user.setNumGameWin(numGameWin);
        }
    }
    public void setUserGoldByNameIDToList(String nameID, double numGold) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(numGold);
                break;
            }
        }
    }

    public void setUserDiamondByNameIDToList(String nameID, double numDiamond) {
        for (User user : listUser) {
            if (user.checkNameID(nameID)) {
                user.setGold(numDiamond);
                break;
            }
        }
    }

    //In danh sách User đã lưu ra màn hình
    public void printAllUserFromList() {
        int stt = 1;
        for (User user : listUser) {
            System.out.println("\nNgười dùng thứ " + stt + ":");
            stt++;
            System.out.println(user);
        }
    }

    public void printAllListUserFromFile(String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) {
                updateListUserFromFile(fileName);
                //Nếu cập nhật rồi mà ArrayList vẫn rỗng thì => chưa lưu user nào
                if (checkNull()) System.out.println("Chưa có User nào đăng kí!");
                else printAllUserFromList();
            } else System.out.println("Chưa có User nào đăng kí!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!checkNull() && fileExist(fileName)){
            printAllUserFromList();
        }
    }

    //Thêm User vào list game
    public void addUserToFile(String fileName, User user) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            addUser(user);
            //Cập nhật vào file để lưu
            updateListUserToFile(fileName);
        } else if (!checkNull() && fileExist(fileName)){
            addUser(user);
            updateListUserToFile(fileName);
        }
    }

    //Xóa User thep STT

    public void removeUserSavedSTT(String fileName, int STT) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) {
                updateListUserFromFile(fileName);
                //Nếu cập nhật rồi mà ArrayList vẫn rỗng thì => chưa có user
                if (checkNull()) System.out.println("Chưa có User nào đăng kí!");
                else {
                    removeSTT(STT);
                    updateListUserToFile(fileName);
                }
            } else System.out.println("Chưa có User nào đăng kí!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!checkNull() && fileExist(fileName)){
            removeSTT(STT);
            updateListUserToFile(fileName);
        }
    }

    public void removeAllUserSaved(String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật rỗng vào file
            if (fileExist(fileName)) {
                updateListUserToFile(fileName);
            } else System.out.println("Chưa có User nào đăng kí!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!checkNull() && fileExist(fileName)){
            removeAllUser();
            updateListUserToFile(fileName);
        }
    }

    public void loginUser(User user, String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            System.out.println("====> LOGIN <====");
            user.inputData();
        } else if (!checkNull() && fileExist(fileName)){
            System.out.println("====> LOGIN <====");
            user.inputData();
        }
    }
    public void registerUser(User user, String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            do {
                System.out.println("====> REGISTER <====");
                user.registerData();
                //Nếu không có trong danh sách thì dừng
                if (user.getNameID().equals("0") || !checknameIDUserFromList(user.getNameID())) break;
                System.out.println("Tài khoản đã có người sử dụng!\nMời bạn nhập lại tài khoản khác (Bấm 0 để thoát)");
            } while (true);
            addUserToFile(fileName, user);
        } else if (!checkNull() && fileExist(fileName)){
            addUserToFile(fileName, user);
        }
    }

    public void askForRegister(User user, String fileName) {
        int playerChoice;
        System.out.println("Bạn có muốn tạo tài khoản mới?");

        do {
            System.out.println("1.Có\n2.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                registerUser(user, fileName);
                break;
            } else if (playerChoice == 2) break;
            else {
                System.out.println("Lựa chọn không hợp lệ!");
                System.out.println("Mời bạn nhập lại");
            }
        } while (true); //Vì chỉ muốn trả lời 1 trong 2
        System.out.println();
    }

    public void changePasswordID(User user, String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            while (true) {
                System.out.println("====> CHANGE PASSWORD <====");
                System.out.println("Nhập mật khẩu hiện tại (Bấm 0 để thoát)");
                String temp1 = sc.nextLine();
                if (temp1.equals("0")) break;
                //Nếu mật khẩu đúng
                if (checkPasswordIDByNameIDFromList(user.getNameID(), temp1)) {
                    do {
                        System.out.println("Nhập mật khẩu mới (Bấm 0 để thoát)");
                        String temp2 = sc.nextLine();
                        if (temp2.equals("0")) break;
                        System.out.println("Nhập lại mật khẩu mới");
                        String temp3 = sc.nextLine();

                        //Nếu 2 cái mật khẩu vừa nhập khác nhau => bắt nhập lại
                        if (!temp2.equals(temp3)) {
                            System.out.println("Hai mật khẩu không trùng khớp");
                            System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                        } else {
                            setPasswordIDByNameIDToFile(fileName, user.getNameID(), temp3);
                            break;
                        }
                    } while (true);
                    break;
                } else {
                    System.out.println("Mật khẩu hiện tại sai!");
                    System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                }
            }
        } else if (!checkNull() && fileExist(fileName)) {
            while (true) {
                System.out.println("====> CHANGE PASSWORD <====");
                System.out.println("Nhập mật khẩu hiện tại (Bấm 0 để thoát)");
                String temp1 = sc.nextLine();
                if (temp1.equals("0")) break;
                //Nếu mật khẩu đúng
                if (checkPasswordIDByNameIDFromList(user.getNameID(), temp1)) {
                    do {
                        System.out.println("Nhập mật khẩu mới (Bấm 0 để thoát)");
                        String temp2 = sc.nextLine();
                        if (temp2.equals("0")) break;
                        System.out.println("Nhập lại mật khẩu mới");
                        String temp3 = sc.nextLine();

                        //Nếu 2 cái mật khẩu vừa nhập khác nhau => bắt nhập lại
                        if (!temp2.equals(temp3)) {
                            System.out.println("Hai mật khẩu không trùng khớp");
                            System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                        } else {
                            setPasswordIDByNameIDToFile(fileName, user.getNameID(), temp3);
                            break;
                        }
                    } while (true);
                    break;
                } else {
                    System.out.println("Mật khẩu hiện tại sai!");
                    System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                }
            }
        }
    }

    public void setSecretQuestion(User user, String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            while (true) {
                System.out.println("====> SECRET QUESTION <====");
                System.out.println(questionOne + " (Bấm 0 để thoát)");
                String temp = sc.nextLine();
                if (temp.equals("0")) break;
                setSecretQuestionByNameIDToFile(fileName, user.getNameID(), temp);
                break;
            }
        } else if (!checkNull() && fileExist(fileName)) {
            while (true) {
                System.out.println("====> SECRET QUESTION <====");
                System.out.println(questionOne + " (Bấm 0 để thoát)");
                String temp = sc.nextLine();
                if (temp.equals("0")) break;
                setSecretQuestionByNameIDToFile(fileName, user.getNameID(), temp);
                break;
            }
        }
    }

    public void forgetPasswordID(User user, String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListUserFromFile(fileName);
            while (true) {
                System.out.println("====> FORGET PASSWORD <====");
                System.out.println("(Bấm 0 để thoát)");
                user.inputNameID();
                if (user.getNameID().equals("0")) break;
                //Kiểm tra tài khoản vừa nhập có trong danh sách tài khoản đăng kí
                if (checknameIDUserFromList(user.getNameID())) {
                    System.out.println(questionOne + " (Bấm 0 để thoát)");
                    String temp1 = sc.nextLine();
                    if (temp1.equals("0")) break;
                    //Nếu nhập đúng câu trả lời
                    if (checkSecretQuestionByNameIDFromList(user.getNameID(), temp1)) {
                        do {
                            System.out.println("Nhập mật khẩu mới");
                            String temp2 = sc.nextLine();
                            if (temp2.equals("0")) break;
                            System.out.println("Nhập lại mật khẩu mới");
                            String temp3 = sc.nextLine();

                            //Nếu 2 cái mật khẩu vừa nhập khác nhau => bắt nhập lại
                            if (!temp2.equals(temp3)) {
                                System.out.println("Hai mật khẩu không trùng khớp");
                                System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                            } else {
                                setPasswordIDByNameIDToFile(fileName, user.getNameID(), temp3);
                                break;
                            }
                        } while (true);
                        break;
                    } else {
                        System.out.println("Câu trả lời cho câu hỏi bảo mật sai !!!");
                    }
                } else System.out.println("Tài khoản không tồn tại hoặc nhập sai\nMời bạn nhập lại ! (Bấm 0 để thoát)");
            }
        } else if (!checkNull() && fileExist(fileName)) {
            while (true) {
                System.out.println("====> FORGET PASSWORD <====");
                System.out.println("Nhập tài khoản: (Bấm 0 để thoát)");
                user.inputNameID();
                if (user.getNameID().equals("0")) break;
                //Kiểm tra tài khoản vừa nhập có trong danh sách tài khoản đăng kí
                if (checknameIDUserFromList(user.getNameID())) {
                    System.out.println(questionOne + " (Bấm 0 để thoát)");
                    String temp1 = sc.nextLine();
                    if (temp1.equals("0")) break;
                    //Nếu nhập đúng câu trả lời
                    if (checkSecretQuestionByNameIDFromList(user.getNameID(), temp1)) {
                        do {
                            System.out.println("Nhập mật khẩu mới");
                            String temp2 = sc.nextLine();
                            if (temp2.equals("0")) break;
                            System.out.println("Nhập lại mật khẩu mới");
                            String temp3 = sc.nextLine();

                            //Nếu 2 cái mật khẩu vừa nhập khác nhau => bắt nhập lại
                            if (!temp2.equals(temp3)) {
                                System.out.println("Hai mật khẩu không trùng khớp");
                                System.out.println("Mời bạn nhập lại ! (Bấm 0 để thoát)");
                            } else {
                                setPasswordIDByNameIDToFile(fileName, user.getNameID(), temp3);
                                break;
                            }
                        } while (true);
                        break;
                    } else {
                        System.out.println("Câu trả lời cho câu hỏi bảo mật sai !!!");
                    }
                } else System.out.println("Tài khoản không tồn tại hoặc nhập sai\nMời bạn nhập lại ! (Bấm 0 để thoát)");
            }
        }
    }


    public void updateInformationUserFromList(User user) {
        user.setNamePlayer(getNamePlayerByNameIDFromList(user.getNameID()));
        user.setNumGameWin(getNumGameWinByNameIDFromList(user.getNameID()));
        user.setGold(getGoldPlayerByNameIDFromList(user.getNameID()));
        user.setDiamond(getDiamondPlayerByNameIDFromList(user.getNameID()));
        user.setSecretQuestion(getSecretQuestionByNameIDFromList(user.getNameID()));
    }
}
