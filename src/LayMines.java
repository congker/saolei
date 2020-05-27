import javax.swing.*;
import java.util.LinkedList;

public class LayMines {
    ImageIcon mineIcon = new ImageIcon("mine.gif");

    LayMines() {
    }

    public void layMinesForBlock(Block[][] block, int mineCount, int m, int n) {
        int row = block.length;
        int column = block[0].length;
        LinkedList<Block> list = new LinkedList();

        int i;
        int j;
        for(i = 0; i < row; ++i) {
            for(j = 0; j < column; ++j) {
                list.add(block[i][j]);
            }
        }

        while(mineCount > 0) {
            i = list.size();
            j = (int)(Math.random() * (double)i);
            Block b = (Block)list.get(j);
            if (b != block[m][n]) {
                b.setIsMine(true);
                b.setMineIcon(this.mineIcon);
                list.remove(j);
                --mineCount;
            }
        }

        for(i = 0; i < row; ++i) {
            for(j = 0; j < column; ++j) {
                if (block[i][j].getIsMine()) {
                    block[i][j].setIsOpen(false);
                    block[i][j].setIsMark(false);
                } else {
                    int mineNumber = 0;

                    for(int k = Math.max(i - 1, 0); k <= Math.min(i + 1, row - 1); ++k) {
                        for(int t = Math.max(j - 1, 0); t <= Math.min(j + 1, column - 1); ++t) {
                            if (block[k][t].getIsMine()) {
                                ++mineNumber;
                            }
                        }
                    }

                    block[i][j].setIsOpen(false);
                    block[i][j].setIsMark(false);
                    block[i][j].setName("" + mineNumber);
                    block[i][j].setAroundMineNumber(mineNumber);
                }
            }
        }

    }
}
