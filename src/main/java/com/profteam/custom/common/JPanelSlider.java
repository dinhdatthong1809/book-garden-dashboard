package com.profteam.custom.common;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelSlider extends JPanel {
  public static final boolean left = false;
  
  public static final boolean right = true;
  
  public JPanelSlider() {
    setLayout(new CardLayout());
    setBorder(BorderFactory.createEtchedBorder());
    Dimension dimension = new Dimension(getWidth(), getHeight());
  }
  
  public void nextPanel(Component panel) {
    Component currentComp = getCurrentComponent(this);
    Rectangle b = currentComp.getBounds();
    panel.setVisible(true);
    JPanelSlidingListener sl = new JPanelSlidingListener(10, currentComp, panel, true);
    Timer t = new Timer(40, sl);
    sl.timer = t;
    t.start();
  }
  
  public void nextPanel(int panelSpeed, Component panel, boolean direction) {
    Component comp = getCurrentComponent(this);
    comp.getBounds();
    panel.setVisible(true);
    JPanelSlidingListener jsl = new JPanelSlidingListener(panelSpeed, comp, panel, direction);
    Timer t = new Timer(40, jsl);
    jsl.timer = t;
    t.start();
  }
  
  public void nextPanel(int panelSpeed, int timeSpeed, Component panel, boolean direction) {
    Component comp = getCurrentComponent(this);
    comp.getBounds();
    panel.setVisible(true);
    JPanelSlidingListener jsl = new JPanelSlidingListener(panelSpeed, comp, panel, direction);
    Timer t = new Timer(timeSpeed, jsl);
    jsl.timer = t;
    t.start();
  }
  
  public Component getCurrentComponent(Container parent) {
    Component comp = null;
    int count = parent.getComponentCount();
    for (int i = 0; i < count; i++) {
      comp = parent.getComponent(i);
      if (comp.isVisible())
        return comp; 
    } 
    return comp;
  }
  
  public String getCurrentComponentShow(Container parent) {
    String panelName = null;
    Component comp = null;
    int count = parent.getComponentCount();
    for (int i = 0; i < count; i++) {
      comp = parent.getComponent(i);
      if (comp.isVisible()) {
        panelName = comp.getName();
        return panelName;
      } 
    } 
    return panelName;
  }
  
  public class JPanelSlidingListener implements ActionListener {
    Component hidePanel;
    
    Component showPanel;
    
    int steps;
    
    int step = 0;
    
    Timer timer;
    
    boolean isNext;
    
    public JPanelSlidingListener(int steps, Component hidePanel, Component panel, boolean isNext) {
      this.steps = steps;
      this.hidePanel = hidePanel;
      this.showPanel = panel;
      this.isNext = isNext;
    }
    
    public void actionPerformed(ActionEvent e) {
      Rectangle bounds = this.hidePanel.getBounds();
      int shift = bounds.width / this.steps;
      if (!this.isNext) {
        this.hidePanel.setLocation(bounds.x - shift, bounds.y);
        this.showPanel.setLocation(bounds.x - shift + bounds.width, bounds.y);
      } else {
        this.hidePanel.setLocation(bounds.x + shift, bounds.y);
        this.showPanel.setLocation(bounds.x + shift - bounds.width, bounds.y);
      } 
      JPanelSlider.this.repaint();
      this.step++;
      if (this.step == this.steps) {
        this.timer.stop();
        this.hidePanel.setVisible(false);
      } 
    }
  }
  
  public void refresh() {
    revalidate();
    repaint();
  }
}
