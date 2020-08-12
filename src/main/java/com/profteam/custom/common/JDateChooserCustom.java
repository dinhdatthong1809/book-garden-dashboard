package com.profteam.custom.common;

import com.profteam.helper.SettingSave;
import com.profteam.view.frame.UserJFrame;
import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Locale;

public class JDateChooserCustom extends JDateChooser
{
	public JDateChooserCustom()
	{	
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLocale(Locale.US);
		getJCalendar().getDayChooser().setDayBordersVisible(true);
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		getJCalendar().setTodayButtonVisible(true);
		getJCalendar().setTodayButtonText("Hôm nay");
		
		getCalendarButton().setIcon(new ImageIcon(UserJFrame.class.getResource("/icon/icons8_planner_24px.png")));
		setDateFormatString(SettingSave.getSetting().getDateFormat());
	}
}
