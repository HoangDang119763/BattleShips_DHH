// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //ListGameBattleShips listGameBattleShips = new ListGameBattleShips();
        //listGameBattleShips.updateListGameFromFile("nguyenminhsongridmap.txt");
        //System.out.println(listGameBattleShips.getValueSaveNum(3));


        //MenuMain menuMain = new MenuMain();
        //menuMain.menuLogin();
        ListUser listUser = new ListUser();
        listUser.updateListUserFromFile(FileName.fileUser);
        listUser.getSecretQuestionByNameIDFromList("trile");
    }
}