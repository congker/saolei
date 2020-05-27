import javax.swing.*;

public class Block {

    String name;
    int aroundMineNumber;
    ImageIcon mineIcon;
    boolean isMine = false;
    boolean isMark = false;
    boolean isOpen = false;

    public Block() {
    }

    public Block(String name, int aroundMineNumber, ImageIcon mineIcon, boolean isMine, boolean isMark, boolean isOpen) {
        this.name = name;
        this.aroundMineNumber = aroundMineNumber;
        this.mineIcon = mineIcon;
        this.isMine = isMine;
        this.isMark = isMark;
        this.isOpen = isOpen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAroundMineNumber(int n) {
        this.aroundMineNumber = n;
    }

    public int getAroundMineNumber() {
        return this.aroundMineNumber;
    }

    public void setMineIcon(ImageIcon icon) {
        this.mineIcon = icon;
    }

    public ImageIcon getMineicon() {
        return this.mineIcon;
    }

    public void setIsMine(boolean b) {
        this.isMine = b;
    }

    public boolean getIsMine() {
        return this.isMine;
    }

    public void setIsMark(boolean m) {
        this.isMark = m;
    }

    public boolean getIsMark() {
        return this.isMark;
    }

    public void setIsOpen(boolean p) {
        this.isOpen = p;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    @Override
    public String toString() {
        return "Block [name=" + this.name + ", aroundMineNumber=" + this.aroundMineNumber + ", mineIcon=" + this.mineIcon + ", isMine=" + this.isMine + ", isMark=" + this.isMark + ", isOpen=" + this.isOpen + "]";
    }
}
