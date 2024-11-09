import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Panel extends JPanel implements ActionListener{

    public int width, height;
    public double x_poz, y_poz;
    Corp[] corpuri = new Corp[50];

    Timer timer;

    public Panel(int width, int height, double x_poz, double y_poz) {
        this.width = width;
        this.height = height;
        this.x_poz = x_poz;
        this.y_poz = y_poz;

        Random rand = new Random();

        for(int i = 0; i < corpuri.length; i++) {
            double vx = rand.nextDouble() * 5 / (double) rand.nextInt(1, 5);
            double vy = rand.nextDouble() * 5 / (double) rand.nextInt(1, 5);
            vx *= (rand.nextBoolean())? -1 : 1;
            vy *= (rand.nextBoolean())? -1 : 1;

            double raza = rand.nextDouble();
            double masa = raza * 9.109;

            corpuri[i] = new Corp(new Vector(rand.nextDouble(x_poz+20,width-20), rand.nextDouble(y_poz+10, height-10)), new Vector(vx, vy), masa, 10);

        }
        timer = new Timer(5, this);
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D rect = new Rectangle2D.Double(x_poz, y_poz, width, height);
        g2d.setColor(Color.PINK);
        g2d.setStroke(new BasicStroke(10));
        g2d.draw(rect);
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);


        for(Corp corp : corpuri) {
            corp.DesenareCorp(g2d, corp.pozitie.i, corp.pozitie.j);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(Corp corp : corpuri) {

            if( corp.pozitie.i - corp.raza >= width || corp.pozitie.i < x_poz+2){
                corp.viteza.i = -corp.viteza.i;                                     // Ciocnirea plastica cu peretii cutiei
            }
            if(corp.pozitie.j - corp.raza >= height || corp.pozitie.j < y_poz+2){
                corp.viteza.j = -corp.viteza.j;
            }

            for(Corp corp2: corpuri) {
                if( Math.sqrt( Math.pow((corp.pozitie.i - corp2.pozitie.i),2) + Math.pow((corp.pozitie.j - corp2.pozitie.j),2) ) < (corp.raza+corp2.raza)/1.33 && corp != corp2 ){
                    corp.Ciocnire(corp2);

                }
            }

            corp.pozitie.i = corp.pozitie.i + corp.viteza.i;
            corp.pozitie.j = corp.pozitie.j + corp.viteza.j;

        }


        repaint();

    }

}
