package com.duan.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.duan.helper.SwingHelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.Customizer;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import javax.swing.JSeparator;

public class MainJFrame2 extends JFrame {

	private static final Color COLOR_MENU_DEFAULT = new Color(102, 205, 170);
	private static final Color COLOR_MENU_HOVER = new Color(150, 222, 197);
	private static final Color COLOR_MENU_CLICKED = new Color(144, 238, 144);
	private static final Border BORDER_HIGHLIGHT = new MatteBorder(0, 7, 0, 0, (Color) new Color(255, 69, 0));

	private JPanel contentPane;
	private SwingHelper sHelper = new SwingHelper();
	private JPanel pnlContent;
	private JPanel pnl1;
	private JPanel pnl2;
	private JPanel pnl3;
	private JPanel pnl4;
	private JPanel pnl5;
	private JPanel pnl6;
	private JPanel pnl7;
	
	
	
	private BorderLayout borderLayout = new BorderLayout(0, 0);
	
	private BookJFrame bookJFrame = new BookJFrame();
	private LoginJFrame loginJFrame = new LoginJFrame();
	private RentBookJFrame rentBookJFrame = new RentBookJFrame();
	private SellBookJFrame sellBookJFrame = new SellBookJFrame();
	private UserJFrame userJFrame = new UserJFrame();
	private AdminJFrame adminJFrame = new AdminJFrame();
	private StatisticalJFrame statisticalJFrame = new StatisticalJFrame();
	
	//Khai báo container
	private Container bookContainer = bookJFrame.getContentPane();
	private Container rentbContainer = rentBookJFrame.getContentPane();
	private Container sellBookContainer = sellBookJFrame.getContentPane();
	private Container userContainer = userJFrame.getContentPane();
	private Container adminContainer = adminJFrame.getContentPane();
	private Container statisticalContainer = new StatisticalJFrame().getContentPane();
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainJFrame2 frame = new MainJFrame2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainJFrame2() 
	{
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				if (sHelper.showConfirm(getContentPane(), "Bạn có chắc muốn tắt ứng dụng này không?"))
				{
					System.exit(0);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJFrame2.class.getResource("/com/duan/icon/icons8_book_64px_3.png")));
		setTitle("Bookstore Managerment");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1093, 687);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setPreferredSize(new Dimension(280, 1));
		pnlMenu.setBackground(new Color(102, 205, 170));
		
		JPanel pnlMenuList = new JPanel();
		pnlMenuList.setBounds(0, 114, 280, 454);
		pnlMenuList.setBackground(COLOR_MENU_DEFAULT);
		pnlMenuList.setLayout(new GridLayout(0, 1, 0, 0));
		
		pnl1 = new JPanel();
		pnlMenuList.add(pnl1);
		pnl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl1.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl1, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl1.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl1, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl1);
				showBookJFrame();
				setHighlightMenu(pnl1);
			}
		});
		pnl1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl1.setBackground(COLOR_MENU_DEFAULT);
		pnl1.setLayout(null);
		
		JLabel lblTItle1 = new JLabel("Kho sách");
		lblTItle1.setForeground(new Color(0, 0, 0));
		lblTItle1.setBounds(22, 0, 178, 66);
		lblTItle1.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_books_32px_1.png")));
		lblTItle1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTItle1.setHorizontalAlignment(SwingConstants.LEFT);
		pnl1.add(lblTItle1);
		
		pnl2 = new JPanel();
		pnlMenuList.add(pnl2);
		pnl2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl2.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl2, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl2.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl2, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl2);
				showRentBookJFrame();
				setHighlightMenu(pnl2);
			}
		});
		pnl2.setBackground(new Color(102, 205, 170));
		pnl2.setLayout(null);
		
		JLabel lblKhchHng = new JLabel("Thuê sách");
		lblKhchHng.setForeground(new Color(0, 0, 0));
		lblKhchHng.setBounds(22, 0, 178, 66);
		lblKhchHng.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_bookmark_32px.png")));
		lblKhchHng.setHorizontalAlignment(SwingConstants.LEFT);
		lblKhchHng.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl2.add(lblKhchHng);
		
		pnl3 = new JPanel();
		pnlMenuList.add(pnl3);
		pnl3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl3.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl3, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl3.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl3, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl3);
				showSellBookJFrame();
				setHighlightMenu(pnl3);
			}
		});
		pnl3.setBackground(COLOR_MENU_DEFAULT);
		pnl3.setLayout(null);
		
		JLabel lblNhnVin = new JLabel("Bán sách");
		lblNhnVin.setForeground(new Color(0, 0, 0));
		lblNhnVin.setBounds(22, 0, 178, 66);
		lblNhnVin.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_buy_for_change_32px.png")));
		lblNhnVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhnVin.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl3.add(lblNhnVin);
		
		pnl7 = new JPanel();
		pnlMenuList.add(pnl7);
		pnl7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl7.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl7, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl7.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl7, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl7);
				showUserJFrame();
				setHighlightMenu(pnl7);
			}
		});
		pnl7.setBackground(COLOR_MENU_DEFAULT);
		pnl7.setLayout(null);
		
		JLabel lblCuHnh = new JLabel("Khách hàng");
		lblCuHnh.setForeground(new Color(0, 0, 0));
		lblCuHnh.setBounds(22, 0, 178, 66);
		lblCuHnh.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_user_group_man_woman_32px.png")));
		lblCuHnh.setHorizontalAlignment(SwingConstants.LEFT);
		lblCuHnh.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl7.add(lblCuHnh);
		
		pnl4 = new JPanel();
		pnlMenuList.add(pnl4);
		pnl4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl4.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl4, COLOR_MENU_HOVER);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl4.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl4, COLOR_MENU_DEFAULT);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl4);
				setHighlightMenu(pnl4);
			}
		});
		pnl4.setBackground(COLOR_MENU_DEFAULT);
		pnl4.setLayout(null);
		
		JLabel lblHan = new JLabel("Mất sách");
		lblHan.setForeground(new Color(0, 0, 0));
		lblHan.setBounds(22, 0, 178, 66);
		lblHan.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_generic_book_file_type_32px_3.png")));
		lblHan.setHorizontalAlignment(SwingConstants.LEFT);
		lblHan.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl4.add(lblHan);
		
		pnl5 = new JPanel();
		pnlMenuList.add(pnl5);
		pnl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl5.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl5.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl5, COLOR_MENU_HOVER);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl5.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl5, COLOR_MENU_DEFAULT);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl5);
				showStatisticalJFrame();
				setHighlightMenu(pnl5);
			}
		});
		pnl5.setBackground(COLOR_MENU_DEFAULT);
		pnl5.setLayout(null);
		
		JLabel lblThu = new JLabel("Thống kê");
		lblThu.setForeground(new Color(0, 0, 0));
		lblThu.setBounds(22, 0, 178, 66);
		lblThu.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_statistics_32px.png")));
		lblThu.setHorizontalAlignment(SwingConstants.LEFT);
		lblThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl5.add(lblThu);
		
		pnl6 = new JPanel();
		pnlMenuList.add(pnl6);
		pnl6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				if (pnl6.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl6, COLOR_MENU_HOVER);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (pnl6.getBackground() != COLOR_MENU_CLICKED)
				{
					sHelper.changeBackground(pnl6, COLOR_MENU_DEFAULT);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setBackgroundMenuClicked(pnl6);
				showAdminJFrame();
				setHighlightMenu(pnl6);
			}
		});
		pnl6.setBackground(COLOR_MENU_DEFAULT);
		pnl6.setLayout(null);
		
		JLabel lblNhnVin_1 = new JLabel("Quản trị viên");
		lblNhnVin_1.setForeground(new Color(0, 0, 0));
		lblNhnVin_1.setBounds(22, 0, 178, 66);
		lblNhnVin_1.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_user_credentials_32px.png")));
		lblNhnVin_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhnVin_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl6.add(lblNhnVin_1);
		
		JPanel pnl8 = new JPanel();
		pnl8.setBounds(0, 586, 280, 51);
		pnl8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sHelper.changeBackground(pnl8, COLOR_MENU_HOVER);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sHelper.changeBackground(pnl8, COLOR_MENU_DEFAULT);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (sHelper.showConfirm(getContentPane(), "Bạn có chắc muốn đăng xuất không?"))
					logout();
			}
		});
		pnl8.setBackground(COLOR_MENU_DEFAULT);
		pnl8.setLayout(null);
		
		JLabel lblngXut = new JLabel("Đăng xuất");
		lblngXut.setForeground(new Color(0, 0, 0));
		lblngXut.setBounds(22, 0, 178, 51);
		lblngXut.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_exit_32px.png")));
		lblngXut.setHorizontalAlignment(SwingConstants.LEFT);
		lblngXut.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnl8.add(lblngXut);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlMenu, BorderLayout.WEST);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 22, 64, 67);
		lblLogo.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/icon/icons8_book_64px_3.png")));
		
		JLabel lblBookstore = new JLabel("BOOKSTORE");
		lblBookstore.setBounds(61, 11, 183, 58);
		lblBookstore.setForeground(new Color(255, 255, 255));
		lblBookstore.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblBookstore.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMenu.setLayout(null);
		pnlMenu.add(pnl8);
		pnlMenu.add(pnlMenuList);
		pnlMenu.add(lblLogo);
		pnlMenu.add(lblBookstore);
		
		JLabel lblManagerment = new JLabel("MANAGERMENT");
		lblManagerment.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagerment.setForeground(Color.WHITE);
		lblManagerment.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblManagerment.setBounds(60, 36, 220, 58);
		pnlMenu.add(lblManagerment);
		
		JLabel lblVersion = new JLabel("Version: 1.0.1");
		lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersion.setForeground(new Color(255, 255, 255));
		lblVersion.setBounds(20, 80, 237, 14);
		pnlMenu.add(lblVersion);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		pnlContent = new JPanel();
		pnlContent.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(64, 224, 208)));
		pnlCenter.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new CardLayout(0, 0));
		
		JLabel iconContent = new JLabel("");
		iconContent.setIcon(new ImageIcon(MainJFrame2.class.getResource("/com/duan/image/Wingman-simple-wallpaper-backgrounds.jpg")));
		iconContent.setHorizontalAlignment(SwingConstants.CENTER);
		pnlContent.add(iconContent, "name_113229955950600");
		sHelper.setAutoResizeIcon_PreferredSize(iconContent);
		setLocationRelativeTo(getOwner());
		
		
//		//create the font
//
//		try {
//		    //create the font to use. Specify the size!
//		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(getClass().getResource("/com/duan/library/OpenSans-Bold.ttf").getFile())).deriveFont(12f);
//		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		    //register the font
//		    ge.registerFont(customFont);
//		    //use the font
//		    lblTitle.setFont(customFont);
//		} catch (IOException e) {
//		    e.printStackTrace();
//		} catch(FontFormatException e) {
//		    e.printStackTrace();
//		}

	}
	
	//Hàm này sẽ set border các panel menu lại thành null và set border cho jpanel truyền vào là BORDER_HIGHLIGHT
	public void setHighlightMenu(JPanel pnl)
	{
		pnl1.setBorder(null);
		pnl2.setBorder(null);
		pnl3.setBorder(null);
		pnl4.setBorder(null);
		pnl5.setBorder(null);
		pnl6.setBorder(null);
		pnl7.setBorder(null);
		pnl.setBorder(BORDER_HIGHLIGHT);
	}
	
	public void setBackgroundMenuClicked(JPanel pnl)
	{
		pnl1.setBackground(COLOR_MENU_DEFAULT);
		pnl2.setBackground(COLOR_MENU_DEFAULT);
		pnl3.setBackground(COLOR_MENU_DEFAULT);
		pnl4.setBackground(COLOR_MENU_DEFAULT);
		pnl5.setBackground(COLOR_MENU_DEFAULT);
		pnl6.setBackground(COLOR_MENU_DEFAULT);
		pnl7.setBackground(COLOR_MENU_DEFAULT);
		pnl.setBackground(COLOR_MENU_CLICKED);
	}
	
	//Hiển thị nội dung chính panel ở giữa, dựa vào Container truyền vào
	public void setContainerShow(Container container)
	{
		pnlContent.removeAll();
		pnlContent.add(container);
		pnlContent.repaint();
		pnlContent.revalidate();
	}
	
	public void showBookJFrame()
	{
		//bookJFrame.setVisible(true);
		setContainerShow(bookContainer);
	}
	
	public void logout()
	{
		dispose();
		loginJFrame.setVisible(true);
	}
	
	public void showRentBookJFrame()
	{
		//rentBookJFrame.setVisible(true);
		setContainerShow(rentbContainer);
	}
	
	public void showSellBookJFrame()
	{
//		sellBookJFrame.setLocationRelativeTo(this);
//		sellBookJFrame.setVisible(true);
		setContainerShow(sellBookContainer);
	}
	
	public void showUserJFrame()
	{
//		userJFrame.setLocationRelativeTo(this);
//		userJFrame.setVisible(true);
		setContainerShow(userContainer);
	}
	
	public void showAdminJFrame()
	{
		setContainerShow(adminContainer);
	}
	
	public void showStatisticalJFrame()
	{
		setContainerShow(statisticalContainer);
	}
}
