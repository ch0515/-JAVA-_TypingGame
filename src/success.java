import javax.swing.*;
import java.awt.*;

public class success extends JFrame{
    private Image background=new ImageIcon(Screen.class.getResource("img/happyTarzan.jpg")).getImage();
    success(){
        // 프레임 보이기 지정
        setTitle("당신이 타잔을 행복하게 해주었습니다!");
        setVisible(true);
        setSize(1000, 700);	//화면 사이즈
        setResizable(false);	//사이즈 재조정 불가능
        setLocationRelativeTo(null);	//창이 가운데에 뜨도록 설정
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(Screen.class.getResource("img/jungleicn.png")));//이상한 외국인이 알려준 프레임 창 아이콘 넣기
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void paint(Graphics g) {//그리는 함수
        g.drawImage(background,0,0, null);//logo 그려줌
    }
}
