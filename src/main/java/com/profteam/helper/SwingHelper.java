package com.profteam.helper;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class SwingHelper 
{
	//Dùng để set Auto Resize icon vừa khít với size label
	public static void setAutoResizeIcon(JLabel label)
	{
		ImageIcon img = (ImageIcon) label.getIcon();
		if (img != null)
			label.setIcon(new ImageIcon(img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
	}
	
	//Dùng để set Auto Resize icon vừa khít với size label có kèm theo kiểu Scale
	public static void setAutoResizeIcon(JLabel label, int ScaleMode)
	{
		ImageIcon img = (ImageIcon) label.getIcon();
		label.setIcon(new ImageIcon(img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), ScaleMode)));
	}
	
	//Dùng để set Auto Resize icon vừa khít với PreferredSize label có kèm theo kiểu Scale
	public static void setAutoResizeIcon_PreferredSize(JLabel label)
	{
		ImageIcon img = (ImageIcon) label.getIcon();
		int height = (int) label.getPreferredSize().getHeight();
		int width = (int) label.getPreferredSize().getWidth();
		
		//System.out.println(height + ", " + width);
		if (img != null)
			label.setIcon(new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_REPLICATE)));
	}
	
	//Dùng để xóa 1 dòng nào đó trong JTable
	public static void removeRow(JTable table, int row)
	{
		((DefaultTableModel) table.getModel()).removeRow(row);
	}
	
	
	public static boolean showConfirmDialog(Component comp, String title, String content)
	{
		int status = JOptionPane.showConfirmDialog(comp, content, title, JOptionPane.YES_NO_OPTION);
		return (status == JOptionPane.YES_OPTION) ? true : false;
	}
	
	public static void changeBackground(Component comp, Color color)
	{
		comp.setBackground(color);
	}
	
	public static void setTextBelowIconButton(JButton btn)
	{
		btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);
	}

	public static Dimension getFullSizeScreenDimension() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public static boolean showConfirm(Component comp, String msg)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int status = JOptionPane.showConfirmDialog(comp, msg, "Thông Báo", JOptionPane.YES_NO_OPTION);
		if (status == JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
	
	public static DefaultTableCellRenderer getRenderRightColumn() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		return rightRenderer;
	}

	public static DefaultTableCellRenderer getRenderLeftColumn() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.LEFT);
		return rightRenderer;
	}

	
	public static enum StatusOrder {
		OPEN(0),
		IN_PROGRESS(1),
		IN_SHIPPING(2),
		DONE(3),
		REJECT(-1);

		private int numberCode;

		StatusOrder(int numberCode) {
			this.numberCode	= numberCode;
		}

		public int getNumberCode() {
			return numberCode;
		}

		@Override
		public String toString() {
			return getTitleStatusOrder(this);
		}
	}


	public static String getTitleStatusOrder(StatusOrder statusOrder) {
		switch (statusOrder) {
			case REJECT:
				return "Đã hủy";
			case IN_PROGRESS:
				return "Đang xử lý";
			case OPEN:
				return "Mới";
			case IN_SHIPPING:
				return "Đang giao";
			case DONE:
				return "Đã xong";
		}

		return "";
	}

	public static StatusOrder getStatusOrderFromNameStatus(String nameStatus) {
		switch (nameStatus) {
			case "REJECT":
				return StatusOrder.REJECT;
			case "IN PROGRESS":
				return StatusOrder.IN_PROGRESS;
			case "OPEN":
				return StatusOrder.OPEN;
			case "IN SHIPPING":
				return StatusOrder.IN_SHIPPING;
			case "DONE":
				return StatusOrder.DONE;
		}
		return null;
	}

	public static String getNameStatusFromStatusOrder(StatusOrder statusOrder) {
		if (statusOrder != null ) {
			switch (statusOrder) {
				case REJECT:
					return "REJECT";
				case IN_PROGRESS:
					return "IN PROGRESS";
				case OPEN:
					return "OPEN";
				case IN_SHIPPING:
					return "IN SHIPPING";
				case DONE:
					return "DONE";
				default:
					return null;
			}
		}

		return null;
	}

	public static StatusOrder getStatusOrderFromTitle(String title) {
		switch (title) {
			case "Đã hủy":
				return StatusOrder.REJECT;
			case "Đang xử lý":
				return StatusOrder.IN_PROGRESS;
			case "Mới":
				return StatusOrder.OPEN;
			case "Đang giao":
				return StatusOrder.IN_SHIPPING;
			case "Đã xong":
				return StatusOrder.DONE;
		}
		return null;
	}

	public static StatusOrder getStatusOrderFromCode(int code) {
		return Arrays.stream(StatusOrder.values())
				.filter(statusOrder -> statusOrder.getNumberCode() == code)
				.findFirst()
				.orElse(null);
	}


	
}
