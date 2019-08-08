package com.duan.custom;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;

public class MessageJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int posX;
	private int posY;
	private JLabel lblIcon;
	
	public static void main(String[] args) 
	{
		try {
			MessageJDialog dialog = new MessageJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MessageJDialog() 
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setModal(true);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				setLocation(getX() + e.getX() - posX, getY() + e.getY() - posY);
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				posX = e.getX();
				posY = e.getY();
			}
		});
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
		setResizable(false);
		setSize(507, 230);
		getContentPane().setLayout(null);
		
		JLabel lblClose = new JLabel("X");
		lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblClose.setForeground(Color.WHITE);
				lblClose.setBackground(Color.RED);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.BLACK);
				lblClose.setBackground(new JLabel().getBackground()); 
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblClose.setOpaque(true);
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(Color.BLACK);
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClose.setBounds(482, 0, 25, 25);
		getContentPane().add(lblClose);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(MessageJDialog.class.getResource("/com/duan/icon/icons8_info_100px_1.png")));
		lblIcon.setBackground(Color.LIGHT_GRAY);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setBounds(10, 31, 100, 100);
		getContentPane().add(lblIcon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(129, 49, 364, 139);
		getContentPane().add(scrollPane);
		
		JTextArea txtContent = new JTextArea();
		txtContent.setWrapStyleWord(true);
		txtContent.setLineWrap(true);
		txtContent.setFont(new Font("Tahoma", Font.BOLD, 13));
		scrollPane.setViewportView(txtContent);
		
		JLabel lblTitle = new JLabel("Thông Báo");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 5, 507, 30);
		getContentPane().add(lblTitle);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnOk.setForeground(SystemColor.windowText);
		btnOk.setBackground(SystemColor.controlHighlight);
		btnOk.setBounds(404, 196, 89, 23);
		getContentPane().add(btnOk);
		
		setLocationRelativeTo(getOwner());

	}
}