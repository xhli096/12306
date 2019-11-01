package com.tu.xinghao.handler;

import com.tu.xinghao.constants.ImageConstant;
import com.tu.xinghao.ui.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: lixinghao
 * @date: 2019-10-16 21:00
 * @Description:
 */
@Component
@Slf4j
public class VerificationCodeEvent<T> implements MouseListener {
    @Autowired
    private Login<T> login;

    /**
     * BUTTON1 : 鼠标左键
     * BUTTON2 : 鼠标滚轮
     * BUTTON3 : 鼠标右键
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        final JComponent component = login.getFrame().getLayeredPane();
        if (e.getButton() == MouseEvent.BUTTON1) {
            ImageIcon imageIcon = new ImageIcon(Login.class.getResource(ImageConstant.LOGO));
            // 如果再次点击添加的label，则从页面中移除
            final JLabel label = new JLabel(imageIcon);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    component.remove(label);
                    component.repaint();
                }
            });
            // 将按钮label放置在内容面板下面
            component.add(label, new Integer(-3));
            label.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            label.setLocation(e.getX() + 60 - (imageIcon.getIconWidth() / 2), e.getY() + 139 - (imageIcon.getIconHeight() / 2));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
