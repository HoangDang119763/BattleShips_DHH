//import java.util.Scanner;
//
//public class BattleShips_Average extends BattleShips {
//    public BattleShips_Average() {
//    }
//
//    public BattleShips_Average(int numRows, int numColums, int playerShips, int computerShips, int levelGame) {
//        super(numRows, numColums, playerShips, computerShips, levelGame);
//    }
//
//    //Tạo map
//    //public void createOceanMap();
//
//    //Người chơi đặt thuyền
//    //public void deployPlayerShips();
//
//    //Máy đặt thuyền
//    public void deployComputerShips() {
//        System.out.println("\nComputer đang đặt tọa độ!");
//        //Đặt tọa độ cho Computer
//        for (int i = 1; i <= getComputerShips(); ) {
//            int x = (int) (Math.random() * getNumRows());
//            int y = (int) (Math.random() * getNumColums());
//            if (checkGrid(x, y) && (gridComputer.getValue(x, y).equals(" "))) //Cho phép trùng
//            {
//                gridComputer.setValue(x, y, "C");
//                //Nếu vị trí đó Player cũng đã đặt thì lưu map chung là $ không thì chỉ là C
//                if (gridPlayer.getValue(x, y).equals("P")) gridMap.setValue(x, y, "$");
//                else gridMap.setValue(x, y, "C");
//                System.out.println("Thuyền thứ: " + i + " ĐÃ ĐƯỢC ĐẶT!!!");
//                i++;
//            }
//
//        }
//        //In cả Player và Computer => Test
//        gridMap.printOceanMap();
//    }
//
//    @Override
//    public void playerTurn() {
//        System.out.println("\nLượt của BẠN!");
//        int x = -1, y = -1;
//        do {
//            Scanner sc = new Scanner(System.in);
//            System.out.print("Nhập tọa độ X: ");
//            x = Integer.parseInt(sc.nextLine());
//            System.out.print("Nhập tọa độ Y: ");
//            y = Integer.parseInt(sc.nextLine());
//            if (checkGrid(x, y)) //valid guess
//            {
//                if (gridMap.getValue(x, y).equals("C") || gridMap.getValue(x, y).equals("$")) {
//                    System.out.println("Boom! Bạn đã bắn trúng 1 thuyền ^^!");
//                    //Nếu chọn vị trí đang trùng mà trúng và PLayer còn sống
//                    if (gridPlayer.getValue(x, y).equals("P") && gridMap.getValue(x, y).equals("$") && gridComputer.getValue(x, y).equals("C")) {
//                        gridPlayer.setValue(x, y, "L");
//                        gridComputer.setValue(x, y, "d");
//                        //Trừ thuyền Computer
//                        setComputerShips(getComputerShips() - 1);
//                    } else if (gridPlayer.getValue(x, y).equals("d") && gridMap.getValue(x, y).equals("$")) {
//                        //Đã bị bắn và chọn vô tình Computer cũng ở đó
//                        gridPlayer.setValue(x, y, "l");
//                        gridComputer.setValue(x, y, "l");
//                        //Set lại map để hiểu là vị trí này cả 2 đã chọn
//                        gridMap.setValue(x, y, "S");
//                        //Trừ thuyền Computer
//                        setComputerShips(getComputerShips() - 1);
//                    } else if (gridMap.getValue(x, y).equals("C")) {
//                        gridPlayer.setValue(x, y, "D"); //Hit mark
//                        gridComputer.setValue(x, y, "d");
//                        gridMap.setValue(x, y, "D"); //Hit mark
//                        //Trừ thuyền Computer
//                        setComputerShips(getComputerShips() - 1);
//                    }
//                } else if (gridPlayer.getValue(x, y).equals("P")) {
//                    System.out.println("Ôi không, bạn đã tự bắn trúng thuyền của bản thân :((");
//                    gridPlayer.setValue(x, y, "x");
//                } else if (gridPlayer.getValue(x, y).equals(" ")) {
//                    System.out.println("Oops, hụt mất rồi :<");
//                    gridPlayer.setValue(x, y, "-");
//                }
//            } else if (!checkGrid(x, y)) //invalid guess
//                System.out.println("Bạn không thể đặt ngoài kích thước map đã quy định: " + getNumRows() + " và " + getNumColums());
//        } while (!checkGrid(x, y));
//        if (getComputerShips() <= 0) gameOver();
//        //Test
//        gridPlayer.printOceanMap();
//    }
//
//    @Override
//    public void computerTurn() {
//        System.out.println("\nLượt của Computer!");
//        //Đoán tọa độ
//        int x = -1, y = -1;
//        do {
//            x = (int) (Math.random() * getNumRows());
//            y = (int) (Math.random() * getNumColums());
//            if (checkGrid(x, y) && !checkRepeatComputer(x, y)) //valid guess
//            {
//                if (gridMap.getValue(x, y).equals("P") || gridMap.getValue(x, y).equals("$")) {
//                    System.out.println("Computer đã bắn trúng một những vị trí đặt thuyền của bạn!");
//                    //Trùng và player còn sống thì được quyền chọn
//                    if (gridComputer.getValue(x, y).equals("C") && gridMap.getValue(x, y).equals("$") && gridPlayer.getValue(x, y).equals("P")) {
//                        gridPlayer.setValue(x, y, "d");
//                        gridComputer.setValue(x, y, "L");
//                        //Trừ thuyền Player
//                        setPlayerShips(getPlayerShips() - 1);
//                    } else if (gridComputer.getValue(x, y).equals("d") && gridMap.getValue(x, y).equals("$")) {
//                        //Đã bị bắn và chọn vô tình Player cũng ở đó
//                        gridPlayer.setValue(x, y, "l");
//                        gridComputer.setValue(x, y, "l");
//                        //Set lại map để hiểu là vị trí này cả 2 đã chọn
//                        gridMap.setValue(x, y, "S");
//                        //Trừ thuyền Player
//                        setPlayerShips(getPlayerShips() - 1);
//                    } else if (gridMap.getValue(x, y).equals("P")) {
//                        gridPlayer.setValue(x, y, "d");
//                        gridComputer.setValue(x, y, "D");
//                        gridMap.setValue(x, y, "d");
//                        //Trừ thuyền Player
//                        setPlayerShips(getPlayerShips() - 1);
//                    }
//                } else if (gridComputer.getValue(x, y).equals("C")) {
//                    System.out.println("Computer đã tự bắn trúng thuyền của bản thân!");
//                    gridComputer.setValue(x, y, "x");
//                } else if (gridComputer.getValue(x, y).equals(" ")) {
//                    System.out.println("Computer đẵ bắn hụt!");
//                    gridComputer.setValue(x, y, "-");
//                }
//            } else x = y = -1;
//        } while (!checkGrid(x, y));
//        //Test
//        System.out.println("x: " + x + " y: " + y);
//        gridComputer.printOceanMap();
//    }
//
//    //Chiến
//    //public void Battle();
//
//    //Kết thúc
//    //public void gameOver();
//
//}
