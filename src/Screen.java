
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JFrame{

    JPanel pan1;
    private Image background=new ImageIcon(Screen.class.getResource("img/main (2).jpg")).getImage();
    ImageIcon img = new ImageIcon(Objects.requireNonNull(Screen.class.getResource("img/StartBut (1).png")));
    JButton bt_img=new JButton(img);
    Screen(){

        homeframe();
        btn();
    }
    public void homeframe() {
        setTitle("시작화면");// 시작화면 출력
        setVisible(true);	//창을 눈에 보이도록 함
        setSize(1000, 700);	//사이즈는 640*480
        setResizable(false);	//사이즈 재조정 불가능
        setLocationRelativeTo(null);	//창이 가운데에 뜨도록 함
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);	//창을 끄면 프로그램을 종료
    }
    public void paint(Graphics g) {//그리는 함수
        g.drawImage(background, 0, 0, null);//background를 그려줌
    }
    /*버튼설정*/
    public void btn() {
        bt_img.setLayout(null);
        bt_img.setBounds(350,200,300,100);
        add(bt_img);
        bt_img.setIcon(img);
    }
    public static void main(String[] args) {
        new Screen();
    }

}