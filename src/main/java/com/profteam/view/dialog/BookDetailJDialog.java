package com.profteam.view.dialog;

import com.profteam.core.Constants;
import com.profteam.core.FireBase;
import com.profteam.custom.message.MessageOptionPane;
import com.profteam.dao.AuthorDAO;
import com.profteam.dao.BookDAO;
import com.profteam.dao.CategoryDAO;
import com.profteam.dao.LocationDAO;
import com.profteam.dao.PublisherDAO;
import com.profteam.helper.DataHelper;
import com.profteam.helper.SettingSave;
import com.profteam.helper.SwingHelper;
import com.profteam.model.Book;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.GroupLayout.Alignment;

public class BookDetailJDialog extends JDialog {

	private JPanel contentPane;
	private JLabel lblMaSach;
	private JLabel lblTenSach;
	private JLabel lblTacGia;
	private JLabel lblTheLoai;
	private JLabel lblTrang;
	private JLabel lblXuatBan;
	private JLabel lblSoLuong;
	private JLabel lblGia;
	private JLabel lblDaBan;
	private JLabel lblChoThue;
	private JLabel lblImage;
	private JLabel lblViTri;
	private JTextField txtDescription;
	private JTextArea txtIntroduce;
	private File fileImage;
	
	private Book book;
	private FireBase fireBase = FireBase.getFireBase();


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					BookDetailJDialog frame = new BookDetailJDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookDetailJDialog(Component comp)
	{
		this();
		setLocationRelativeTo(comp);
	}
	public BookDetailJDialog() 
	{
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookDetailJDialog.class.getResource("/icon/icons8_details_popup_50px_1.png")));
		setTitle("Code Dạo Ký Sự");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblImage = new JLabel("Không có ảnh");
		lblImage.setSize(new Dimension(278, 365));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "Th\u00F4ng tin v\u1EC1 s\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel item0 = new JPanel();
		panel_3.add(item0);
		FlowLayout fl_item0 = (FlowLayout) item0.getLayout();
		fl_item0.setAlignment(FlowLayout.LEFT);
		
		JLabel lblMSch = new JLabel("Mã Sách:");
		lblMSch.setForeground(Color.DARK_GRAY);
		item0.add(lblMSch);
		lblMSch.setHorizontalAlignment(SwingConstants.LEFT);
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblMaSach = new JLabel("10580");
		lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item0.add(lblMaSach);
		
		JPanel item1 = new JPanel();
		panel_3.add(item1);
		FlowLayout flowLayout = (FlowLayout) item1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblTnSch_1 = new JLabel("Tên sách:");
		lblTnSch_1.setForeground(Color.DARK_GRAY);
		lblTnSch_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnSch_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		item1.add(lblTnSch_1);
		
		lblTenSach = new JLabel("Code Dạo Ký Sự");
		lblTenSach.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item1.add(lblTenSach);
		
		JPanel item2 = new JPanel();
		panel_3.add(item2);
		FlowLayout flowLayout_1 = (FlowLayout) item2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		JLabel lblTcGi = new JLabel("Tác giả:");
		lblTcGi.setForeground(Color.DARK_GRAY);
		item2.add(lblTcGi);
		lblTcGi.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTacGia = new JLabel("Phạm Hoàng Huy");
		lblTacGia.setForeground(new Color(255, 69, 0));
		lblTacGia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				AuthorDetailJDialog authorDetailJDialog = new AuthorDetailJDialog();
				authorDetailJDialog.setDetailModel(book.getAuthorId());
				authorDetailJDialog.setLocationRelativeTo(contentPane);
				authorDetailJDialog.setVisible(true);
			}
		});
		lblTacGia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblTacGia.setFont(new Font("Tahoma", Font.BOLD, 14));
		item2.add(lblTacGia);
		
		JPanel item3 = new JPanel();
		panel_3.add(item3);
		FlowLayout flowLayout_2 = (FlowLayout) item3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		JLabel lblXutBn = new JLabel("Thể loại:");
		lblXutBn.setForeground(Color.DARK_GRAY);
		lblXutBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item3.add(lblXutBn);
		
		lblTheLoai = new JLabel("Công Nghệ Thông Tin");
		lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item3.add(lblTheLoai);
		
		JPanel item4 = new JPanel();
		panel_3.add(item4);
		FlowLayout fl_item4 = (FlowLayout) item4.getLayout();
		fl_item4.setAlignment(FlowLayout.LEFT);
		
		JLabel lblSTrang = new JLabel("Số trang:");
		lblSTrang.setForeground(Color.DARK_GRAY);
		lblSTrang.setHorizontalAlignment(SwingConstants.LEFT);
		lblSTrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		item4.add(lblSTrang);
		
		lblTrang = new JLabel("325 trang");
		lblTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item4.add(lblTrang);
		
		JPanel item5 = new JPanel();
		panel_3.add(item5);
		FlowLayout fl_item5 = (FlowLayout) item5.getLayout();
		fl_item5.setAlignment(FlowLayout.LEFT);
		
		JLabel lblSLng = new JLabel("Xuất bản:");
		lblSLng.setForeground(Color.DARK_GRAY);
		lblSLng.setHorizontalAlignment(SwingConstants.LEFT);
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
		item5.add(lblSLng);
		
		lblXuatBan = new JLabel("Nhà Xuất Bản Trẻ (2017)");
		lblXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item5.add(lblXuatBan);
		
		JPanel item10 = new JPanel();
		FlowLayout fl_item10 = (FlowLayout) item10.getLayout();
		fl_item10.setAlignment(FlowLayout.LEFT);
		panel_3.add(item10);
		
		JLabel lblVTr = new JLabel("Vị trí:");
		lblVTr.setHorizontalAlignment(SwingConstants.LEFT);
		lblVTr.setForeground(Color.DARK_GRAY);
		lblVTr.setFont(new Font("Tahoma", Font.BOLD, 14));
		item10.add(lblVTr);
		
		lblViTri = new JLabel("30 quyển");
		lblViTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item10.add(lblViTri);
		
		JPanel item6 = new JPanel();
		panel_3.add(item6);
		FlowLayout fl_item6 = (FlowLayout) item6.getLayout();
		fl_item6.setAlignment(FlowLayout.LEFT);
		
		JLabel lblGiBn = new JLabel("Số lượng:");
		lblGiBn.setForeground(Color.DARK_GRAY);
		lblGiBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item6.add(lblGiBn);
		
		lblSoLuong = new JLabel("30 quyển");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item6.add(lblSoLuong);
		
		JPanel item7 = new JPanel();
		FlowLayout fl_item7 = (FlowLayout) item7.getLayout();
		fl_item7.setAlignment(FlowLayout.LEFT);
		panel_3.add(item7);
		
		JLabel lblGiBn_1 = new JLabel("Giá bán:");
		lblGiBn_1.setForeground(Color.DARK_GRAY);
		lblGiBn_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiBn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		item7.add(lblGiBn_1);
		
		lblGia = new JLabel("110.000 đ");
		lblGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item7.add(lblGia);
		
		JPanel item8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) item8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.add(item8);
		
		JLabel lblBn = new JLabel("Đã bán:");
		lblBn.setForeground(Color.RED);
		lblBn.setHorizontalAlignment(SwingConstants.LEFT);
		lblBn.setFont(new Font("Tahoma", Font.BOLD, 14));
		item8.add(lblBn);
		
		lblDaBan = new JLabel("15 quyển");
		lblDaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item8.add(lblDaBan);
		
		JPanel item9 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) item9.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_3.add(item9);
		
		JLabel lblangChoThu = new JLabel("Đang cho thuê:");
		lblangChoThu.setHorizontalAlignment(SwingConstants.LEFT);
		lblangChoThu.setForeground(Color.BLUE);
		lblangChoThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		item9.add(lblangChoThu);
		
		lblChoThue = new JLabel("10 quyển");
		lblChoThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		item9.add(lblChoThue);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 2, true), "Gi\u1EDBi thi\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		txtIntroduce = new JTextArea();
		txtIntroduce.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtIntroduce.setWrapStyleWord(true);
		txtIntroduce.setLineWrap(true);
		txtIntroduce.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtIntroduce.setOpaque(false);
		txtIntroduce.setEditable(false);
		scrollPane.setViewportView(txtIntroduce);
		
		JLabel lblGhiCh = new JLabel("Ghi chú:");
		lblGhiCh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGhiCh.setForeground(Color.DARK_GRAY);
		lblGhiCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescription.setBorder(null);
		txtDescription.setEditable(false);
		txtDescription.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblGhiCh)
							.addGap(10)
							.addComponent(txtDescription, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGhiCh)
						.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setDetail(Book book) throws SQLException
	{
		this.book = book;
		
		String price = DataHelper.getFormatForMoney(book.getPrice()) + SettingSave.getSetting().getMoneySymbol();
		String publisher = PublisherDAO.findById(book.getPublisherId()).getName() + " (" + book.getPublicationYear() + ")";
		String categoryTitle = CategoryDAO.getTitleById(book.getCategoryId());
		String locationTitle = LocationDAO.findByID(book.getLocationId()).getLocationName();
		String authorName = AuthorDAO.findById(book.getAuthorId()).getFullName();
		
		lblMaSach.setText(book.getId());
		lblTenSach.setText(book.getTitle());
		lblTacGia.setText(authorName);
		lblTheLoai.setText(categoryTitle);
		lblTrang.setText(book.getPageNum() + " trang");
		lblXuatBan.setText(publisher);
		lblSoLuong.setText(book.getAmount() + " quyển");
		lblViTri.setText(locationTitle);
		lblGia.setText(price);
		lblDaBan.setText(BookDAO.getCountSold(book.getId()) + " quyển");
		lblChoThue.setText(BookDAO.getCountBeingRented(book.getId()) + " quyển");
		txtIntroduce.setText(book.getIntroduce());
		txtDescription.setText(book.getDescription());
		setTitle("Thông tin sách | " + book.getTitle());
		
		//Set image
		if (book.getImage() != null)
		{
			try 
			{
				setImage(book.getImage());
			} 
			catch (NullPointerException e) 
			{
				setImage(null);
			}
		}
		else 
		{
			setImage(null);
		}
	}
	
	//Cập nhật lại ảnh của sách
	public void setImage(String imageName)
	{
		if (imageName != null && imageName.length() > 0)
		{
			ImageIcon icon = null;
			try {
				icon = new ImageIcon(fireBase.downloadImg(Constants.REMOTE_BOOK_IMG_FOLDER, imageName));
				lblImage.setIcon(icon);
				lblImage.setText("");
				SwingHelper.setAutoResizeIcon(lblImage, Image.SCALE_DEFAULT);
			}
			catch (IOException e) {
				MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra khi tải hình", MessageOptionPane.ICON_NAME_WARNING);
				removeImage();
			}
		}
		else
		{
			lblImage.setIcon(null);
			lblImage.setText("Không có ảnh");
		}
	}

	//Xóa icon lblImage và set fileImage = null
	public void removeImage() {
		lblImage.setText("Chưa có ảnh");
		lblImage.setIcon(null);
		this.fileImage = null;
	}
}
