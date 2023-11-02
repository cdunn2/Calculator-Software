package gui;

import java.awt.*;

import javax.swing.*;

public class AboutWindow  extends JFrame {

	private static final long serialVersionUID = 1L;

	public AboutWindow() {
		super("About Fragile");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        add(panel);

        //title
        JLabel titleLabel = new JLabel("About", SwingConstants.CENTER);
        panel.add(titleLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        //icon
        ImageIcon logoIcon = new ImageIcon("icon.png");
        JLabel logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        panel.add(logoLabel);

        //version
        JLabel versionLabel = new JLabel("Fragile v1.0", SwingConstants.CENTER);
        panel.add(versionLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        //about
        JLabel line_one = new JLabel("Fragile is a modern, easy-to-use mixed "
        		+ "fraction calculator.", SwingConstants.CENTER);
        JLabel line_two = new JLabel("It is a product of Sagacious Media that was",
        		SwingConstants.CENTER); 
        JLabel line_three = new JLabel("developed by:", SwingConstants.CENTER); 
        JLabel line_four = new JLabel("Connor Dunn, Colin McCaffery, Ben Berry, and Logan Page",
        		SwingConstants.CENTER); 
        
        panel.add(line_one);
        panel.add(line_two);
        panel.add(line_three);
        panel.add(line_four);
        
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        line_one.setAlignmentX(Component.CENTER_ALIGNMENT);
        line_two.setAlignmentX(Component.CENTER_ALIGNMENT);
        line_three.setAlignmentX(Component.CENTER_ALIGNMENT);
        line_four.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public static void main(String[] args) {
		AboutWindow aboutWindow = new AboutWindow();
		aboutWindow.setVisible(true);
	}
}
