import com.sun.prism.impl.Disposer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineArea extends JPanel implements ActionListener, MouseListener {
    JButton reStart = new JButton("重新开始");
    Block[][] block;
    BlockView[][] blockView;
    LayMines lay;
    int row;
    int colum;
    int mineCount;
    int markMount;
    ImageIcon mark = new ImageIcon("mark.png");
    int grade;
    JPanel pCenter;
    JPanel pNorth;
    JTextField showTime = new JTextField(5);
    JTextField showMarkedMineCount = new JTextField(5);
    Timer time = new Timer(1000, this);
    int spendTime = 0;
    Record record;
    JDialog lose = new JDialog();
    JPanel panel;
    JLabel str;
    JButton restart1 = new JButton("重新开始");
    JButton cancel = new JButton("取消");

    public MineArea(int row, int colum, int mineCount, int grade) {
        this.showTime.setHorizontalAlignment(0);
        this.showTime.setFont(new Font("Arial", 1, 16));
        this.showTime.setDisabledTextColor(Color.black);
        this.showTime.setBorder(BorderFactory.createLineBorder(Color.gray));
        this.showTime.setEnabled(false);
        this.showMarkedMineCount.setHorizontalAlignment(0);
        this.showMarkedMineCount.setFont(new Font("Arial", 1, 16));
        this.showMarkedMineCount.setDisabledTextColor(Color.black);
        this.showMarkedMineCount.setBorder(BorderFactory.createLineBorder(Color.gray));
        this.showMarkedMineCount.setEnabled(false);
        this.pCenter = new JPanel();
        this.pNorth = new JPanel();
        this.lay = new LayMines();
        this.initMineArea(row, colum, mineCount, grade);
        this.reStart.addActionListener(this);
        this.pNorth.add(this.showMarkedMineCount);
        this.pNorth.add(this.reStart);
        this.pNorth.add(this.showTime);
        this.setLayout(new BorderLayout());
        this.add(this.pNorth, "North");
        this.add(this.pCenter, "Center");
    }

    public void initMineArea(int row, int colum, int mineCount, int grade) {
        this.pCenter.removeAll();
        this.spendTime = 0;
        this.markMount = mineCount;
        this.row = row;
        this.colum = colum;
        this.mineCount = mineCount;
        this.grade = grade;
        this.block = new Block[row][colum];

        int i;
        int j;
        for(i = 0; i < row; ++i) {
            for(j = 0; j < colum; ++j) {
                this.block[i][j] = new Block();
            }
        }

        this.blockView = new BlockView[row][colum];
        this.pCenter.setLayout(new GridLayout(row, colum));

        for(i = 0; i < row; ++i) {
            for(j = 0; j < colum; ++j) {
                this.blockView[i][j] = new BlockView();
                this.pCenter.add(this.blockView[i][j]);
                this.blockView[i][j].getBlockCover().addActionListener(this);
                this.blockView[i][j].getBlockCover().addMouseListener(this);
                this.blockView[i][j].seeBlockCover();
                this.blockView[i][j].getBlockCover().setEnabled(true);
                this.blockView[i][j].getBlockCover().setIcon((Icon)null);
            }
        }

        this.showMarkedMineCount.setText("" + this.markMount);
        this.validate();
    }

    public void initMine(int m, int n) {
        this.lay.layMinesForBlock(this.block, this.mineCount, m, n);

        for(int i = 0; i < this.row; ++i) {
            for(int j = 0; j < this.colum; ++j) {
                this.blockView[i][j].giveView(this.block[i][j]);
            }
        }

    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.restart1) {
            this.initMineArea(this.row, this.colum, this.mineCount, this.grade);
            this.lose.dispose();
        } else if (e.getSource() == this.cancel) {
            this.lose.dispose();
        } else {
            int temp = 0;
            if (e.getSource() != this.reStart && e.getSource() != this.time) {
                this.time.start();
                int m = -1;
                int n = -1;

                int i;
                int j;
                for(i = 0; i < this.row; ++i) {
                    for(j = 0; j < this.colum; ++j) {
                        if (this.block[i][j].getIsMine()) {
                            temp = -1;
                        }

                        if (e.getSource() == this.blockView[i][j].getBlockCover()) {
                            m = i;
                            n = j;
                        }
                    }
                }

                if (temp == 0) {
                    this.initMine(m, n);
                }

                if (this.block[m][n].getIsMine()) {
                    for(i = 0; i < this.row; ++i) {
                        for(j = 0; j < this.colum; ++j) {
                            this.blockView[i][j].getBlockCover().setEnabled(false);
                            if (this.block[i][j].getIsMine()) {
                                this.blockView[i][j].seeBlockNameOrIcon();
                            }
                        }
                    }

                    this.panel = new JPanel();
                    this.panel.setLayout(new FlowLayout());
                    this.str = new JLabel("你输了哦！", 0);
                    this.restart1.addActionListener(this);
                    this.cancel.addActionListener(this);
                    this.lose.setTitle("输了");
                    this.lose.setBounds(300, 100, 200, 150);
                    this.lose.setResizable(false);
                    this.lose.setVisible(false);
                    this.lose.setModal(true);
                    this.time.stop();
                    this.spendTime = 0;
                    this.markMount = this.mineCount;
                    this.lose.add(this.str, "Center");
                    this.panel.add(this.restart1);
                    this.panel.add(this.cancel);
                    this.lose.add(this.panel, "South");
                    this.lose.setVisible(true);
                } else {
                    this.show(m, n);
                }
            }

            if (e.getSource() == this.reStart) {
                this.initMineArea(this.row, this.colum, this.mineCount, this.grade);
            }

            if (e.getSource() == this.time) {
                ++this.spendTime;
                this.showTime.setText("" + this.spendTime);
            }

            this.inquireWin();
        }
    }

    public void show(int m, int n) {
        if (this.block[m][n].getAroundMineNumber() > 0 && !this.block[m][n].getIsOpen()) {
            this.blockView[m][n].seeBlockNameOrIcon();
            this.block[m][n].setIsOpen(true);
        } else {
            if (this.block[m][n].getAroundMineNumber() == 0 && !this.block[m][n].getIsOpen()) {
                this.blockView[m][n].seeBlockNameOrIcon();
                this.block[m][n].setIsOpen(true);

                for(int k = Math.max(m - 1, 0); k <= Math.min(m + 1, this.row - 1); ++k) {
                    for(int t = Math.max(n - 1, 0); t <= Math.min(n + 1, this.colum - 1); ++t) {
                        this.show(k, t);
                    }
                }
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton source = (JButton)e.getSource();

        for(int i = 0; i < this.row; ++i) {
            for(int j = 0; j < this.colum; ++j) {
                if (e.getModifiers() == 4 && source == this.blockView[i][j].getBlockCover()) {
                    if (this.block[i][j].getIsMark()) {
                        source.setIcon((Icon)null);
                        this.block[i][j].setIsMark(false);
                        ++this.markMount;
                        this.showMarkedMineCount.setText("" + this.markMount);
                    } else {
                        source.setIcon(this.mark);
                        this.block[i][j].setIsMark(true);
                        --this.markMount;
                        this.showMarkedMineCount.setText("" + this.markMount);
                    }
                }
            }
        }

    }

    public void inquireWin() {
        int number = 0;

        for(int i = 0; i < this.row; ++i) {
            for(int j = 0; j < this.colum; ++j) {
                if (!this.block[i][j].getIsOpen()) {
                    ++number;
                }
            }
        }

        if (number == this.mineCount) {
            this.time.stop();
            this.record = new Record();
            switch(this.grade) {
                case 1:
                    this.record.setGrade("初级");
                    break;
                case 2:
                    this.record.setGrade("中级");
                    break;
                case 3:
                    this.record.setGrade("高级");
            }

            this.record.setTime(this.spendTime);
            this.record.setVisible(true);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
