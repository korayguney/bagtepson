package com.bagtep.utils;

import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
* This program demonstrates how to capture screenshot of a portion of screen.
* 
*/
public class PartialScreenCaptureExample extends JFrame {

   /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
       PartialScreenCaptureExample p = new PartialScreenCaptureExample();
       try {
          /*
           * Let the program wait for 5 seconds to allow you to open the
           * window whose screenshot has to be captured
           */
           Thread.sleep(5000);
           Robot robot = new Robot();
           String fileName = "D://PartScreenshot.jpg";

           // Define an area of size 500*400 starting at coordinates (10,50)
           Rectangle rectArea = new Rectangle(10, 50, 500, 400);
           BufferedImage screenFullImage = robot.createScreenCapture(rectArea);
           ImageIO.write(screenFullImage, "jpg", new File(fileName));

           p.setLocation(500, 500);
           JLabel text = new JLabel("A portion of the screen saved!");
           p.add(text);
           p.setSize(200, 100);
           p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           p.getContentPane().setLayout(new FlowLayout());
           p.setVisible(true);
       } catch (AWTException | IOException | InterruptedException ex) {
                System.err.println(ex);
       }
    }
}