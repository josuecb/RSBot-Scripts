package osrs.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class SettingWindow extends AbstractWindow implements Listeners {
    Button startBtn;
    TextField textInput;
    Panel rootContainer;

    public SettingWindow(String title) {
        super(title);

        this.setVisibility();
    }

    @Override
    public void setComponents() {
        Component[] textWrapper = createInputText("Username");

        rootContainer = (Panel) textWrapper[0];
        textInput = (TextField) textWrapper[1];

        startBtn = createButton("Start");
    }

    @Override
    public void buildComponents() {
        this.appendE(rootContainer);
        this.appendE(startBtn);
    }

    @Override
    public void setListeners() {
        this.addWindowListener(this);
        startBtn.addActionListener(this);
    }

    public boolean start() {
        return this.hide && validUsername();
    }

    public void switchOff() {
        this.hide = false;
    }

    public String getUsername() {
        return textInput.getText();
    }

    private boolean validUsername() {
        return textInput.getText() != null && !textInput.getText().isEmpty();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startBtn)) {
            this.hide = !this.hide;

            if (this.hide) {
                startBtn.setLabel("Stop");
                dispose();
            } else {
                startBtn.setLabel("Start");
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


}
