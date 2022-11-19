
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Screen extends JFrame{


    private Image background=new ImageIcon(Screen.class.getResource("img/main (4).jpg")).getImage();
    ImageIcon img = new ImageIcon(Screen.class.getResource("img/startbu.png"));
    JButton bt_img=new JButton(img);
    private Image logo = new ImageIcon(Screen.class.getResource("img/logo.png")).getImage();
    //ImageIcon iconImg = new ImageIcon("img/jungleicn.png");

    Screen(){

        homeframe();
        btn();
        bt_img.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gameScreen();
                setVisible(false); // 창 안보이게 하기
            }
        });//end addActionListener
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(Screen.class.getResource("img/jungleicn.png")));//gui 창 아이콘 넣기
    }//end Screen
    public void homeframe() {
        setTitle("시작화면");// 시작화면 출력
        setVisible(true);	//창을 눈에 보이도록 함
        setSize(1000, 700);	//사이즈는 640*480
        setResizable(false);	//사이즈 재조정 불가능
        setLocationRelativeTo(null);	//창이 가운데에 뜨도록 함
        setLayout(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);	//창을 끄면 프로그램을 종료
    }//end homeframe
    public void paint(Graphics g) {//그리는 함수
        g.drawImage(background, 0, 0, null);//background를 그려줌
        g.drawImage(logo, 250, 60, null);//logo 그려줌
    }//end paing

    /*버튼설정*/
    public void btn() {
        bt_img.setLayout(null);
        bt_img.setBounds(370,350,270,100);// 버튼 위치 & 크기 지정
        add(bt_img);
        bt_img.setIcon(img);
        bt_img.setBorderPainted(false);
    }//end btn

    public static void main(String[] args) {
        new Screen();
    }//end main
}//end class

