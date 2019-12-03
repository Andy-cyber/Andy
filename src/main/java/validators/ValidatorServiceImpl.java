package validators;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidatorServiceImpl {
    private static final String NAME_PATTERN = "([A-Z]([a-zA-Z]*(\\s?)))+";
    private static final String PRICE_PATTERN = "\\d{1,6}((\\.0)?(\\.\\d{2})?)";
    private static final String FLIGHTTIME_PATTERN = "\\d{1,3}";

    public ValidatorServiceImpl(){

    }

    public  boolean isValidId(int id) {
        return id >= 0;
    }


    public boolean isValidName(String string) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }


    public boolean isValidPrice(Double string) {
        Pattern pattern = Pattern.compile(PRICE_PATTERN);
        Matcher matcher = pattern.matcher(Double.toString(string));
        return matcher.matches();
    }


    public boolean isValidTimeInFlight(Integer string) {
        Pattern pattern = Pattern.compile(FLIGHTTIME_PATTERN);
        Matcher matcher = pattern.matcher(Integer.toString(string));
        return matcher.matches();
    }
}