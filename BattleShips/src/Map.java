import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Map implements Serializable {
    private int numRows;
    private int numColums;

    private String[][] gridCustom;

    private int[] saveNum = new int[5];

    public Map(int numRows, int numColums) {
        this.numRows = numRows;
        this.numColums = numColums;
        this.gridCustom = new String[numRows][numColums];
    }

    public String getValue(int numRows, int numColums) {
        return this.gridCustom[numRows][numColums];
    }

    public void setValue(int numRows, int numColums, String value) {
        this.gridCustom[numRows][numColums] = value;
    }

    public void printOceanMap() {
        System.out.println();
        //Phần đầu của map
        System.out.print("  ");
        for (int i = 0; i < numColums; i++)
            System.out.print(i);
        System.out.println();
        //Phần giữa của map
        for (int i = 0; i < numRows; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < numColums; j++) {
                System.out.print(getValue(i, j));
            }
            System.out.println("|" + i);
        }
        //Phần cuối của map
        System.out.print("  ");
        for (int i = 0; i < numColums; i++)
            System.out.print(i);
        System.out.println();
    }

    public void setValueSaveNum(int stt, int value) {
        this.saveNum[stt] = value;
    }

    public int getValueSaveNum(int stt) {
        return this.saveNum[stt];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColums; j++) {
                sb.append(getValue(i, j)).append(",");
            }
            //sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
