
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountryWithoutProvinces extends Country{
    private HashMap<LocalDate,ArrayList<Integer>> reports;
    public CountryWithoutProvinces(String name) {
        super(name);
        reports = new HashMap<>();
    }

    public void addDailyStatistic(LocalDate date, int infections, int deaths){
        if(!reports.containsKey(date)) {
            ArrayList<Integer> stats = new ArrayList<>();
            stats.add(infections);
            stats.add(deaths);
            reports.put(date, stats);
        }
        else{
            ArrayList<Integer> stats = new ArrayList<>();
            ArrayList<Integer> currentValues = reports.get(date) ;
            stats.add(currentValues.get(0) + infections);
            stats.add(currentValues.get(1) + deaths);

            reports.put(date,stats);
        }
        System.out.println(reports);
    }

}


