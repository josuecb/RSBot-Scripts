package osrs.gui;

import java.awt.*;

public abstract class AbstractWindow extends Frame implements LifeCycle {
    boolean hide = false;

    public AbstractWindow(String title) {
        super(title);
        this.setComponents();
        this.buildComponents();
        this.setListeners();
    }

    protected Component[] createInputText(String label) {
        Panel componentHolder = new Panel();
        Label lb = new Label(label);
        TextField textInput = new TextField(15);

        componentHolder.add(lb);
        componentHolder.add(textInput);

        componentHolder.setLayout(new FlowLayout());
        componentHolder.setSize(300, 300);
        componentHolder.setVisible(true);
        return new Component[]{componentHolder, textInput};
    }

    protected Button createButton(String buttonText) {
        return new Button(buttonText);
    }


    protected void appendE(Component c) {
        this.add(c);
    }

    protected void setVisibility() {
        this.setLayout(new FlowLayout());
        this.setSize(300, 300);
        this.setVisible(true);
    }
}
