package files.reader;

public interface FileValidator<T> {
    boolean validate(T regex);
}
