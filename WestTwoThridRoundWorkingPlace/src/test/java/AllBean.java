public class AllBean {
    private long confirmed;
    private long recovered;
    private long deaths;
    private String country;
    private long population;
    private long sq_km_area;
    private double life_expectancy;
    private String elevation_in_meters;
    private String continent;
    private String abbreviation;
    private String location;
    private long iso;
    private String capital_city;

//    public AllBean(long confirmed, long recovered, long deaths, String country, long population, long sq_km_area, double life_expectancy, long elevation_in_meters, String continent, String abbreviation, String location, long iso, String capital_city) {
//
//        this.confirmed = confirmed;
//        this.recovered = recovered;
//        this.deaths = deaths;
//        this.country = country;
//        this.population = population;
//        this.sq_km_area = sq_km_area;
//        this.life_expectancy = life_expectancy;
//        this.elevation_in_meters = elevation_in_meters;
//        this.continent = continent;
//        this.abbreviation = abbreviation;
//        this.location = location;
//        this.iso = iso;
//        this.capital_city = capital_city;
//    }
//getter
    public long getConfirmed() {
        return confirmed;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getDeaths() {
        return deaths;
    }

    public String getCountry() {
        return country;
    }

    public long getPopulation() {
        return population;
    }

    public long getSq_km_area() {
        return sq_km_area;
    }

    public double getLife_expectancy() {
        return life_expectancy;
    }

    public String getElevation_in_meters() {
        return elevation_in_meters;
    }

    public String getContinent() {
        return continent;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getLocation() {
        return location;
    }

    public long getIso() {
        return iso;
    }

    public String getCapital_city() {
        return capital_city;
    }

    //Setter

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setSq_km_area(long sq_km_area) {
        this.sq_km_area = sq_km_area;
    }

    public void setLife_expectancy(double life_expectancy) {
        this.life_expectancy = life_expectancy;
    }

    public void setElevation_in_meters(String elevation_in_meters) {
        this.elevation_in_meters = elevation_in_meters;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIso(long iso) {
        this.iso = iso;
    }

    public void setCapital_city(String capital_city) {
        this.capital_city = capital_city;
    }
}
