
import java.io.*;
import java.util.ArrayList;

public class ListGameBattleShips {
    private ArrayList<Map> listGame;

    public ListGameBattleShips() {
        this.listGame = new ArrayList<>();
    }

    public ListGameBattleShips(ArrayList<Map> listGame) {
        this.listGame = listGame;
    }

    //Thêm game vào danh sách để lưu
    public void addGame(Map gridMap) {
        this.listGame.add(gridMap);
    }

    //Xóa game ra khỏi danh sách
    /*public void removeGame(Map gridMap) {
        this.listGame.remove(gridMap);
    }*/

    //Kiểm tra rỗng
    /*public boolean checkNull() {
        return this.listGame.isEmpty();
    }*/

    //In ra số map game đã lưu
    public int numGame() {
        return this.listGame.size();
    }

    //Xóa hết game đã lưu
    public void removeAllGame() {
        this.listGame.removeAll(listGame);
    }

    //Xóa theo số thứ tự
    public void removeSTT(int STT) {
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
                        Map map = (Map) obj;
                        this.listGame.add(map);
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
    public void printAllGameSaved() {
        int stt = 1;
        for (Map map : listGame) {
            System.out.println("\nGame thứ " + stt + ":");
            stt++;
            //In
            map.printOceanMap();
            //System.out.println(map);
        }
    }

    public int getValueSaveNum(int STT, int stt) {
        return listGame.get(STT - 1).getValueSaveNum(stt);
    }

    public Map getGameSTT(int STT) {
        return listGame.get(STT - 1);
    }
}
