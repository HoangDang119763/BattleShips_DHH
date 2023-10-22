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

    //Thông báo không hợp lệ
    private void notificationMess() {
        System.out.println("Lựa chọn không hợp lệ!");
        System.out.println("Mời bạn nhập lại");
    }

    public void menuLogin() {
        int playerChoice;
        do {
            System.out.println("====> CLIENT <====");
            System.out.println("1.Đăng nhập\n2.Đăng kí\n3.Quên MK\n0.Thoát");

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1: //Đăng nhập
                    login();
                    user = new User();
                    break;
                case 2: //Đăng kí
                    listUser.registerUser(user, fileUser);
                    user = new User();
                    break;
                case 3: //Quên mật khẩu
                    listUser.forgetPasswordID(user, fileUser);
                    user = new User();
                    break;
                case 110604://Chức năng admin

                    break;
                default:
                    notificationMess();
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void login() {
        for (int i = 0; i <= 2; i++) {
            if (i == 2) {
                System.out.println("Bạn đã nhập sai quá 3 lần!");
                listUser.askForRegister(user, fileUser);
                user = new User();
                System.out.println("THÔNG BÁO THOÁT RA CLIENT!");
                break;
            }
            listUser.loginUser(user, fileUser);
            //Nhập 0 để thoát
            if (listUser.checkInputUserFromList(user.getNameID(), user.getPasswordID())) break;
            else {
                System.out.println("Bạn đã nhập sai tài khoản (mật khẩu)!");
                System.out.println("Mời bạn nhập lại (Bấm 0 để thoát)");
            }
        }
        listUser.updateInformationUserFromList(user);
        menuGame();
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
                    break;
                default:
                    notificationMess();
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
                    bs.choiceLevelGameTypeOne();
                    bs.startBattle(user.getNameID(), listSave, listSaveGridPlayer, listSaveGridComputer, listSaveGridMap);
                    bs = new BattleShips_TypeOne();
                    break;
                case 2: //In các game đã lưu
                    listSave.printAllListGameSavedByNameID(user.getNameID());
                    break;
                case 3: //Xóa game
                    listSave.askForRemoveGameDone(user.getNameID());
                    break;
                default:
                    notificationMess();//Done!
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
                    break;
                case 2:
                    listSave_Still.askForRemoveGameStill(user.getNameID());
                    break;
                case 3:
                    bs.startBattleStill(user.getNameID(), listSave, listSaveGridPlayer, listSaveGridComputer, listSaveGridMap);
                    bs = new BattleShips_TypeOne();
                    break;
                default:
                    notificationMess();//Done!
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void menuInformationNameID() {
        int choice1;
        do {
            user.printInformationUser();
            choice1 = Integer.parseInt(sc.nextLine());
            switch (choice1) {
                case 0:
                    break;
                case 1: //Đổi mật khẩu
                    listUser.changePasswordID(user, fileUser);
                    break;
                case 2: //Đặt câu hỏi bảo mật
                    listUser.setSecretQuestion(user, fileUser);
                    break;
            }
        } while (choice1 != 0);
    }
}
