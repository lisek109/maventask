package files.reader;

public class Validator implements FileValidator<String> {

    @Override
    public  boolean validate(String input) {
        return !input.isEmpty() && (input.matches(".+\\.txt") || input.matches(".+\\.csv") || input.matches(".+\\.json"));
    }
}
