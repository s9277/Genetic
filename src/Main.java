import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.log;


public class Main {

    public static void main(String[] args) {

        Genetic g = new Genetic();
        List<Specimen> pop = g.generatePopulation(40);

        System.out.println("Population entry------------------------------------------------------------------------------");
        g.printPopulation(pop);

        for (int i = 0;i<40;i++) {

            System.out.println(i + "selected------------------------------------------------------------------------------");

            List<Specimen> firstGen =  g.selectPopulation(pop);
            if(firstGen.isEmpty()) break;

            g.printPopulation(firstGen);

            System.out.println(i + "th generation---------------------------------------------------------------------------------");

            List<Specimen> secondGen = g.crossingOver(firstGen);
            g.printPopulation(secondGen);

        }

    }
}
