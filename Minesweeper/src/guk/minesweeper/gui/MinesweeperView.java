package guk.minesweeper.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MinesweeperView {
    private int gameFieldSize;
    private JFrame frame;

    private JMenuItem newGame;
    private JMenuItem highScores;
    private JMenuItem about;
    private JMenuItem exit;

    private JTextField score;
    private JTextField time;
    private JButton newGameButton;


    public MinesweeperView(int gameFieldSize) {
        this.gameFieldSize = gameFieldSize;

        frame = new JFrame("Сапер");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setSize(getFrameSize());// frame.setMinimumSize(getFrameSize());

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createSettingsMenu());
        frame.setJMenuBar(menuBar);

        JPanel gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setPreferredSize(new Dimension(200, 50));
        score = new JTextField("Score");
        time = new JTextField("Time");
        newGameButton = new JButton("New");
        gamePanel.add(score);
        gamePanel.add(newGameButton);
        gamePanel.add(time);

        JPanel gameFieldPanel = new JPanel(new GridLayout(gameFieldSize, gameFieldSize));
        gameFieldPanel.setMinimumSize(new Dimension(250,250));
        JButton[] buttonsArray = new JButton[(int) Math.pow(gameFieldSize, 2)];
        for (int i = 0; i < buttonsArray.length; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(20, 20));
           // button.setIcon(new ImageIcon("./Minesweeper/src/guk/minesweeper/resources/closed.png"));
            buttonsArray[i] = button;
        }

        for (JButton b : buttonsArray) {
            gameFieldPanel.add(b);
        }

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainBox.add(gamePanel);
        mainBox.add(gameFieldPanel);


        frame.add(mainBox);
        frame.pack();
        frame.setVisible(true);


    }

 /*   private Dimension getFrameSize() { //todo: передалать на точные размеры? нужно будет для уровней в большим количество клеток
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        double widthMultiplier = (double) 1 / 5;
        double heightMultiplier = (double) 1 / 2.5;

        screenSize.setSize((int) (width * widthMultiplier), (int) (height*heightMultiplier));

        return screenSize;
    }
*/
    private JMenu createSettingsMenu() {
        JMenu menu = new JMenu("Настройки");
        newGame = new JMenuItem("Новая игра");
        highScores = new JMenuItem("Таблица рекордов");
        about = new JMenuItem("Об игре");
        exit = new JMenuItem("Выйти");

        menu.add(newGame);
        menu.add(highScores);
        menu.add(about);
        menu.add(exit);

        return menu;
    }
}
