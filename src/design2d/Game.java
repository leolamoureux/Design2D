package design2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game extends JFrame {

    protected String drawingName;
    protected Scanner sc = new Scanner(System.in);
    protected SaveAndLoad data = new SaveAndLoad();
    protected PlayerDrawing player = new PlayerDrawing();
    protected Drawing drawing = new Drawing(drawingName);
    
    

    protected void mainWindow() {
        this.setTitle("Design2D");
        this.setFont(new java.awt.Font("Verdana",Font.BOLD,30));
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    protected void startGame() {
        mainWindow();
        
        JMenuBar menuBar = new JMenuBar();

        JMenu mainMenu = new JMenu("Game");
        mainMenu.setFont(new java.awt.Font("Verdana",Font.BOLD,20));
        mainMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(mainMenu);

        JMenuItem newParty = new JMenuItem("New drawing", KeyEvent.VK_N);
        mainMenu.add(newParty);
        newParty.addActionListener((event) -> newDrawing(menuBar));

        JMenuItem loadParty = new JMenuItem("Load drawing", KeyEvent.VK_L);
        mainMenu.add(loadParty);
        loadParty.addActionListener((event) -> {
            loadDrawing();
        });

        JMenuItem exitParty = new JMenuItem("Exit", KeyEvent.VK_E);
        mainMenu.add(exitParty);
        exitParty.addActionListener((event) -> System.exit(0));

        this.add(menuBar, BorderLayout.NORTH);
        this.setVisible(true);
    }

    protected void loadDrawing() {
        JPanel panelLoad = new JPanel(); repaint();
        panelLoad.setLayout(new FlowLayout());
        panelLoad.setBackground(Color.white);
        this.add(panelLoad, BorderLayout.CENTER);
   
        data.loadData();
        JLabel name = new JLabel(player.getNameDraw());
        panelLoad.add(name, BorderLayout.CENTER);
        name.setFont(new java.awt.Font("Verdana",Font.BOLD,20));
        name.setForeground(Color.ORANGE);
        System.out.println("draw's name : "+name.getText());
        for (JLabel lab : player.getDraw()) {
        	System.out.println("saved piece : "+lab.getText());
            panelLoad.add(lab, BorderLayout.CENTER);
        }
        this.setVisible(true);
    }

    
    protected void newDrawing(JMenuBar menuBar) {
      	System.out.println("add all your shapes, then move them where you want\n");
    	System.out.println("click left on the shapes to move them, right to see options\n");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        this.add(panel, BorderLayout.SOUTH);

        JLabel newDrawing = new JLabel("Enter a name for your draw : ");
        panel.add(newDrawing);
        int hexaColor = 0xE12323;
        Color myColor = new Color(hexaColor);
        newDrawing.setFont(new java.awt.Font("Verdana",Font.BOLD,20));
        newDrawing.setForeground(myColor);
        JTextField enterDrawingName = new JTextField(25);
        panel.add(enterDrawingName);
        JButton bouton = new JButton("Valider");
     
        bouton.addActionListener((event) -> {
        	drawingName = enterDrawingName.getText();
        	System.out.println("name's choice : "+drawingName);
        });
        
        panel.add(bouton);

        interaction(menuBar, panel);
    }

    protected void interaction(JMenuBar menuBar, JPanel panel) {
    	int hexaColor = 476847;
        Color myColor = new Color(hexaColor);
        JMenu mainMenu = new JMenu("Drawing");
        mainMenu.setFont(new java.awt.Font("Verdana",Font.BOLD,20));
        mainMenu.setForeground(myColor);
        mainMenu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(mainMenu);

        JPanel panelDraw = new JPanel();
        panelDraw.setLayout(new FlowLayout());
        panelDraw.setBackground(Color.white);
        this.add(panelDraw, BorderLayout.CENTER);

        JMenuItem addCircle = new JMenuItem("Add circle", KeyEvent.VK_C);
        mainMenu.add(addCircle);
        addCircle.addActionListener((event) -> {
            panelDraw.add(drawing.addCircle(panelDraw));
            this.setVisible(true);
        });

        JMenuItem addRectangle = new JMenuItem("Add rectangle", KeyEvent.VK_R);
        mainMenu.add(addRectangle);
        addRectangle.addActionListener((event) -> {
            panelDraw.add(drawing.addRectangle(panelDraw));
            this.setVisible(true);
        });

        JMenuItem removeAll = new JMenuItem("Remove drawing", KeyEvent.VK_E);
        mainMenu.add(removeAll);
        removeAll.addActionListener((event) -> drawing.removeAll(panelDraw));

        JMenuItem saveData = new JMenuItem("Save draw", KeyEvent.VK_S);
        mainMenu.add(saveData);
        saveData.addActionListener((event) -> {
            player.setNameDraw(drawingName);
            player.setDraw(drawing.listOfForms);
            data.saveData();
          
        });

        this.setVisible(true);
    }
}
