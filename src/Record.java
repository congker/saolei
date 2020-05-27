import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Record extends JDialog implements ActionListener {
    int time = 0;
    String grade = null;
    String key = null;
    String message = null;
    JTextField textName;
    JLabel label = null;
    JButton confirm;
    JButton cancel;

    public Record() {
        this.setTitle("记录你的成绩");
        this.setBounds(300, 300, 240, 160);
        this.setResizable(false);
        this.setModal(true);
        this.confirm = new JButton("确定");
        this.cancel = new JButton("取消");
        this.textName = new JTextField(8);
        this.textName.setText("匿名");
        this.confirm.addActionListener(this);
        this.cancel.addActionListener(this);
        this.setLayout(new GridLayout(2, 1));
        this.label = new JLabel("您现在是...高手,输入您的大名上榜");
        this.add(this.label);
        JPanel p = new JPanel();
        p.add(this.textName);
        p.add(this.confirm);
        p.add(this.cancel);
        this.add(p);
        this.setDefaultCloseOperation(1);
    }

    public void setGrade(String grade) {
        this.grade = grade;
        this.label.setText("您现在是" + grade + "高手,输入您的大名上榜");
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.confirm) {
            this.message = this.grade + "#" + this.time + "#" + " " + this.textName.getText();
            this.key = this.grade;
            this.writeRecord(this.key, this.message);
            this.setVisible(false);
        }

        if (e.getSource() == this.cancel) {
            this.setVisible(false);
        }

    }

    public void writeRecord(String key, String message) {
        File f = new File("英雄榜.txt");

        try {
            FileInputStream in = new FileInputStream(f);
            ObjectInputStream objectIn = new ObjectInputStream(in);
            Hashtable hashtable = (Hashtable) objectIn.readObject();
            objectIn.close();
            in.close();
            String temp = (String) hashtable.get(key);
            StringTokenizer fenxi = new StringTokenizer(temp, "#");
            fenxi.nextToken();
            int n = Integer.parseInt(fenxi.nextToken());
            if (this.time < n) {
                hashtable.put(key, message);
                FileOutputStream out = new FileOutputStream(f);
                ObjectOutputStream objectOut = new ObjectOutputStream(out);
                objectOut.writeObject(hashtable);
                objectOut.close();
                out.close();
            }
        } catch (Exception var12) {
            System.out.println(var12);
        }

    }
}
