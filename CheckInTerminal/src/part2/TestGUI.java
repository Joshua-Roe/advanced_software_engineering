package part2;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestGUI implements Observer{
    private SimTime t;
    private Timer timer;

    public TestGUI(Timer timer, SimTime t) {
        this.timer = timer;
        this.t = t;
        createGUI();
    }

    public synchronized void createGUI() {
        JFrame mainFrame = new JFrame();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        JButton speedUp = new JButton("Speed up");
        speedUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.set(4);
            }
        });
        JButton speedDown = new JButton("Speed Down");
        speedDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.set(1);
            }
        });

        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.pauseSim();
            }
        });

        JButton resume = new JButton("Resume");
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.resumeSim();
            }
        });

        mainFrame.add(buttonPanel);
        mainFrame.setVisible(true);
        buttonPanel.add(speedUp);
        buttonPanel.add(speedDown);
        buttonPanel.add(pause);
        buttonPanel.add(resume);
        mainFrame.setMinimumSize(new Dimension(200,200));

    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }
}