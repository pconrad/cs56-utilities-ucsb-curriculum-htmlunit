import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class CourseSearchGui {
    private CourseSearch guiCourseSearch;
    Lecture lect;
    private JFrame frame;
    private JPanel savePanel, searchPanel, resultsPanel;//, sendPanel, receivePanel;
    private JButton save, load, search;//, send, receive;
	
    public CourseSearchGui() {
	guiCourseSearch = new CourseSearch();
	lect = null;
	frame = new JFrame("UCSB Curriculum Search");
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
    public void showGui() {
	buildOptionsGui();
	buildSearchGui();
		
	frame.setVisible(true);  
    }
	
    private void buildOptionsGui() {
	savePanel = new JPanel();
		
	save = new JButton("Save");
	load = new JButton("Load");
	search = new JButton("Search");
	//send = new JButton("Send");
	//receive = new JButton("Receive");
		
	save.addActionListener(new saveButtonListener());
	load.addActionListener(new loadButtonListener());
	search.addActionListener(new searchButtonListener());
	//send.addActionListener(new sendButtonListener());
	//receive.addActionListener(new receiveButtonListener());
		
	savePanel.add(save);
	savePanel.add(load);
	savePanel.add(search);
	//savePanel.add(send);
	//savePanel.add(receive);
	frame.getContentPane().add(savePanel);
	frame.revalidate();
    }
    
    private void buildSearchGui() {
	save.setEnabled(false);
	load.setEnabled(true);
	search.setEnabled(false);
	//send.setEnabled(true);
	//receive.setEnabled(true);
		
	searchPanel = new JPanel();
	searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		
	for (int i = 1; i <= 4; i++) {
	    JComboBox<String> optionBox = setDropDownOption(i);
	    JLabel optionLabel = null;
	    switch (i) {
	    case 1: optionLabel = new JLabel("Please Choose the Department");
		break;
	    case 2: optionLabel = new JLabel("Please Choose the Quarter");
		break;
	    case 3: optionLabel = new JLabel("Please Choose the Grad Level");
		break;
	    case 4: optionLabel = new JLabel("Please Choose the Course");
		break;
	    }
	    optionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    searchPanel.add(optionLabel);
	    searchPanel.add(optionBox);
	}
		
	JButton searchSubmit = new JButton("Submit");
	searchSubmit.addActionListener(new searchSubmitListener());
	searchSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
	searchPanel.add(searchSubmit);

	frame.getContentPane().add(searchPanel);
	frame.setSize(500, 300);
	frame.revalidate();
    }
	
    private void buildResultsGui(Lecture theLect) {
	save.setEnabled(true);
	load.setEnabled(true);
	search.setEnabled(true);
	//send.setEnabled(true);
	//receive.setEnabled(true);
		
	resultsPanel = new JPanel();
	resultsPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		
	JLabel title = new JLabel(theLect.getCourseAbbr() + " - " + theLect.getFullTitle());
	title.setAlignmentX(Component.CENTER_ALIGNMENT);
	title.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

	title.setPreferredSize(new Dimension(750,30));
	resultsPanel.add(title);
		
	buildMiscPanel(theLect);
	buildUCSBClassPanels(theLect);
	frame.getContentPane().add(resultsPanel);
	frame.revalidate();
	frame.setSize(770,500);
    }
	
    private void buildMiscPanel(Lecture theLect) {
	JPanel miscPanel = new JPanel();
		
	miscPanel.setLayout(new GridBagLayout());
	miscPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
		
	JLabel courseName = new JLabel(theLect.getCourseName(),JLabel.CENTER);
		
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 2;
	miscPanel.add(courseName,c);
		
	JLabel [] field = new JLabel[4];
	JTextPane [] fieldData = new JTextPane[3];
		
	JTextArea [] longData = new JTextArea[2];
	longData[0] = new JTextArea(4,20);
	longData[1] = new JTextArea(4,15);
	longData[0].setText(theLect.getDescription());
	longData[1].setText(theLect.getPreReq());
		
	for (int i = 0; i < 2; i++) {
	    longData[i].setLineWrap(true);
	    longData[i].setWrapStyleWord(true);
	    longData[i].setCaretPosition(0);
	    longData[i].setEditable(false);
	    longData[i].setBackground(frame.getContentPane().getBackground());
	    JScrollPane scroll = new JScrollPane(longData[i]);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    c.ipady = 10;
	    if (i ==0 ) {
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
	    }
	    else {
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
	    }
	    miscPanel.add(scroll,c);
	}
		
	field[0] = new JLabel("PreReqs:  ");
	field[1] = new JLabel("College:  ");
	field[2] = new JLabel("Grading:  ");
	field[3] = new JLabel("Units:    ");
		
	for (int i = 0; i < 3; i++) {
	    fieldData[i] = new JTextPane();
	}
		
	fieldData[0].setText(theLect.getCollege());
	fieldData[1].setText(theLect.getGrading());
	fieldData[2].setText(theLect.getUnits());
		
	for (int i = 0; i < 4; i++) {
	    field[i].setAlignmentX(Component.LEFT_ALIGNMENT);
	    c.gridx = 0;
	    c.gridy = i+2;
	    miscPanel.add(field[i],c);
	}
		
	for (int i = 0; i < 3; i++) {
	    fieldData[i].setAlignmentX(Component.LEFT_ALIGNMENT);
	    fieldData[i].setBackground(frame.getContentPane().getBackground());
	    fieldData[i].setEditable(false);
	    c.ipady = -2;
	    c.gridx = 1;
	    c.gridy = i+3;
	    miscPanel.add(fieldData[i],c);
	}
	resultsPanel.add(miscPanel);
    }
	
    private void buildUCSBClassPanels(Lecture theLect) {
	JPanel lectureSectionPanel = new JPanel();
	lectureSectionPanel.setLayout(new BoxLayout(lectureSectionPanel, BoxLayout.Y_AXIS));
	lectureSectionPanel.add(Box.createRigidArea(new Dimension(5,15)));
		
	buildLectureSectionPanel(theLect, lectureSectionPanel);
	for (Section sect : theLect.sections) {
	    buildLectureSectionPanel(sect, lectureSectionPanel);
	}
	JScrollPane scroll = new JScrollPane(lectureSectionPanel);
	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scroll.setPreferredSize(new Dimension(450,250));
	resultsPanel.add(scroll);
    }
	
    private void buildLectureSectionPanel(UCSBClass theClass, JPanel mainPanel) {
	JPanel lsPanel = new JPanel(new GridBagLayout());
	lsPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;
	c.weightx = 0.5;
		
	JLabel type = new JLabel();
	if (theClass instanceof Lecture) {
	    type.setText("<HTML><U>Lecture<U><HTML>");
	    type.setForeground(Color.BLUE);
	} else {
	    type.setText("<HTML><U>Section<U><HTML>");
	    type.setForeground(Color.GREEN);
	}
	type.setAlignmentX(Component.CENTER_ALIGNMENT);
	type.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		
	JLabel [] field = new JLabel[6];
	JLabel [] fieldData = new JLabel[6];
		
	field[0] = new JLabel("Days:        ");
	field[1] = new JLabel("Time:        ");
	field[2] = new JLabel("Location:    ");
	field[3] = new JLabel("Instructor:  ");
	field[4] = new JLabel("Enrolled:    ");
	field[5] = new JLabel("Enroll Code: ");
		
	fieldData[0] = new JLabel(theClass.getDays());
	fieldData[1] = new JLabel(theClass.getTime());
	fieldData[2] = new JLabel(theClass.getLocation());
	fieldData[3] = new JLabel(theClass.getInstructor());
	fieldData[4] = new JLabel(theClass.getEnrolled());
	fieldData[5] = new JLabel(theClass.getEnrollCode());
		
	for(int i = 0; i < 3; i++) {
	    field[i].setAlignmentX(Component.LEFT_ALIGNMENT);
	    fieldData[i].setAlignmentX(Component.LEFT_ALIGNMENT);
	    fieldData[i].setFont(fieldData[i].getFont().deriveFont(Font.PLAIN));
	    c.gridx = 0;
	    c.gridy = i+1;
	    lsPanel.add(field[i],c);
			
	    c.gridx = 1;
	    lsPanel.add(fieldData[i],c);
	}
		
	for(int i = 3; i < 6; i++) {
	    field[i].setAlignmentX(Component.LEFT_ALIGNMENT);
	    fieldData[i].setAlignmentX(Component.LEFT_ALIGNMENT);
	    fieldData[i].setFont(fieldData[i].getFont().deriveFont(Font.PLAIN));
	    c.gridx = 4;
	    c.gridy = i-2;
	    lsPanel.add(field[i],c);
			
	    c.gridx = 5;
	    lsPanel.add(fieldData[i],c);
	}
	c.anchor = GridBagConstraints.PAGE_START;
	c.gridx = 2;	 c.gridy = 0;	c.gridwidth = 2;
	lsPanel.add(type,c);
		
	mainPanel.add(lsPanel);
	mainPanel.add(Box.createRigidArea(new Dimension(5,15)));
    }
	
    private JComboBox<String> setDropDownOption(int theOption) {
	List<String> optionList;
	String [] optionArray;
		
	switch (theOption) {
	case 1:	optionList = guiCourseSearch.getDeptOptions();
	    guiCourseSearch.setDept(optionList.get(0));
	    break;
	case 2:	optionList = guiCourseSearch.getQuarterOptions();
	    guiCourseSearch.setQuarter(optionList.get(0));
	    break;
	case 3:	optionList = guiCourseSearch.getGradLevelOptions();
	    guiCourseSearch.setGradLevel(optionList.get(0));
	    break;
	case 4:	optionList = guiCourseSearch.getCourseOptions();
	    guiCourseSearch.setCourse(optionList.get(0));
	    break;
	default:optionList = null;
	    break;	
	}
		
	if (optionList == null) {
	    System.out.println("Invalid argument (Enter between 1 and 4)");
	    return null;
	}
	optionArray = new String [optionList.size()];
	optionArray = optionList.toArray(optionArray);
		
	JComboBox<String> optionBox = new JComboBox<String>(optionArray);
	switch(theOption) {
	case 1:	optionBox.addActionListener(new dropDownDeptListener());
	    break;
	case 2:	optionBox.addActionListener(new dropDownQuarterListener());
	    break;
	case 3:	optionBox.addActionListener(new dropDownGradLevelListener());
	    break;
	case 4:	optionBox.addActionListener(new dropDownCourseListener());
	    break;
	}
	optionBox.setMaximumSize(new Dimension(300,20));
	return optionBox;
    }
	
    private void refreshCourseDropDown() {
	searchPanel.remove(7);
	searchPanel.remove(7);
		
	JButton searchSubmit = new JButton("Submit");
	searchSubmit.addActionListener(new searchSubmitListener());
	searchSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	JComboBox<String> courseBox = setDropDownOption(4);
	searchPanel.add(courseBox);
	searchPanel.add(searchSubmit);
		
	frame.revalidate();
    }
	
    private class dropDownDeptListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    @SuppressWarnings("unchecked")
		String optionSelect = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
	    guiCourseSearch.setDept(optionSelect);
			
	    refreshCourseDropDown();
	}
    }
	
    private class dropDownQuarterListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    @SuppressWarnings("unchecked")
		String optionSelect = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
	    guiCourseSearch.setQuarter(optionSelect);
			
	    refreshCourseDropDown();
	}
    }
	
    private class dropDownGradLevelListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    @SuppressWarnings("unchecked")
		String optionSelect = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
	    guiCourseSearch.setGradLevel(optionSelect);
			
	    refreshCourseDropDown();
	}
    }
	
    private class dropDownCourseListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    @SuppressWarnings("unchecked")
		String optionSelect = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
	    guiCourseSearch.setCourse(optionSelect);
	}
    }

    private class searchSubmitListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    boolean finish = guiCourseSearch.setLecture();
			
	    if (finish) {
		lect = guiCourseSearch.getLecture();
		frame.getContentPane().remove(1);
		buildResultsGui(lect);
	    }
	    else {
		JOptionPane.showMessageDialog(frame, "Error: No info could be found");
		return;
	    }
	}
    }
	
    private class saveButtonListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    FileOutputStream fileStream = null;
	    ObjectOutputStream objectStream = null;
			
	    JFileChooser chooser = new JFileChooser();
	    int retrival = chooser.showSaveDialog(null);
		    
	    if (retrival == JFileChooser.APPROVE_OPTION) {
		try {
		    fileStream = new FileOutputStream(chooser.getSelectedFile()+".ser");
		    objectStream = new ObjectOutputStream(fileStream);
		    objectStream.writeObject(lect);
		    objectStream.close();
		    JOptionPane.showMessageDialog(frame, "Lectue Saved Successfully");
		} catch (Exception e2){
		    JOptionPane.showMessageDialog(frame, "Error: Lecture could not be saved");
		    try {
			if(objectStream  != null){
			    objectStream.close();
			}
		    }catch (IOException e3) {
			System.out.println("Error closing the unfinished objectStream");
		    }
		}
	    }
	}
    }
	
    private class loadButtonListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    FileInputStream fileStream = null;
	    ObjectInputStream objectStream = null;
			
	    JFileChooser chooser = new JFileChooser();
	    int retrival = chooser.showOpenDialog(null);
		    
	    if (retrival == JFileChooser.APPROVE_OPTION) {
		try {
		    fileStream = new FileInputStream(chooser.getSelectedFile());
		    objectStream = new ObjectInputStream(fileStream);
		    lect = (Lecture) objectStream.readObject();
		    objectStream.close();
		    frame.getContentPane().remove(1);
		    buildResultsGui(lect);
		} catch (Exception e1) {
		    JOptionPane.showMessageDialog(frame, "Error: Lecture could not be loaded");
		    if(objectStream  != null){
			try {
			    objectStream.close();
			} catch (IOException e2) {
			    System.out.println("Error closing the unfinished objectStream");
			}
		    }
		}
	    }
	}
    }
	
    private class searchButtonListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    frame.getContentPane().remove(1);
	    buildSearchGui();	
	}
    }
	
	/* Can't test this - can't find an open server port
	 * 
	 * 
	private class sendButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SendReceiveLecture srl = new SendReceiveLecture();
			srl.setServerPort(25536);
			srl.send();
		}
	}
	
	private class receiveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SendReceiveLecture srl = new SendReceiveLecture();
			srl.setServerPort(25536);
			srl.receive();
			frame.getContentPane().remove(1);
			buildResultsGui(lect);
		}
	}
	
	public class SendReceiveLecture {
		int serverPort;
		
		public void setServerPort(int theServerPort) {
			this.serverPort = theServerPort;
		}
		
		public class ClientHandler implements Runnable {
			ObjectInputStream inputStream;
			Socket clientSocket;
			public ClientHandler() {
				try {
					ServerSocket serverSock = new ServerSocket(serverPort);
					clientSocket = serverSock.accept();
					inputStream = new ObjectInputStream(clientSocket.getInputStream());
					clientSocket.close();
					serverSock.close();
				} catch (IOException e) {
					e.printStackTrace(); 
					System.exit(0);
				}
			}
			
			@Override
			public void run() {
				Lecture tempLect;
				try {
					while ((tempLect = (Lecture) inputStream.readObject()) != null) {
						lect = tempLect;
					}
				} catch (Exception ex) { ex.printStackTrace(); }
			}
		}
		
		public void receive() {
			while (true) {
				Thread t = new Thread(new ClientHandler());
				t.start();
			}
		}
		
		public void send() {
			try {
				ServerSocket serverSock = new ServerSocket(serverPort);
				Socket clientSocket = serverSock.accept();
				ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				outputStream.writeObject(lect);
				outputStream.close();
				clientSocket.close();
				serverSock.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(0);
			}
		}
	}
	*/
	
    public static void main(String [] args) {
	CourseSearchGui gui = new CourseSearchGui();
	gui.showGui();
    }
}
