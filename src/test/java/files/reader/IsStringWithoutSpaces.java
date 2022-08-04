package files.reader;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsStringWithoutSpaces extends TypeSafeMatcher<String> {

    @Override
    protected boolean matchesSafely(String s) {
        return !StringUtils.containsWhitespace(s);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("String without spaces");
    }

    public static Matcher<String> isAStringWithoutSpaces() {
        return new IsStringWithoutSpaces();
    }
}
