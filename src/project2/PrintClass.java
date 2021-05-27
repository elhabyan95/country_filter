/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.List;
import java.util.stream.Collectors;

public class PrintClass {

    public void printCountriesPopulation(List<Country> countryOut) {
        System.out.println(countryOut.stream()
                .map(Country::getPopulation)
                .collect(Collectors.toList()));

    }

    public void printAvgOfPopulation(List<Country> countryOut) {
        System.out.printf("average population is %s%n", countryOut.stream()
                .map(Country::getPopulation)
                .mapToInt(a -> a).average().getAsDouble());

    }

    public void printMaxPopulation(List<Country> countryOut) {
        System.out.printf("max population is %d%n", countryOut.stream()
                .map(Country::getPopulation)
                .max(Integer::compareTo).get());

    }

}
