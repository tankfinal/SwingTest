package JTable;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.event.PaintEvent;
import java.awt.Component;
import java.awt.MenuComponent;

public class TestEvent {
	public static void main(String[] args) {
		final JDialog dlg = new JDialog();
		dlg.setTitle("Test Event Queue");
		JButton btn = new JButton("Test");
		dlg.getContentPane().add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long now = System.currentTimeMillis();
				EventQueue theQueue = dlg.getToolkit().getSystemEventQueue();
				System.out.println("at least 5000 millis");
				while (System.currentTimeMillis() - now < 5000l) {
					try {
						// This is essentially the body of EventDispatchThread
						AWTEvent event = theQueue.getNextEvent();
						Object src = event.getSource();
						if (event instanceof ActiveEvent) {
							((ActiveEvent) event).dispatch();
						} else if (src instanceof Component) {
							((Component) src).dispatchEvent(event);
						} else if (src instanceof MenuComponent) {
							((MenuComponent) src).dispatchEvent(event);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				System.out.println("end");
			}
		});
		dlg.pack();
		dlg.show();
	}
}
