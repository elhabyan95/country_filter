package project2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainClass {

    public static void main(String[] args) {

        PrintClass p1 = new PrintClass();
        CountryDao c1 = new CountryDao();
        List<Country> countryOut = new ArrayList<>();

        try {
            countryOut = c1.readCountryFromCsv("F:\\Countries.csv");
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        CityDao c2 = new CityDao();
        List<City> cityOut = new ArrayList<>();
        try {
            cityOut = c2.readCityFromCsv("F:\\Cities.csv");
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Creating a map that uses the country code as keys and a list of cities as the value for each country.
        
        Map< String, List<City>> listOfCites = new LinkedHashMap<>();

        for (Country country : countryOut) {
            String str = country.getCode();
            List<City> ls = cityOut.stream()
                    .filter(var -> var.getCountryCode().equals(str))
                    .collect(Collectors.toList());
            listOfCites.put(str, ls);

        }
        System.out.println(listOfCites);
        
        // For a given country code (from Console) sort the cities according to the population.

        Scanner in = new Scanner(System.in);
        System.out.println("please enter any string");
        String s1 = in.nextLine();
        cityOut.stream()
                .filter(d -> d.getCountryCode().equals(s1))
                .sorted( Comparator.comparing(City::getPopulation).reversed())
                .collect(Collectors.toList())
                .forEach(val -> System.out.println(val));
        
          // print a List of countries population.
        p1.printCountriesPopulation(countryOut);
        
        //print the average countries population.
        p1.printAvgOfPopulation(countryOut);
        
        //print the maximum countries population.
        p1.printMaxPopulation(countryOut);
        
        //listOfCites.forEach((k,v) -> System.out.println(k + " " + v.stream().mapToInt(City::getPopulation).max().getAsInt()));
        
        
        
        //print the highest population city of each country.
        List<Integer> list = new ArrayList<Integer>();
        for (Map.Entry<String, List<City>> entry : listOfCites.entrySet()) {
            String key = entry.getKey();
            List<City> value = entry.getValue();
            if (value.size() != 0) {
                int l1 = (value.stream()
                        .map(City::getPopulation)
                        .max(Integer::compareTo).get());

                list.add(l1);

            }
        }

        for (Integer integer : list) {
            
             cityOut.stream()
                    .filter(val -> val.getPopulation() == integer )
                    .forEach(val -> System.out.println(val.getName()));
            
        }
        List<Integer> capitals = countryOut.stream().map(Country::getCapital).collect(Collectors.toList());
       
        System.out.println(cityOut.stream().filter(var -> capitals.contains(var.getId())).mapToInt(City::getPopulation).max());
        

        }}
