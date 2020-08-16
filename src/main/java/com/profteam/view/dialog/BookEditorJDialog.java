package com.profteam.view.dialog;

import com.profteam.core.Constants;
import com.profteam.core.FireBase;
import com.profteam.custom.message.MessageOptionPane;
import com.profteam.dao.AuthorDAO;
import com.profteam.dao.BookDAO;
import com.profteam.dao.CategoryDAO;
import com.profteam.dao.LocationDAO;
import com.profteam.dao.PublisherDAO;
import com.profteam.dao.StorageDetailDao;
import com.profteam.helper.DataHelper;
import com.profteam.helper.SettingSave;
import com.profteam.helper.SwingHelper;
import com.profteam.model.Author;
import com.profteam.model.Book;
import com.profteam.model.Category;
import com.profteam.model.Location;
import com.profteam.model.Publisher;
import com.profteam.view.frame.BookJFrame;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class BookEditorJDialog extends JDialog {
    
    private JPanel contentPane;
    private JTextField txtMaSach;
    private JTextField txtTenSach;
    private JTextField txtSoTrang;
    private JTextField txtGia;
    private JComboBox cboTheLoai;
    private JTextArea txtGhiChu;
    private JComboBox cboNamXuatBan;
    private JLabel lblImage;
    private JComboBox cboViTri;
    
    CategoryJDialog categoryJDialog = new CategoryJDialog();
    LocationJDialog locationJDialog = new LocationJDialog();
    AuthorJDialog authorJDialog = new AuthorJDialog();
    PublisherJDialog publisherJDialog = new PublisherJDialog();
    
    List<Category> listCategory;
    List<Location> listLocation;
    List<Publisher> listPublisher;
    List<Author> listAuthor;
    
    BookJFrame bookJFrame;
    private File fileImage;
    
    private boolean isEditMode;
    
    private Book bookEdit;
    private JComboBox cboPublisher;
    private JComboBox cboAuthor;
    private JTextArea txtIntroduct;
    
    private FireBase fireBase = FireBase.getFireBase();
    
    private long time;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BookEditorJDialog frame = new BookEditorJDialog();
                frame.setVisible(true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public BookEditorJDialog() {
        setResizable(false);
        init();
        setTitle("Thêm Sách");
        isEditMode = false;
    }
    
    public BookEditorJDialog(Book book) {
        this();
        try {
            setBookEditor(book);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    private void init() {
        setModal(true);
        setTitle("Thêm sách");
        setIconImage(Toolkit.getDefaultToolkit().getImage(BookEditorJDialog.class.getResource("/icon/icons8_edit_property_50px.png")));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 648, 478);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.menu);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel pnlForm = new JPanel();
        pnlForm.setBackground(SystemColor.menu);
        pnlForm.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        pnlForm.setBounds(10, 11, 398, 425);
        contentPane.add(pnlForm);
        pnlForm.setLayout(null);
        
        JLabel lblMaSach = new JLabel("Mã sách");
        lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMaSach.setBounds(10, 11, 75, 24);
        pnlForm.add(lblMaSach);
        
        txtMaSach = new JTextField();
        txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtMaSach.setBounds(95, 11, 293, 24);
        pnlForm.add(txtMaSach);
        txtMaSach.setColumns(10);
        
        JLabel lblTieuDe = new JLabel("Tiêu đề");
        lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblTieuDe.setBounds(10, 46, 75, 24);
        pnlForm.add(lblTieuDe);
        
        txtTenSach = new JTextField();
        txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtTenSach.setColumns(10);
        txtTenSach.setBounds(95, 46, 293, 24);
        pnlForm.add(txtTenSach);
        
        JLabel lblTheLoai = new JLabel("Thể loại");
        lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblTheLoai.setBounds(10, 81, 75, 24);
        pnlForm.add(lblTheLoai);
        
        cboTheLoai = new JComboBox();
        cboTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cboTheLoai.setBounds(95, 81, 247, 24);
        pnlForm.add(cboTheLoai);
        
        JButton btnEditTheLoai = new JButton("...");
        btnEditTheLoai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCategoryJDialog();
            }
        });
        btnEditTheLoai.setBounds(352, 81, 36, 24);
        pnlForm.add(btnEditTheLoai);
        
        JLabel lblSTrang = new JLabel("Số trang");
        lblSTrang.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSTrang.setBounds(10, 116, 75, 24);
        pnlForm.add(lblSTrang);
        
        txtSoTrang = new JTextField();
        txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtSoTrang.setColumns(10);
        txtSoTrang.setBounds(95, 116, 75, 24);
        pnlForm.add(txtSoTrang);
        
        JLabel lblSTrang_1 = new JLabel("Giá bán");
        lblSTrang_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSTrang_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSTrang_1.setBounds(180, 116, 46, 24);
        pnlForm.add(lblSTrang_1);
        
        txtGia = new JTextField();
        txtGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtGia.setColumns(10);
        txtGia.setBounds(236, 116, 124, 24);
        pnlForm.add(txtGia);
        
        JLabel lblNhXutBn = new JLabel("Nhà xuất bản");
        lblNhXutBn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNhXutBn.setBounds(10, 186, 75, 24);
        pnlForm.add(lblNhXutBn);
        
        JLabel lblNmXutBn = new JLabel("Năm xuất bản");
        lblNmXutBn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNmXutBn.setBounds(10, 221, 81, 24);
        pnlForm.add(lblNmXutBn);
        
        cboNamXuatBan = new JComboBox();
        cboNamXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int yearToday = localDate.getYear();
        //System.out.println(yearToday);
        for (int i = 1975; i <= yearToday; i++) {
            cboNamXuatBan.addItem(i);
        }
        cboNamXuatBan.setBounds(95, 221, 81, 24);
        pnlForm.add(cboNamXuatBan);
        
        JLabel lblGhiCh = new JLabel("Ghi chú");
        lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblGhiCh.setBounds(10, 351, 75, 24);
        pnlForm.add(lblGhiCh);
        
        JLabel lblVn = new JLabel(SettingSave.getSetting().getMoneySymbol());
        lblVn.setHorizontalAlignment(SwingConstants.CENTER);
        lblVn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblVn.setBounds(360, 116, 28, 24);
        pnlForm.add(lblVn);
        
        JLabel lblTcGi = new JLabel("Tác giả");
        lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblTcGi.setBounds(10, 151, 75, 24);
        pnlForm.add(lblTcGi);
        
        JLabel lblVTr = new JLabel("Vị trí đặt");
        lblVTr.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblVTr.setBounds(194, 221, 52, 24);
        pnlForm.add(lblVTr);
        
        cboViTri = new JComboBox();
        cboViTri.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cboViTri.setModel(new DefaultComboBoxModel(new String[]{"Kệ A1", "Kệ A2", "Kệ A3"}));
        cboViTri.setBounds(250, 221, 92, 24);
        pnlForm.add(cboViTri);
        
        JButton btnEditLocation = new JButton("Tùy chỉnh");
        btnEditLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLocationJDialog();
            }
        });
        btnEditLocation.setBounds(352, 221, 36, 24);
        pnlForm.add(btnEditLocation);
        
        cboAuthor = new JComboBox();
        cboAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cboAuthor.setBounds(95, 151, 247, 24);
        pnlForm.add(cboAuthor);
        
        JButton btnEditAuthor = new JButton("...");
        btnEditAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showAuthorJDialog();
            }
        });
        btnEditAuthor.setBounds(352, 151, 36, 24);
        pnlForm.add(btnEditAuthor);
        
        cboPublisher = new JComboBox();
        cboPublisher.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cboPublisher.setBounds(95, 186, 247, 24);
        pnlForm.add(cboPublisher);
        
        JButton btnEditPublisher = new JButton("...");
        btnEditPublisher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPublisherJDialog();
            }
        });
        btnEditPublisher.setBounds(352, 186, 36, 24);
        pnlForm.add(btnEditPublisher);
        
        JLabel lblMT = new JLabel("Mô tả");
        lblMT.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMT.setBounds(10, 256, 75, 24);
        pnlForm.add(lblMT);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(95, 256, 293, 84);
        pnlForm.add(scrollPane);
        
        txtIntroduct = new JTextArea();
        scrollPane.setViewportView(txtIntroduct);
        txtIntroduct.setWrapStyleWord(true);
        txtIntroduct.setLineWrap(true);
        txtIntroduct.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtIntroduct.setBorder(new LineBorder(SystemColor.inactiveCaption));
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(95, 351, 293, 63);
        pnlForm.add(scrollPane_1);
        
        txtGhiChu = new JTextArea();
        scrollPane_1.setViewportView(txtGhiChu);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setBorder(new LineBorder(SystemColor.inactiveCaption));
        txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        JPanel pnlControllImage = new JPanel();
        pnlControllImage.setBounds(418, 244, 214, 61);
        contentPane.add(pnlControllImage);
        pnlControllImage.setBackground(SystemColor.menu);
        pnlControllImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        pnlControllImage.setLayout(null);
        
        JButton btnSelectPicture = new JButton("Chọn ảnh");
        btnSelectPicture.addActionListener(e -> chooseImage());
        SwingHelper.setTextBelowIconButton(btnSelectPicture);
        btnSelectPicture.setBounds(10, 11, 85, 39);
        pnlControllImage.add(btnSelectPicture);
        
        JButton btnRemovePicture = new JButton("Xóa ảnh");
        btnRemovePicture.addActionListener(e -> removeImage());
        btnRemovePicture.setBounds(115, 11, 89, 39);
        pnlControllImage.add(btnRemovePicture);
        
        JPanel pnlImage = new JPanel();
        pnlImage.setBackground(SystemColor.menu);
        pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        pnlImage.setBounds(418, 11, 214, 235);
        contentPane.add(pnlImage);
        pnlImage.setLayout(null);
        
        lblImage = new JLabel("Chưa có ảnh");
        lblImage.setBounds(2, 2, 210, 231);
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        pnlImage.add(lblImage);
        
        JButton btnConfirm = new JButton("Xác nhận");
        btnConfirm.addActionListener(e -> {
            time = System.nanoTime();
            String oldImage = bookEdit == null ? null : bookEdit.getImage();
            try {
                if (isEditMode) {
                    if (validateAll() && updateBook()) {
                        //Tiến hành ghi file ảnh vào folder image sau khi insert Book thành công
                        if (fileImage != null) {
                            try {
                                if (oldImage != null) {
                                    fireBase.deteleImg(Constants.REMOTE_BOOK_IMG_FOLDER, oldImage);
                                }
                                fireBase.uploadImg(Constants.REMOTE_BOOK_IMG_FOLDER, fileImage, bookEdit.getId() + time);
                            }
                            catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        
                        bookJFrame.getDataToList();
                        bookJFrame.fillToTable();
                        MessageOptionPane.showAlertDialog(getContentPane(), "Cập nhật thông tin sách thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
                    }
                } else {
                    if (validateAll() && insertBook()) {
                        //Tiến hành ghi file ảnh vào folder image sau khi insert Book thành công
                        if (fileImage != null) {
                            try {
                                fireBase.uploadImg(Constants.REMOTE_BOOK_IMG_FOLDER, fileImage, bookEdit.getId()  + time);
                            }
                            catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        
                        //Sau đó đổ lại dữ liệu vào JFrame Book và fill ngược vào table
                        bookJFrame.getDataToList();
                        bookJFrame.fillToTable();
                        MessageOptionPane.showAlertDialog(getContentPane(), "Thêm sách mới thành công!", MessageOptionPane.ICON_NAME_SUCCESS);
                    }
                }
            }
            catch (SQLException e1) {
                MessageOptionPane.showAlertDialog(getContentPane(), "Tiến trình xử lý thất bại [ERROR CODE: " + e1.getErrorCode() + "]", MessageOptionPane.ICON_NAME_WARNING);
                e1.printStackTrace();
            }
        });
        btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnConfirm.setIcon(new ImageIcon(BookEditorJDialog.class.getResource("/icon/icons8_checked_50px.png")));
        btnConfirm.setBounds(418, 316, 214, 120);
        contentPane.add(btnConfirm);
        
        setLocationRelativeTo(getOwner());
        getDataToList();
        fillToCbo();
    }
    
    //Lấy dữ liệu từ DB về listCategory
    public void getDataToList() {
        try {
            listCategory = CategoryDAO.getAll();
            listLocation = LocationDAO.getAll();
            listAuthor = AuthorDAO.getAll();
            listPublisher = PublisherDAO.getAll();
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Đổ dữ liệu từ cboCategory vào cboTheLoai
    public void fillToCbo() {
        int indexCategory = cboTheLoai.getSelectedIndex();
        int indexLocation = cboViTri.getSelectedIndex();
        int indexAuthor = cboAuthor.getSelectedIndex();
        int indexPublisher = cboPublisher.getSelectedIndex();
        
        cboTheLoai.removeAll();
        cboTheLoai.removeAllItems();
        
        cboViTri.removeAll();
        cboViTri.removeAllItems();
        
        cboAuthor.removeAll();
        cboAuthor.removeAllItems();
        
        cboPublisher.removeAll();
        cboPublisher.removeAllItems();
        
        for (Category e : listCategory) {
            cboTheLoai.addItem(e.getCategoryTitle());
        }
        
        for (Location e : listLocation) {
            cboViTri.addItem(e.getLocationName());
        }
        
        for (Author e : listAuthor) {
            cboAuthor.addItem(e.getFullName());
        }
        
        for (Publisher e : listPublisher) {
            cboPublisher.addItem(e.getName());
        }
        
        cboAuthor.setSelectedIndex((indexAuthor != -1) ? indexAuthor : 0);
        cboPublisher.setSelectedIndex((indexPublisher != -1) ? indexPublisher : 0);
        cboTheLoai.setSelectedIndex((indexCategory != -1) ? indexCategory : 0);
        cboViTri.setSelectedIndex((indexLocation != -1) ? indexLocation : 0);
        
    }
    
    //Trả về một model Book từ các dữ liệu trên form
    public Book getBookFromForm() {
        String categoryId = listCategory.get(cboTheLoai.getSelectedIndex()).getId();
        String locationId = listLocation.get(cboViTri.getSelectedIndex()).getId();
        int publicationYear = DataHelper.getInt(cboNamXuatBan.getItemAt(cboNamXuatBan.getSelectedIndex()).toString());
        int authorId = listAuthor.get(cboAuthor.getSelectedIndex()).getId();
        int publisherId = listPublisher.get(cboPublisher.getSelectedIndex()).getId();
        
        Date createdDate = new Date();
    
        if (isEditMode) {
            createdDate = bookEdit.getCreatedDate();
        } else {
            this.bookEdit = new Book();
        }
        
        this.bookEdit.setId(txtMaSach.getText());
        this.bookEdit.setTitle(txtTenSach.getText());
        this.bookEdit.setCategoryId(categoryId);
        this.bookEdit.setPageNum(DataHelper.getInt(txtSoTrang.getText()));
        this.bookEdit.setPrice(DataHelper.getDouble(txtGia.getText()));
        this.bookEdit.setAuthorId(authorId);
        this.bookEdit.setPublisherId(publisherId);
        this.bookEdit.setPublicationYear(publicationYear);
        this.bookEdit.setLocationId(locationId);
        this.bookEdit.setDescription(txtGhiChu.getText());
        this.bookEdit.setCreatedDate(createdDate);
        if (fileImage == null) {
            this.bookEdit.setImage("");
        }
        else {
            this.bookEdit.setImage(txtMaSach.getText() + time);
        }
        this.bookEdit.setIntroduce(txtIntroduct.getText());
        
        return this.bookEdit;
    }
    
    //Cập nhật lại book editor thành book, chuyển chế độ editMode = true
    public void setBookEditor(Book book) throws SQLException {
        setTitle("Cập nhật sách");
        isEditMode = true;
        this.bookEdit = book;
        
        txtMaSach.setEnabled(false);
        
        if (book.getImage() != null) {
            try {
                setImage(book.getImage());
            }
            catch (NullPointerException e) {
                removeImage();
            }
        }
    }
    
    //Kiểm tra bắt lỗi dữ liệu trên form, trả về TRUE nếu hợp lệ
    public boolean validateAll() throws SQLException {
        boolean isSuccess = true;
        String msg = "";
        
        //CHECK - Mã Sách
        if (txtMaSach.getText().isEmpty()) {
            isSuccess = false;
            msg += "+ Mã sách đang để trống\n";
        } else if (isEditMode == false && BookDAO.findByID(txtMaSach.getText()) != null) {
            isSuccess = false;
            msg += "+ Mã sách '" + txtMaSach.getText() + "' này đã tồn tại\n";
        }
        
        //CHECK - Tiêu Đề
        if (txtTenSach.getText().isEmpty()) {
            isSuccess = false;
            msg += "+ Tiêu đề sách đang để trống\n";
        }
        
        //CHECK - Số Trang
        if (txtSoTrang.getText().isEmpty()) {
            isSuccess = false;
            msg += "+ Số trang đang để trống\n";
        } else if (DataHelper.isInteger(txtSoTrang.getText()) == false) {
            isSuccess = false;
            msg += "+ Số trang nhập vào phải là số nguyên\n";
        } else if (DataHelper.getInt(txtSoTrang.getText()) <= 0) {
            isSuccess = false;
            msg += "+ Số trang nhập vào phải lớn hơn 0\n";
        }
        
        //CHECK - Giá bán
        if (txtGia.getText().isEmpty()) {
            isSuccess = false;
            msg += "+ Giá bán đang để trống\n";
        } else if (DataHelper.isDouble(txtGia.getText()) == false) {
            isSuccess = false;
            msg += "+ Giá bán nhập vào phải là số\n";
        } else if (DataHelper.getDouble(txtGia.getText()) <= 0) {
            isSuccess = false;
            msg += "+ Giá bán nhập vào phải lớn hơn 0\n";
        } else if (isEditMode && DataHelper.getDouble(txtGia.getText()) <= StorageDetailDao.getClosestPriceStorageWithBook(bookEdit.getId())) {
            String priceStorageStr = DataHelper.getFormatForMoney(StorageDetailDao.getClosestPriceStorageWithBook(bookEdit.getId())) + SettingSave.getSetting().getMoneySymbol();
            isSuccess = false;
            msg += "+ Giá bán ra phải lớn hơn giá tiền nhập sách vào (" + priceStorageStr + ")\n";
        }
        
        if (isSuccess == false) {
            MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra:\n" + msg, MessageOptionPane.ICON_NAME_WARNING);
        }
        
        return isSuccess;
    }
    
    //Set BookJFrame để sau khi update xong thì có thể gọi hàm fillToTable bên bookJframe bênt kia
    public void setBookJFrame(BookJFrame bookJFrame) {
        this.bookJFrame = bookJFrame;
    }
    
    //Thực hiện thêm model book vào database
    public boolean insertBook() throws SQLException {
        Book book = getBookFromForm();
        return BookDAO.insert(book);
    }
    
    //Thựch hiện update lại model book vào database
    public boolean updateBook() throws SQLException {
        Book book = getBookFromForm();
        return BookDAO.update(book, book.getId());
    }
    
    //Hiển thị các thông tin của model Book lên form
    public void showDataToForm(Book book) throws SQLException {
        String categoryTitle = CategoryDAO.getTitleById(book.getCategoryId());
        String locationName = LocationDAO.findByID(book.getLocationId()).getLocationName();
        String authorFullName = AuthorDAO.findById(book.getAuthorId()).getFullName();
        String publisherName = PublisherDAO.findById(book.getPublisherId()).getName();
        
        txtMaSach.setText(book.getId());
        txtTenSach.setText(book.getTitle());
        cboAuthor.setSelectedItem(authorFullName);
        cboPublisher.setSelectedItem(publisherName);
        //txtSoLuong.setText(book.getAmount() + "");
        txtSoTrang.setText(book.getPageNum() + "");
        txtGia.setText(book.getPrice() + "");
        txtGhiChu.setText(book.getDescription());
        cboNamXuatBan.setSelectedItem(book.getPublicationYear());
        cboTheLoai.setSelectedItem(categoryTitle);
        cboViTri.setSelectedItem(locationName);
        txtIntroduct.setText(book.getIntroduce());
    
        //Xử lý ảnh
        if (book.getImage() != null) {
            setImage(book.getImage());
        }
        else {
            removeImage();
        }
    }
    
    public void chooseImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        int status = chooser.showOpenDialog(this);
        
        //Đã chọn 1 file nào đó
        if (status == JFileChooser.APPROVE_OPTION) {
            File fileImage = chooser.getSelectedFile();
            //Kiểm tra xem nó có phải là file ảnh không
            if (DataHelper.checkFileExtension(fileImage.getName(), DataHelper.EXTENSIONS_FILE_IMAGE)) {
                this.fileImage = fileImage;
                setImage(fileImage);
                lblImage.setText("");
            } else {
                //Xử lý không đúng file ảnh!
            }
        }
    }
    
    //Cập nhật lại icon cho lblImage = file vừa truyền vào
    public void setImage(File file) {
        if (file.exists()) {
            lblImage.setText("");
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            lblImage.setIcon(icon);
            SwingHelper.setAutoResizeIcon(lblImage);
        }
    }
    
    //Cập nhật lại icon cho lblImage lấy ảnh từ folder image có nameFile.
    public void setImage(String imageName) {
        if (imageName != null && imageName.length() > 0) {
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(fireBase.downloadImg(Constants.REMOTE_BOOK_IMG_FOLDER, imageName));
            }
            catch (IOException e) {
                MessageOptionPane.showMessageDialog(contentPane, "Đã có lỗi sảy ra khi tải hình", MessageOptionPane.ICON_NAME_WARNING);
                removeImage();
            }

            lblImage.setIcon(icon);
            lblImage.setText("");
            SwingHelper.setAutoResizeIcon(lblImage);
        } else {
            removeImage();
        }
        
    }
    
    //Xóa icon lblImage và set fileImage = null
    public void removeImage() {
        lblImage.setText("Chưa có ảnh");
        lblImage.setIcon(null);
        this.fileImage = null;
    }
    
    public void showCategoryJDialog() {
        categoryJDialog.setLocationRelativeTo(contentPane);
        categoryJDialog.setVisible(true);
        getDataToList();
        fillToCbo();
    }
    
    public void showAuthorJDialog() {
        authorJDialog.setLocationRelativeTo(contentPane);
        authorJDialog.setVisible(true);
        getDataToList();
        fillToCbo();
    }
    
    public void showPublisherJDialog() {
        publisherJDialog.setLocationRelativeTo(contentPane);
        publisherJDialog.setVisible(true);
        getDataToList();
        fillToCbo();
    }
    
    public void showLocationJDialog() {
        locationJDialog.setLocationRelativeTo(contentPane);
        locationJDialog.setVisible(true);
        getDataToList();
        fillToCbo();
    }
}
