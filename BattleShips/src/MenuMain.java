import java.util.Scanner;

public class MenuMain implements FileName {
    Scanner sc = new Scanner(System.in);

    //Phần game
    ListGameBattleShips listSave = new ListGameBattleShips();
    ListGameBattleShips listSave_Still = new ListGameBattleShips();
    ListGameBattleShips listSaveGridPlayer = new ListGameBattleShips();
    ListGameBattleShips listSaveGridComputer = new ListGameBattleShips();
    ListGameBattleShips listSaveGridMap = new ListGameBattleShips();
    BattleShips bs = new BattleShips_TypeOne();

    //Phần người dùng
    User user = new User();
    ListUser listUser = new ListUser();

    //Phần shop
    ItemShop itemShop = new ItemShop();
    ListItemShop listItemShop = new ListItemShop();

    //Thông báo không hợp lệ
    private void notificationMess() {
        System.out.println("Lựa chọn không hợp lệ!");
        System.out.println("Mời bạn nhập lại");
    }

    public void menuLogin() {
        int playerChoice;
        do {
            System.out.println("====> CLIENT <====");
            System.out.println("1.Đăng nhập\n2.Đăng kí\n3.Quên mật khẩu\n0.Thoát");

            //Cập nhật list User và list Shop
            listUser.updateInformationListFromFile(fileUser);
            listItemShop.updateInformationListFromFile(fileShop);

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1: //Đăng nhập
                    login();
                    break;
                case 2: //Đăng kí
                    listUser.registerUser(user, itemShop, listItemShop);
                    System.out.println("----> **** <----");
                    user = new User();
                    itemShop = new ItemShop();
                    break;
                case 3: //Quên mật khẩu
                    listUser.forgetPasswordID(user);
                    System.out.println("----> **** <----");
                    user = new User();
                    break;
                case 110604://Chức năng admin

                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void login() {
        for (int i = 0; i <= 2; i++) {
            if (i == 2) {
                System.out.println("Bạn đã nhập sai quá 3 lần!");
                listUser.askForRegister(user, itemShop, listItemShop);
                user = new User();
                itemShop = new ItemShop();
                System.out.println("THÔNG BÁO THOÁT RA CLIENT!");
                System.out.println("----> **** <----");
                break;
            }
            System.out.println("====> LOGIN <====");
            user.inputNameID();
            if (user.getNameID().equals("0")) break;
            user.inputPassword();
            //Nhập 0 để thoát
            if (listUser.checkInputUserFromList(user.getNameID(), user.getPasswordID())) {
                i = 0;
                break;
            }
            else {
                System.out.println("Bạn đã nhập sai tài khoản (mật khẩu)!");
                System.out.println("Mời bạn nhập lại (Bấm 0 để thoát)");
                System.out.println("----> **** <----");
            }
        }
        System.out.println("----> **** <----");
        if (listUser.checkInputUserFromList(user.getNameID(), user.getPasswordID())) {
            listUser.updateInformationUserFromList(user);
            menuGame();
        }
        user = new User();
        itemShop = new ItemShop();
        listSave = new ListGameBattleShips();
        listSave_Still = new ListGameBattleShips();
        listSaveGridPlayer = new ListGameBattleShips();
        listSaveGridComputer = new ListGameBattleShips();
        listSaveGridMap = new ListGameBattleShips();
    }

    public void menuGame() {
        int playerChoice;
        System.out.println("**** Chào mừng đến với Battle Ships Game ****");

        do {
            System.out.println("====> MENU <====");
            System.out.println("1.Thông tin tài khoản");
            System.out.println("2.Chơi mới");
            System.out.println("3.Chơi tiếp");
            System.out.println("4.Thông tin chi tiết");
            System.out.println("5.Bảng xếp hạng");
            System.out.println("6.Cửa hàng");
            System.out.println("7.Nạp");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0: //Thoát
                    break;
                case 1: //Thông tin tài khoản
                    menuInformationNameID();
                    break;
                case 2: //Chơi
                    menuGameTypeOne();
                    break;
                case 3: //Chơi tiếp
                    menuGameTypeOne_Still();
                    break;
                case 4: //Thông tin chi tiết
                    bs.detailInformationSymbols();
                    System.out.println("----> **** <----");
                    break;
                case 5: //Bảng xếp hạng
                    listUser.showListRankUser(fileUser);
                    System.out.println("----> **** <----");
                    break;
                case 6: //Cửa hàng
                    menuShop();
                    System.out.println("----> **** <----");
                    break;
                case 7: //Nạp
                    menuBank();
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuBank() {
        int playerChoice;
        double temp;
        do {
            System.out.println("====> BANK <====");
            System.out.println("1.Nạp Gold");
            System.out.println("2.Nạp Diamond");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0: //Thoát
                    break;
                case 1:
                    System.out.println("Nhập số Gold: ");
                    temp = Double.parseDouble(sc.nextLine());
                    user.setGold(temp);
                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    System.out.println("----> **** <----");
                    break;
                case 2:
                    System.out.println("Nhập số Diamond: ");
                    temp = Double.parseDouble(sc.nextLine());
                    user.setDiamond(temp);
                    listUser.setUserDiamondByNameIDToFile(fileUser, user.getNameID(), user.getDiamond());
                    System.out.println("----> **** <----");
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }
    public void menuGameTypeOne() {
        int playerChoice;

        do {
            System.out.println("====> GAME <====");
            System.out.println("1.Chơi");
            System.out.println("2.In các game đã lưu");
            System.out.println("3.Xóa game đã lưu");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    bs.choiceLevelGameTypeOne(user, listSave, listSaveGridPlayer, listSaveGridComputer, listSaveGridMap, listUser, listItemShop);
                    bs = new BattleShips_TypeOne();
                    break;
                case 2: //In các game đã lưu
                    listSave.printAllListGameSavedByNameID(user.getNameID());
                    System.out.println("----> **** <----");
                    break;
                case 3: //Xóa game
                    listSave.askForRemoveGameDone(user.getNameID());
                    System.out.println("----> **** <----");
                    break;
                default:
                    notificationMess();//Done!
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuGameTypeOne_Still() {
        int playerChoice;

        do {
            System.out.println("====> GAME STILL PLAYING <====");
            System.out.println("1.In các game đang dang dở đã lưu");
            System.out.println("2.Xóa game đã lưu");
            System.out.println("3.Chọn game đã lưu để chơi tiếp theo STT trong danh sách!");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    listSave_Still.printAllListGame_StillSavedByNameID(user.getNameID());
                    System.out.println("----> **** <----");
                    break;
                case 2:
                    listSave_Still.askForRemoveGameStill(user.getNameID());
                    System.out.println("----> **** <----");
                    break;
                case 3:
                    bs.startBattleStill(user, listSave, listSaveGridPlayer, listSaveGridComputer, listSaveGridMap, listUser);
                    /*listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    listUser.updateInformationUserFromList(user, listItemShop);*/
                    System.out.println("----> **** <----");
                    bs = new BattleShips_TypeOne();
                    break;
                default:
                    notificationMess();//Done!
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuInformationNameID() {
        int choice1;
        do {
            user.printInformationUser(listItemShop);
            choice1 = Integer.parseInt(sc.nextLine());
            switch (choice1) {
                case 0:
                    break;
                case 1: //Đổi mật khẩu
                    listUser.changePasswordID(user);
                    System.out.println("----> **** <----");
                    break;
                case 2: //Đặt câu hỏi bảo mật
                    listUser.setSecretQuestion(user);
                    System.out.println("----> **** <----");
                    break;
                default:
                    notificationMess();//Done!
                    System.out.println("----> **** <----");
                    break;
            }
        } while (choice1 != 0);
        System.out.println("----> **** <----");
    }

    public void menuShop() {
        int playerChoice;
        do {
            System.out.println("====> SHOP <====");
            System.out.println("1.Mua giới hạn lưu game");
            System.out.println("2.Mua biệt hiệu");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0: //Thoát
                    break;
                case 1:
                    listItemShop.askForBuySavedGameLimited(user, listUser);
//                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
//                    listUser.setUserDiamondByNameIDToFile(fileUser, user.getNameID(), user.getDiamond());
//                    listUser.updateInformationUserFromList(user, listItemShop);
                    System.out.println("----> **** <----");
                    break;
                case 2:
                    listItemShop.askForBuyNickName(user, listUser);
//                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
//                    listUser.setUserDiamondByNameIDToFile(fileUser, user.getNameID(), user.getDiamond());
//                    listUser.setNickNameByNameIDToFile(fileUser, user.getNameID(), user.getNickName());
//                    listUser.updateInformationUserFromList(user, listItemShop);
                    System.out.println("----> **** <----");
                    break;
                default:
                    notificationMess();
                    System.out.println("----> **** <----");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

}
