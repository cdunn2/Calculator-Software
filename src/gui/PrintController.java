package gui;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.*;

/**
 * Controls printing operations.
 */
public class PrintController
{

  /**
   * Prints the given printable.
   *
   * @param printable Printable object.
   * @param parent Parent frame.
   */
  public static void print(final Printable printable, final JFrame parent)
  {
    PrinterJob job = PrinterJob.getPrinterJob();

    try
    {
      job.setPrintable(printable);
      boolean shouldPrint = job.printDialog();
      if (shouldPrint)
        job.print();
    }
    catch (PrinterException e)
    {
      JOptionPane.showMessageDialog(parent, "Unable to print", "Error", JOptionPane.ERROR_MESSAGE);
    }

  }

}
