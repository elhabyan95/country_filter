
package project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityDao {

    public List<City> readCityFromCsv(String Filename) throws FileNotFoundException, IOException {

        List<City> cityInfo = new ArrayList<>();
        FileReader fr = new FileReader(Filename);
        BufferedReader reader = new BufferedReader(fr);
        String[] parts;
        City city;
        String line;

        do {
            line = reader.readLine();
            if (line != null) {
                parts = line.split(",");

                city = new City(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]),parts[3].trim());
                cityInfo.add(city);

            }
        } while (line != null);

        return cityInfo;

    }

}

