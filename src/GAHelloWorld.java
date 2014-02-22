
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
* The MIT License
* 
* Copyright (c) 2011 John Svazic
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

/**
 * Driver class for the "Hello, world!" genetic algorithm simulation.
 * 
 * This class is strictly used for an entry point into the simulation itself,
 * it has no other functionality.
 * 
 * @author John Svazic
 * @version 1.0
 */
public class GAHelloWorld {

	/**
	 * The main method used for execution of the application.
	 * 
	 * @param args Command-line arguments (ignored).
	 */
	public static void main(String[] args) {
		File f1 = new File("info.txt");
        File f2 = new File("kandidat.txt");
        File f3 = new File("tempat.txt");
        Nanto n = new Nanto();
        n.load(f1);
  
        Scanner scan = null;
        try{
           scan = new Scanner(f2);
        }
        catch(FileNotFoundException e){
           System.exit(0);
        }
        int j = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                Nanto.jCandid[j][i] = Character.getNumericValue(line.charAt(i));
            }
            j += 1;
        }
        scan.close();
        
        scan = null;
        try{
           scan = new Scanner(f3);
        }
        catch(FileNotFoundException e){
           System.exit(0);
        }
        j = 0;
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                Nanto.jPlace[j][i] = Character.getNumericValue(line.charAt(i));
            }
            j += 1;
        }
        scan.close();
		
		// The size of the simulation population
		final int populationSize = 4;
		
		// The maximum number of generations for the simulation.
		final int maxGenerations = 16384;
		
		// The probability of crossover for any member of the population,
		// where 0.0 <= crossoverRatio <= 1.0
		final float crossoverRatio = 0.5f;
		
		// The portion of the population that will be retained without change
		// between evolutions, where 0.0 <= elitismRatio < 1.0
		final float elitismRatio = 0.1f;
		
		// The probability of mutation for any member of the population,
		// where 0.0 <= mutationRatio <= 1.0
		final float mutationRatio = 0.1f;
	
		// Get the current run time.  Not very accurate, but useful for 
		// some simple reporting.
		long startTime = System.currentTimeMillis();
		
		// Create the initial population
		Population pop = new Population(populationSize, crossoverRatio, 
				elitismRatio, mutationRatio);

		// Start evolving the population, stopping when the maximum number of
		// generations is reached, or when we find a solution.
		int i = 0;
		Chromosome best = pop.getPopulation()[0];
		
		while (i++ <= maxGenerations) {
			//System.out.println("Generation " + i + ": " + best.getGene());
			pop.evolve();
			best = pop.getPopulation()[0];
		}
		
		// Get the end time for the simulation.
		long endTime = System.currentTimeMillis();
		
		// Print out some information to the console.
		System.out.println("Generation   " + i + ": " + best.getGene());
		System.out.println("Gene Fitness " + i + ": " + best.getFitness());
		System.out.println("Total execution time: " + (endTime - startTime) + 
				"ms");
	}
}