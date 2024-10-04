package edu.curtin.saed.assignment1;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.swing.*;


/**
 * This is demonstration code intended for you to modify. Currently, it sets up a rudimentary
 * Swing GUI with the basic elements required for the assignment.
 *
 * (There is an equivalent JavaFX version of this, if you'd prefer.)
 *
 * You will need to use the GridArea object, and create various GridAreaIcon objects, to represent
 * the on-screen map.
 *
 * Use the startBtn, endBtn, statusText and textArea objects for the other input/output required by
 * the assignment specification.
 *
 * Break this up into multiple methods and/or classes if it seems appropriate. Promote some of the
 * local variables to fields if needed.
 */
public class App
{
    public static void main(String[] args) throws ParseException
    {
        if(args.length < 1){
            System.out.println("Please provide an input file as a command line arg");
            System.exit(1);
        }else if(args.length >1){
            System.out.println("Please only provide one command line arg");
            System.exit(1);
        }
        String fileName = args[0];
        SwingUtilities.invokeLater(()->start(fileName));  // Equivalent to JavaFX's Platform.runLater().

    }

    public static void start(String fileName)
    {
        var window = new JFrame("Maze Game");

        // Set up the main "top-down" display area. This is an example only, and you should
        // change this to set it up as you require.

        GridArea area = new GridArea(10, 10);
        // area.setGridLines(false); // If desired
        area.setBackground(new Color(0, 0x60, 0));

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                System.out.println("Window closed");
            }
        });
        // Below is basically just the GUI "plumbing" (connecting things together).

       
        Container contentPane = window.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(area, BorderLayout.CENTER); // Add GridArea directly to the center
    
        window.setPreferredSize(new Dimension(1200, 1000));
        window.pack();
        window.setVisible(true);

        //parse file

        /*try{
            FileInputStream inputFile = new FileInputStream(fileName);
            GameFileParser parser = new GameFileParser(fileName);
            parser.InputFile();
            System.out.println("Completed file parsing");
        }catch(FileNotFoundException e){
            System.out.println("Input file not found "+e.getMessage());
        }catch(ParseException e){
            System.out.println();
            System.out.println("Error parsing file: "+e.getMessage());
        }*/
    }
}
