package com.profteam.core;

import com.profteam.custom.message.MessageOptionPane;
import com.profteam.helper.SettingSave;
import com.profteam.model.Setting;
import com.profteam.view.dialog.SettingJDialog;
import com.profteam.view.frame.LoginJFrame;

import javax.swing.UIManager;
import java.sql.SQLException;

public class App {
    public static void run() {
        // init fireBase
        FireBase fireBase = FireBase.getFireBase();

        SettingSave.loadSetting();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        LoginJFrame loginJFrame = new LoginJFrame();
        SettingJDialog settingJDialog = new SettingJDialog();
        
        // Kiểm tra xem file save setting có tồn tại hay không, nếu có thì chạy thẳng loginJframe
        // Chưa có thì phải hiện settingJdialog trước.
        
        if (SettingSave.isFileSettingExist()) {
            SettingSave.loadSetting();
            Setting st = SettingSave.getSetting();
            
            try {
                DBConnection.setConnection(st.getHostDB(), st.getNameDB(), st.getUsernameDB(), st.getPasswordDB());
                loginJFrame.setVisible(true);
                
            }
            catch (SQLException e) {
                e.printStackTrace();
                MessageOptionPane.showMessageDialog(null, "Đã có sự cố sảy ra: (ERROR CODE: " + e.getErrorCode() + ")\n" + e.getMessage(), MessageOptionPane.ICON_NAME_ERROR);
            }
        }
        else {
            String msg = "Đây là lần đầu phần mềm được mở, vui lòng thiết lập các giá trị cần thiết trước khi chương trình được chạy!";
            MessageOptionPane.showMessageDialog(null, msg);
            settingJDialog.setVisible(true);
        }
    }
}
