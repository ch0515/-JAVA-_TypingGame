import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class Words extends JLabel implements Runnable{
    int y=0;
    int x=0;

    String name_str="1";//점수 초기화

    public Words(String name){
        super(name);//레이블 이름 세팅
        this.setFont(new Font("고딕",Font.BOLD,20));//폰트 세팅
    }

    @Override
    public void run() {
        x = (int)(Math.random()*700);//처음 위치
        y = (int)(Math.random()*-500);//화면 위로 y값을 위치 시킨다.
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            y += (int)(Math.random() * 10); //y값 증가
            this.setBounds(x, y, 100, 100);
            if(y > 600)
                y = (int)(Math.random()*-500);//y값이 오버되면 다시 위로 올린다.

        }
    }
}

public class gameScreen extends JFrame implements ActionListener,KeyListener {
    final int max = 20;     //스레드, 단언, 레이블 수
    int count = 0;
    int failure = 0;
    private Image background=new ImageIcon(Screen.class.getResource("img/background3.jpg")).getImage();

    JPanel Pright = new JPanel();//오른쪽 패널
    JPanel PCenter = new JPanel();//센터 레이블들이 움직이는 곳
    JPanel PSouth = new JPanel();//텍스트 필드가 있는 곳
    JTextField tx = new JTextField(20);//텍스트 필드 생성
    JLabel jl1 = new JLabel("점수");//점수레이블
    JLabel jl2 = new JLabel();//게임끝을 나타내는 레이블 게임이 끝난다음에 setText("게임끝");으로 세팅한다.
    JButton jb = new JButton("확인");//버튼
    Words[] wr = new Words[max];//레이블이자 Runnable를 구현한 객체
    Thread[] th = new Thread[max];//스레드
    String str[] = {"int", "double", "String", "sizeof", "for", "while", "if", "else", "final",
            "static", "main", "return", "exception", "interface", "abstract", "thread", "stack",
            "!=", "package", "class", "void"
            , "onClick", "object", "System", "out", "println"};


    public gameScreen() throws InterruptedException {


        setTitle("게임 화면");
        setSize(900, 600);
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(Screen.class.getResource("img/jungleicn.png")));//이상한 외국인이 알려준 프레임 창 아이콘 넣기
        setResizable(false);	//사이즈 재조정 불가능
        setLocationRelativeTo(null);	//창이 가운데에 뜨도록 설정
        ImageIcon img = new ImageIcon("img/Tarzan1.png");



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        for (int i = 0; i < max; i++)
            PCenter.add(wr[i] = new Words(str[(int) (Math.random() * 25)]));//센터에 레이블을 붙인다.


        PCenter.add(jl2);//게임을 끝낼때 쓸려고 마련해 둔 레이블을 센터에 붙인다.
        jl1.setFont(new Font("고딕", Font.BOLD, 20));
        jl2.setFont(new Font("고딕", Font.BOLD, 30));
        jl1.setIcon(img);
        PSouth.add(tx); //텍스트 필드를 붙인다.
        PSouth.add(jb);//버튼을 붙인다.
        Pright.add(jl1);//레이블 jl1을 붙인다.

        jb.addActionListener(this);//버튼 리스너
        tx.addKeyListener(this);//tx필드 리스너

        for (int i = 0; i < max; i++)//스레드 생성
        {
            th[i] = new Thread(wr[i]);
            th[i].start();
        }
        this.setVisible(true);

        add(Pright, BorderLayout.LINE_END);//BorderLayout을 생성해서 맨 왼쪽에 붙인다.
        add(PCenter, BorderLayout.CENTER);//오른쪽에 붙인다.
        add(PSouth, BorderLayout.PAGE_END);//아랫단에 붙인다.

        tx.requestFocus();
        this.setFocusable(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

    }
//    public void paint(Graphics g) {//그리는 함수
//        g.drawImage(background, 0, 0, null);//background를 그려줌
//    }

    /*keyListener를 구현*/
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)//텍스트 필드에 엔터가 들어왔는지 검사해서 엔터를 쳤으면 통과
        {
            for (int i = 0; i < max; i++)
                if ((tx.getText()).equals(wr[i].getText()))//텍스트 필드에 있는 단어와 센터에서 떨어지고 있는 단어 중에 같은 것이 있는지 검사
                {
                    if (wr[i].name_str.equals("1"))//점수계산:같은 단어가 있으면 count를 증가 시키고 name_str를 2로 만들어 중복으로
                    {
                        jl1.setText("점수:" + ++count + "");
                        wr[i].name_str = "2";
                    }
                    PCenter.remove(wr[i]);//텍스트 필드와 단어가 같으면 레이블 삭제
                    repaint();//다시 그려 줌

                    th[i] = null;//스레드를 없애줌 성능 향상을 위해서

                }
            tx.setText("");//텍스트 필드에 기존 단어를  없애 줌
        }
        if (count == 20) {//단어가 다 사라지면 게임끝 레이블을 붙여줌
            new success();
            setVisible(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}