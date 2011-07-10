package ru.blokhin.originalizer.rabotaharvester;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 07.07.11
 * Time: 3:12
 * To change this template use File | Settings | File Templates.
 */

import junit.framework.*;

public class LocationTest extends TestCase {

    public LocationTest(String name) {
        super(name);
    }

    public void testGetCode(){
        Location location;
        for(Location l : Location.values()){
            assertNotNull(l.getCode());
        }
    }

}
