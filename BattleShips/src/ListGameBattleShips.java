
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListGameBattleShips {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Map> listGame;

    public ListGameBattleShips() {
        this.listGame = new ArrayList<>();
    }

    public ListGameBattleShips(ArrayList<Map> listGame) {
        this.listGame = listGame;
    }

    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    //Thêm game vào danh sách để lưu
    public void addGame(Map gridMap) {
        this.listGame.add(gridMap);
    }

    //Xóa game ra khỏi danh sách
    public void removeGame(Map gridMap) {
        this.listGame.remove(gridMap);
    }

    //Kiểm tra rỗng
    public boolean checkNull() {
        return this.listGame.isEmpty();
    }

    //In ra số map game đã lưu
    public int numGame() {
        return this.listGame.size();
    }

    //Xóa hết game đã lưu
    public void removeAllGame() {
        this.listGame.removeAll(listGame);
    }

    //Xóa theo số thứ tự
    public void removeSTTGame(int STT) {
        this.listGame.remove(STT - 1);
    }

    //Cập nhật danh sách từ file
    public void updateListGameFromFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
                while (true) {
                    Object obj = objectIn.readObject();
                    if (obj instanceof Map) {
                        //Map map = (Map) obj;
                        this.listGame.add((Map) obj);
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
    public void updateListGameToFile(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Map map : listGame) {
                objectOut.writeObject(map);
            }
            objectOut.close();
            fileOut.close();
            //System.out.println("Đã lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Lưu thất bại!");
        }
    }

    //In list game trong danh sách ra màn hình
    public void printAllGameFromList() {
        int stt = 1;
        for (Map map : listGame) {
            System.out.println("\nGame thứ " + stt + ":");
            stt++;
            map.printOceanMap();
        }
    }

    public int getValueSaveNumFromListSTTstt(int STT, int stt) {
        return listGame.get(STT - 1).getValueSaveNum(stt);
    }

    public Map getGameSTTFromList(int STT) {
        return listGame.get(STT - 1);
    }

    //In list game của User có tài khoàn ? từ file ?
    public void printAllListGameByNameIDFromFile(String nameID, String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(nameID + fileName)) {
                updateListGameFromFile(nameID + fileName);
                //Nếu cập nhật rồi mà ArrayList vẫn rỗng thì => chưa lưu game nào
                if (checkNull()) System.out.println("Bạn chưa lưu game nào!");
                else printAllGameFromList();
            } else System.out.println("Bạn chưa lưu game nào!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!checkNull() && fileExist(nameID + fileName)){
            printAllGameFromList();
        }
    }

    public void printAllListGameByNameIDFromFile(String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) {
                updateListGameFromFile(fileName);
                //Nếu cập nhật rồi mà ArrayList vẫn rỗng thì => chưa lưu game nào
                if (checkNull()) System.out.println("Bạn chưa lưu game nào!");
                else printAllGameFromList();
            } else System.out.println("Bạn chưa lưu game nào!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!checkNull() && fileExist(fileName)){
            printAllGameFromList();
        }
    }

    //In list game đã lưu(Hoàn thành) của User có tài khoản:
    public void printAllListGameSavedByNameID(String nameID) {
        printAllListGameByNameIDFromFile(nameID, ".txt");
    }

    //In list game dang dở của User có tài khoản:
    public void printAllListGame_StillSavedByNameID(String nameID) {
        printAllListGameByNameIDFromFile(nameID, "gridplayer.txt");
    }

    //Thêm game vào list game của User ? vào file ?
    public void addGameByNameIDToFile(String nameID, String fileName, Map gridMap) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(nameID + fileName)) updateListGameFromFile(nameID + fileName);
            addGame(gridMap);
            //Cập nhật vào file để lưu
            updateListGameToFile(nameID + fileName);
        } else if (!checkNull() && fileExist(nameID + fileName)){
            addGame(gridMap);
            updateListGameToFile(nameID + fileName);
        }
    }

    //Xoa game trong list game cua User ? từ file ? theo STT
    public void removeGameByNameIDSTTToFile(String nameID, String fileName, int STT) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(nameID + fileName)) {
                updateListGameFromFile(nameID + fileName);
                //Nếu cập nhật rồi mà ArrayList vẫn rỗng thì => chưa lưu game nào
                if (checkNull()) System.out.println("Bạn chưa lưu game nào!");
                else {
                    removeSTTGame(STT);
                    updateListGameToFile(nameID + fileName);
                }
            } else System.out.println("Bạn chưa lưu game nào!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!checkNull() && fileExist(nameID + fileName)){
            removeSTTGame(STT);
            updateListGameToFile(nameID + fileName);
        }
    }

    public void removeAllGameByNameIDToFile(String nameID, String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật rỗng vào file
            if (fileExist(nameID + fileName)) {
                updateListGameToFile(nameID + fileName);
            } else System.out.println("Bạn chưa lưu game nào!"); //File k tồn tại thì chắc chắn chưa lưu game nào
        } else if (!checkNull() && fileExist(nameID + fileName)){
            removeAllGame();
            updateListGameToFile(nameID + fileName);
        }
    }

    //

    //Hỏi việc lưu game hoàn thành của User ?
    public void askForSaveGameDone(User user, Map gridPlayer) {
        int playerChoice;
        System.out.println("Bạn có muốn lưu không ?");
        System.out.println("1.Có\t0.Không");

        do {
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    //if (numGame() < user.getSavedGameLimited()) addGameByNameIDToFile(user.getNameID(), ".txt", gridPlayer);
                    //else System.out.println("Bạn đã đạt giới hạn lưu GAME!");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (playerChoice != 1 && playerChoice != 0);
        System.out.println("----> **** <----");
    }

    public void askForSaveGameDone(User user, Map gridPlayer, ListItemShop listItemShop) {
        int playerChoice;
        System.out.println("Bạn có muốn lưu không ?");
        System.out.println("1.Có\t0.Không");

        do {
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    if (numGame() < listItemShop.getSavedGameLimitedByNameIDFromList(user.getNameID())) addGameByNameIDToFile(user.getNameID(), ".txt", gridPlayer);
                    else System.out.println("Bạn đã đạt giới hạn lưu GAME!");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (playerChoice != 1 && playerChoice != 0);
        System.out.println("----> **** <----");
    }

    //Hỏi việc xóa game của User ?
    public void askForRemoveGameDone(String nameID) {
        int playerChoice;

        do {
            System.out.println("Mời bạn chọn kiểu xóa:");
            System.out.println("1.Xóa game theo STT\t2.Xóa hết!\t0.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    int stt;

                    do {
                        System.out.println("Mời bạn nhập STT của game cần xóa (Bấm 0 để thoát!)");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;

                        if (checkNull()) {
                            if (fileExist(nameID + ".txt")) updateListGameFromFile(nameID + ".txt");
                            //Cập nhật rồi mà vẫn rỗng thì chưa lưu game nào
                            if (checkNull()) {
                                System.out.println("Bạn chưa lưu game nào!");
                                break;
                            }
                            else {
                                if (stt >= 1 && stt <= numGame()) { //Không rỗng  thì kiểm trả STT có hợp lệ không
                                    removeSTTGame(stt);
                                    updateListGameToFile(nameID + ".txt");
                                    break;
                                } else {
                                    System.out.println("Số thứ tự không hợp lệ!");
                                    System.out.println("Mời bạn nhập lại");
                                }
                            }
                        } else if (!checkNull() && fileExist(nameID + ".txt")){
                            if (stt >= 1 && stt <= numGame()) {
                                removeSTTGame(stt);
                                updateListGameToFile(nameID + ".txt");
                                break;
                            } else {
                                System.out.println("Số thứ tự không hợp lệ!");
                                System.out.println("Mời bạn nhập lại");
                            }
                        }
                    } while (true);
                    break;
                case 2:
                    if (checkNull()) {
                        if (fileExist(nameID + ".txt")) updateListGameFromFile(nameID + ".txt");
                        //Cập nhật rồi mà vẫn rỗng thì chưa lưu game nào
                        if (checkNull()) {
                            System.out.println("Bạn chưa lưu game nào!");
                            break;
                        } else {
                            removeAllGame();
                            updateListGameToFile(nameID + ".txt");
                            break;
                        }
                    } else if (!checkNull() && fileExist(nameID + ".txt")){
                        removeAllGame();
                        updateListGameToFile(nameID + ".txt");
                        break;
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

    //Hỏi việc xóa game dang dở của User ?
    public void askForRemoveGameStill(String nameID) {
        int playerChoice;

        do {
            System.out.println("Mời bạn chọn kiểu xóa:");
            System.out.println("1.Xóa game theo STT\t2.Xóa hết!\t0.Không");
            playerChoice = Integer.parseInt(sc.nextLine());
            switch (playerChoice) {
                case 1:
                    int stt;

                    do {
                        System.out.println("Mời bạn nhập STT của game cần xóa (Bấm 0 để thoát!)");
                        stt = Integer.parseInt(sc.nextLine());
                        if (stt == 0) break;

                        if (checkNull()) {
                            if (fileExist(nameID + "gridplayer.txt")) updateListGameFromFile(nameID + "gridplayer.txt");
                            //Cập nhật rồi mà vẫn rỗng thì chưa lưu game nào
                            if (checkNull()) {
                                System.out.println("Bạn chưa lưu game nào!");
                                break;
                            }
                            else {
                                if (stt >= 1 && stt <= numGame()) { //Không rỗng  thì kiểm trả STT có hợp lệ không
                                    removeSTTGame(stt);
                                    updateListGameToFile(nameID + "gridplayer.txt");
                                    break;
                                } else {
                                    System.out.println("Số thứ tự không hợp lệ!");
                                    System.out.println("Mời bạn nhập lại");
                                }
                            }
                        } else if (!checkNull() && fileExist(nameID + "gridplayer.txt")){
                            if (stt >= 1 && stt <= numGame()) {
                                removeSTTGame(stt);
                                updateListGameToFile(nameID + "gridplayer.txt");
                                break;
                            } else {
                                System.out.println("Số thứ tự không hợp lệ!");
                                System.out.println("Mời bạn nhập lại");
                            }
                        }
                    } while (true);
                    break;
                case 2:
                    if (checkNull()) {
                        if (fileExist(nameID + "gridplayer.txt")) updateListGameFromFile(nameID + "gridplayer.txt");
                        //Cập nhật rồi mà vẫn rỗng thì chưa lưu game nào
                        if (checkNull()) {
                            System.out.println("Bạn chưa lưu game nào!");
                            break;
                        } else {
                            removeAllGame();
                            updateListGameToFile(nameID + "gridplayer.txt");
                            break;
                        }
                    } else if (!checkNull() && fileExist(nameID + "gridplayer.txt")){
                        removeAllGame();
                        updateListGameToFile(nameID + "gridplayer.txt");
                        break;
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    System.out.println("Mời bạn nhập lại");
                    break;
            }
        } while (playerChoice != 0);
        System.out.println("----> **** <----");
    }

}
