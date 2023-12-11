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

/**
 * Constructs and manages the GUI for Fragile's calculator.
 */
public class FragileWindow extends JFrame
{

  public static final ResourceBundle STRINGS = ResourceBundle.getBundle("gui.Strings");
  private static final long serialVersionUID = 1L;
  private static final Locale LOCALE = Locale.getDefault();
  private static JLabel logoLabel;
  private static final String GREATER = ">";
  private static final String LESSER = "<";
  private static final String ZERO = "0";
  private static final String ONE = "1";
  private static final String TWO = "2";
  private static final String THREE = "3";
  private static final String FOUR = "4";
  private static final String FIVE = "5";
  private static final String SIX = "6";
  private static final String SEVEN = "7";
  private static final String EIGHT = "8";
  private static final String NINE = "9";
  private static final String PLUS = "+";
  private static final String MINUS = "-";
  private static final String TIMES = "x";
  private static final String EXPONENT = "x\u207F";
  private static final String EQUALTO = "≝";
  private static final String DIVIDE = "\u00F7";
  private static final String POSITION = "Pos";
  private static final String EQUALS = "=";
  private static final String SHIFTEQUALS = "shift EQUALS";
  private static final String MINUSSTRING = "MINUS";
  private static final String SHIFT8 = "shift 8";
  private static final String SLASHSTRING = "SLASH";
  private static final String PERIOD = "PERIOD";
  private static final String ENTER = "ENTER";
  private JPanel calcHistoryArea = new JPanel();
  private Display calculatorDisplay;
  private GridBagConstraints gbc;
  private JCheckBoxMenuItem properItem;
  private JCheckBoxMenuItem reducedItem;
  private JRadioButtonMenuItem barItem;
  private JRadioButtonMenuItem slashItem;
  private JRadioButtonMenuItem solidusItem;
  private CreateMenu menu;

  /**
   * Constructs a new window for the calculator.
   */
  public FragileWindow()
  {
    super(STRINGS.getString("CALCULATOR"));
    this.calcHistoryArea = new JPanel();
    this.calculatorDisplay = new Display(TypesettingStyles.SLASH, true, this);
    this.gbc = new GridBagConstraints();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(400, 500);
    this.setLayout(new BorderLayout());
    // frame.setResizable(false);

    this.menu = new CreateMenu(this);
    this.properItem = menu.getProperItem();
    this.reducedItem = menu.getReducedItem();
    this.barItem = menu.getBarItem();
    this.slashItem = menu.getSlashItem();
    this.solidusItem = menu.getSolidusItem();

    // Fragile logo
    ImageIcon logoIcon = new ImageIcon(FragileWindow.class.getResource("/icons/Fragile_Logo.png"));
    logoLabel = new JLabel(logoIcon);
    JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    logoPanel.add(logoLabel);

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(logoPanel, BorderLayout.NORTH);
    // Manually change display style here, does not work dynamically yet.
    Container display = calculatorDisplay;
    display.setFont(new Font("Arial", Font.BOLD, 24));
    // topPanel.add(new JLabel(""), BorderLayout.EAST);
    // topPanel.add(new JLabel(""), BorderLayout.WEST);
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

    this.addComponentListener(new ComponentAdapter()
    {
      public void componentMoved(final ComponentEvent e)
      {
        secondaryWindow.setLocation(FragileWindow.this.getX() + FragileWindow.this.getWidth() - 7,
            FragileWindow.this.getY() + 100);
      }

      public void componentResized(final ComponentEvent e)
      {
        secondaryWindow.setSize(secondaryWindow.getWidth(), FragileWindow.this.getHeight() - 170);
        secondaryWindow.setLocation(FragileWindow.this.getX() + FragileWindow.this.getWidth() - 7,
            FragileWindow.this.getY() + 100);
      }
    });

    // This is the area for the calculation history and the button to open/close it
    JPanel containerPanel = new JPanel(new BorderLayout());
    calcHistoryArea.setLayout(new BoxLayout(calcHistoryArea, BoxLayout.Y_AXIS));
    calcHistoryArea.setAlignmentY(TOP_ALIGNMENT);
    JButton arrowButton = new JButton(GREATER);
    arrowButton.setBackground(Color.white);
    arrowButton.setMargin(new Insets(0, 18, 0, 18));
    arrowButton.setFocusPainted(false);
    JScrollPane scrollPane = new JScrollPane(calcHistoryArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    containerPanel.add(scrollPane, BorderLayout.CENTER);
    containerPanel.add(arrowButton, BorderLayout.EAST);
    secondaryWindow.add(containerPanel);
    arrowButton.addActionListener(new ActionListener()
    {
      Timer timer;

      public void actionPerformed(final ActionEvent e)
      {
        if (secondaryWindow.getWidth() == 50)
        {
          timer = new Timer(5, new ActionListener()
          {
            public void actionPerformed(final ActionEvent evt)
            {
              if (secondaryWindow.getWidth() < 400)
              {
                secondaryWindow.setSize(secondaryWindow.getWidth() + 10,
                    secondaryWindow.getHeight());
              }
              else
              {
                ((Timer) evt.getSource()).stop();
                arrowButton.setText(LESSER);
              }
            }
          });
          timer.start();
        }
        else
        {
          timer = new Timer(5, new ActionListener()
          {
            public void actionPerformed(final ActionEvent evt)
            {
              if (secondaryWindow.getWidth() > 50)
              {
                secondaryWindow.setSize(secondaryWindow.getWidth() - 10,
                    secondaryWindow.getHeight());
              }
              else
              {
                ((Timer) evt.getSource()).stop();
                arrowButton.setText(GREATER);
              }
            }
          });
          timer.start();
        }
      }
    });

    String[][] buttons = {{"R", "C", "\u2190", PLUS, "\u21F9", "\u00B1", GREATER},
        {SEVEN, EIGHT, NINE, MINUS, EXPONENT, "Inv", LESSER},
        {FOUR, FIVE, SIX, TIMES, "", "\u2193", EQUALTO}, {ONE, TWO, THREE, DIVIDE},
        {ZERO, "", POSITION, EQUALS}};

    for (int i = 0; i < buttons.length; i++)
    {
      for (int j = 0; j < buttons[i].length; j++)
      {
        gb.gridx = j;
        gb.gridy = i;
        gb.fill = GridBagConstraints.BOTH;

        if (i == 4 && j == 0)
        {
          gb.gridwidth = 2;
        }
        else
        {
          gb.gridwidth = 1;
        }

        if (!buttons[i][j].isEmpty())
        {
          JButton button = new JButton(buttons[i][j]);
          button.setFocusPainted(false);
          button.setBackground(Color.white);
          button.addActionListener(new ActionListener()
          {
            public void actionPerformed(final ActionEvent e)
            {
              calculatorDisplay.manageButtons(((JButton) e.getSource()).getText());
            }
          });
          buttonsPanel.add(button, gb);
        }
      }
    }

    // Key bindings for each physical key
    String[] keys = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, PERIOD,
        SHIFTEQUALS, MINUSSTRING, SHIFT8, SLASHSTRING, ENTER};
    for (String key : keys)
    {
      KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
      // For some reason I can't change input without it throwing a fit so this is what we got
      // The Display class also doesn't like it if I try to do alternate cases for these for some
      // reason
      if (key.equals(SHIFTEQUALS))
      {
        key = PLUS;
      }
      if (key.equals(MINUSSTRING))
      {
        key = MINUS;
      }
      if (key.equals(SHIFT8))
      {
        key = TIMES;
      }
      if (key.equals(SLASHSTRING))
      {
        key = DIVIDE;
      }
      if (key.equals(PERIOD))
      {
        key = POSITION;
      }
      String input = key;
      this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, input);
      this.getRootPane().getActionMap().put(input, new AbstractAction()
      {
        private static final long serialVersionUID = 1L;

        public void actionPerformed(final ActionEvent e)
        {
          calculatorDisplay.manageButtons(input);
        }
      });
    }

    this.add(buttonsPanel, BorderLayout.CENTER);
    this.setVisible(true);
    secondaryWindow.setVisible(true);
    this.toFront();
  }

  /**
   * Opens a new Fragile window.
   */
  public static void openNewWindow()
  {
    EventQueue.invokeLater(() -> 
    {
      new FragileWindow();
    });
  }

  /**
   * Adds an equation to the history panel.
   *
   * @param entryPanel
   *          The panel the entry is from.
   */
  public void addHistoryEntry(final JPanel entryPanel)
  {
    entryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    entryPanel
        .setMaximumSize(new Dimension(Integer.MAX_VALUE, entryPanel.getPreferredSize().height));
    calcHistoryArea.add(entryPanel);
    calcHistoryArea.revalidate();
    calcHistoryArea.repaint();
  }

  /**
   * @return the menu
   */
  public CreateMenu getMenu()
  {
    return menu;
  }

  /**
   * Returns the calculator history area.
   *
   * @return JPanel The calculator history area.
   */
  public JPanel getHistoryArea()
  {
    return this.calcHistoryArea;
  }

  /**
   * Returns the proper menu item.
   *
   * @return JCheckBoxMenuItem The proper menu item.
   */
  public JCheckBoxMenuItem getProperItem()
  {
    return this.properItem;
  }

  /**
   * Returns the reduced menu item.
   *
   * @return JCheckBoxMenuItem The reduced menu item.
   */
  public JCheckBoxMenuItem getReducedItem()
  {
    return this.reducedItem;
  }

  /**
   * Returns the bar menu item.
   *
   * @return JRadioButtonMenuItem The bar menu item.
   */
  public JRadioButtonMenuItem getBarItem()
  {
    return this.barItem;
  }

  /**
   * Returns the slasj menu item.
   *
   * @return JRadioButtonMenuItem The slash menu item.
   */
  public JRadioButtonMenuItem getSlashItem()
  {
    return this.slashItem;
  }

  /**
   * Returns the solidus menu item.
   *
   * @return JRadioButtonMenuItem The solidus menu item.
   */
  public JRadioButtonMenuItem getSolidusItem()
  {
    return this.solidusItem;
  }
  
  /**
   * Returns this window's display object.
   *
   * @return Display The window's display object.
   */
  public Display getCalculatorDisplay() 
  {
    return this.calculatorDisplay;
  }

  /**
   * Main method to run the calculator.
   *
   * @param args
   *          Command line arguments.
   */
  public static void main(final String[] args)
  {
    new FragileWindow();
  }

}
