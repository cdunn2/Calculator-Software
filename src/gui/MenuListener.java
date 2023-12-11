package gui;

import static gui.FragileWindow.*;

import gui.display.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

/**
 * This is the class that creates the listener for the menu items.
 *
 * @author Connor Dunn
 * @version 1.3
 */
public class MenuListener implements ActionListener
{

  private static Boolean reduce = false;
  private static Boolean proper = false;
  private FragileWindow fw;
  private PreferencesDialog preferencesDialog;

  /**
   * Constructs a new menu listener for the given window.
   *
   * @param fw Fragile window.
   */
  public MenuListener(final FragileWindow fw)
  {
    this.fw = fw;
    this.preferencesDialog = new PreferencesDialog(fw);
    preferencesDialog.setVisible(false);
  }

  /**
   * This is the main method in the class that adds behavior to what happens when a menu item is
   * selected.
   *
   * @param e
   *          The current action event
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getActionCommand().equals(STRINGS.getString("EXIT")))
    {
      fw.dispose();
    }
    else if (e.getActionCommand().equals(STRINGS.getString("ABOUT")))
    {
      AboutWindow.main(null);
    }
    else if (e.getActionCommand().equals(STRINGS.getString("NEW")))
    {
      FragileWindow.openNewWindow();
    }
    else if (e.getActionCommand().equals(STRINGS.getString("PRINT")))
    {
      DelegatingPrintable print = new DelegatingPrintable(fw.getHistoryArea());
      PrintController.print(print, null);
    }
    else if (e.getActionCommand().equals(STRINGS.getString("SAVE")))
    {
      preferencesDialog.saveToFile();
    }
    else if (e.getActionCommand().equals(STRINGS.getString("OPEN")))
    {
      preferencesDialog.loadFromFile();
    }
    else if (e.getActionCommand().equals(STRINGS.getString("EDIT")))
    {

      preferencesDialog.setVisible(true);

      // for preferences, use a dialog:
      // https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
      // maybe have a text file to save the preferences
    }
    else if (e.getActionCommand().equals(STRINGS.getString("HELP")))
    {

      try
      {
        Path tempDir = ResourceCopier.copyResourcesToTemp("temp", "resources");

        URI indexHtmlURI = tempDir.resolve(STRINGS.getString("HTML")).toUri();

        Desktop.getDesktop().browse(indexHtmlURI);

      }
      catch (IOException | URISyntaxException er)
      {
        er.printStackTrace();
      }
    }
    if (fw.getProperItem().isSelected())
    {
      proper = true;
    }
    else if (!fw.getProperItem().isSelected())
    {
      proper = false;
    }
    if (fw.getReducedItem().isSelected())
    {
      reduce = true;
    }
    else if (!fw.getReducedItem().isSelected())
    {
      reduce = false;
    }

    if (fw.getBarItem().isSelected())
    {
      fw.getCalculatorDisplay().changeStyle(TypesettingStyles.BAR);
    }
    else if (fw.getSlashItem().isSelected())
    {
      fw.getCalculatorDisplay().changeStyle(TypesettingStyles.SLASH);
    }
    else if (fw.getSolidusItem().isSelected())
    {
      fw.getCalculatorDisplay().changeStyle(TypesettingStyles.SOLIDUS);
    }

  }

  /**
   * Returns if fractions should be reduced.
   *
   * @return boolean If fractions should be reduced.
   */
  public static boolean getReduce()
  {
    return reduce;
  }

  /**
   * Returns if fractions should be proper.
   *
   * @return boolean If fractions should be proper.
   */
  public static boolean getProper()
  {
    return proper;
  }

}
