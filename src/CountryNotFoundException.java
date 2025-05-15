public class CountryNotFoundException extends Exception{
    private String countryName;

    public CountryNotFoundException(String countryName) {
        this.countryName = countryName;
    }

    public String getMessage(String countryName){
        return countryName;
    }
}
