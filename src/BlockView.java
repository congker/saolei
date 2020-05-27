import com.sun.deploy.ui.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class BlockView extends JPanel {
    JLabel blockNameOrIcon;
    JButton blockCover;
    CardLayout card = new CardLayout();
    ImageIcon icon1;
    ImageIcon icon2;
    ImageIcon icon3;
    ImageIcon icon4;
    ImageIcon icon5;
    ImageIcon icon6;
    ImageIcon icon7;
    ImageIcon icon8;

    BlockView() {
        this.setLayout(this.card);
        this.blockNameOrIcon = new JLabel("", 0);
        this.blockNameOrIcon.setHorizontalTextPosition(0);
        this.blockNameOrIcon.setVerticalTextPosition(0);
        this.blockCover = new JButton();
        this.add("cover", this.blockCover);
        this.add("view", this.blockNameOrIcon);
    }

    public void giveView(Block block) {
        if (block.isMine) {
            this.blockNameOrIcon.setIcon(block.getMineicon());
        } else {
            int n = block.getAroundMineNumber();
            System.out.println("file:" + getClass().getResource("1.jpg"));
            if (n == 1) {
//                URL imgURL = getResource("images/1.jpg");
                this.icon1 = new ImageIcon("1.jpg");
                this.blockNameOrIcon.setIcon(this.icon1);
            }

            if (n == 2) {
//                URL imgURL = getResource("images/2.jpg");
                this.icon2 = new ImageIcon("2.jpg");
                this.blockNameOrIcon.setIcon(this.icon2);
            }

            if (n == 3) {
//                URL imgURL = getResource("images/3.jpg");
                this.icon3 = new ImageIcon("3.jpg");
                this.blockNameOrIcon.setIcon(this.icon3);
            }

            if (n == 4) {
//                URL imgURL = getResource("images/4.jpg");
                this.icon4 = new ImageIcon("4.jpg");
                this.blockNameOrIcon.setIcon(this.icon4);
            }

            if (n == 5) {
//                URL imgURL = getResource("images/5.jpg");
                this.icon5 = new ImageIcon("5.jpg");
                this.blockNameOrIcon.setIcon(this.icon5);
            }

            if (n == 6) {
//                URL imgURL = getResource("images/6.jpg");
                this.icon6 = new ImageIcon("6.jpg");
                this.blockNameOrIcon.setIcon(this.icon6);
            }

            if (n == 7) {
//                URL imgURL = getResource("images/7.jpg");
                this.icon7 = new ImageIcon("7.jpg");
                this.blockNameOrIcon.setIcon(this.icon7);
            }

            if (n == 8) {
//                URL imgURL = getResource("images/8.jpg");
                this.icon8 = new ImageIcon("8.jpg");
                this.blockNameOrIcon.setIcon(this.icon8);
            } else {
                this.blockNameOrIcon.setText(" ");
            }
        }

    }

    public void seeBlockNameOrIcon() {
        this.card.show(this, "view");
        this.validate();
    }

    public void seeBlockCover() {
        this.card.show(this, "cover");
        this.validate();
    }

    public JButton getBlockCover() {
        return this.blockCover;
    }

    public void main(String[] args) {
        URL url = this.getClass().getResource("/");
        System.out.println("path:" + url.getPath());
//        InputStream imgURL = ImageLoader.class.getResourceAsStream("src\\images\\1.jpg");
//        System.out.println("imageUrl:" + imgURL);
//        ImageIcon icon = new ImageIcon("src\\images\\1.jpg");
//        System.out.println("icon:" + icon);
    }
}
