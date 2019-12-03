package validators;

public interface ValidatorService {

    boolean isValidId(int id);

    boolean isValidName(String string);

    boolean isValidPrice(Double string);

    boolean isValidTimeInFlight(Integer string);

}
