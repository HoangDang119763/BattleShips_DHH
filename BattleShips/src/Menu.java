import java.io.File;
import java.util.Scanner;

public class Menu implements FileName {
    Scanner sc = new Scanner(System.in);

    //Phần game
    ListGameBattleShips listGame = new ListGameBattleShips();
    ListGameBattleShips listGame2 = new ListGameBattleShips();
    ListGameBattleShips listGame3 = new ListGameBattleShips();
    BattleShips bs = new BattleShips_TypeOne();

    //Phần người dùng
    User user = new User();
    ListUser listUser = new ListUser();

    //In thông tin kí hiệu quy ước
    private void detailInformationSymbols() {
        System.out.println("Các kí hiệu quy ước");
        System.out.println("Kí hiệu tượng trưng cho Player: P");
        System.out.println("Kí hiệu tượng trưng cho Computer: C");
        System.out.println("Kí hiệu PLayer tiêu diệt được Computer: D");
        System.out.println("Kí hiệu PLayer bị tiêu diệt bởi Computer: d");
        System.out.println("Kí hiệu PLayer đánh hụt: -");
        System.out.println("Kí hiệu PLayer đánh nhầm: x");
        System.out.println("Kí hiệu PLayer tiêu diệt được Computer tại vị trí trùng nhau và chưa bị tiêu diệt: L");
        System.out.println("Kí hiệu PLayer tiêu diệt được Computer tại vị trí trùng nhau và đã bị tiêu diệt: l");
        System.out.println("Kí hiệu cả Player và Computer đều bắn được điểm trùng: S");
    }

    //Thông báo không hợp lệ
    private void notificationMess() {
        System.out.println("Lựa chọn không hợp lệ!");
        System.out.println("Mời bạn nhập lại");
    }

    //Thông báo không có User hợp lệ
    private void notificationUser() {
        System.out.println("Bạn đã nhập sai tài khoản (mật khẩu)!");
        System.out.println("Mời bạn nhập lại");
    }

    //Kiểm tra file tồn tại không
    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public void askForSave(BattleShips bs) {
        int playerChoice;
        System.out.println("Bạn có muốn lưu không ?");
        System.out.println("1.Có\t2.Không");

        do {
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    listGame.removeAllGame(); //Xóa để tránh bị trùng
                    //B1: Kiểm trả file người chơi đó đã được tạo chưa, nếu tạo rồi thì cập nhật lên ArrayList hiện thành
                    if (fileExist(user.getNameID() + ".txt"))
                        listGame.updateListGameFromFile(user.getNameID() + ".txt");
                    //B2: Thêm game vào ArrayList hiện thành
                    listGame.addGame(bs.gridPlayer);
                    //B3: Lưu lên file
                    listGame.updateListGameToFile(user.getNameID() + ".txt");
                    //B4: Xóa ArrayList hiện thành để đảm bảo k lỗi
                    listGame.removeAllGame();
                    break;
                case 2:
                    break;
                default:
                    notificationMess();
                    break;
            }
        } while (playerChoice != 1 && playerChoice != 2);
        System.out.println();
    }

    public void askForRemove() {
        int playerChoice;


        do {
            System.out.println("Mời bạn chọn kiểu xóa:");
            System.out.println("1.Xóa game theo STT\t2.Xóa hết!\t0.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    int choice;
                    listGame.removeAllGame();
                    if (fileExist(user.getNameID() + ".txt"))
                        listGame.updateListGameFromFile(user.getNameID() + ".txt");
                    if (listGame.numGame() <= 0) System.out.println("Bạn chưa lưu Game nào!");
                    else {
                        System.out.println("Các Game bạn đã lưu:");
                        listGame.printAllGameSaved();
                        do {
                            System.out.println("Mời bạn nhập STT của game cần xóa (Bấm 0 để thoát!)");
                            choice = Integer.parseInt(sc.nextLine());
                            if (choice == 0) break;
                            else if (choice >= 1 || choice <= listGame.numGame()) {
                                listGame.removeSTT(choice);
                                System.out.println("List Game sau khi xóa:");
                                listGame.printAllGameSaved();
                                listGame.updateListGameToFile(user.getNameID() + ".txt");
                                break;
                            } else {
                                notificationMess();
                            }
                        } while (true);
                    }
                    listGame.removeAllGame();
                    break;
                case 2:
                    listGame.removeAllGame();
                    listGame.updateListGameToFile(user.getNameID() + ".txt");
                    System.out.println("List Game sau khi xóa:");
                    listGame.printAllGameSaved();
                    listGame.removeAllGame();
                    break;
                case 0:
                    break;
                default:
                    notificationMess();
                    break;
            }
        } while (playerChoice != 0);
        System.out.println();
    }

    public void askForRegister() {
        int playerChoice;
        System.out.println("Bạn có muốn tạo tài khoản mới?");
        do {
            System.out.println("1.Có\n2.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                register();
                break;
            } else if (playerChoice == 2) break;
            else {
                notificationMess();
            }
        } while (true); //Vì chỉ muốn trả lời 1 trong 2
        System.out.println();
    }

    public void playerChoice() {
        int playerChoice;
        System.out.println("**** Chào mừng đến với Battle Ships Game ****");

        do {
            menu();
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    user.printInformationUser();
                    int choice1;
                    do {
                        user.printInformationUser();
                        choice1 = Integer.parseInt(sc.nextLine());
                        switch (choice1) {
                            case 0:
                                break;
                            case 1:
                                changePasswordID();
                                break;
                            case 2:
                                if (!listUser.getSecretQuestionFromList(user.getNameID()).equals("null"))
                                {
                                    System.out.println("Đã cài câu hỏi bảo mật!");
                                } else {
                                    System.out.println("====> Secret QUESTION <====");
                                    System.out.println(questionOne + " (Bấm 0 để thoát)");
                                    String temp = sc.nextLine();
                                    if (temp.equals("0")) break;
                                    listUser.setSecretQuestionByNameIDToFile(user.getNameID(), temp);
                                    listUser.updateListUserToFile(fileUser);
                                    System.out.println("Đặt câu hỏi bảo mật thành công !!!");
                                }
                                break;
                        }
                    } while (choice1 != 0);
                    break;
                case 2:
                    System.out.println("Mời bạn chon độ khó: ");
                    System.out.println("1.Dễ\t2.Trung bình\t0.Thoát");
                    int choice;
                    do {
                        choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 0:
                                break;
                            case 1:
                                bs.setLevelGame(1);
                                startBattleShips_TypeOne();//Done!
                                break;
                            case 2:
                                bs.setLevelGame(2);
                                startBattleShips_TypeOne();//Done!
                                break;
                            default:
                                notificationMess();//Done!
                                break;
                        }
                    } while (choice != 0);
                    break;
                case 3:
                    playerChoiceForBattleShips_Still();
                    break;
                case 4:
                    detailInformationSymbols();//Done!
                    break;
                case 5:
                    printListGame();//Done!
                    break;
                case 6://Xóa game
                    //Đang phát triển
                    askForRemove();
                    break;
                default:
                    notificationMess();//Done!
                    break;
            }
        } while (playerChoice != 0);
        System.out.println();
    }

    public void startBattleShips_TypeOne() {
        bs.inputData();
        //B1: Tạo map
        bs.createOceanMap();
        //B2. Player đặt thuyền
        bs.deployPlayerShips();
        //B3: Computer đặt thuyền
        bs.deployComputerShips();
        //B4: Chiến
        do {
            //bs.Battle();

            bs.playerTurn();
            if (bs.getPlayerShips() != -1) {
                //Kiểm tra nếu nhập
                if (bs.getComputerShips() > 0) bs.computerTurn();
                System.out.println();
                System.out.println("Số thuyền của bạn: " + bs.getPlayerShips() + " | Số thuyền của Computer: " + bs.getComputerShips());
                System.out.println();
            } else if (bs.getPlayerShips() == -1) {
                listGame.removeAllGame(); //Xóa để tránh bị trùng
                listGame2.removeAllGame(); //Xóa để tránh bị trùng
                listGame3.removeAllGame(); //Xóa để tránh bị trùng
                //B1: Kiểm trả file người chơi đó đã được tạo chưa, nếu tạo rồi thì cập nhật lên ArrayList hiện thành
                //GridPlayer
                if (fileExist(user.getNameID() + "gridplayer.txt"))
                    listGame.updateListGameFromFile(user.getNameID() + "gridplayer.txt");
                if (fileExist(user.getNameID() + "gridcomputer.txt"))
                    listGame2.updateListGameFromFile(user.getNameID() + "gridcomputer.txt");
                if (fileExist(user.getNameID() + "gridmap.txt"))
                    listGame3.updateListGameFromFile(user.getNameID() + "gridmap.txt");
                //B2: Thêm game vào ArrayList hiện thành
                listGame.addGame(bs.gridPlayer);
                listGame2.addGame(bs.gridComputer);
                listGame3.addGame(bs.gridMap);
                //B3: Lưu lên file
                listGame.updateListGameToFile(user.getNameID() + "gridplayer.txt");
                listGame2.updateListGameToFile(user.getNameID() + "gridcomputer.txt");
                listGame3.updateListGameToFile(user.getNameID() + "gridmap.txt");
                //B4: Xóa ArrayList hiện thành để đảm bảo k lỗi
                listGame.removeAllGame();
                listGame2.removeAllGame();
                listGame2.removeAllGame();
            }
        } while (bs.getPlayerShips() != 0 && bs.getComputerShips() != 0);
        //B5: Kết thúc
        if (bs.getPlayerShips() != -1) {
            bs.gameOver();
            askForSave(bs);
        }
        bs = new BattleShips_TypeOne();
    }

    public void loginUser() {
        int playerChoice;
        do {
            System.out.println("====> CLIENT <====");
            System.out.println("1.Đăng nhập\n2.Đăng kí\n3.Quên MK\n0.Thoát");

            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 0:
                    break;
                case 1:
                    int count = 0;
                    listUser.removeAllUser();
                    //Đăng nhập
                    do {
                        if (count == 3) {
                            System.out.println("Bạn đã nhập sai quá 3 lần!");
                            askForRegister();
                            System.out.println("THÔNG BÁO THOÁT RA CLIENT!");
                            break;
                        }
                        login();
                        if (fileExist(fileUser))
                            listUser.updateListUserFromFile(fileUser);
                        //Nếu kiểm tra trong file có tài khoàn này thì đăng nhập thành công
                        //Nếu flag = true có nghĩa là tải khoàn và mật khẩu đều đúng
                        if (listUser.checkInputUserFromFile(user.getNameID(), user.getPasswordID())) break;
                        else {
                            notificationUser();
                            count++;
                        }
                    } while (true);
                    //Nếu đăng nhập không thành công thì ra màn hình CLIENT
                    if (count == 3) break;

                    //Vô game
                    user.setNamePlayer(listUser.getNamePlayerFromList(user.getNameID()));
                    user.setGold(listUser.getGoldPlayerFromListByNameID(user.getNameID()));
                    user.setDiamond(listUser.getDiamondPlayerFromListByNameID(user.getNameID()));
                    playerChoice();
                    //Khởi tạo lại User mới để đăng nhập tiếp
                    listUser.removeAllUser();
                    user = new User();
                    break;
                case 2:
                    //Đăng kí
                    register();//Done!
                    break;
                case 3:
                    forgetPasswordID();
                    break;
                case 110604://Chức năng admin
                    Console admin = new Console();
                    admin.adminChoice();
                    break;
                default:
                    notificationMess();//Done!
                    break;
            }
        } while (playerChoice != 0);
        System.out.println();
    }

    public void menu() {
        System.out.println("====> MENU <====");
        System.out.println("1.Thông tin tài khoản");
        System.out.println("2.Chơi!");
        System.out.println("3.Chơi tiếp");
        System.out.println("4.Thông tin chi tiết");
        System.out.println("5.In các game đã lưu");
        System.out.println("6.Xóa game đã lưu");
        System.out.println("0.Thoát");
    }

    public void login() {
        System.out.println("====> LOGIN <====");
        user.inputData();
    }

    public void register() {
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

    public void changePasswordID() {
        while (true) {
            System.out.println("====> CHANGE PASSWORD <====");
            System.out.println("Nhập mật khẩu hiện tại");
            String temp1 = sc.nextLine();
            if (temp1.equals("0")) break;
            //Nếu kiểm tra trong file mật khẩu đúng thì cho nhập tiếp mật khẩu mới
            //Nếu flag = true có nghĩa là mật khẩu đúng
            if (listUser.checkPasswordUserByNameIDFromFile(user.getNameID(), temp1)) {
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
                        listUser.setPasswordIDByNameIDToFile(user.getNameID(), temp3);
                        listUser.updateListUserToFile(fileUser);
                        System.out.println("Đổi mật khẩu thành công !!!");
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

    public void forgetPasswordID() {
        listUser.removeAllUser();
        if (fileExist(fileUser)) listUser.updateListUserFromFile(fileUser);
        do {
            System.out.println("====> FORGET PASSWORD <====");
            user.inputNameID();
            if (user.getNameID().equals("0")) break;
            //Nếu tài khoản đúng thì
            if (listUser.checknameIDUserFromFile(user.getNameID())) {
                System.out.println(questionOne + " (Bấm 0 để thoát)");
                String temp = sc.nextLine();
                if (temp.equals("0")) break;
                //Nếu nhập đúng câu trả lời
                if (temp.equals(listUser.getSecretQuestionFromList(user.getNameID()))) {
                    changePasswordID();
                }
                break;
            }
            System.out.println("Tài khoản không tồn tại hoặc nhập sai\nMời bạn nhập lại ! (Bấm 0 để thoát)");
        } while (true);
        listUser.removeAllUser();
        user = new User();
    }

    public void printListGame() {
        listGame.removeAllGame();
        if (fileExist(user.getNameID() + ".txt")) {
            listGame.updateListGameFromFile(user.getNameID() + ".txt");
            //Nếu có file thì cập nhật rồi in
            listGame.printAllGameSaved();
        } else System.out.println("Bạn chưa lưu game nào!");
        listGame.removeAllGame();
    }

    //Chức năng chơi tiếp
    public void playerChoiceForBattleShips_Still() {
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
                    printListGame_Still();
                    break;
                case 2:
                    askForRemove_Still();
                    break;
                case 3:
                    startBattleShips_TypeOne_Still();
                    break;
                default:
                    notificationMess();//Done!
                    break;
            }
        } while (playerChoice != 0);
        System.out.println();
    }

    public void startBattleShips_TypeOne_Still() {
        int choice;
        listGame.removeAllGame();
        listGame2.removeAllGame();
        listGame3.removeAllGame();
        if (fileExist(user.getNameID() + "gridplayer.txt"))
            listGame.updateListGameFromFile(user.getNameID() + "gridplayer.txt");
        if (fileExist(user.getNameID() + "gridcomputer.txt"))
            listGame2.updateListGameFromFile(user.getNameID() + "gridcomputer.txt");
        if (fileExist(user.getNameID() + "gridmap.txt"))
            listGame3.updateListGameFromFile(user.getNameID() + "gridmap.txt");
        if (listGame.numGame() <= 0) System.out.println("Bạn chưa lưu Game nào!");
        else {
            do {
                System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!))");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 0) break;
                else if (choice >= 1 && choice <= listGame.numGame()) {
                    bs.gridPlayer = new Map(listGame3.getValueSaveNum(choice, 3), listGame3.getValueSaveNum(choice, 4));
                    bs.gridComputer = new Map(listGame3.getValueSaveNum(choice, 3), listGame3.getValueSaveNum(choice, 4));
                    bs.gridMap = new Map(listGame3.getValueSaveNum(choice, 3), listGame3.getValueSaveNum(choice, 4));

                    bs.gridPlayer = listGame.getGameSTT(choice);
                    bs.gridComputer = listGame2.getGameSTT(choice);
                    bs.gridMap = listGame3.getGameSTT(choice);

                    bs.setNumRows(listGame3.getValueSaveNum(choice, 3));
                    bs.setNumColums(listGame3.getValueSaveNum(choice, 4));
                    bs.setPlayerShips(listGame3.getValueSaveNum(choice, 0));
                    bs.setComputerShips(listGame3.getValueSaveNum(choice, 1));
                    bs.setLevelGame(listGame3.getValueSaveNum(choice, 2));

                    listGame.removeSTT(choice);
                    listGame2.removeSTT(choice);
                    listGame3.removeSTT(choice);
                    listGame.updateListGameToFile(user.getNameID() + "gridplayer.txt");
                    listGame2.updateListGameToFile(user.getNameID() + "gridcomputer.txt");
                    listGame3.updateListGameToFile(user.getNameID() + "gridmap.txt");

                    do {
                        //bs.Battle();

                        bs.playerTurn();
                        if (bs.getPlayerShips() != -1) {
                            //Kiểm tra nếu nhập
                            if (bs.getComputerShips() > 0) bs.computerTurn();
                            System.out.println();
                            System.out.println("Số thuyền của bạn: " + bs.getPlayerShips() + " | Số thuyền của Computer: " + bs.getComputerShips());
                            System.out.println();
                        } else if (bs.getPlayerShips() == -1) {
                            listGame.removeAllGame(); //Xóa để tránh bị trùng
                            listGame2.removeAllGame(); //Xóa để tránh bị trùng
                            listGame3.removeAllGame(); //Xóa để tránh bị trùng
                            //B1: Kiểm trả file người chơi đó đã được tạo chưa, nếu tạo rồi thì cập nhật lên ArrayList hiện thành
                            //GridPlayer
                            if (fileExist(user.getNameID() + "gridplayer.txt"))
                                listGame.updateListGameFromFile(user.getNameID() + "gridplayer.txt");
                            if (fileExist(user.getNameID() + "gridcomputer.txt"))
                                listGame2.updateListGameFromFile(user.getNameID() + "gridcomputer.txt");
                            if (fileExist(user.getNameID() + "gridmap.txt"))
                                listGame3.updateListGameFromFile(user.getNameID() + "gridmap.txt");
                            //B2: Thêm game vào ArrayList hiện thành
                            listGame.addGame(bs.gridPlayer);
                            listGame2.addGame(bs.gridComputer);
                            listGame3.addGame(bs.gridMap);
                            //B3: Lưu lên file
                            listGame.updateListGameToFile(user.getNameID() + "gridplayer.txt");
                            listGame2.updateListGameToFile(user.getNameID() + "gridcomputer.txt");
                            listGame3.updateListGameToFile(user.getNameID() + "gridmap.txt");
                            //B4: Xóa ArrayList hiện thành để đảm bảo k lỗi
                            listGame.removeAllGame();
                            listGame2.removeAllGame();
                            listGame2.removeAllGame();
                        }
                    } while (bs.getPlayerShips() != 0 && bs.getComputerShips() != 0);
                    //B5: Kết thúc
                    if (bs.getPlayerShips() != -1) {
                        bs.gameOver();
                        askForSave(bs);
                    }
                    bs = new BattleShips_TypeOne();
                    break;
                } else {
                    notificationMess();
                }
            } while (true);
        }
        listGame.removeAllGame();
        listGame2.removeAllGame();
        listGame3.removeAllGame();
    }

    public void printListGame_Still() {
        listGame.removeAllGame();
        if (fileExist(user.getNameID() + "gridplayer.txt")) {
            listGame.updateListGameFromFile(user.getNameID() + "gridplayer.txt");
            listGame.printAllGameSaved();
        } else System.out.println("Bạn chưa lưu game nào!");
        listGame.removeAllGame();
    }

    public void askForRemove_Still() {
        int playerChoice;


        do {
            System.out.println("Mời bạn chọn kiểu xóa:");
            System.out.println("1.Xóa game theo STT\t2.Xóa hết!\t0.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    int choice;
                    listGame.removeAllGame();
                    listGame2.removeAllGame();
                    listGame3.removeAllGame();
                    if (fileExist(user.getNameID() + "gridplayer.txt"))
                        listGame.updateListGameFromFile(user.getNameID() + "gridplayer.txt");
                    if (fileExist(user.getNameID() + "gridcomputer.txt"))
                        listGame2.updateListGameFromFile(user.getNameID() + "gridcomputer.txt");
                    if (fileExist(user.getNameID() + "gridmap.txt"))
                        listGame3.updateListGameFromFile(user.getNameID() + "gridmap.txt");
                    if (listGame.numGame() <= 0) System.out.println("Bạn chưa lưu Game nào!");
                    else {
                        do {
                            System.out.println("Mời bạn nhập STT của game cần xóa (Bấm 0 để thoát!)");
                            choice = Integer.parseInt(sc.nextLine());
                            if (choice == 0) break;
                            else if (choice >= 1 || choice <= listGame.numGame()) {
                                listGame.removeSTT(choice);
                                listGame2.removeSTT(choice);
                                listGame3.removeSTT(choice);
                                System.out.println("List Game sau khi xóa:");
                                listGame.printAllGameSaved();
                                listGame.updateListGameToFile(user.getNameID() + "gridplayer.txt");
                                listGame2.updateListGameToFile(user.getNameID() + "gridcomputer.txt");
                                listGame3.updateListGameToFile(user.getNameID() + "gridmap.txt");
                                break;
                            } else {
                                notificationMess();
                            }
                        } while (true);
                    }
                    listGame.removeAllGame();
                    listGame2.removeAllGame();
                    listGame3.removeAllGame();
                    break;
                case 2:
                    listGame.removeAllGame();
                    listGame2.removeAllGame();
                    listGame3.removeAllGame();
                    System.out.println("List Game sau khi xóa:");
                    listGame.printAllGameSaved();
                    listGame.updateListGameToFile(user.getNameID() + "gridplayer.txt");
                    listGame2.updateListGameToFile(user.getNameID() + "gridcomputer.txt");
                    listGame3.updateListGameToFile(user.getNameID() + "gridmap.txt");
                    listGame.removeAllGame();
                    listGame2.removeAllGame();
                    listGame3.removeAllGame();
                    break;
                case 0:
                    break;
                default:
                    notificationMess();
                    break;
            }
        } while (playerChoice != 0);
        System.out.println();
    }

    //*************************Tà đạo chức năng chơi tiếp

}
