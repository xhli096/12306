package com.tu.xinghao.handler;

import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: lixinghao
 * @date: 2019-10-17 15:06
 * @Description:
 */
@Component
public class FrameEvent<T> implements MouseListener {
    /**
     * 点击右键，提交验证码
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {

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
