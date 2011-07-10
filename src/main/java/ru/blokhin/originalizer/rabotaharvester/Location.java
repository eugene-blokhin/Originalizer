package ru.blokhin.originalizer.rabotaharvester;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 05.07.11
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
public enum Location {
    ST_PETERSBURG,
    MOSCOW;

    int getCode() {
        switch (this) {
            case MOSCOW:
                return 1;
            case ST_PETERSBURG:
                return 2;
        }
        throw new IllegalArgumentException("Code number was not found!");
    }
}