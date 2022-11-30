import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class Words extends JLabel implements Runnable{
    int y=0;
    int x=0;

    String name_str="1";

    public Words(String name){
        super(name);//레이블 이름 세팅
        this.setFont(new Font("고딕",Font.BOLD,20));//폰트 세팅
    }

    @Override
    public void run() {
        x = (int)(Math.random()*700);
        y = (int)(Math.random()*-500);
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            y += (int)(Math.random() * 10);
            this.setBounds(x, y, 100, 100);
            if(y > 600)
                y = (int)(Math.random()*-500);

        }
    }
}

public class gameScreen extends JFrame implements ActionListener,KeyListener {
    final int max = 20;
    int count = 0;


    JPanel Pright = new JPanel();
    JPanel PCenter = new JPanel();
    JPanel PSouth = new JPanel();
    JTextField tx = new JTextField(20);
    JLabel jl1 = new JLabel("점수");
    JButton jb = new JButton("확인");
    Words[] wr = new Words[max];
    Thread[] th = new Thread[max];
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




        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        for (int i = 0; i < max; i++)
            PCenter.add(wr[i] = new Words(str[(int) (Math.random() * 25)]));



        jl1.setFont(new Font("고딕", Font.BOLD, 20));
        PSouth.add(tx);
        PSouth.add(jb);
        Pright.add(jl1);

        jb.addActionListener(this);//버튼 리스너
        tx.addKeyListener(this);

        for (int i = 0; i < max; i++)
        {
            th[i] = new Thread(wr[i]);
            th[i].start();
        }
        this.setVisible(true);

        add(Pright, BorderLayout.LINE_END);
        add(PCenter, BorderLayout.CENTER);
        add(PSouth, BorderLayout.PAGE_END);

        tx.requestFocus();
        this.setFocusable(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)//텍스트 필드에 엔터 들어오면 넘어가게
        {
            for (int i = 0; i < max; i++)
                if ((tx.getText()).equals(wr[i].getText()))
                {
                    if (wr[i].name_str.equals("1"))
                    {
                        jl1.setText("점수:" + ++count + "");
                        wr[i].name_str = "2";
                    }

                    PCenter.remove(wr[i]);//텍스트 필드랑 단어 같으면 삭제
                    repaint();//다시 그려 줌

                    th[i] = null;//스레드 없애기

                }

                tx.setText("");//텍스트 필드 단어 없애기

        }
        if (count == 20) {//게임 끝 화면
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