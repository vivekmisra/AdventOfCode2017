/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adventofcode2017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Piet
 */
public class Exercise02 {
    
    private int[][] inputArray2D;
    
    private Exercise02() {
        initializeInputArray();
    }
    
    public static void main(String... args) {
        Exercise02 opgave02 = new Exercise02();
        int solutionPart1 = opgave02.solvePart1();
        int solutionPart2 = opgave02.solvePart2();
        System.out.println("solution part 1: " + solutionPart1);
        System.out.println("solution part 2: " + solutionPart2);
    }
    
    private void initializeInputArray() {
        try {
            URL url = Exercise02.class.getResource("Repositories/inputExercise02.txt");
            Path path = Paths.get(url.toURI());
            inputArray2D = Files.lines(path).map(this::translateStringToIntArray).toArray(int[][]::new);
        }
        catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    private int solvePart1() {
        return Arrays.stream(inputArray2D).mapToInt(this::getMaxMinusMin).sum();
    }
    
    private int solvePart2() {
        return Arrays.stream(inputArray2D).mapToInt(this::getDivisables).sum();
    }
    
    private int getMaxMinusMin(int[] a) {
        IntSummaryStatistics stats = Arrays.stream(a).summaryStatistics();
        return stats.getMax() - stats.getMin();
    }
     
    private int[] translateStringToIntArray(String s) {
        return Pattern.compile("\\s+").splitAsStream(s)
                .mapToInt(Integer::parseInt).sorted().toArray()
        ;
    }
    
    private int getDivisables(int[] a) {
        for (int index = a.length - 1; index > 0; index--) {
            for (int i = index - 1; i >= 0; i--) {
                if (a[index] % a[i] == 0) return a[index] / a[i]; 
            }
        }
        throw new RuntimeException("no divisables found in array " + Arrays.toString(a));
    }
}