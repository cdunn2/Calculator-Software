package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import gui.display.Display;
import gui.display.TypesettingStyles;

public class FragileWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	static final Locale LOCALE = Locale.getDefault();
	static final ResourceBundle STRINGS = ResourceBundle.getBundle("gui.Strings");
	private static JLabel logoLabel;
	public JPanel calcHistoryArea = new JPanel();
	public Display calculatorDisplay;
	public GridBagConstraints gbc;
	


public static void main(String[] args) {
		new FragileWindow();
	}

	public FragileWindow() {
		super(STRINGS.getString("CALCULATOR"));
		this.calcHistoryArea = new JPanel();
        this.calculatorDisplay = new Display(TypesettingStyles.SLASH, true, this);
        this.gbc = new GridBagConstraints();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 500);
		this.setLayout(new BorderLayout());
		//frame.setResizable(false);

		new CreateMenu(this);

		// Fragile logo
		ImageIcon logoIcon = new ImageIcon(FragileWindow.class.getResource("/icons/Fragile_Logo.png"));
		logoLabel = new JLabel(logoIcon);
		JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		logoPanel.add(logoLabel);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(logoPanel, BorderLayout.NORTH);
		//Manually change display style here, does not work dynamically yet.
		Container display = calculatorDisplay;
		display.setFont(new Font("Arial", Font.BOLD, 24));
		//topPanel.add(new JLabel(""), BorderLayout.EAST);
		//topPanel.add(new JLabel(""), BorderLayout.WEST);
		topPanel.add(display, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);

		JPanel buttonsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();

		gb.weightx = 1.0;
		gb.weighty = 1.0;
		
		JWindow secondaryWindow = new JWindow(this);
	    secondaryWindow.setSize(50, 500);
	    secondaryWindow.setLocationRelativeTo(this);
	    secondaryWindow.setLocation(this.getX() + this.getWidth(), this.getY());

	    this.addComponentListener(new ComponentAdapter() {
	        public void componentMoved(ComponentEvent e) {
	        	secondaryWindow.setLocation(FragileWindow.this.getX() + FragileWindow.this.getWidth() - 7, 
                        FragileWindow.this.getY() + 100);
	        }

	        public void componentResized(ComponentEvent e) {
	        	secondaryWindow.setSize(secondaryWindow.getWidth(), 
                        FragileWindow.this.getHeight() - 170);
	        	secondaryWindow.setLocation(FragileWindow.this.getX() + FragileWindow.this.getWidth() - 7, 
                            FragileWindow.this.getY() + 100);
	        }
	    });
		
		// This is the area for the calculation history and the button to open/close it
	    JPanel containerPanel = new JPanel(new BorderLayout());
	    calcHistoryArea.setLayout(new BoxLayout(calcHistoryArea, BoxLayout.Y_AXIS));
	    calcHistoryArea.setAlignmentY(TOP_ALIGNMENT);
	    JButton arrowButton = new JButton(">");
	    arrowButton.setBackground(Color.white);
	    arrowButton.setMargin(new Insets(0, 18, 0, 18));
	    arrowButton.setFocusPainted(false);
	    JScrollPane scrollPane = new JScrollPane(calcHistoryArea);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    containerPanel.add(scrollPane, BorderLayout.CENTER);
	    containerPanel.add(arrowButton, BorderLayout.EAST);
	    secondaryWindow.add(containerPanel);
	    secondaryWindow.setVisible(true);
	    arrowButton.addActionListener(new ActionListener() {
	        Timer timer;
	        public void actionPerformed(ActionEvent e) {
	            if (secondaryWindow.getWidth() == 50) {
	                timer = new Timer(5, new ActionListener() {
	                    public void actionPerformed(ActionEvent evt) {
	                        if (secondaryWindow.getWidth() < 400) {
	                            secondaryWindow.setSize(secondaryWindow.getWidth() + 10, secondaryWindow.getHeight());
	                        } else {
	                            ((Timer) evt.getSource()).stop();
	                            arrowButton.setText("<");
	                        }
	                    }
	                });
	                timer.start();
	            }
	            else {
	                timer = new Timer(5, new ActionListener() {
	                    public void actionPerformed(ActionEvent evt) {
	                        if (secondaryWindow.getWidth() > 50) {
	                            secondaryWindow.setSize(secondaryWindow.getWidth() - 10, secondaryWindow.getHeight());
	                        } else {
	                            ((Timer) evt.getSource()).stop();
	                            arrowButton.setText(">");
	                        }
	                    }
	                });
	                timer.start();
	            }
	        }
	    });

		String[][] buttons = {
				{"R", "C", "\u2190", "+", "\u21F9","\u00B1", ">"},
				{"7", "8", "9", "-", "x\u207F", "Inv", "<"},
				{"4", "5", "6", "x", "", "\u2193", "≝"},
				{"1", "2", "3", "\u00F7"},
				{"0", "", "Pos", "="}
		};

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				gb.gridx = j;
				gb.gridy = i;
				gb.fill = GridBagConstraints.BOTH;

				if (i == 4 && j == 0) {
					gb.gridwidth = 2;
				} else {
					gb.gridwidth = 1;
				}

				if (!buttons[i][j].isEmpty()) {
					JButton button = new JButton(buttons[i][j]);
					button.setFocusPainted(false);
					button.setBackground(Color.white);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							calculatorDisplay.manageButtons(((JButton) e.getSource()).getText());
						}
					});
					buttonsPanel.add(button, gb);
				}
			}
		}
		
		// Key bindings for each physical key
		String[] keys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "PERIOD", "shift EQUALS", "MINUS", "shift 8", "SLASH", "ENTER"};
	    for (String key : keys) {
	        KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
	        // For some reason I can't change input without it throwing a fit so this is what we got
	        // The Display class also doesn't like it if I try to do alternate cases for these for some reason
	        if (key.equals("shift EQUALS")) {
	        	key = "+";
	        }
	        if (key.equals("MINUS")) {
	        	key = "-";
	        }
	        if (key.equals("shift 8")) {
	        	key = "x";
	        }
	        if (key.equals("SLASH")) {
	        	key = "\u00F7";
	        }
	        if (key.equals("PERIOD")) {
	        	key = "Pos";
	        }
	        String input = key;
	        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, input);
	        this.getRootPane().getActionMap().put(input, new AbstractAction() {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent e) {
	                calculatorDisplay.manageButtons(input);
	            }
	        });
	    }
	    
		this.add(buttonsPanel, BorderLayout.CENTER);
		this.setVisible(true);
		secondaryWindow.setVisible(true);
		this.toFront();
	}
	
	public static void openNewWindow() {
        EventQueue.invokeLater(() -> {
            new FragileWindow().setVisible(true);
        });
    }

	// Please do not delete this (or the stuff dealing with calcHistoryArea) it took me so many hours to get this working
	// I am currently writing this at 6:49 am
	// I'm tired boss
	public void addHistoryEntry(JPanel entryPanel) {
	    entryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    entryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, entryPanel.getPreferredSize().height));
	    calcHistoryArea.add(entryPanel);
	    calcHistoryArea.revalidate();
	    calcHistoryArea.repaint();
	}
}
