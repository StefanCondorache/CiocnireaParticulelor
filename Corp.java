
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Corp {
    Vector viteza;
    Vector viteza_new;
    Vector pozitie ;
    double masa;
    double raza;

    public Corp() { pozitie = new Vector(50);
        viteza = new Vector(1);
        masa = 10;
        raza = 10; }

    public Corp(Vector pozitie, Vector viteza, double masa, double raza) {
        this.pozitie = pozitie;
        this.viteza = new Vector(viteza);
        this.masa = masa;
        this.raza = raza;
    }

    public void DesenareCorp(Graphics2D g, Double x_poz, Double y_poz) {

        Ellipse2D.Double corp = new Ellipse2D.Double(x_poz,y_poz, raza, raza);

        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(2));
        g.draw(corp);
        g.setColor(Color.YELLOW);
        g.fill(corp);

    }

    public void Ciocnire(Corp x){

        double c11 = 2*x.masa / (this.masa+x.masa);
        double c12 = 2*this.masa / (this.masa+x.masa);

        double c21 = (this.viteza.i-x.viteza.i)*(this.pozitie.i-x.pozitie.i) + (this.viteza.j-x.viteza.j)*(this.pozitie.j-x.pozitie.j);
        double c22 = (x.viteza.i-this.viteza.i)*(x.pozitie.i-this.pozitie.i) + (x.viteza.j-this.viteza.j)*(x.pozitie.j-this.pozitie.j);

        double c31 = Math.pow(this.pozitie.i-x.pozitie.i,2) + Math.pow(this.pozitie.j-x.pozitie.j,2);
        double c32 = Math.pow(x.pozitie.i-this.pozitie.i,2) + Math.pow(x.pozitie.j-this.pozitie.j,2);

        double k1 = c11*c21/c31;
        double k2 = c12*c22/c32;

        this.viteza_new = new Vector(this.viteza.i-(this.pozitie.i-x.pozitie.i)*k1, this.viteza.j-(this.pozitie.j-x.pozitie.j)*k1);
        x.viteza_new = new Vector(x.viteza.i-(x.pozitie.i-this.pozitie.i)*k2, x.viteza.j-(x.pozitie.j-this.pozitie.j)*k2);

        this.viteza = this.viteza_new;
        x.viteza = x.viteza_new;

        /*
        System.out.println("corp1: viteza =  " + this.viteza.i + " " + this.viteza.j + " \n" +
            "viteza_new = "+ this.viteza_new.i + "  " + this.viteza_new.j);

        System.out.println("corp2: viteza =  " + x.viteza.i + " " + x.viteza.j + " \n" +
                "viteza_new = "+ x.viteza_new.i + "  " + x.viteza_new.j);

        System.out.println("c21 = (" + this.viteza.i + "-"+ x.viteza.i + ")*(" + this.pozitie.i + "-" + x.pozitie.i +")+("+ this.viteza.j+"-"+x.viteza.j+")*("+this.pozitie.j+ "-" +x.pozitie.j+")");
        System.out.println("x31 = " + Math.pow(this.pozitie.i-x.pozitie.i,2) +"+"+ Math.pow(this.pozitie.j-x.pozitie.j,2));
        System.out.println("c21/c31 = " + c21 + "/" + c31 + "=" +c21/c31);
        */
    }


}
