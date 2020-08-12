package com.profteam.view;

import com.profteam.controller.ExportPDF;
import com.profteam.custom.common.JTableBlue;
import com.profteam.dao.AdminDAO;
import com.profteam.dao.OrderDAO;
import com.profteam.dao.OrderDetailDAO;
import com.profteam.dao.UserDAO;
import com.profteam.helper.DataHelper;
import com.profteam.helper.DateHelper;
import com.profteam.helper.SettingSave;
import com.profteam.helper.SwingHelper;
import com.profteam.model.Admin;
import com.profteam.model.Book;
import com.profteam.model.BookProduct;
import com.profteam.model.Order;
import com.profteam.model.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailJDialog extends JDialog {

	private JPanel contentPane;
	private JTableBlue tblOrderDetail;
	private JLabel lblUser;
	private JLabel lblNhnVinBn;
	private JLabel lblAdmin;
	private JLabel lblCreatedDate;
	private List<BookProduct> listBookProduct = new ArrayList<BookProduct>();
	private Order order;
	private JLabel lblTngCng;
	private JLabel lblTotalPrice;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					OrderDetailJDialog frame = new OrderDetailJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OrderDetailJDialog(Component comp)
	{
		this();
		setLocationRelativeTo(comp);
	}
	public OrderDetailJDialog() 
	{
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderDetailJDialog.class.getResource("/icon/icons8_details_popup_50px_1.png")));
		setTitle("Chi tiết đơn hàng số: 3303");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTiKhonMua = new JLabel("Tài khoản mua:");
		lblTiKhonMua.setForeground(Color.DARK_GRAY);
		lblTiKhonMua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTiKhonMua.setBounds(10, 11, 105, 21);
		contentPane.add(lblTiKhonMua);
		
		JLabel lblNgyMua = new JLabel("Ngày mua:");
		lblNgyMua.setForeground(Color.DARK_GRAY);
		lblNgyMua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgyMua.setBounds(10, 43, 105, 21);
		contentPane.add(lblNgyMua);
		
		lblCreatedDate = new JLabel("12-05-2019");
		lblCreatedDate.setForeground(Color.DARK_GRAY);
		lblCreatedDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreatedDate.setBounds(125, 43, 299, 21);
		contentPane.add(lblCreatedDate);
		
		lblAdmin = new JLabel("Đào Quang Tiến (quantienpoly)");
		lblAdmin.setForeground(Color.DARK_GRAY);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdmin.setBounds(125, 75, 299, 21);
		contentPane.add(lblAdmin);
		
		lblNhnVinBn = new JLabel("Nhân viên bán:");
		lblNhnVinBn.setForeground(Color.DARK_GRAY);
		lblNhnVinBn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhnVinBn.setBounds(10, 75, 105, 21);
		contentPane.add(lblNhnVinBn);
		
		lblUser = new JLabel("Nguyễn Đại Hào (daihao12mc)");
		lblUser.setForeground(Color.RED);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUser.setBounds(125, 11, 299, 21);
		contentPane.add(lblUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 516, 174);
		contentPane.add(scrollPane);
		
		tblOrderDetail = new JTableBlue();
		tblOrderDetail.setRowHeight(30);
		tblOrderDetail.setModel(new DefaultTableModel(null,new String[] {"MÃ SÁCH", "TÊN SÁCH", "GIÁ HIỆN TẠI", "GIÁ LÚC MUA", "SỐ LƯỢNG"}) 
		{
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		tblOrderDetail.getColumnModel().getColumn(0).setPreferredWidth(50);;
		tblOrderDetail.getColumnModel().getColumn(1).setPreferredWidth(180);;
		scrollPane.setViewportView(tblOrderDetail);
		
		JButton btnPrint = new JButton("Xuất đơn");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (ExportPDF.writeOrder(order))
				{
					ExportPDF.showPDFOrder();
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPrint.setIcon(new ImageIcon(OrderDetailJDialog.class.getResource("/icon/icons8_print_32px.png")));
		btnPrint.setBounds(434, 11, 92, 87);
		SwingHelper.setTextBelowIconButton(btnPrint);
		contentPane.add(btnPrint);
		
		lblTngCng = new JLabel("Tổng cộng:");
		lblTngCng.setForeground(Color.DARK_GRAY);
		lblTngCng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTngCng.setBounds(10, 292, 69, 21);
		contentPane.add(lblTngCng);
		
		lblTotalPrice = new JLabel("350000đ");
		lblTotalPrice.setForeground(Color.RED);
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalPrice.setBounds(89, 292, 169, 21);
		contentPane.add(lblTotalPrice);
	}
	
	public void setDetailModel(int order_id)
	{
		try 
		{
			this.order = OrderDAO.findByID(order_id);
			listBookProduct = OrderDetailDAO.getListProducts(this.order.getId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setDetailModel(Order order)
	{
		try 
		{
			this.order = order;
			listBookProduct = OrderDetailDAO.getListProducts(this.order.getId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hiển thị thông tin chi tiết lên form
	public void showDetail()
	{
		User user;
		try 
		{
			user = UserDAO.findByID(order.getUserId());
			Admin admin = AdminDAO.findByID(order.getAdminId());
			String createdDate = DateHelper.dateToString(order.getDateCreated(), SettingSave.getSetting().getDateFormat());
			String totalPrice = DataHelper.getFormatForMoney(OrderDetailDAO.getTotalPrice(order.getId())) + SettingSave.getSetting().getMoneySymbol();
			//String totalAmount = DataHelper.getFormatForMoney(OrderDetailDAO.getTotalAmountBook(order.getId())) + SettingSave.getSetting().getMoneySymbol();
			
			setTitle("Chi tiết đơn hàng số: " + order.getId());
			
			if (user != null)
			{
				lblUser.setText(user.getFullname() + " (" + user.getUsername() + ")");
			}
			else
			{
				lblUser.setText("Không có");
			}
			
			lblCreatedDate.setText(createdDate);
			lblAdmin.setText(admin.getFullname() + " (" + admin.getUsername() + ")");
			lblTotalPrice.setText(totalPrice);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblOrderDetail.getModel();
		model.setRowCount(0);
		
		for (BookProduct p : listBookProduct)
		{
			Book book = p.getBook();
			String giaHienTai = DataHelper.getFormatForMoney(book.getPrice()) + SettingSave.getSetting().getMoneySymbol();
			String giaLucMua =  DataHelper.getFormatForMoney(p.getPrice()) + SettingSave.getSetting().getMoneySymbol();
			
			Object[] rowData = {book.getId(), book.getTitle(), giaHienTai, giaLucMua, p.getAmount()};
			model.addRow(rowData);
		}
	}
}
