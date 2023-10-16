import java.io.File;
import java.util.Scanner;

public class Console implements FileName {
    Scanner sc = new Scanner(System.in);

//    String fileUser = "ListUser.txt";
//    String fileAdmin = "ListAdmin.txt";

    //Phần game
    ListGameBattleShips listGame = new ListGameBattleShips();
    BattleShips bs = new BattleShips_TypeOne();

    //Phần người dùng
    User user = new User();
    ListUser listUser = new ListUser();

    //Kiểm tra file tồn tại không
    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    private void notificationMess() {
        System.out.println("Lựa chọn không hợp lệ!");
        System.out.println("Mời bạn nhập lại");
    }

    public void menu() {
        System.out.println("1.In ra danh sách các User đã đăng kí");
        System.out.println("2.Thêm User");
        System.out.println("3.Xóa User theo nameID(Tài khoản)");
        System.out.println("4.Xóa tất cả User");
        System.out.println("5.In ra thông tin User theo NameID(Tài khoản)");
        System.out.println("6.In ra số Gold của User");
        System.out.println("7.In ra số Diamond của User");
        System.out.println("8.Thêm số Gold cho User theo NameID(Tài khoản)");
        System.out.println("9.Thêm số Diamond cho User theo NameID(Tài khoản)");
        System.out.println("10.Trừ số Gold cho User theo NameID(Tài khoản)");
        System.out.println("11.Trừ số Diamond cho User theo NameID(Tài khoản)");
        System.out.println("12.Đặt lại số Gold cho User theo NameID(Tài khoản)");
        System.out.println("13.Đặt lại số Diamond cho User theo NameID(Tài khoản)");
        System.out.println("14.In ra danh sách các Game mà User chỉ định bằng NameID(Tài khoản) dang lưu");
        System.out.println("15.Xóa Game đã lưu ủa User chỉ định bằng NameID(Tài khoản) theo STT Game");
        System.out.println("16.Xóa tất cả Game đã lưu ủa User chỉ định bằng NameID(Tài khoản)");
        System.out.println("0.Thoát");
    }

    public void adminChoice() {
        int playerChoice = 0;
        System.out.println("**** ADMIN ROLE ****");

        do {
            menu();
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    showListUser();
                    break;
                case 2:
                    addUserToFile();
                    break;
                case 3:
                    removeUserBynameID();
                    break;
                case 4:
                    removeAllUserSaved();
                    break;
                case 5:
                    showUserInformatioByNameID();
                    break;
                case 6:
                    showUserGoldByNameID();
                    break;
                case 7:
                    showUserDiamondByNameID();
                    break;
                case 8:
                    addUserGoldByNameID();
                    break;
                case 9:
                    addUserDiamondByNameID();
                    break;
                case 10:
                    subUserGoldByNameID();
                    break;
                case 11:
                    subUserDiamondByNameID();
                    break;
                case 12:
                    setUserGoldByNameID();
                    break;
                case 13:
                    setUserDiamondByNameID();
                    break;
                case 14:
                    showListGameUserByNameID();
                    break;
                case 15:
                    removeGameUserNameIDBySTT();
                    break;
                case 16:
                    removeALLGameUserNameID();
                    break;
                default:
                    notificationMess();//Done!
                    break;
            }
        } while (playerChoice != 0);
        System.out.println();
    }

    //Chức năng phần User
    /*
    1.In ra danh sách các User đã đăng kí
    2.Thêm User
    3.Xóa User theo nameID
    4.Xóa hết các User hiện có
    4.In ra thông tin User theo NameID(Tài khoản)
    5.Tìm kiếm thông tin User theo NameID
    6.In ra tài sản của User gold + diamond
    7.Thêm/Trừ Gold của User theo NameID
    8.Thêm/Trừ Diamond của User theo NameID
    9.Đặt lại Gold/Diamond của User theo NameID
     */

    //1.
    public void showListUser() {
        listUser.removeAllUser();
        if (fileExist(fileUser)) {
            listUser.updateListUserFromFile(fileUser);
            //Nếu có file thì cập nhật rồi in
            System.out.println("Các User đã đăng kí:");
            listUser.printAllUserSaved();
        } else System.out.println("Chưa có User nào đăng kí");
        listUser.removeAllUser();
    }

    //2.
    public void addUserToFile() {
        listUser.removeAllUser();
        if (fileExist(fileUser)) listUser.updateListUserFromFile(fileUser);
        do {
            System.out.println("====> REGISTER <====");
            user.registerData();
            //Nếu không có trong danh sách thì dừng
            if (!listUser.checknameIDUserFromFile(user.getNameID())) break;
            System.out.println("Tài khoản đã có người sử dụng!\nMời bạn nhập lại tài khoản khác");
        } while (true);
        listUser.addUser(user);
        listUser.updateListUserToFile(fileUser);
        listUser.removeAllUser();
        user = new User();
    }

    //3.
    public void removeUserBynameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần xóa (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    listUser.removeNameID(nameID);
                    System.out.println("List User sau khi xóa:");
                    listUser.printAllUserSaved();
                    listUser.updateListUserToFile(fileUser);
                    break;
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần xóa!");
                }
            } while (true);
        }
        listGame.removeAllGame();
    }

    //4.
    public void removeAllUserSaved() {
        listUser.removeAllUser();
        listUser.updateListUserToFile(fileUser);
        System.out.println("List User sau khi xóa:");
        listUser.printAllUserSaved();
        listUser.removeAllUser();
    }

    //5.
    public void showUserInformatioByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần xem thông tin (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                if (nameID.equals("0")) {
                    break;
                }
                //Kiêm trả xem tài khoản này có trong danh sách không
                else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Thông tin của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserInformationByNameID(nameID);
                    break;
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần in thông tin!");
                }
            } while (true);
        }
        listGame.removeAllGame();
    }

    //6.
    public void showUserGoldByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần in Gold (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Gold của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserGoldByNameID(nameID);
                    break;
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần in Gold!");
                }
            } while (true);
        }
        listGame.removeAllGame();
    }

    public void showUserDiamondByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần in Diamond (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Diamond của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserDiamondByNameID(nameID);
                    break;
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần in Diamond!");
                }
            } while (true);
        }
        listGame.removeAllGame();
    }

    //7.
    public void addUserGoldByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
//            System.out.println("Các User đã đăng kí:");
//            listUser.printAllUserSaved();
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần cộng Gold (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Gold của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserGoldByNameID(nameID);
                    double numGold;
                    do {
                        System.out.println("Nhập số Gold cần thêm cho User có NameID(Tài khoản) " + nameID + ": ");
                        numGold = Double.parseDouble(sc.nextLine());
                        if (numGold >= 0) {
                            listUser.addUserGoldByNameID(nameID, numGold);
                            System.out.println("Số Gold của User có NameID(Tài khoản) " + nameID + " sau khi thêm: ");
                            listUser.printUserGoldByNameID(nameID);
                            listUser.updateListUserToFile(fileUser);
                            break;
                        } else {
                            System.out.println("Số Gold nhập không hợp lệ!");
                        }
                    } while (true);
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần thêm số Gold!");
                }
            } while (true);
        }
        listUser.removeAllUser();
    }

    public void subUserGoldByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần trừ Gold (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Gold của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserGoldByNameID(nameID);
                    double numGold;
                    do {
                        System.out.println("Nhập số Gold cần trừ cho User có NameID(Tài khoản) " + nameID + ": ");
                        numGold = Double.parseDouble(sc.nextLine());
                        if (numGold >= 0) {
                            listUser.subUserGoldByNameID(nameID, numGold);
                            System.out.println("Số Gold của User có NameID(Tài khoản) " + nameID + " sau khi trừ: ");
                            listUser.printUserGoldByNameID(nameID);
                            listUser.updateListUserToFile(fileUser);
                            break;
                        } else {
                            System.out.println("Số Gold nhập không hợp lệ!");
                        }
                    } while (true);
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần trừ số Gold!");
                }
            } while (true);
        }
        listUser.removeAllUser();
    }

    //8
    public void addUserDiamondByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần cộng Diamond (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Diamond của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserDiamondByNameID(nameID);
                    double numDiamond;
                    do {
                        System.out.println("Nhập số Diamond cần thêm cho User có NameID(Tài khoản) " + nameID + ": ");
                        numDiamond = Double.parseDouble(sc.nextLine());
                        if (numDiamond >= 0) {
                            listUser.addUserDiamondByNameID(nameID, numDiamond);
                            System.out.println("Số Diamond của User có NameID(Tài khoản) " + nameID + " sau khi thêm: ");
                            listUser.printUserDiamondByNameID(nameID);
                            listUser.updateListUserToFile(fileUser);
                            break;
                        } else {
                            System.out.println("Số Diamond nhập không hợp lệ!");
                        }
                    } while (true);
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần thêm số Diamond!");
                }
            } while (true);
        }
        listUser.removeAllUser();
    }

    public void subUserDiamondByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần trừ Diamond (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Diamond của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserDiamondByNameID(nameID);
                    double numDiamond;
                    do {
                        System.out.println("Nhập số Diamond cần trừ cho User có NameID(Tài khoản) " + nameID + ": ");
                        numDiamond = Double.parseDouble(sc.nextLine());
                        if (numDiamond >= 0) {
                            listUser.subUserDiamondByNameID(nameID, numDiamond);
                            System.out.println("Số Diamond của User có NameID(Tài khoản) " + nameID + " sau khi trừ: ");
                            listUser.printUserDiamondByNameID(nameID);
                            listUser.updateListUserToFile(fileUser);
                            break;
                        } else {
                            System.out.println("Số Diamond nhập không hợp lệ!");
                        }
                    } while (true);
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần trừ số Diamond!");
                }
            } while (true);
        }
        listUser.removeAllUser();
    }

    //9
    public void setUserGoldByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần đặt Gold (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Gold của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserGoldByNameID(nameID);
                    double numGold;
                    do {
                        System.out.println("Nhập số Gold cần đặt cho User có NameID(Tài khoản) " + nameID + ": ");
                        numGold = Double.parseDouble(sc.nextLine());
                        if (numGold >= 0) {
                            listUser.setUserGoldByNameID(nameID, numGold);
                            System.out.println("Số Gold của User có NameID(Tài khoản) " + nameID + " sau khi đặt: ");
                            listUser.printUserGoldByNameID(nameID);
                            listUser.updateListUserToFile(fileUser);
                            break;
                        } else {
                            System.out.println("Số Gold nhập không hợp lệ!");
                        }
                    } while (true);
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần đặt lại số Gold!");
                }
            } while (true);
        }
        listUser.removeAllUser();
    }

    public void setUserDiamondByNameID() {
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần đặt Diamond (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Số Diamond của User có NameID(Tài khoản) " + nameID + ": ");
                    listUser.printUserDiamondByNameID(nameID);
                    double numDiamond;
                    do {
                        System.out.println("Nhập số Diamond cần đặt cho User có NameID(Tài khoản) " + nameID + ": ");
                        numDiamond = Double.parseDouble(sc.nextLine());
                        if (numDiamond >= 0) {
                            listUser.setUserDiamondByNameID(nameID, numDiamond);
                            System.out.println("Số Diamond của User có NameID(Tài khoản) " + nameID + " sau khi đặt: ");
                            listUser.printUserDiamondByNameID(nameID);
                            listUser.updateListUserToFile(fileUser);
                            break;
                        } else {
                            System.out.println("Số Diamond nhập không hợp lệ!");
                        }
                    } while (true);
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần đặt lại số Diamond!");
                }
            } while (true);
        }
        listUser.removeAllUser();
    }

    //Chức năng phần Game
    /*
    1.In ra danh sách các Game mà User chỉ định bằng NameID dang lưu
    2.Xóa Game đã lưu ủa User chỉ định bằng NameID theo STT Game
    3.Xóa  HẾT Game đã lưu ủa User chỉ định bằng NameID
     */

    public void showListGameUserByNameID() {
        int choice = -1;
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần in danh sách Game đã lưu (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    System.out.println("Danh sách Game đã lưu của User có NameID(Tài khoản) " + nameID + ": ");
                    listGame.removeAllGame();
                    if (fileExist(nameID + ".txt")) {
                        listGame.updateListGameFromFile(nameID + ".txt");
                        //Nếu có file thì cập nhật rồi in
                        listGame.printAllGameSaved();
                    } else System.out.println("User có NameID(Tài khoản) " + nameID + " chưa lưu game nào!");
                    listGame.removeAllGame();
                    break;
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần in danh sách Game đã lưu!");
                }
            } while (true);
        }
        listGame.removeAllGame();
    }

    public void removeGameUserNameIDBySTT() {
        int playerChoice = -1;
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần xóa Game trong danh sách đã lưu (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    int choice = -1;
                    listGame.removeAllGame();
                    if (fileExist(nameID + ".txt"))
                        listGame.updateListGameFromFile(nameID + ".txt");
                    if (listGame.numGame() <= 0)
                        System.out.println("User có NameID(Tài khoản) " + nameID + " chưa lưu Game nào!");
                    else {
                        System.out.println("Danh sách Game đã lưu của User có NameID(Tài khoản) " + nameID + ": ");
                        listGame.printAllGameSaved();
                        do {
                            System.out.println("Mời bạn nhập STT của game cần xóa: ");
                            choice = Integer.parseInt(sc.nextLine());
                            if (choice >= 1 || choice <= listGame.numGame()) {
                                listGame.removeSTT(choice);
                                System.out.println("List Game sau khi xóa:");
                                listGame.printAllGameSaved();
                                listGame.updateListGameToFile(nameID + ".txt");
                                break;
                            } else {
                                System.out.println("Hoặc số STT không hợp lệ!");
                            }
                        } while (true);
                    }
                    listGame.removeAllGame();
                    break;
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần xóa Game trong danh sách đã lưu");
                }
            } while (true);
        }
        listGame.removeAllGame();
    }

    public void removeALLGameUserNameID() {
        int playerChoice = -1;
        listUser.removeAllUser();
        if (fileExist(fileUser))
            listUser.updateListUserFromFile(fileUser);
        if (listUser.numUser() <= 0) System.out.println("Chưa có User nào đăng kí");
        else {
            do {
                System.out.println("Mời bạn nhập NameID(Tài khoản) của User cần xóa hết Game trong danh sách đã lưu (Bấm 0 để thoát!)");
                String nameID = sc.nextLine();
                //Kiêm trả xem tài khoản này có trong danh sách không
                if (nameID.equals("0")) {
                    break;
                } else if (listUser.checknameIDUserFromFile(nameID)) {
                    listGame.updateListGameToFile(nameID + ".txt");
                    System.out.println("List Game sau khi xóa:");
                    listGame.printAllGameSaved();
                    break;
                } else {
                    System.out.println("Không tìm thấy NameID(Tài khoản) của User cần xóa hết Game trong danh sách đã lưu");
                }
            } while (true);
        }
        listGame.removeAllGame();
    }

    //Chức năng phần ShopItem

}
