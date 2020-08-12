package com.profteam.custom.common;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class JTextFieldDark extends JTextField
{
	public JTextFieldDark() {
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
	}

}
