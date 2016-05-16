import java.util.Random;

import static java.lang.Math.*;


public class Specimen {

	//ciagi 01 dlugosci 5
    int [] chromosome_x = new int[5];
    int [] chromosome_y = new int[5];

    double fitValue;

    public Specimen() {

        Random generator = new Random();
        
        //losowanie chromosomow
        //pierwszy element znak(0 +; 1 -)

        for (int j = 0;j<5;j++){
            this.chromosome_x[j]= generator.nextInt(2);
            this.chromosome_y[j]= generator.nextInt(2);
        }
    }
   
    //funkcja oceny
    public double fitness(double x, double y){
        double max = 10.0;
        double min = -10.0;
        if(x<max && x > min && y < max && y > min){
            //return (0.26*(pow(x,2.0) + pow(y,2.0)) - 0.48*x*y);    //wartosc funkcji Matyas
            return (100 / (0.26 * (pow(x, 2.0) + pow(y, 2.0)) - 0.48 * x * y));    //100/wartosc funkcji Matyas -> optymalizacja wartosci
        }
        else return -1;
    }

    //funkcja dekodujaca chromosomy do wartosci decymalnych
    public double[] decode(){

        double x = 0;
        for (int i = 1; i < chromosome_x.length; i++) {
            x = x*2 + chromosome_x[i];
        }
        if(chromosome_x[0]!= 0) {
            x = -x;
        }
        double y = 0;
        for (int i = 1; i < chromosome_y.length; i++) {
            y = y*2 + chromosome_y[i];
        }
        if(chromosome_y[0]!= 0) {
            y = -y;
        }
        return new double [] {x,y};
    }



}
