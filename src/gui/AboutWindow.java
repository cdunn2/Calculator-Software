package gui;

import java.awt.*;
import static gui.FragileWindow.*;
import javax.swing.*;

/**
 * About window for the calculator.
 */
public class AboutWindow extends JFrame
{

  private static final long serialVersionUID = 1L;
  private static final String ABOUT = "ABOUT";

  /**
   * Constructs the calculators about window.
   */
  public AboutWindow()
  {
    super(STRINGS.getString(ABOUT) + " Fragile");

    setSize(400, 200);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    add(panel);

    // title
    JLabel titleLabel = new JLabel(STRINGS.getString(ABOUT), SwingConstants.CENTER);
    panel.add(titleLabel);

    panel.add(Box.createRigidArea(new Dimension(0, 20)));

    // icon
    ImageIcon logoIcon = new ImageIcon(FragileWindow.class.getResource("/icons/icon.png"));
    JLabel iconLabel = new JLabel(logoIcon, SwingConstants.CENTER);
    panel.add(iconLabel);

    // version
    JLabel versionLabel = new JLabel("Fragile v1.0", SwingConstants.CENTER);
    panel.add(versionLabel);

    panel.add(Box.createRigidArea(new Dimension(0, 15)));

    // about
    JLabel lineOne = new JLabel("Fragile " + STRINGS.getString("LINE_ONE"), SwingConstants.CENTER);
    JLabel lineTwo = new JLabel(STRINGS.getString("LINE_TWO"), SwingConstants.CENTER);
    JLabel lineThree = new JLabel(STRINGS.getString("LINE_THREE"), SwingConstants.CENTER);
    JLabel lineFour = new JLabel(STRINGS.getString("LINE_FOUR"), SwingConstants.CENTER);

    panel.add(lineOne);
    panel.add(lineTwo);
    panel.add(lineThree);
    panel.add(lineFour);

    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    lineOne.setAlignmentX(Component.CENTER_ALIGNMENT);
    lineTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
    lineThree.setAlignmentX(Component.CENTER_ALIGNMENT);
    lineFour.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  /**
   * Main class.
   *
   * @param args Command line arguments.
   */
  public static void main(final String[] args)
  {
    AboutWindow aboutWindow = new AboutWindow();
    aboutWindow.setVisible(true);
  }
}
