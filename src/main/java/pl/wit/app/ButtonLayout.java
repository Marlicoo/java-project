package pl.wit.app;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonLayout extends JPanel {
	    public static final int MY_WIDTH = 500;
	    public static final int MY_HEIGHT = 200;
	    private static final float BTN_SIZE = 12f;
	    
	    private String[] buttonTexts = {"Wybierz folder źródłowy", "Wybierz folder docelowy", 
	            "Start"};
	    
	    public ButtonLayout(JFrame frame) {
	        int colGap = 20;
	        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, colGap));
	        for (String btnText : buttonTexts) {
	            JButton button = new JButton(btnText);
	            button.addActionListener((ActionListener) frame);
	            
	            // set first letter of text as mnemonic (alt-char shortcut)
	            int mnemonic = (int) btnText.charAt(0);
	            button.setMnemonic(mnemonic);
	            
	            // make button bigger by increasing its font
	            button.setFont(button.getFont().deriveFont(BTN_SIZE));
	            
	            // add to the GridLayout-using JPanel
	            buttonPanel.add(button);
	        }
	        
	        // set layout of main panel to GridBag
	        setLayout(new GridBagLayout());
	        
	        // add the button panel in a "default" manner (no constraints)
	        // which centers this panel
	        add(buttonPanel);
	    }
	    
	    @Override
	    public Dimension getPreferredSize() {
	        Dimension superSize = super.getPreferredSize();
	        int width = Math.max(MY_WIDTH, superSize.width);
	        int height = Math.max(MY_HEIGHT, superSize.height);
	        
	        return new Dimension(width, height);
	    }
}
