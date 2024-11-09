import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    Panel panel;
    JPanel panel2;

    public Frame(){
        panel = new Panel(1500,775, 20, 20);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.CYAN);
        this.setTitle("Ciocnirea particulelor");
        this.setSize(1920,1080);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);



    }
}
