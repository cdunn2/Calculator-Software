package gui;

import java.awt.print.Printable;

import java.awt.print.PrinterJob;

import javax.swing.*;

public class PrintController {

	public static void print(Printable printable, JFrame parent) {
		PrinterJob job = PrinterJob.getPrinterJob();
		
		try {
			job.setPrintable(printable);
			boolean shouldPrint = job.printDialog();
			if (shouldPrint) job.print();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(parent, "Unable to print", "Error",
										JOptionPane.ERROR_MESSAGE);
		}

	}

}
