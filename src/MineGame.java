import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;

public class MineGame extends JFrame implements ActionListener {
    JMenuBar bar;
    JMenu fileMenu1;
    JMenu fileMenu2;
    JMenuItem junior;
    JMenuItem middle;
    JMenuItem senior;
    JMenuItem custom;
    JMenuItem mineSweeperList;
    JMenuItem introduction;
    JMenuItem palyMethod;
    MineArea mineArea = null;
    File heroList = new File("heroList.txt");
    Hashtable hashtable = null;
    ShowRecord showHeroRecord = null;
    JDialog set = null;
    JPanel panel;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JLabel label;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextField row = null;
    JTextField column = null;
    JTextField mine = null;
    JButton confirm;
    JButton cancel;
    JDialog introduce = null;
    JDialog play = null;
    JLabel label4;
    JLabel label5;

    MineGame() {
        this.mineArea = new MineArea(16, 16, 40, 2);
        this.add(this.mineArea, "Center");
        this.bar = new JMenuBar();
        this.fileMenu1 = new JMenu("游戏");
        this.junior = new JMenuItem("junior");
        this.middle = new JMenuItem("middle");
        this.senior = new JMenuItem("senior");
        this.custom = new JMenuItem("自定义");
        this.mineSweeperList = new JMenuItem("mineSweeperList");
        this.fileMenu1.add(this.junior);
        this.fileMenu1.add(this.middle);
        this.fileMenu1.add(this.senior);
        this.fileMenu1.add(this.custom);
        this.fileMenu1.add(this.mineSweeperList);
        this.fileMenu2 = new JMenu("帮助");
        this.introduction = new JMenuItem("介绍");
        this.palyMethod = new JMenuItem("玩法");
        this.fileMenu2.add(this.introduction);
        this.fileMenu2.add(this.palyMethod);
        this.bar.add(this.fileMenu1);
        this.bar.add(this.fileMenu2);
        this.setJMenuBar(this.bar);
        this.junior.addActionListener(this);
        this.middle.addActionListener(this);
        this.senior.addActionListener(this);
        this.custom.addActionListener(this);
        this.mineSweeperList.addActionListener(this);
        this.introduction.addActionListener(this);
        this.palyMethod.addActionListener(this);
        this.hashtable = new Hashtable();
        this.hashtable.put("junior", "junior#999#匿名");
        this.hashtable.put("middle", "middle#999#匿名");
        this.hashtable.put("senior", "senior#999#匿名");
        if (!this.heroList.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(this.heroList);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(this.hashtable);
                objectOut.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.showHeroRecord = new ShowRecord(this, this.hashtable);
        this.setBounds(300, 100, 480, 560);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.junior) {
            this.mineArea.initMineArea(9, 9, 10, 1);
            this.setBounds(300, 100, 270, 350);
        }

        if (e.getSource() == this.middle) {
            this.mineArea.initMineArea(16, 16, 40, 2);
            this.setBounds(300, 100, 480, 560);
        }

        if (e.getSource() == this.senior) {
            this.mineArea.initMineArea(16, 30, 99, 3);
            this.setBounds(100, 100, 900, 560);
        }

        if (e.getSource() == this.custom) {
            this.set = new JDialog();
            this.set.setTitle("自定义难度");
            this.set.setBounds(300, 100, 300, 130);
            this.set.setResizable(false);
            this.set.setModal(true);
            this.panel = new JPanel();
            this.panel1 = new JPanel();
            this.panel2 = new JPanel();
            this.panel3 = new JPanel();
            this.panel4 = new JPanel();
            this.label = new JLabel("请输入行列数与地雷数：", 0);
            this.label1 = new JLabel("行：", 0);
            this.label2 = new JLabel("列：", 0);
            this.label3 = new JLabel("地雷数：", 0);
            this.row = new JTextField();
            this.row.setText("16");
            this.row.setSize(1, 6);
            this.column = new JTextField();
            this.column.setText("16");
            this.mine = new JTextField();
            this.mine.setText("40");
            this.confirm = new JButton("confirm");
            this.confirm.addActionListener(this);
            this.cancel = new JButton("cancel");
            this.cancel.addActionListener(this);
            this.panel1.add(this.label1);
            this.panel1.add(this.row);
            this.panel2.add(this.label2);
            this.panel2.add(this.column);
            this.panel3.add(this.label3);
            this.panel3.add(this.mine);
            this.panel4.add(this.confirm);
            this.panel4.add(this.cancel);
            this.panel.add(this.panel1);
            this.panel.add(this.panel2);
            this.panel.add(this.panel3);
            this.set.add(this.label, "North");
            this.set.add(this.panel, "Center");
            this.set.add(this.panel4, "South");
            this.set.setVisible(true);
        }

        if (e.getSource() == this.mineSweeperList && this.showHeroRecord != null) {
            this.showHeroRecord.setVisible(true);
        }

        if (e.getSource() == this.confirm) {
            int rowNum = Integer.parseInt(this.row.getText());
            int columnNum = Integer.parseInt(this.column.getText());
            int mineNum = Integer.parseInt(this.mine.getText());
            if (rowNum < 9) {
                rowNum = 9;
            }

            if (rowNum > 16) {
                rowNum = 16;
            }

            if (columnNum < 9) {
                columnNum = 9;
            }

            if (columnNum > 30) {
                columnNum = 30;
            }

            if (mineNum < 10) {
                mineNum = 10;
            }

            if (mineNum > 99) {
                mineNum = 99;
            }

            this.mineArea.initMineArea(rowNum, columnNum, mineNum, 4);
            this.setBounds(100, 100, columnNum * 30, rowNum * 30 + 80);
            this.set.setVisible(false);
        }

        if (e.getSource() == this.cancel) {
            this.set.setVisible(false);
        }

        if (e.getSource() == this.introduction) {
            this.introduce = new JDialog();
            this.introduce.setTitle("扫雷介绍");
            this.introduce.setBounds(300, 100, 300, 300);
            this.introduce.setResizable(false);
            this.introduce.setModal(true);
            this.label4 = new JLabel();
            this.label4.setSize(280, 250);
            this.label4.setText("<html><body>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp扫雷最原始的版本可以追溯到1973年一款名为“方块”的游戏。不久，“方块”被改写成了游戏“Rlogic”。在“Rlogic”里，玩家的任务是作为美国海军陆战队队员，为指挥中心探出一条没有地雷的安全路线，如果路全被地雷堵死就算输。两年后，汤姆·安德森在“Rlogic”的基础上又编写出了游戏“地雷”，由此奠定了现代扫雷游戏的雏形。1981年，微软公司的罗伯特·杜尔和卡特·约翰逊两位工程师在Windows3.1系统上加载了该游戏，扫雷游戏才正式在全世界推广开来。</body></html>");
            this.introduce.add(this.label4);
            this.introduce.setVisible(true);
        }

        if (e.getSource() == this.palyMethod) {
            this.play = new JDialog();
            this.play.setTitle("游戏玩法");
            this.play.setBounds(300, 100, 300, 300);
            this.play.setResizable(false);
            this.play.setModal(true);
            this.label4 = new JLabel();
            this.label4.setSize(280, 250);
            this.label4.setText("<html><body>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp扫游戏目标是在最短的时间内根据点击格子出现的数字找出所有非雷格子，同时避免踩雷。<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp当玩家点开一个格子时,会在该格子上显示周围8个格子的雷数（如果没有雷则自动点开周围的格子），玩家需要通过这些数字来判断雷的位置，将是雷的格子标记为小红旗。当所有地雷被标记且非雷格子都被点开时游戏胜利。</body></html>");
            this.play.add(this.label4);
            this.play.setVisible(true);
        }

        this.validate();
    }

    public static void main(String[] args) {
        new MineGame();
    }
}
