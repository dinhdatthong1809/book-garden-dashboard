package com.profteam.custom.common;

import com.profteam.helper.SwingHelper;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class BookJPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BookJPanel() {
		setBorder(new LineBorder(Color.DARK_GRAY, 2));
		setLayout(new BorderLayout(0, 0));
		setSize(208, 265);
		setPreferredSize(new Dimension(180, 240));
		
		JLabel lblTitle = new JLabel("Tôi thấy hoa vàng trên cỏ xanh");
		lblTitle.setPreferredSize(new Dimension(151, 25));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle, BorderLayout.SOUTH);
		
		JLabel lblImage = new JLabel("chưa có ảnh");
		lblImage.setIcon(new ImageIcon(BookJPanel.class.getResource("/image/toi_thay_hoa_vang_tren_co_xanh__nguyen_nhat_anh.jpg")));
		lblImage.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblImage, BorderLayout.CENTER);
		SwingHelper.setAutoResizeIcon_PreferredSize(lblImage);
	}

}
