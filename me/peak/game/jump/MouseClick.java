package me.peak.game.jump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseClick extends JFrame{
    private JLabel label;
    int clickTime = 0;
    int x;
    int y;
    static String adbCommand = "E:\\dev_tools\\platform-tools\\adb ";
    static String filaPath = "C:\\Users\\peak\\screen\\";
    static String fileName = filaPath + "0.png";

    public static void main(String[] args) {
        new MouseClick();
    }

    public MouseClick(){
        JFrame frame = new JFrame("test");
        frame.setSize(540,1000);//设置窗体的宽和高
        frame.setVisible(true);//设置窗体可见
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));//框架流布局且居中对齐
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击关闭按钮时的默认操作
        getPhoneScreen(fileName);
        final JLabel label = new JLabel();
        setImage(label, fileName);
        frame.add(label); // 将标签放入窗体
        frame.setVisible(true);


        frame.addMouseListener(new MouseAdapter(){  //匿名内部类，鼠标事件
            public void mouseClicked(MouseEvent e){   //鼠标完成点击事件
                if(e.getButton() == MouseEvent.BUTTON1){
                    if (clickTime%2 == 0) {
                        x = e.getX();
                        y = e.getY();
                        System.out.println("position " + x + "," + y);
                    } else {
                        int distance = calcDistance(x, y, e.getX(), e.getY());
                        System.out.println("distance " + distance);
                        try {
                            Process p = Runtime.getRuntime().exec(
                                    adbCommand + "shell input swipe 900 1800 900 1800 " + getTimeByDistance(distance));
                            p.waitFor();
                            System.out.println("input swipe exit value " + p.exitValue());
                            p.destroy();
                        } catch (IOException | InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        fileName = filaPath + clickTime + ".png";
                        getPhoneScreen(fileName);
                        setImage(label, fileName);
                    }
                    clickTime++;
                } else if (e.getButton() == MouseEvent.BUTTON3){
                    clickTime = 0;
                    fileName = filaPath + clickTime + ".png";
                    getPhoneScreen(fileName);
                    setImage(label, fileName);
                }
            }
        });
    }

    public static int getTimeByDistance(int distance) {
        if (distance >= 200) {
            return (int) (distance * 2.8);
        } else {
            return (int) (distance * 3);
        }
    }

    public static void setImage(JLabel label, String fileName) {
        ImageIcon imageIcon = new ImageIcon(fileName);
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(540, 960, Image.SCALE_DEFAULT));
        label.setIcon(imageIcon);
    }

    public static void getPhoneScreen(String localPath){
        try {
            Process p = Runtime.getRuntime().exec(adbCommand + "shell screencap -p /sdcard/peak/1.png ");
            p.waitFor();
            System.out.println("screen cap exit value " + p.exitValue());
            p.destroy();
            p = Runtime.getRuntime().exec(adbCommand + "pull /sdcard/peak/1.png " + localPath);
            p.waitFor();
            System.out.println("pull png exit value " + p.exitValue());
            p.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int calcDistance(int x1, int y1, int x2, int y2) {
        int xDiff = Math.abs(x1-x2);
        int yDiff = Math.abs(y1-y2);
        return (int)Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }

}
