import java.io.File;
import java.util.Scanner;

public abstract class BattleShips implements FileName {
    Scanner sc = new Scanner(System.in);
    private int numRows;
    private int numColums;
    private int playerShips;
    private int computerShips;

    public boolean status;

    protected Map gridPlayer;
    protected Map gridComputer;
    protected Map gridMap;

    private int levelGame;

    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    //Constructor
    public BattleShips() {
    }

    public BattleShips(int numRows, int numColums, int playerShips, int computerShips, int levelGame) {
        this.numRows = numRows;
        this.numColums = numColums;
        this.playerShips = playerShips;
        this.computerShips = computerShips;
        this.levelGame = levelGame;
        //Khởi tạo mảng
    }

    //Getter và Setter
    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColums() {
        return numColums;
    }

    public void setNumColums(int numColums) {
        this.numColums = numColums;
    }

    public int getPlayerShips() {
        return playerShips;
    }

    public void setPlayerShips(int playerShips) {
        this.playerShips = playerShips;
    }

    public int getComputerShips() {
        return computerShips;
    }

    public void setComputerShips(int computerShips) {
        this.computerShips = computerShips;
    }

    public int getLevelGame() {
        return levelGame;
    }

    public void setLevelGame(int levelGame) {
        this.levelGame = levelGame;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Cấu trúc của game BattleShips
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mời bạn nhập số hàng(Row): ");
        setNumRows(Integer.parseInt(sc.nextLine()));
        System.out.print("Mời bạn nhập số cột(Colum): ");
        setNumColums(Integer.parseInt(sc.nextLine()));
        System.out.print("Mời bạn nhập số thuyền của BẠN: ");
        setPlayerShips(Integer.parseInt(sc.nextLine()));
        System.out.print("Mời bạn nhập số thuyền của COMPUTER: ");
        setComputerShips(Integer.parseInt(sc.nextLine()));
        this.gridPlayer = new Map(getNumRows(), getNumColums());
        this.gridComputer = new Map(getNumRows(), getNumColums());
        this.gridMap = new Map(getNumRows(), getNumColums());
    }

    public void createOceanMap() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColums; j++) {
                gridPlayer.setValue(i, j, " ");
                gridComputer.setValue(i, j, " ");
                gridMap.setValue(i, j, " ");
            }
        }
        //In ra xem thử map vừa tạo
        gridMap.printOceanMap();
        System.out.println();
    }

    public void deployPlayerShips() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nTriển khai thuyền của bạn:");
        //Deploying five ships for player
        for (int i = 1; i <= getPlayerShips(); ) {
            System.out.print("Nhập tọa độ X cho thuyền thứ " + i + " : ");
            int x = sc.nextInt();
            System.out.print("Nhập tọa độ Y cho thuyền thứ " + i + " : ");
            int y = sc.nextInt();
            if (checkGrid(x, y) && (gridPlayer.getValue(x, y).equals(" "))) {
                gridMap.setValue(x, y, "P"); //PLayer
                gridPlayer.setValue(x, y, "P");
                i++;
            } else if (checkGrid(x, y) && gridPlayer.getValue(x, y).equals("P"))
                System.out.println("Bạn không thể đặt nhiều hơn một thuyền tại cùng một vị trí!");
            else if (!checkGrid(x, y))
                System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + getNumRows() + " và " + getNumColums());
        }
        //In map của Player
        gridPlayer.printOceanMap();
    }

    public abstract void deployComputerShips();

    public abstract void playerTurn();

    public abstract void computerTurn();

//    public void Battle() {
//        playerTurn();
//        if (getPlayerShips() != -1) {
//            //Kiểm tra nếu nhập
//            if (getComputerShips() > 0) computerTurn();
//            System.out.println();
//            System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
//            System.out.println();
//        }
//    }

    public void gameOver() {
        System.out.println("====> Kết quả <====");
        System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
        if (getPlayerShips() > 0 && getComputerShips() <= 0)
            System.out.println("Yeah! Bạn đã THẮNG Game Battle Ships!!! :>");
        else
            System.out.println("Chia buồn! Bạn đã THUA Game Battle Ships!!! :<");
        //In map tổng quát
        gridMap.printOceanMap();
    }

    public void startBattle(User user, ListGameBattleShips list1, ListGameBattleShips list2, ListGameBattleShips list3, ListGameBattleShips list4, ListUser listUser) {
        setStatus(true);
        inputData();
        //B1: Tạo map
        createOceanMap();
        //B2. Player đặt thuyền
        deployPlayerShips();
        //B3: Computer đặt thuyền
        deployComputerShips();
        //B4: Chiến
        do {
            //bs.Battle();
            playerTurn();
            if (status == false) {
                list2.addGameByNameIDToFile(user.getNameID(), "gridplayer.txt", gridPlayer);
                list3.addGameByNameIDToFile(user.getNameID(), "gridcomputer.txt", gridComputer);
                list4.addGameByNameIDToFile(user.getNameID(), "gridmap.txt", gridMap);
                break;
            }
            //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
            if (getComputerShips() > 0) {
                computerTurn();
                System.out.println();
                System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
                System.out.println();
            }
        } while (getPlayerShips() != 0 && getComputerShips() != 0 && status == true);
        //B5: Kết thúc
        if (status == true) {
            gameOver();
            if (getComputerShips() == 0) {
                user.setGold(user.getGold() + 150);
                listUser.setNumGameWinByNameIDToFile(FileName.fileUser, user.getNameID(), user.getNumGameWin() + 1);
            }
            list1.askForSaveGameDone(user, gridPlayer);
        }
    }

    public void startBattle(User user, ListGameBattleShips list1, ListGameBattleShips list2, ListGameBattleShips list3, ListGameBattleShips list4, ListUser listUser, ListItemShop listItemShop) {
        setStatus(true);
        inputData();
        //B1: Tạo map
        createOceanMap();
        //B2. Player đặt thuyền
        deployPlayerShips();
        //B3: Computer đặt thuyền
        deployComputerShips();
        //B4: Chiến
        do {
            //bs.Battle();
            playerTurn();
            if (status == false) {
                list2.addGameByNameIDToFile(user.getNameID(), "gridplayer.txt", gridPlayer);
                list3.addGameByNameIDToFile(user.getNameID(), "gridcomputer.txt", gridComputer);
                list4.addGameByNameIDToFile(user.getNameID(), "gridmap.txt", gridMap);
                break;
            }
            //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
            if (getComputerShips() > 0) {
                computerTurn();
                System.out.println();
                System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
                System.out.println();
            }
        } while (getPlayerShips() != 0 && getComputerShips() != 0 && status == true);
        //B5: Kết thúc
        if (status == true) {
            gameOver();
            if (getComputerShips() == 0) {
                user.setGold(user.getGold() + 150);
                listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                listUser.setNumGameWinByNameIDToFile(FileName.fileUser, user.getNameID(), user.getNumGameWin() + 1);
            }
            listUser.updateInformationUserFromList(user);
            list1.askForSaveGameDone(user, gridPlayer, listItemShop);
        }
    }

    public void startBattleStill(User user, ListGameBattleShips list1, ListGameBattleShips list2, ListGameBattleShips list3, ListGameBattleShips list4, ListUser listUser) {
        boolean status = true;
        int stt;
        //Nếu list gridplayer rỗng thì
        if (list2.checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(user.getNameID() + "gridplayer.txt")) {
                list2.updateListGameFromFile(user.getNameID() + "gridplayer.txt");
                list3.updateListGameFromFile(user.getNameID() + "gridcomputer.txt");
                list4.updateListGameFromFile(user.getNameID() + "gridmap.txt");

                //Nếu cập nhật rồi mà ArrayList vẫn rỗng thì => chưa lưu game nào
                if (list2.checkNull()) System.out.println("Bạn chưa lưu game nào!");
                else {
                    do {
                        //Tức là có dữ liệu
                        System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!))");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;
                        else if (stt >= 1 && stt <= list2.numGame()) {
                            gridPlayer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                            gridComputer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                            gridMap = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));

                            gridPlayer = list2.getGameSTTFromList(stt);
                            gridComputer = list3.getGameSTTFromList(stt);
                            gridMap = list4.getGameSTTFromList(stt);

                            setComputerShips(list2.getValueSaveNumFromListSTTstt(stt, 0));
                            setPlayerShips(list2.getValueSaveNumFromListSTTstt(stt, 1));
                            setLevelGame(list2.getValueSaveNumFromListSTTstt(stt, 2));
                            setNumRows(list2.getValueSaveNumFromListSTTstt(stt, 3));
                            setNumColums(list2.getValueSaveNumFromListSTTstt(stt, 4));

                            list2.removeGameByNameIDSTTToFile(user.getNameID(), "gridplayer.txt", stt);
                            list3.removeGameByNameIDSTTToFile(user.getNameID(), "gridcomputer.txt", stt);
                            list4.removeGameByNameIDSTTToFile(user.getNameID(), "gridmap.txt", stt);

                            gridPlayer.printOceanMap();

                            do {
                                //bs.Battle();
                                playerTurn();
                                if (status == false) {
                                    list2.addGameByNameIDToFile(user.getNameID(), "gridplayer.txt", gridPlayer);
                                    list3.addGameByNameIDToFile(user.getNameID(), "gridcomputer.txt", gridComputer);
                                    list4.addGameByNameIDToFile(user.getNameID(), "gridmap.txt", gridMap);
                                    break;
                                }
                                //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
                                if (getComputerShips() > 0) {
                                    computerTurn();
                                    System.out.println();
                                    System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
                                    System.out.println();
                                }
                            } while (getPlayerShips() != 0 && getComputerShips() != 0 && status == true);
                            //B5: Kết thúc
                            if (status == true) {
                                gameOver();
                                if (getComputerShips() == 0) {
                                    user.setGold(user.getGold() + 150);
                                    listUser.setNumGameWinByNameIDToFile(FileName.fileUser, user.getNameID(), user.getNumGameWin() + 1);
                                }
                                list1.askForSaveGameDone(user, gridPlayer);
                                break;
                            }
                        } else {
                            System.out.println("Số thứ tự không hợp lệ!");
                            System.out.println("Mời bạn nhập lại");
                        }
                    } while (true);
                }
            } else System.out.println("Bạn chưa lưu game nào!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!list2.checkNull() && fileExist(user.getNameID() + "gridplayer.txt")) { //trường hợp là đã gọi hàm in danh sách trước
            do {
                //Tức là có dữ liệu
                System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!))");
                stt = Integer.parseInt(sc.nextLine());
                if (stt == 0) break;
                else if (stt >= 1 && stt <= list2.numGame()) {
                    gridPlayer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                    gridComputer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                    gridMap = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));

                    gridPlayer = list2.getGameSTTFromList(stt);
                    gridComputer = list3.getGameSTTFromList(stt);
                    gridMap = list4.getGameSTTFromList(stt);

                    setComputerShips(list2.getValueSaveNumFromListSTTstt(stt, 0));
                    setPlayerShips(list2.getValueSaveNumFromListSTTstt(stt, 1));
                    setLevelGame(list2.getValueSaveNumFromListSTTstt(stt, 2));
                    setNumRows(list2.getValueSaveNumFromListSTTstt(stt, 3));
                    setNumColums(list2.getValueSaveNumFromListSTTstt(stt, 4));

                    list2.removeGameByNameIDSTTToFile(user.getNameID(), "gridplayer.txt", stt);
                    list3.removeGameByNameIDSTTToFile(user.getNameID(), "gridcomputer.txt", stt);
                    list4.removeGameByNameIDSTTToFile(user.getNameID(), "gridmap.txt", stt);

                    gridPlayer.printOceanMap();

                    do {
                        //bs.Battle();
                        playerTurn();
                        if (status == false) {
                            list2.addGameByNameIDToFile(user.getNameID(), "gridplayer.txt", gridPlayer);
                            list3.addGameByNameIDToFile(user.getNameID(), "gridcomputer.txt", gridComputer);
                            list4.addGameByNameIDToFile(user.getNameID(), "gridmap.txt", gridMap);
                            break;
                        }
                        //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
                        if (getComputerShips() > 0) {
                            computerTurn();
                            System.out.println();
                            System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
                            System.out.println();
                        }
                    } while (getPlayerShips() != 0 && getComputerShips() != 0 && status == true);
                    //B5: Kết thúc
                    if (status == true) {
                        gameOver();
                        if (getComputerShips() == 0) {
                            user.setGold(user.getGold() + 150);
                            listUser.setNumGameWinByNameIDToFile(FileName.fileUser, user.getNameID(), user.getNumGameWin() + 1);
                        }
                        list1.askForSaveGameDone(user, gridPlayer);
                        break;
                    }
                } else {
                    System.out.println("Số thứ tự không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                }
            } while (true);
        }
    }

    public void startBattleStill(User user, ListGameBattleShips list1, ListGameBattleShips list2, ListGameBattleShips list3, ListGameBattleShips list4, ListUser listUser, ListItemShop listItemShop) {
        boolean status = true;
        int stt;
        //Nếu list gridplayer rỗng thì
        if (list2.checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(user.getNameID() + "gridplayer.txt")) {
                list2.updateListGameFromFile(user.getNameID() + "gridplayer.txt");
                list3.updateListGameFromFile(user.getNameID() + "gridcomputer.txt");
                list4.updateListGameFromFile(user.getNameID() + "gridmap.txt");

                //Nếu cập nhật rồi mà ArrayList vẫn rỗng thì => chưa lưu game nào
                if (list2.checkNull()) System.out.println("Bạn chưa lưu game nào!");
                else {
                    do {
                        //Tức là có dữ liệu
                        System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!))");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;
                        else if (stt >= 1 && stt <= list2.numGame()) {
                            gridPlayer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                            gridComputer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                            gridMap = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));

                            gridPlayer = list2.getGameSTTFromList(stt);
                            gridComputer = list3.getGameSTTFromList(stt);
                            gridMap = list4.getGameSTTFromList(stt);

                            setComputerShips(list2.getValueSaveNumFromListSTTstt(stt, 0));
                            setPlayerShips(list2.getValueSaveNumFromListSTTstt(stt, 1));
                            setLevelGame(list2.getValueSaveNumFromListSTTstt(stt, 2));
                            setNumRows(list2.getValueSaveNumFromListSTTstt(stt, 3));
                            setNumColums(list2.getValueSaveNumFromListSTTstt(stt, 4));

                            list2.removeGameByNameIDSTTToFile(user.getNameID(), "gridplayer.txt", stt);
                            list3.removeGameByNameIDSTTToFile(user.getNameID(), "gridcomputer.txt", stt);
                            list4.removeGameByNameIDSTTToFile(user.getNameID(), "gridmap.txt", stt);

                            gridPlayer.printOceanMap();

                            do {
                                //bs.Battle();
                                playerTurn();
                                if (status == false) {
                                    list2.addGameByNameIDToFile(user.getNameID(), "gridplayer.txt", gridPlayer);
                                    list3.addGameByNameIDToFile(user.getNameID(), "gridcomputer.txt", gridComputer);
                                    list4.addGameByNameIDToFile(user.getNameID(), "gridmap.txt", gridMap);
                                    break;
                                }
                                //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
                                if (getComputerShips() > 0) {
                                    computerTurn();
                                    System.out.println();
                                    System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
                                    System.out.println();
                                }
                            } while (getPlayerShips() != 0 && getComputerShips() != 0 && status == true);
                            //B5: Kết thúc
                            if (status == true) {
                                gameOver();
                                if (getComputerShips() == 0) {
                                    user.setGold(user.getGold() + 150);
                                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                                    listUser.setNumGameWinByNameIDToFile(FileName.fileUser, user.getNameID(), user.getNumGameWin() + 1);
                                }
                                list1.askForSaveGameDone(user, gridPlayer, listItemShop);
                                listUser.updateInformationUserFromList(user);
                                break;
                            }
                        } else {
                            System.out.println("Số thứ tự không hợp lệ!");
                            System.out.println("Mời bạn nhập lại");
                        }
                    } while (true);
                }
            } else System.out.println("Bạn chưa lưu game nào!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!list2.checkNull() && fileExist(user.getNameID() + "gridplayer.txt")) { //trường hợp là đã gọi hàm in danh sách trước
            do {
                //Tức là có dữ liệu
                System.out.println("Mời bạn nhập STT của Game muốn chơi tiếp! (Bấm 0 để thoát!))");
                stt = Integer.parseInt(sc.nextLine());
                if (stt == 0) break;
                else if (stt >= 1 && stt <= list2.numGame()) {
                    gridPlayer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                    gridComputer = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));
                    gridMap = new Map(list2.getValueSaveNumFromListSTTstt(stt, 3), list2.getValueSaveNumFromListSTTstt(stt, 4));

                    gridPlayer = list2.getGameSTTFromList(stt);
                    gridComputer = list3.getGameSTTFromList(stt);
                    gridMap = list4.getGameSTTFromList(stt);

                    setComputerShips(list2.getValueSaveNumFromListSTTstt(stt, 0));
                    setPlayerShips(list2.getValueSaveNumFromListSTTstt(stt, 1));
                    setLevelGame(list2.getValueSaveNumFromListSTTstt(stt, 2));
                    setNumRows(list2.getValueSaveNumFromListSTTstt(stt, 3));
                    setNumColums(list2.getValueSaveNumFromListSTTstt(stt, 4));

                    list2.removeGameByNameIDSTTToFile(user.getNameID(), "gridplayer.txt", stt);
                    list3.removeGameByNameIDSTTToFile(user.getNameID(), "gridcomputer.txt", stt);
                    list4.removeGameByNameIDSTTToFile(user.getNameID(), "gridmap.txt", stt);

                    gridPlayer.printOceanMap();

                    do {
                        //bs.Battle();
                        playerTurn();
                        if (status == false) {
                            list2.addGameByNameIDToFile(user.getNameID(), "gridplayer.txt", gridPlayer);
                            list3.addGameByNameIDToFile(user.getNameID(), "gridcomputer.txt", gridComputer);
                            list4.addGameByNameIDToFile(user.getNameID(), "gridmap.txt", gridMap);
                            break;
                        }
                        //Tránh trường hợp người chơi tiêu diệt hết sau đó máy cũng tiêu diệt hết player dẫn đến sai
                        if (getComputerShips() > 0) {
                            computerTurn();
                            System.out.println();
                            System.out.println("Số thuyền của bạn: " + getPlayerShips() + " | Số thuyền của Computer: " + getComputerShips());
                            System.out.println();
                        }
                    } while (getPlayerShips() != 0 && getComputerShips() != 0 && status == true);
                    //B5: Kết thúc
                    if (status == true) {
                        gameOver();
                        if (getComputerShips() == 0) {
                            user.setGold(user.getGold() + 150);
                            listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                            listUser.setNumGameWinByNameIDToFile(FileName.fileUser, user.getNameID(), user.getNumGameWin() + 1);
                        }
                        list1.askForSaveGameDone(user, gridPlayer, listItemShop);
                        listUser.updateInformationUserFromList(user);
                        break;
                    }
                } else {
                    System.out.println("Số thứ tự không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                }
            } while (true);
        }
    }

    public void saveInformationBattle() {
        gridPlayer.setValueSaveNum(0, getPlayerShips());
        gridPlayer.setValueSaveNum(1, getComputerShips());
        gridPlayer.setValueSaveNum(2, getLevelGame());
        gridPlayer.setValueSaveNum(3, getNumRows());
        gridPlayer.setValueSaveNum(4, getNumColums());
    }

    public void choiceLevelGameTypeOne(User user, ListGameBattleShips list1, ListGameBattleShips list2, ListGameBattleShips list3, ListGameBattleShips list4, ListUser listUser) {
        System.out.println("Mời bạn chon độ khó: ");
        System.out.println("1.Dễ\t2.Trung bình\t0.Thoát");
        int choice;
        do {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    break;
                case 1:
                    setLevelGame(1);
                    startBattle(user, list1, list2, list3, list4, listUser);
                    break;
                case 2:
                    setLevelGame(2);
                    startBattle(user, list1, list2, list3, list4, listUser);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 0);
    }

    public void choiceLevelGameTypeOne(User user, ListGameBattleShips list1, ListGameBattleShips list2, ListGameBattleShips list3, ListGameBattleShips list4, ListUser listUser, ListItemShop listItemShop) {
        System.out.println("Mời bạn chon độ khó: ");
        System.out.println("1.Dễ\t2.Trung bình\t0.Thoát");
        int choice;
        do {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0:
                    break;
                case 1:
                    setLevelGame(1);
                    startBattle(user, list1, list2, list3, list4, listUser, listItemShop);
                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    listUser.updateInformationUserFromList(user);
                    break;
                case 2:
                    setLevelGame(2);
                    startBattle(user, list1, list2, list3, list4, listUser, listItemShop);
                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    listUser.updateInformationUserFromList(user);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 0);
    }

    //Kiểm tra có thuộc bản đồ không
    public boolean checkGrid(int x, int y) {
        boolean flag = true;
        if ((x < 0 || x >= getNumRows()) || (y < 0 || y >= getNumColums())) flag = false;
        return flag;
    }

    //Kiểm tra máy có lặp lại lựa chọn không
    public boolean checkRepeatComputer(int x, int y) {
        boolean flag = false;
        if ((gridComputer.getValue(x, y).equals("-")) || (gridComputer.getValue(x, y).equals("x"))
                || (gridComputer.getValue(x, y).equals("D")) || (gridMap.getValue(x, y).equals("D"))
                || gridComputer.getValue(x, y).equals("l") || gridComputer.getValue(x, y).equals("L")) flag = true;
        return flag;
    }

    //In thông tin kí hiệu quy ước
    public void detailInformationSymbols() {
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
}
