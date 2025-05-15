import java.time.LocalDate;
public class Main{
    public static void main(String[] args) {
        CountryWithoutProvinces polska = new CountryWithoutProvinces("PL");
        polska.addDailyStatistic(LocalDate.of(2025, 1, 1), 5, 2);
        polska.addDailyStatistic(LocalDate.of(2025, 1, 1), 5, 2);
    }
}