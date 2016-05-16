import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by Agata on 2015-06-04.(
 */
public class Genetic {

    Random generator = new Random();
    double mutationProb = generator.nextDouble();


    public List<Specimen> generatePopulation(int n){

        List<Specimen> population = new ArrayList<Specimen>();

        for (int i = 0;i<n;i++){
            Specimen s = new Specimen();
            double [] d = s.decode();                      // decode chromosomes
            s.fitValue = s.fitness(d[0], d[1]);          //count fitness
            population.add(i,s);
        }
        population = clearPopulation(population);
        return population;
    }



    public List<Specimen> selectPopulation(List<Specimen> pop){
        List<Specimen> selected = new ArrayList<Specimen>();
        double fitsum = 0;

        for (int i = 0; i < pop.size(); i++) {
            fitsum += pop.get(i).fitValue;
        }

        double randFit = 0 + generator.nextDouble()*fitsum;

        //System.out.println(fitsum+ " " + randFit);

        double sum = 0;

        for (int j=0;j<pop.size();j++){
            sum += pop.get(j).fitValue;
            if (sum > randFit){
                selected.add(pop.get(j));
            }
        }
        return selected;
    }

    public List<Specimen> selectStrongPopulation(List<Specimen> pop){
        List<Specimen> selected = new ArrayList<Specimen>();

        List<Double> fits = new ArrayList<Double>();

        for (int i = 0; i<pop.size();i++){
            fits.add(pop.get(i).fitValue);
        }
        double min = fits.get(fits.indexOf(Collections.min(fits)));
        double max = fits.get(fits.indexOf(Collections.max(fits)))

        double randFit = min + generator.nextDouble()*max;

        for (int j = 0;j<pop.size();j++){
            if(!(pop.get(j).fitValue<randFit)){
                selected.add(pop.get(j));
            }
        }
        return selected;
    }


    public List<Specimen> crossingOver(List<Specimen> parents){
        List<Specimen> offspring= new ArrayList<Specimen>();
        int cp = generator.nextInt(4) + 1;
        
        for (int i = 0;i<parents.size()-1;i+=2){
           for (int j = cp;j<5;j++){                                                   //hardcode na dlugosc chromosomu
               int temp = parents.get(i).chromosome_x[j];
               parents.get(i).chromosome_x[j] = parents.get(i+1).chromosome_x[j];
               parents.get(i+1).chromosome_x[j] = temp;

           }

            for (int k = cp;k<5;k++){                                                  //jw
                int temp = parents.get(i).chromosome_y[k];
                parents.get(i).chromosome_y[k] = parents.get(i+1).chromosome_y[k];
                parents.get(i+1).chromosome_y[k] = temp;

            }

            for (int p = 0;p<parents.size();p++){
                double [] d = parents.get(p).decode();                      // decode chromosomes
                parents.get(p).fitValue = parents.get(p).fitness(d[0], d[1]);
                }
        }

        offspring = mutate(offspring);

        offspring = clearPopulation(parents);
         return offspring;
    }
    private List<Specimen> mutate(List<Specimen> pop){

        for(Specimen s : pop){
            double mProb = generator.nextDouble();
            int chr =generator.nextInt(2);

            if(mProb>mutationProb){
                int cp = generator.nextInt(4) + 1;
                if(chr == 0){
                    s.chromosome_x[cp]  = s.chromosome_x[cp]-1;
                }
                else {
                    s.chromosome_y[cp]  = s.chromosome_y[cp]-1;
                }
            }

            for (int p = 0;p<pop.size();p++){
                double [] d = pop.get(p).decode();                      // decode chromosomes
                pop.get(p).fitValue = pop.get(p).fitness(d[0], d[1]);
            }
        }

        List<Specimen> offspring= new ArrayList<Specimen>();
        offspring = clearPopulation(pop);
        return offspring;
    }


    public void printPopulation(List<Specimen> pop){

        for(Specimen temp : pop){
           System.out.println("wartosc fitness " + pop.indexOf(temp) + " wynosi:  " + temp.fitValue);
            System.out.println("dla x = " + temp.decode()[0] + ", y = " + temp.decode()[1]   );
        }

    }

    private List<Specimen> clearPopulation(List<Specimen> p){
        // removes specimens with chromosomes out of range (-10,10)

        List<Specimen> selected = new ArrayList<Specimen>();
        for (int i= 0; i<p.size();i++){
            if(!(p.get(i).fitValue<0)){
                selected.add(p.get(i));
            }
        }
        return selected;
    }
}
