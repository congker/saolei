import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class ShowRecord extends JDialog implements ActionListener {
    File file = new File("英雄榜.txt");
    String name = null;
    Hashtable hashtable = null;
    JButton show;
    JButton resetScore;
    JLabel[] juniorLabel;
    JLabel[] middleLabel;
    JLabel[] seniorLabel;

    public ShowRecord(JFrame frame, Hashtable h) {
        this.setTitle("扫雷榜");
        this.hashtable = h;
        this.setBounds(300, 300, 320, 185);
        this.setResizable(false);
        this.setVisible(false);
        this.setModal(true);
        this.juniorLabel = new JLabel[3];
        this.middleLabel = new JLabel[3];
        this.seniorLabel = new JLabel[3];

        for (int i = 0; i < 3; ++i) {
            this.juniorLabel[i] = new JLabel();
            this.juniorLabel[i].setBorder((Border) null);
            this.middleLabel[i] = new JLabel();
            this.middleLabel[i].setBorder((Border) null);
            this.seniorLabel[i] = new JLabel();
            this.seniorLabel[i].setBorder((Border) null);
        }

        this.juniorLabel[0].setText("初级");
        this.juniorLabel[1].setText("999");
        this.juniorLabel[1].setText("匿名");
        this.middleLabel[0].setText("中级");
        this.middleLabel[1].setText("999");
        this.middleLabel[1].setText("匿名");
        this.seniorLabel[0].setText("高级");
        this.seniorLabel[1].setText("999");
        this.seniorLabel[1].setText("匿名");
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(3, 3));

        int i;
        for (i = 0; i < 3; ++i) {
            pCenter.add(this.juniorLabel[i]);
        }

        for (i = 0; i < 3; ++i) {
            pCenter.add(this.middleLabel[i]);
        }

        for (i = 0; i < 3; ++i) {
            pCenter.add(this.seniorLabel[i]);
        }

        pCenter.setBorder(BorderFactory.createTitledBorder("扫雷榜"));
        this.show = new JButton("最佳成绩");
        this.resetScore = new JButton("resetScore");
        this.show.addActionListener(this);
        this.resetScore.addActionListener(this);
        JPanel pSouth = new JPanel();
        pSouth.setLayout(new FlowLayout(2));
        pSouth.add(this.resetScore);
        pSouth.add(this.show);
        this.add(pCenter, "Center");
        this.add(pSouth, "South");
    }

    public void readAndShow() {
        try {
            FileInputStream in = new FileInputStream(this.file);
            ObjectInputStream object_in = new ObjectInputStream(in);
            this.hashtable = (Hashtable) object_in.readObject();
            object_in.close();
            in.close();
            String temp = (String) this.hashtable.get("初级");
            StringTokenizer fenxi = new StringTokenizer(temp, "#");
            this.juniorLabel[0].setText(fenxi.nextToken());
            this.juniorLabel[1].setText(fenxi.nextToken());
            this.juniorLabel[2].setText(fenxi.nextToken());
            temp = (String) this.hashtable.get("中级");
            fenxi = new StringTokenizer(temp, "#");
            this.middleLabel[0].setText(fenxi.nextToken());
            this.middleLabel[1].setText(fenxi.nextToken());
            this.middleLabel[2].setText(fenxi.nextToken());
            temp = (String) this.hashtable.get("高级");
            fenxi = new StringTokenizer(temp, "#");
            this.seniorLabel[0].setText(fenxi.nextToken());
            this.seniorLabel[1].setText(fenxi.nextToken());
            this.seniorLabel[2].setText(fenxi.nextToken());
        } catch (Exception var5) {
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.resetScore) {
            this.hashtable.put("初级", "初级#999#匿名");
            this.juniorLabel[0].setText("初级");
            this.juniorLabel[1].setText("999");
            this.juniorLabel[2].setText("匿名");
            this.hashtable.put("中级", "中级#999#匿名");
            this.middleLabel[0].setText("中级");
            this.middleLabel[1].setText("999");
            this.middleLabel[2].setText("匿名");
            this.hashtable.put("高级", "高级#999#匿名");
            this.seniorLabel[0].setText("高级");
            this.seniorLabel[1].setText("999");
            this.seniorLabel[2].setText("匿名");

            try {
                FileOutputStream out = new FileOutputStream(this.file);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(this.hashtable);
                objectOut.close();
                out.close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }

            this.setVisible(false);
        }

        if (e.getSource() == this.show) {
            this.readAndShow();
        }

    }

}
