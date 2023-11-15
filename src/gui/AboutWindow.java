package gui;

import java.awt.*;
import static gui.FragileWindow.*;
import javax.swing.*;

public class AboutWindow  extends JFrame {

	private static final long serialVersionUID = 1L;

	public AboutWindow() {
		super(STRINGS.getString("ABOUT") + " Fragile");

		setSize(400, 200);
		
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        add(panel);

        //title
        JLabel titleLabel = new JLabel(STRINGS.getString("ABOUT"), SwingConstants.CENTER);
        panel.add(titleLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        //icon
        ImageIcon logoIcon = new ImageIcon(FragileWindow.class.getResource("/icons/icon.png"));
        JLabel iconLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        panel.add(iconLabel);

        //version
        JLabel versionLabel = new JLabel("Fragile v1.0", SwingConstants.CENTER);
        panel.add(versionLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        //about
        JLabel line_one = new JLabel("Fragile " + STRINGS.getString("LINE_ONE"),
        		SwingConstants.CENTER);
        JLabel line_two = new JLabel(STRINGS.getString("LINE_TWO"),
        		SwingConstants.CENTER); 
        JLabel line_three = new JLabel(STRINGS.getString("LINE_THREE"),
        		SwingConstants.CENTER); 
        JLabel line_four = new JLabel(STRINGS.getString("LINE_FOUR"),
        		SwingConstants.CENTER); 
        
        panel.add(line_one);
        panel.add(line_two);
        panel.add(line_three);
        panel.add(line_four);
        
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
