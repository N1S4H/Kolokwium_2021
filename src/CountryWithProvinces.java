public class CountryWithProvinces extends Country{
    private Country[] provinces;
    //private HashMap<LocalDate,ArrayList<ArrayList<Integer>>>
    public CountryWithProvinces(String name, Country[] provinces) {
        super(name);
        this.provinces = provinces;
    }


}
