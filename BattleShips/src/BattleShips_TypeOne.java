import java.util.Scanner;

public class BattleShips_TypeOne extends BattleShips {
    public BattleShips_TypeOne() {
    }

    public BattleShips_TypeOne(int numRows, int numColums, int playerShips, int computerShips, int levelGame) {
        super(numRows, numColums, playerShips, computerShips, levelGame);
    }

    //Tạo map
    //public void createOceanMap();

    //Người chơi đặt thuyền
    //public void deployPlayerShips();

    //Máy đặt thuyền
    public void deployComputerShips() {
        System.out.println("\nComputer đang đặt tọa độ!");
        //Đặt tọa độ cho Computer
        for (int i = 1; i <= getComputerShips(); ) {
            int x = (int) (Math.random() * getNumRows());
            int y = (int) (Math.random() * getNumColums());
            //Chế độ thường
            if (this.getLevelGame() == 1) {
                if (checkGrid(x, y) && (gridComputer.getValue(x, y).equals(" ")) && !(gridPlayer.getValue(x, y).equals("P"))) //Không cho phép trùng
                {
                    gridComputer.setValue(x, y, "C");
                    gridMap.setValue(x, y, "C");
                    System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
                    i++;
                }
            } else if (this.getLevelGame() == 2) { //Chế độ trung bình
                if (checkGrid(x, y) && (gridComputer.getValue(x, y).equals(" "))) //Cho phép trùng
                {
                    gridComputer.setValue(x, y, "C");
                    //Nếu vị trí đó Player cũng đã đặt thì lưu map chung là $ không thì chỉ là C
                    if (gridPlayer.getValue(x, y).equals("P")) gridMap.setValue(x, y, "$");
                    else gridMap.setValue(x, y, "C");
                    System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
                    i++;
                }
            }
        }
        //In cả Player và Computer => Test
        gridMap.printOceanMap();
    }

    @Override
    public void playerTurn() {
        System.out.println("\nLượt của BẠN!");
        int x = -1, y = -1;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập tọa độ X: ");
            x = Integer.parseInt(sc.nextLine());
            //Nếu x == -1 => Save
            if (x == -1) {
                gridMap.setValueSaveNum(0, getPlayerShips());
                gridMap.setValueSaveNum(1, getComputerShips());
                gridMap.setValueSaveNum(2, getLevelGame());
                gridMap.setValueSaveNum(3, getNumRows());
                gridMap.setValueSaveNum(4, getNumColums());
                setPlayerShips(-1);
                setComputerShips(0);
                break;
            }
            System.out.print("Nhập tọa độ Y: ");
            y = Integer.parseInt(sc.nextLine());
            if (checkGrid(x, y)) //valid guess Integer.parseInt(x)
            {
                //Chế độ thường
                if (this.getLevelGame() == 1) {
                    if (gridMap.getValue(x, y).equals("C")) //Kiểm tra xem ỏ đó có thuyền Computer không
                    {
                        System.out.println("Boom! Bạn đã bắn trúng 1 thuyền ^^!");
                        //Cập nhật map
                        gridPlayer.setValue(x, y, "D"); //Hit mark
                        gridComputer.setValue(x, y, "d");
                        gridMap.setValue(x, y, "D"); //Hit mark
                        setComputerShips(getComputerShips() - 1);
                    } else if (gridMap.getValue(x, y).equals("P")) {
                        System.out.println("Ôi không, bạn đã tự bắn trúng thuyền của bản thân :((");
                        gridPlayer.setValue(x, y, "x");
                    } else if (gridMap.getValue(x, y).equals(" ")) {
                        System.out.println("Oops, hụt mất rồi :<");
                        gridPlayer.setValue(x, y, "-");
                    }
                }
                //Chế độ trung bình
                else if (this.getLevelGame() == 2) {
                    if (gridMap.getValue(x, y).equals("C") || gridMap.getValue(x, y).equals("$")) {
                        System.out.println("Boom! Bạn đã bắn trúng 1 thuyền ^^!");
                        //Nếu chọn vị trí đang trùng mà trúng và PLayer còn sống
                        if (gridPlayer.getValue(x, y).equals("P") && gridMap.getValue(x, y).equals("$") && gridComputer.getValue(x, y).equals("C")) {
                            gridPlayer.setValue(x, y, "L");
                            gridComputer.setValue(x, y, "d");
                            //Trừ thuyền Computer
                            setComputerShips(getComputerShips() - 1);
                        } else if (gridPlayer.getValue(x, y).equals("d") && gridMap.getValue(x, y).equals("$")) {
                            //Đã bị bắn và chọn vô tình Computer cũng ở đó
                            gridPlayer.setValue(x, y, "l");
                            gridComputer.setValue(x, y, "l");
                            //Set lại map để hiểu là vị trí này cả 2 đã chọn
                            gridMap.setValue(x, y, "S");
                            //Trừ thuyền Computer
                            setComputerShips(getComputerShips() - 1);
                        } else if (gridMap.getValue(x, y).equals("C")) {
                            gridPlayer.setValue(x, y, "D"); //Hit mark
                            gridComputer.setValue(x, y, "d");
                            gridMap.setValue(x, y, "D"); //Hit mark
                            //Trừ thuyền Computer
                            setComputerShips(getComputerShips() - 1);
                        }
                    } else if (gridPlayer.getValue(x, y).equals("P")) {
                        System.out.println("Ôi không, bạn đã tự bắn trúng thuyền của bản thân :((");
                        gridPlayer.setValue(x, y, "x");
                    } else if (gridPlayer.getValue(x, y).equals(" ")) {
                        System.out.println("Oops, hụt mất rồi :<");
                        gridPlayer.setValue(x, y, "-");
                    }
                }
            } else if (!checkGrid(x, y)) //Tọa độ đoán không hợp lệ
                System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + getNumRows() + " và " + getNumColums());
        } while (!checkGrid(x, y));
        //Vì có khả năng người chơi tiêu diệt hết thuyền của Computer và ngay sau đó Computer cũng tiêu diệt được player dẫn đến
        //việc sai sót
        //Test
        gridPlayer.printOceanMap();
    }

    @Override
    public void computerTurn() {
        System.out.println("\nLượt của Computer!");
        //Đoán tọa độ
        int x = -1, y = -1;
        do {
            x = (int) (Math.random() * getNumRows());
            y = (int) (Math.random() * getNumColums());
            if (checkGrid(x, y) && !checkRepeatComputer(x, y)) //valid guess
            {
                if (this.getLevelGame() == 1) {
                    if (gridMap.getValue(x, y).equals("P")) //if player ship is already there; player loses ship
                    {
                        System.out.println("Computer đã bắn trúng một những vị trí đặt thuyền của bạn!");
                        gridPlayer.setValue(x, y, "d");
                        gridComputer.setValue(x, y, "D"); //Player ship is sunk
                        gridMap.setValue(x, y, "d");
                        setPlayerShips(getPlayerShips() - 1);
                    } else if (gridMap.getValue(x, y).equals("C")) {
                        System.out.println("Computer đã tự bắn trúng thuyền của bản thân!");
                        gridComputer.setValue(x, y, "x");
                    } else if (gridMap.getValue(x, y).equals(" ")) {
                        System.out.println("Computer đẵ bắn hụt!");
                        gridComputer.setValue(x, y, "-");
                    }
                }
                else if (this.getLevelGame() == 2) {
                    if (gridMap.getValue(x, y).equals("P") || gridMap.getValue(x, y).equals("$")) {
                        System.out.println("Computer đã bắn trúng một những vị trí đặt thuyền của bạn!");
                        //Trùng và player còn sống thì được quyền chọn
                        if (gridComputer.getValue(x, y).equals("C") && gridMap.getValue(x, y).equals("$") && gridPlayer.getValue(x, y).equals("P")) {
                            gridPlayer.setValue(x, y, "d");
                            gridComputer.setValue(x, y, "L");
                            //Trừ thuyền Player
                            setPlayerShips(getPlayerShips() - 1);
                        } else if (gridComputer.getValue(x, y).equals("d") && gridMap.getValue(x, y).equals("$")) {
                            //Đã bị bắn và chọn vô tình Player cũng ở đó
                            gridPlayer.setValue(x, y, "l");
                            gridComputer.setValue(x, y, "l");
                            //Set lại map để hiểu là vị trí này cả 2 đã chọn
                            gridMap.setValue(x, y, "S");
                            //Trừ thuyền Player
                            setPlayerShips(getPlayerShips() - 1);
                        } else if (gridMap.getValue(x, y).equals("P")) {
                            gridPlayer.setValue(x, y, "d");
                            gridComputer.setValue(x, y, "D");
                            gridMap.setValue(x, y, "d");
                            //Trừ thuyền Player
                            setPlayerShips(getPlayerShips() - 1);
                        }
                    } else if (gridComputer.getValue(x, y).equals("C")) {
                        System.out.println("Computer đã tự bắn trúng thuyền của bản thân!");
                        gridComputer.setValue(x, y, "x");
                    } else if (gridComputer.getValue(x, y).equals(" ")) {
                        System.out.println("Computer đẵ bắn hụt!");
                        gridComputer.setValue(x, y, "-");
                    }
                }
            } else x = y = -1; //Không thỏa điều kiện thì lặp lại
        } while (!checkGrid(x, y)); //Example: x = -1 => checkGrid = false => ! flase = true
        //Test
        System.out.println("x: " + x + " y: " + y);
        gridComputer.printOceanMap();
    }

    //Chiến
    //public void Battle();

    //Kết thúc
    //public void gameOver();

}
