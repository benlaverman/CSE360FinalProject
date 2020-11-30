import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.*;

public class DatePicker extends JFrame implements ActionListener {
	protected String Date;
	protected boolean Selected;
	protected JDatePickerImpl datePicker;
	
	public void DatePicker() {
		Date = "";
		Selected = false;
	}
	
	public void selectDate(JFrame frame) {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setSize(300,300);
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		JButton submit = new JButton("Submit");
	    submit.setVerticalTextPosition(AbstractButton.CENTER);
	    submit.addActionListener(this);
		 
		panel.add(datePicker);
		panel.add(submit);
		panel.setVisible(true);
		
		JDialog dialog = new JDialog(frame, "Choose Date");
		dialog.setSize(300,300);
		dialog.add(panel);
		dialog.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent event) {
		Date = datePicker.getJFormattedTextField().getText();
		System.out.print(Date);
		Selected = true;
	}
	
	public String getDate() {
		return Date;
	}
	
}
