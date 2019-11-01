package com.tu.xinghao.ui;

import com.tu.xinghao.util.ConfigUtils;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * @author: lixinghao
 * @date: 2019-10-16 20:14
 * @Description:
 */
@Component
@Getter
@Accessors(chain = true)
public class Login<T> {
    private JButton refreshButton;
    private JFrame frame;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JLabel verificationCode;
    private JLabel msgLabel;
    private JCheckBox rememberCheckBox;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JButton loginButton;
    private boolean pic = true;

    public Login() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            frame = new JFrame("欢迎使用途抢票工具");
            frame.setBounds(100, 100, 440, 465);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);

            userNameField = new JTextField();
            userNameField.setBounds(123, 23, 230, 33);
            frame.getContentPane().add(userNameField);
            userNameField.setColumns(10);

            JLabel userNameLabel = new JLabel("用户名");
            userNameLabel.setBounds(61, 32, 54, 15);
            frame.getContentPane().add(userNameLabel);

            JLabel passwordLabel = new JLabel("密  码");
            passwordLabel.setBounds(61, 76, 54, 15);
            frame.getContentPane().add(passwordLabel);

            JLabel verificationCodeLabel = new JLabel("验证码");
            verificationCodeLabel.setBounds(61, 114, 54, 15);
            frame.getContentPane().add(verificationCodeLabel);

            verificationCode = new JLabel(new ImageIcon(ConfigUtils.initVerificationCode()));
            verificationCode.setBounds(60, 139, 293, 191);
            frame.getContentPane().add(verificationCode);

            refreshButton = new JButton("刷新");
            refreshButton.setBounds(123, 110, 104, 23);
            frame.getContentPane().add(refreshButton);

            passwordField = new JPasswordField("");
            passwordField.setEchoChar('♫');
            passwordField.setToolTipText("");
            passwordField.setBounds(123, 67, 230, 33);
            frame.getContentPane().add(passwordField);

            msgLabel = new JLabel("提示：选择完验证码后，提交验证码");
            msgLabel.setBounds(61, 340, 337, 29);
            msgLabel.setForeground(Color.blue);
            frame.getContentPane().add(msgLabel);

            loginButton = new JButton("登录");
            loginButton.setBounds(61, 366, 303, 51);
            frame.getContentPane().add(loginButton);

            rememberCheckBox = new JCheckBox("记住");
            rememberCheckBox.setSelected(true);
            rememberCheckBox.setBounds(354, 29, 116, 21);
            frame.getContentPane().add(rememberCheckBox);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
