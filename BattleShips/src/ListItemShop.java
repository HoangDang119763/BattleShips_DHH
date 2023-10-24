import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListItemShop implements FileName {
    Scanner sc = new Scanner(System.in);

    private ArrayList<ItemShop> listItemShop;

    public ListItemShop() {
        this.listItemShop = new ArrayList<>();
    }

    public ListItemShop(ArrayList<ItemShop> listItemShop) {
        this.listItemShop = listItemShop;
    }

    public boolean fileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public void addItemShop(ItemShop itemShop) {
        this.listItemShop.add(itemShop);
    }

    public void addItemShopToFile(String fileName, ItemShop itemShop) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateInformationListFromFile(fileName);
            addItemShop(itemShop);
            //Cập nhật vào file để lưu
            updateListItemShopToFile(fileName);
        } else if (!checkNull() && fileExist(fileName)) {
            addItemShop(itemShop);
            //Cập nhật vào file để lưu
            updateListItemShopToFile(fileName);
        }
    }

    public void removeItemShop(ItemShop itemShop) {
        this.listItemShop.remove(itemShop);
    }

    public boolean checkNull() {
        return this.listItemShop.isEmpty();
    }

    public int numItemShop() {
        return this.listItemShop.size();
    }

    public void removeAllItemShop() {
        this.listItemShop.removeAll(listItemShop);
    }

    public void removeSTT(int STT) {
        this.listItemShop.remove(STT - 1);
    }

    //Xóa người dùng khỏi list shop
    public void removeListShopByNameID(String nameID) {
        for (ItemShop itemShop : listItemShop) {
            if (itemShop.checkNameID(nameID)) {
                removeItemShop(itemShop);
                break;
            }
        }
    }

    //Cập nhật danh sách từ file
    public void updateListItemShoprFromFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
                while (true) {
                    Object obj = objectIn.readObject();
                    if (obj instanceof ItemShop) {
                        ItemShop itemShop = (ItemShop) obj;
                        this.listItemShop.add(itemShop);
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
    public void updateListItemShopToFile(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (ItemShop itemShop : listItemShop) {
                objectOut.writeObject(itemShop);
            }
            objectOut.close();
            fileOut.close();
            //System.out.println("Đã lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Lưu thất bại!");
        }
    }

    public ItemShop getUserSTTFromList(int STT) {
        return listItemShop.get(STT - 1);
    }

    public int getSavedGameLimitedByNameIDFromList(String nameID) {
        int temp = 0;
        for (ItemShop itemShop : listItemShop) {
            if (itemShop.checkNameID(nameID)) {
                temp = itemShop.getSavedGameLimited();
            }
        }
        return temp;
    }

    public String getNickNameByNameIDFromList(String nameID) {
        String temp = "";
        for (ItemShop itemShop : listItemShop) {
            if (itemShop.checkNameID(nameID)) {
                temp = itemShop.getNickName();
            }
        }
        return temp;
    }

    public void addSavedGameLimitedByNameIDToList(String nameID, int savedGameLimited) {
        for (ItemShop itemShop : listItemShop) {
            if (itemShop.checkNameID(nameID)) {
                itemShop.setSavedGameLimited(getSavedGameLimitedByNameIDFromList(nameID) + savedGameLimited);
                break;
            }
        }
    }

    public void addSavedGameLimitedByNameIDToFile(String fileName, String nameID, int savedGameLimited) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListItemShoprFromFile(fileName);
            if (checkNull()) {
                System.out.println("Người dùng này chưa đăng kí!");
            } else {
                addSavedGameLimitedByNameIDToList(nameID, savedGameLimited);
                updateListItemShopToFile(fileName);
            }
        } else if (!checkNull() && fileExist(fileName)) {
            addSavedGameLimitedByNameIDToList(nameID, savedGameLimited);
            updateListItemShopToFile(fileName);
        }
    }

    public void subSavedGameLimitedByNameIDToList(String nameID, int savedGameLimited) {
        for (ItemShop itemShop : listItemShop) {
            if (itemShop.checkNameID(nameID)) {
                itemShop.setSavedGameLimited(getSavedGameLimitedByNameIDFromList(nameID) - savedGameLimited);
                break;
            }
        }
    }

    public void subSavedGameLimitedByNameIDToFile(String fileName, String nameID, int savedGameLimited) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListItemShoprFromFile(fileName);
            if (checkNull()) {
                System.out.println("Người dùng này chưa đăng kí!");
            } else {
                subSavedGameLimitedByNameIDToList(nameID, savedGameLimited);
                updateListItemShopToFile(fileName);
            }
        } else if (!checkNull() && fileExist(fileName)) {
            subSavedGameLimitedByNameIDToList(nameID, savedGameLimited);
            updateListItemShopToFile(fileName);
        }
    }

    public void setSavedGameLimitedByNameIDToList(String nameID, int savedGameLimited) {
        for (ItemShop itemShop : listItemShop) {
            if (itemShop.checkNameID(nameID)) {
                itemShop.setSavedGameLimited(savedGameLimited);
                break;
            }
        }
    }

    public void setSavedGameLimitedByNameIDToFile(String fileName, String nameID, int savedGameLimited) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListItemShoprFromFile(fileName);
            if (checkNull()) {
                System.out.println("Người dùng này chưa đăng kí!");
            } else {
                setSavedGameLimitedByNameIDToList(nameID, savedGameLimited);
                updateListItemShopToFile(fileName);
            }
        } else if (!checkNull() && fileExist(fileName)) {
            setSavedGameLimitedByNameIDToList(nameID, savedGameLimited);
            updateListItemShopToFile(fileName);
        }
    }

    public void setNickNameByNameIDToList(String nameID, String nickName) {
        for (ItemShop itemShop : listItemShop) {
            if (itemShop.checkNameID(nameID)) {
                itemShop.setNickName(nickName);
                break;
            }
        }
    }

    public void setNickNameByNameIDToFile(String fileName, String nameID, String nickName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListItemShoprFromFile(fileName);
            if (checkNull()) {
                System.out.println("Người dùng này chưa đăng kí!");
            } else {
                setNickNameByNameIDToList(nameID, nickName);
                updateListItemShopToFile(fileName);
            }
        } else if (!checkNull() && fileExist(fileName)) {
            setNickNameByNameIDToList(nameID, nickName);
            updateListItemShopToFile(fileName);
        }
    }

    public void updateInformationListFromFile(String fileName) {
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListItemShoprFromFile(fileName);
        }
    }

    public void askForBuySavedGameLimited(User user) {
        int playerChoice;

        do {
            System.out.println("Mời bạn chọn kiểu mua");
            System.out.println("1.Mua 1 giới hạn (Giá 200 Gold!)");
            System.out.println("2.Mua n giới hạn (Giá 200 Gold/1!)");
            System.out.println("3.Mua 1 giới hạn (Giá 10 Diamond/1!)");
            System.out.println("4.Mua n giới hạn (Giá 10 Diamond/1!)");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                if (user.getGold() < 200) System.out.println("Bạn không đủ Gold!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), 1);
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 200);
                }
                break;
            } else if (playerChoice == 2) {
                System.out.println("Mời bạn nhập n: ");
                int n = Integer.parseInt(sc.nextLine());
                if ((user.getGold() * n) < 200 * n) System.out.println("Bạn không đủ Gold!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), n);
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 200 * n);
                }
                break;
            } else if (playerChoice == 3) {
                if (user.getDiamond() < 10) System.out.println("Bạn không đủ Diamond!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), 1);
                    System.out.println("Mua thành công!");
                    user.setDiamond(user.getDiamond() - 10);
                }
                break;
            } else if (playerChoice == 4) {
                System.out.println("Mời bạn nhập n: ");
                int n = Integer.parseInt(sc.nextLine());
                if ((user.getDiamond() * n) < 10 * n) System.out.println("Bạn không đủ Gold!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), n);
                    System.out.println("Mua thành công!");
                    user.setDiamond(user.getDiamond() - 200 * n);
                }
                break;
            } else if (playerChoice == 0) break;
            else {
                System.out.println("Lựa chọn không hợp lệ!");
                System.out.println("Mời bạn nhập lại");
            }
        } while (true); //Vì chỉ muốn trả lời 1 trong 2
        System.out.println();
    }

    public void askForBuySavedGameLimited(User user, ListUser listUser) {
        int playerChoice;

        do {
            System.out.println("Mời bạn chọn kiểu mua");
            System.out.println("1.Mua 1 giới hạn (Giá 200 Gold!)");
            System.out.println("2.Mua n giới hạn (Giá 200 Gold/1!)");
            System.out.println("3.Mua 1 giới hạn (Giá 10 Diamond/1!)");
            System.out.println("4.Mua n giới hạn (Giá 10 Diamond/1!)");
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                if (user.getGold() < 200) System.out.println("Bạn không đủ Gold!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), 1);
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 200);
                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    listUser.updateInformationUserFromList(user);
                }
                break;
            } else if (playerChoice == 2) {
                System.out.println("Mời bạn nhập n: ");
                int n = Integer.parseInt(sc.nextLine());
                if ((user.getGold() * n) < 200 * n) System.out.println("Bạn không đủ Gold!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), n);
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 200 * n);
                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    listUser.updateInformationUserFromList(user);
                }
                break;
            } else if (playerChoice == 3) {
                if (user.getDiamond() < 10) System.out.println("Bạn không đủ Diamond!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), 1);
                    System.out.println("Mua thành công!");
                    user.setDiamond(user.getDiamond() - 10);
                    listUser.setUserDiamondByNameIDToFile(fileUser, user.getNameID(), user.getDiamond());
                    listUser.updateInformationUserFromList(user);
                }
                break;
            } else if (playerChoice == 4) {
                System.out.println("Mời bạn nhập n: ");
                int n = Integer.parseInt(sc.nextLine());
                if ((user.getDiamond() * n) < 10 * n) System.out.println("Bạn không đủ Gold!");
                else {
                    addSavedGameLimitedByNameIDToFile(fileShop, user.getNameID(), n);
                    System.out.println("Mua thành công!");
                    user.setDiamond(user.getDiamond() - 200 * n);
                    listUser.setUserDiamondByNameIDToFile(fileUser, user.getNameID(), user.getDiamond());
                    listUser.updateInformationUserFromList(user);
                }
                break;
            } else if (playerChoice == 0) break;
            else {
                System.out.println("Lựa chọn không hợp lệ!");
                System.out.println("Mời bạn nhập lại");
            }
        } while (true);
        System.out.println();
    }

    public void askForBuyNickName(User user) {
        int playerChoice;

        do {
            System.out.println("Danh sách các danh hiệu: ");
            if (this.getNickNameByNameIDFromList(user.getNameID()).equals(nickNameRank[0])) {
                System.out.println("1."+ nickNameRank[1] + " (1111.1111 Gold)");
                System.out.println("2."+ nickNameRank[2] + " (999999999.99 Gold|5000.99 Diamond)");
            } else if (this.getNickNameByNameIDFromList(user.getNameID()).equals(nickNameRank[1])) {
                System.out.println("2."+ nickNameRank[2] + " (999999999.99 Gold|5000.99 Diamond)");
            }
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                if (user.getGold() < 1111.1111) System.out.println("Bạn không đủ Gold!");
                else {
                    setNickNameByNameIDToFile(fileShop, user.getNameID(), nickNameRank[1]);
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 1111.1111);
                }
                break;
            } else if (playerChoice == 2) {
                if (user.getGold() < 1111.1111) {
                    System.out.println("Bạn không đủ Gold!");
                    if (user.getDiamond() > 5000.99) {
                        while (true) {
                            System.out.println("Bạn có muôn dùng Diamond không?");
                            System.out.println("1.Có\t0.Không");
                            int temp = Integer.parseInt(sc.nextLine());
                            if (temp == 1) {
                                setNickNameByNameIDToFile(fileShop, user.getNameID(), nickNameRank[2]);
                                System.out.println("Mua thành công!");
                                user.setDiamond(user.getDiamond() - 5000.99);
                                break;
                            } else if (temp == 0) break;
                            else {
                                System.out.println("Lựa chọn không hợp lệ!");
                                System.out.println("Mời bạn nhập lại");
                            }
                        }
                    }
                } else {
                    setNickNameByNameIDToFile(fileShop, user.getNameID(), "MVP PLayer");
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 999999999.99);
                }
                break;
            } else if (playerChoice == 0) break;
            else {
                System.out.println("Lựa chọn không hợp lệ!");
                System.out.println("Mời bạn nhập lại");
            }
        } while (true); //Vì chỉ muốn trả lời 1 trong 2
        System.out.println();
    }

    public void askForBuyNickName(User user, ListUser listUser) {
        int playerChoice;

        do {
            System.out.println("Danh sách các danh hiệu: ");
            if (this.getNickNameByNameIDFromList(user.getNameID()).equals(nickNameRank[0])) {
                System.out.println("1."+ nickNameRank[1] + " (1111.1111 Gold)");
                System.out.println("2."+ nickNameRank[2] + " (999999999.99 Gold|5000.99 Diamond)");
            } else if (this.getNickNameByNameIDFromList(user.getNameID()).equals(nickNameRank[1])) {
                System.out.println("2."+ nickNameRank[2] + " (999999999.99 Gold|5000.99 Diamond)");
            }
            System.out.println("0.Thoát");
            playerChoice = Integer.parseInt(sc.nextLine());
            if (playerChoice == 1) {
                if (user.getGold() < 1111.1111) System.out.println("Bạn không đủ Gold!");
                else {
                    setNickNameByNameIDToFile(fileShop, user.getNameID(), nickNameRank[1]);
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 1111.1111);
                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    listUser.updateInformationUserFromList(user);
                }
                break;
            } else if (playerChoice == 2) {
                if (user.getGold() < 1111.1111) {
                    System.out.println("Bạn không đủ Gold!");
                    if (user.getDiamond() > 5000.99) {
                        while (true) {
                            System.out.println("Bạn có muôn dùng Diamond không?");
                            System.out.println("1.Có\t0.Không");
                            int temp = Integer.parseInt(sc.nextLine());
                            if (temp == 1) {
                                setNickNameByNameIDToFile(fileShop, user.getNameID(), nickNameRank[2]);
                                System.out.println("Mua thành công!");
                                user.setDiamond(user.getDiamond() - 5000.99);
                                listUser.setUserDiamondByNameIDToFile(fileUser, user.getNameID(), user.getDiamond());
                                listUser.updateInformationUserFromList(user);
                                break;
                            } else if (temp == 0) break;
                            else {
                                System.out.println("Lựa chọn không hợp lệ!");
                                System.out.println("Mời bạn nhập lại");
                            }
                        }
                    }
                } else {
                    setNickNameByNameIDToFile(fileShop, user.getNameID(), "MVP PLayer");
                    System.out.println("Mua thành công!");
                    user.setGold(user.getGold() - 999999999.99);
                    listUser.setUserGoldByNameIDToFile(fileUser, user.getNameID(), user.getGold());
                    listUser.updateInformationUserFromList(user);
                }
                break;
            } else if (playerChoice == 0) break;
            else {
                System.out.println("Lựa chọn không hợp lệ!");
                System.out.println("Mời bạn nhập lại");
            }
        } while (true); //Vì chỉ muốn trả lời 1 trong 2
        System.out.println();
    }

    public boolean getStatusInformationListFromFile(String fileName) {
        boolean flag = true;
        if (checkNull()) {
            //Kiểm tra xem file có tồn tại không, Nếu có thì cập nhật vào ArrayList
            if (fileExist(fileName)) updateListItemShoprFromFile(fileName);
            if (checkNull()) {
                flag = false;
                System.out.println("System.out.println(\"Chưa có User nào mua hàng!\");");
            }
        }
        return flag;
    }
}
