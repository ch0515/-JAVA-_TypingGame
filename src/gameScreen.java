import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameScreen extends JFrame {
    private Image background=new ImageIcon(Screen.class.getResource("img/background3.jpg")).getImage();
    gameScreen(){
        homeframe();
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(Screen.class.getResource("img/jungleicn.png")));//이상한 외국인이 알려준 프레임 창에 아이콘 넣기
    }
    public void homeframe() {
        setTitle("게임화면");// 시작화면 출력
        setVisible(true);	//창을 눈에 보이도록 함
        setSize(1000, 700);	//화면 사이즈
        setResizable(false);	//사이즈 재조정 불가능
        setLocationRelativeTo(null);	//창이 가운데에 뜨도록 설정
        setLayout(null);


        setDefaultCloseOperation(EXIT_ON_CLOSE);	//창을 끄면 프로그램을 종료
    }//end homeframe
    public void paint(Graphics g) {//그리는 함수
        g.drawImage(background, 0, 0, null);//background를 그려줌
    }//end paint

    public static void main(String[] args) {
        new gameScreen();
    }
}
