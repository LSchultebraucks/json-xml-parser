package parser;

import java.io.IOException;

public interface Parser<T> {
    T parseString(String object);
}
