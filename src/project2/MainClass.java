package project2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        Map< String, List<City>> listOfCites = new LinkedHashMap<>();

        for (Country country : countryOut) {
            String str = country.getCode();
            List<City> ls = cityOut.stream()
                    .filter(var -> var.getCountryCode().equals(str))
                    .collect(Collectors.toList());
            listOfCites.put(str, ls);

        }
        System.out.println(listOfCites);

//
        Scanner in = new Scanner(System.in);
        System.out.println("please enter any string");
        String s1 = in.nextLine();
        cityOut.stream()
                .filter(d -> d.getCountryCode().equals(s1))
                .map(City::getPopulation)
                .sorted()
                .collect(Collectors.toList())
                .forEach(val -> System.out.println(val));

        p1.printCountriesPopulation(countryOut);
        
        p1.printAvgOfPopulation(countryOut);
        
        p1.printMaxPopulation(countryOut);
        
      
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
                    .forEach(val -> System.out.println(val));
            
        }
    }
}
