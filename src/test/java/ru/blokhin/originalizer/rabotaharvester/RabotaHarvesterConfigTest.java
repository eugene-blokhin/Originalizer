package ru.blokhin.originalizer.rabotaharvester;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 07.07.11
 * Time: 3:20
 * To change this template use File | Settings | File Templates.
 */

import junit.framework.*;

public class RabotaHarvesterConfigTest extends TestCase {

    public RabotaHarvesterConfigTest(String name) {
        super(name);
    }

    public void testGetUri(){
        RabotaHarvesterConfig config = new RabotaHarvesterConfig("C developer", Location.ST_PETERSBURG, 1000, 30);
        assertEquals(config.getUrl(), "http://www.rabota.ru/v3_searchVacancyByParamsResults.html?sm=102&d=desc&w=C+developer&sf=1000&c=2&p=30");
    }
}
