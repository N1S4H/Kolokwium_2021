import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

abstract public class Country {
    private final String name;
    private static String pathCases;
    private static String pathDeaths;

    public String getName() {
        return name;
    }

    public Country(String name) {
        this.name = name;
    }

    public static void setFiles(String path1, String path2) throws FileNotFoundException {
        File f1 = new File(path1);
        File f2 = new File(path2);
        if(!f1.exists() || !f1.canRead()) {
            throw new FileNotFoundException(path1);
        }

        if(f2.exists() || !f2.canRead()) {
            throw new FileNotFoundException(path2);
        }

        pathCases=path1;
        pathDeaths=path2;

    }

    private static Country fromCsv(String countryName) throws CountryNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(pathCases))) {
            String line = br.readLine();

            CountryColumns info = getCountryColumns(line, countryName);

            line = br.readLine();
            String[] provinces = getProvinces(line, info);
            CountryWithoutProvinces[] pr = new CountryWithoutProvinces[info.columnCount];
            for(int i=0; i<provinces.length; i++){
                CountryWithoutProvinces c = new CountryWithoutProvinces(provinces[i]);
                pr[i] = c;
            }


            if(info.columnCount == 0){

                while ((line = br.readLine()) != null) {
                    return new CountryWithoutProvinces(countryName);
                }
            }
            else{
                while ((line = br.readLine()) != null) {
                    return  new CountryWithProvinces(line.trim(), pr);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CountryNotFoundException e) {
//            throw new RuntimeException(e);
            throw e;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(pathDeaths))) {
            String line;
            while ((line = br.readLine()) != null) {
//                return new Country(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String[] getProvinces(String line, CountryColumns info) {
        String[] provincesName = line.split(";");
        String[] name = new String[info.columnCount];
        for (int i= info.firstColumndex; i<info.firstColumndex+info.columnCount; i++){
            name[i] = provincesName[i];
        }
        return name;
    }


    private static CountryColumns  getCountryColumns(String line, String foundName) throws CountryNotFoundException {
        String[] columns = line.split(";");
        int count=0;
        int startIndex = -1;
        for(int i=0; i< columns.length; i++){
            if(Objects.equals(columns[i], foundName)){
                if (startIndex == -1) {
                    startIndex = i;
                }
                count ++;
            }
            else{
                break;
            }
            if (startIndex == -1) {
                throw new CountryNotFoundException(foundName);
            }
        }
        return new CountryColumns(startIndex,count);
    }



    private static class CountryColumns{
        public final int firstColumndex;
        public final int columnCount;

        public CountryColumns(int firstColumndex, int columnCount) {
            this.firstColumndex = firstColumndex;
            this.columnCount = columnCount;
        }
    }

}
