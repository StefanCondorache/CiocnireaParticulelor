public class Vector {
    double i = 0;
    double j = 0;

    public Vector() {}
    public Vector(double k){ this.i = k; this.j = k;}
    public Vector(double i, double j) { this.i = i; this.j = j; }
    public Vector(Vector v){ this.i = v.i; this.j = v.j;}

    public Vector plus(Vector x){
        this.i += x.i;
        this.j += x.j;

        return this;
    }


    public double times(Vector x){ return this.i*x.i + this.j*x.j; } // produs scalar

    public Vector ProdusVectorial(Vector x){
        double x1 = this.i,      x2 = x.i;
        double y1 = this.j,      y2 = x.j;

        this.i = y1-y2;
        this.j = x2-x1;

        return this;
    }


}
