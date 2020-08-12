package com.profteam.custom.common;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

public class JTableBlue extends JTable
{
	public JTableBlue() 
	{
		setGridColor(Color.BLACK);
		setShowHorizontalLines(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setBorder(new LineBorder(Color.DARK_GRAY));
		setSelectionBackground(new Color(230, 72, 100));
		setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER );
		setDefaultRenderer(String.class, centerRenderer);
		setDefaultRenderer(Integer.class, centerRenderer);
		setDefaultRenderer(Object.class, centerRenderer);
		
		//Set text nằm giữa cho header table
		((DefaultTableCellRenderer) getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		getTableHeader().setOpaque(false);
		getTableHeader().setBackground(new Color(0, 169, 224));
		getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		getTableHeader().setForeground(Color.WHITE);
		
		//Chặn không cho phép người dùng kéo cột đi
		getTableHeader().setReorderingAllowed(false);
	}
}
