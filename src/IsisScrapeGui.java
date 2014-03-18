import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class IsisScrapeGui {

    private IsisScrape is;
    private JFrame frame;
	
    public IsisScrapeGui() {
	is = new IsisScrape();
	frame = new JFrame("ISIS Scrape");
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
    public void showGui() {
	frame.setVisible(true);  
    }
	
    public static void main(String [] args) {
	IsisScrapeGui gui = new IsisScrapeGui();
	gui.showGui();
    }
}
