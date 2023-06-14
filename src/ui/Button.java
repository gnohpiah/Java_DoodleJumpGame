package ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Button {
    public void initButton();
    public void drawButton(Graphics g);
    public int clickable(MouseEvent e);
}
