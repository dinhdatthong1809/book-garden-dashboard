package com.duan.custom.common;

import com.duan.helper.SettingSave;
import com.duan.view.UserJFrame;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
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
		
		getCalendarButton().setIcon(new ImageIcon(UserJFrame.class.getResource("/com/duan/icon/icons8_planner_24px.png")));
		setDateFormatString(SettingSave.getSetting().getDateFormat());
	}
}
