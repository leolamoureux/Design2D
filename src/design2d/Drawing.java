package design2d;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class Drawing extends MouseAdapter {

    public Drawing(String name) {
    }

    protected ArrayList<JLabel> listOfForms = new ArrayList<JLabel>();
    protected ArrayList<JLabel> listOfFormsSelected = new ArrayList<JLabel>();

    protected JLabel addCircle(JPanel jpanel) {
        JLabel newCircle = new JLabel("Circle");
        popUpForm(jpanel, newCircle);
        moveElement(newCircle);
        listOfForms.add(newCircle);
        newCircle.setFont(new java.awt.Font("Verdana",Font.BOLD,30));
        return newCircle;
    }

    protected JLabel addRectangle(JPanel jpanel) {
        JLabel newRectangle = new JLabel("Rectangle");
        popUpForm(jpanel, newRectangle);
        moveElement(newRectangle);
        listOfForms.add(newRectangle);
        newRectangle.setFont(new java.awt.Font("Verdana",Font.BOLD,30));
        return newRectangle;
    }

    protected void popUpForm(JPanel jpanel, JLabel label) {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem m1 = new JMenuItem("Select");
        JMenuItem m2 = new JMenuItem("Unselect");
        JMenuItem m3 = new JMenuItem("Remove");
        popup.add(m1);
        popup.add(m2);
        popup.add(m3);

        m2.setEnabled(false);

        m1.addActionListener((event) -> {
            listOfFormsSelected.add(label);
            m1.setEnabled(false);
            m2.setEnabled(true);
            label.setForeground(Color.red);
        });

        m2.addActionListener((event) -> {
            listOfFormsSelected.remove(label);
            m1.setEnabled(true);
            m2.setEnabled(false);
            label.setForeground(Color.black);
        });

        m3.addActionListener((event) -> {
            if (listOfFormsSelected.size()>1 && listOfFormsSelected.contains(label)) {
            	for(int i=0;i<listOfFormsSelected.size();i++) {
            		jpanel.remove(listOfFormsSelected.get(i));
            	}
            }
            jpanel.remove(label);
            jpanel.revalidate();
    		jpanel.repaint();
        });

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    label.add(popup);
                    popup.show(label, 20, 20);
                }
            }
        });
    }

    protected void moveElement(JLabel jlabel) {
        jlabel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                	if(listOfFormsSelected.size()>1 && listOfFormsSelected.contains(jlabel)) {
                		for(int i=0;i<listOfFormsSelected.size();i++) {
                			 JComponent jc = (JComponent) listOfFormsSelected.get(i);
                             jc.setLocation(listOfFormsSelected.get(i).getX() + e.getX(), listOfFormsSelected.get(i).getY() + e.getY());
                             jc.repaint();
                         }
                	}
                	else {
                    JComponent jc = (JComponent) e.getSource();
                    jc.setLocation(jc.getX() + e.getX(), jc.getY() + e.getY());
                    jc.repaint();
                    }
                }   
            }
        });
    }

    protected void removeAll(JPanel jpanel) {
        if (listOfForms.size() > 0) {
            listOfForms.removeAll(listOfForms);
            jpanel.removeAll();
            jpanel.revalidate();
            jpanel.repaint();
        }
    }
}
