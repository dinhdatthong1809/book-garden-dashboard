package com.profteam.view.frame;

import com.profteam.custom.common.JTableRed;
import com.profteam.custom.message.MessageOptionPane;
import com.profteam.dao.AdminDAO;
import com.profteam.dao.OrderDAO;
import com.profteam.dao.OrderDetailDAO;
import com.profteam.dao.UserDAO;
import com.profteam.helper.DataHelper;
import com.profteam.helper.DateHelper;
import com.profteam.helper.SettingSave;
import com.profteam.helper.SwingHelper;
import com.profteam.model.Order;
import com.profteam.view.dialog.OrderDetailJDialog;
import com.profteam.view.dialog.OrderEditorJDialog;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class OrderJFrame extends JFrame {

	private JPanel contentPane;
	private JTableRed tblOrder;
	private JButton btnMaxLeft;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnMaxRight;
	private JButton btnDetail;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JComboBox cbxStatus;

	private OrderEditorJDialog insertOrderJDialog = new OrderEditorJDialog();
	private OrderEditorJDialog editOrderJDialog = new OrderEditorJDialog();
	private OrderDetailJDialog orderDetailJDialog = new OrderDetailJDialog();
	
	private List<Order> listOrder;
	private int indexSelected = -1;
	private JTextField txtSearch;
	private JButton btnSearch;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderJFrame frame = new OrderJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OrderJFrame() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(OrderJFrame.class.getResource("/icon/icons8_buy_for_change_64px_1.png")));
		setTitle("Bán Sách");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 833, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u1EA3ng d\u1EEF li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel pnlController = new JPanel();
		pnlController.setBorder(new TitledBorder(null, "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlController.setLayout(new GridLayout(0, 1, 10, 5));
		
		btnDetail = new JButton("Xem chi tiết");
		btnDetail.setEnabled(false);
		btnDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnDetail);
		btnDetail.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				showOrderDetail();
			}
		});
		btnDetail.setIcon(new ImageIcon(OrderJFrame.class.getResource("/icon/icons8_details_popup_50px.png")));
		pnlController.add(btnDetail);
		
		btnAdd = new JButton("Thêm mới");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showInsertOrder();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnAdd);
		btnAdd.setIcon(new ImageIcon(OrderJFrame.class.getResource("/icon/icons8_add_50px_3.png")));
		pnlController.add(btnAdd);
		
		btnEdit = new JButton("Cập nhật");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showEditOrder();
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnEdit);
		btnEdit.setIcon(new ImageIcon(OrderJFrame.class.getResource("/icon/icons8_edit_property_50px.png")));
		pnlController.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(MessageOptionPane.showConfirmDialog(contentPane, "Bạn có muốn xóa đơn hàng này?"))
				{
					try 
					{
						OrderDAO.delete(listOrder.get(indexSelected).getId());
						listOrder.remove(indexSelected);
						fillToTable();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		SwingHelper.setTextBelowIconButton(btnDelete);
		btnDelete.setIcon(new ImageIcon(OrderJFrame.class.getResource("/icon/icons8_delete_50px.png")));
		pnlController.add(btnDelete);
		
		tblOrder = new JTableRed();
		tblOrder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				eventTableSelectRow();
				System.out.println("OK");
			}
		});
		tblOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				eventTableSelectRow();
			}
			@Override
			public void mouseClicked(MouseEvent evt) 
			{
				if (evt.getClickCount() >= 2 && indexSelected != -1)
				{
					showOrderDetail();
				}
			}
		});
		tblOrder.setRowHeight(35);
		tblOrder.setModel(new DefaultTableModel(null, new String[] {"MÃ ĐƠN", "TÀI KHOẢNG", "NHÂN VIÊN", "SỐ LƯỢNG", "TỔNG TIỀN", "NGÀY BÁN", "TRẠNG THÁI"})
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
//		tblOrder.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent evt) 
//			{
//				eventTableSelectRow();
//			}
//		});
		scrollPane_1.setViewportView(tblOrder);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0, 15, 0));
		
		btnMaxLeft = new JButton("|<");
		btnMaxLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblOrder.getRowCount();
				if (rowCount > 0)
				{
					indexSelected = 0;
					tblOrder.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		panel.add(btnMaxLeft);
		
		btnLeft = new JButton("<");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblOrder.getRowCount();
				if (indexSelected > 0 && rowCount > 0)
				{
					indexSelected--;
					tblOrder.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		btnLeft.setEnabled(false);
		panel.add(btnLeft);
		
		btnRight = new JButton(">");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int rowCount = tblOrder.getRowCount();
				if (indexSelected < rowCount - 1 && rowCount > 0)
				{
					indexSelected++;
					tblOrder.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		btnRight.setEnabled(false);
		panel.add(btnRight);
		
		JButton btnMaxRight = new JButton(">|");
		btnMaxRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int rowCount = tblOrder.getRowCount();
				if (rowCount > 0)
				{
					indexSelected = rowCount - 1;
					tblOrder.setRowSelectionInterval(indexSelected, indexSelected);
					setControllModeTo_Editable();
				}
			}
		});
		panel.add(btnMaxRight);
		
		JButton label = new JButton("Tải lại");
		label.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				refresh();
			}
		});
		label.setIcon(new ImageIcon(OrderJFrame.class.getResource("/icon/icons8_synchronize_24px.png")));
		label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cbxStatus = new JComboBox();
		cbxStatus.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Đã hủy", "Đang xử lý", "Mới", "Đang giao", "Đã xong"}));
//		cbxStatus.setModel(new DefaultComboBoxModel(SwingHelper.StatusOrder.values()));

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		
		txtSearch = new JTextField();
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSearch();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTrangThai)
									.addGap(10)
									.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSearch))
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
							.addGap(10)
							.addComponent(pnlController, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addComponent(lblTrangThai))
								.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
						.addComponent(pnlController, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		contentPane.setLayout(gl_contentPane);
		
		getDataTolist();
		fillToTable();
	}

	private void onSearch() {
		String searchValue = txtSearch.getText();
		try
		{
			listOrder = OrderDAO.searchByIdAndStatus(DataHelper.getInt(searchValue), SwingHelper.getStatusOrderFromTitle(cbxStatus.getSelectedItem().toString()));
			fillToTable();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void getDataTolist()
	{
		try 
		{
			listOrder = OrderDAO.getAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void fillToTable()
	{
		DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
		model.setRowCount(0);
		
		try 
		{
			for (Order order : listOrder)
			{
				String user = "Không có";
				if (UserDAO.findByID(order.getUserId()) != null)
					user = UserDAO.findByID(order.getUserId()).getUsername();
				
				String admin = AdminDAO.findByID(order.getAdminId()).getUsername();
				String createdDate = DateHelper.dateToString(order.getDateCreated(), SettingSave.getSetting().getDateFormat());
				String totalPrice = DataHelper.getFormatForMoney(OrderDetailDAO.getTotalPrice(order.getId())) + SettingSave.getSetting().getMoneySymbol();
				String totalAmount = OrderDetailDAO.getTotalAmountBook(order.getId()) + " quyển";
				String status = SwingHelper.getTitleStatusOrder(SwingHelper.getStatusOrderFromNameStatus(order.getStatus()));
				
				Object[] rowData = {order.getId(), user, admin, totalAmount, totalPrice, createdDate, status};
				model.addRow(rowData);
			}
			
			//Nếu điều kiện hợp lý thì set select row lại y như lúc chưa fillToTable
			int rowCount = tblOrder.getRowCount();
			if (indexSelected != -1)
			{
				if (indexSelected < rowCount && rowCount > 0)
				{
					tblOrder.setRowSelectionInterval(indexSelected, indexSelected);
				}
				else
				{
					indexSelected = rowCount - 1;
					if (indexSelected > -1)
					{
						tblOrder.setRowSelectionInterval(indexSelected, indexSelected);
					}
					else
					{
						setControllModeTo_Nothing();
					}
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	//Hiển thị hộp thoại JDialog chi tiết thông tin về đơn hàng đang được chọn
	private void showOrderDetail()
	{
		int order_id = DataHelper.getInt( tblOrder.getValueAt(indexSelected, 0).toString() );
		orderDetailJDialog.setLocationRelativeTo(this);
		orderDetailJDialog.setDetailModel(order_id);
		orderDetailJDialog.showDetail();
		orderDetailJDialog.fillToTable();
		orderDetailJDialog.setVisible(true);
	}
	
	
	//Sự kiện được gọi khi nhấn nút 'Thêm mới' nó sẽ mở ra một cửa sổ để nhập thông tin order vào
	private void showInsertOrder()
	{
		insertOrderJDialog = new OrderEditorJDialog();
		insertOrderJDialog.setLocationRelativeTo(this);
		insertOrderJDialog.setEditMode(false);
		insertOrderJDialog.setOrderJFrame(this);
		insertOrderJDialog.setVisible(true);
	}
	
	//Mở ra một cửa sổ để chỉnh sửa thông tin order đang chọn
	private void showEditOrder()
	{
		try 
		{
			insertOrderJDialog = new OrderEditorJDialog();
			editOrderJDialog.setLocationRelativeTo(this);
			editOrderJDialog.setEditMode(true);
			editOrderJDialog.setOrderJFrame(this);
			Order order = OrderDAO.findByID(DataHelper.getInt(tblOrder.getValueAt(indexSelected, 0).toString()));
			editOrderJDialog.setOrderModel(listOrder.get(indexSelected));
			editOrderJDialog.setVisible(true);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Hàm này sẽ dc gọi khi có 1 dòng trong bảng dc chọn vào
	public void eventTableSelectRow()
	{
		indexSelected = tblOrder.getSelectedRow();
		setControllModeTo_Editable();
	}
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Nothing()
	{
		btnAdd.setEnabled(true);
		
		btnDelete.setEnabled(false);
		btnDetail.setEnabled(false);
		btnEdit.setEnabled(false);
		
		//Các nút di chuyển select
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
	}
	
	//Set các nút nhấn controll chỉ enable nút "Thêm Mới"
	//Chỉ gọi khi không có dòng nào trong bảng tblBook được chọn
	public void setControllModeTo_Editable()
	{
		btnDelete.setEnabled(true);
		btnDetail.setEnabled(true);
		btnEdit.setEnabled(true);
		btnAdd.setEnabled(true);
		
		//Các nút di chuyển select
		btnLeft.setEnabled(true);
		btnRight.setEnabled(true);
	}
	
	public void refresh()
	{
		getDataTolist();
		fillToTable();
	}
}
