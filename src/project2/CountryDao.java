
package project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryDao {

    public CountryDao() {
    }
    
     public List<Country>readCountryFromCsv(String Filename) throws FileNotFoundException, IOException {

        List<Country> countryInfo = new ArrayList<>();
        FileReader fr = new FileReader(Filename);
        BufferedReader reader = new BufferedReader(fr);
        String[] parts;
        Country coun;
        String line;
        
        line = reader.readLine();
        
                 do {
            line=reader.readLine();
            if (line !=null )
            { 
               parts=line.split(",");
               
                   coun= new Country(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]),Double.parseDouble(parts[4]),Double.parseDouble(parts[5]),Integer.parseInt(parts[6]));
                   countryInfo.add(coun);
               
            }
}         while(line != null);
        
        
      
        return countryInfo;
    
    
    
}
}