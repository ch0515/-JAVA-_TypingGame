
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class gameScreen extends JFrame implements ActionListener, Runnable {
    static final int WIDTH = 900;
    static final int HEIGHT = 600;
    int life = 10;
    Vector<String> words;
    Vector<Word> viewingWords;
    BufferedReader inputStream;
    Thread t;
    long time;

    DropArea da1;
    JTextField t1;

    private Image background=new ImageIcon(Screen.class.getResource("img/background.jpg")).getImage();

    public gameScreen() throws IOException {
        // 단어 목록 읽기
        setTitle("게임화면");
//        setSize(300, 200);
        setIconImage(Toolkit.getDefaultToolkit().
                getImage(Screen.class.getResource("img/jungleicn.png")));//이상한 외국인이 알려준 프레임 창 아이콘 넣기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            inputStream = new BufferedReader(new FileReader("words.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        words = new Vector<String>();
        viewingWords = new Vector<Word>();
        String w;
        while((w = inputStream.readLine()) != null)
        {
            words.add(w);
        }
        da1 = new DropArea();
        da1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(da1, BorderLayout.CENTER);
        t1 = new JTextField(20);
        t1.addActionListener(this);
        add(t1, BorderLayout.SOUTH);

        t = new Thread(this);

        pack();
        setVisible(true);
        t.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        int index = -1;
        for(Word w : viewingWords) {
            if(t1.getText().equals(w.str))
            {
                index = viewingWords.indexOf(w);
            }
        }
        if(index != -1)
        {
            viewingWords.remove(index);
            repaint();
        }
        t1.selectAll();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true)
        {
            try {
                t.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            time++;

            for(Word w : viewingWords)
            {
                w.y += 10;
            }

            if(!viewingWords.isEmpty())
            {
                if(viewingWords.get(0).y > HEIGHT-50)
                {
                    life--;
                    viewingWords.remove(0);
                }
            }

            if(life <= 0)
            {
                JOptionPane.showMessageDialog(this, "게임종료(게임시간:" + time/10.0 + "초)");
                t.stop();//게임 종료 시
            }

            if(time % 50 == 0)
            {
                viewingWords.add(new Word());
            }


            repaint();

        }

    }

    class DropArea extends JComponent
    {
        public void paint(Graphics g)
        {
            g.drawString("life="+life, 10, 10);
            for(Word w: viewingWords)
            {
                g.setColor(Color.BLACK);
                g.drawString(w.str, w.x, w.y);
            }
        }
    }

    class Word
    {
        public int x;
        public int y;
        String str;

        Word()
        {
            x = (int) (Math.random() * WIDTH - 40);
            y = 0;

            str = words.get((int)(Math.random() * words.size()));
        }
    }


//    public static void main(String[] args) throws IOException {
//        // TODO Auto-generated method stub
//        new gameScreen();
//    }
//    public void paint(Graphics g) {//그리는 함수
//        g.drawImage(background, 0, 0, null);//background를 그려줌
//    }//추가하면 입력칸하고 단어 내려오는 것들이 안보임 <-- 왜?지
}