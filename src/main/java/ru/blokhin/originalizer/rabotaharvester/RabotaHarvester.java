package ru.blokhin.originalizer.rabotaharvester;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 05.07.11
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */


import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.log4j.Level;
import org.webharvest.utils.Constants;
import ru.blokhin.originalizer.rabotaharvester.entities.*;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import com.thoughtworks.xstream.*;
import java.net.URL;
import org.apache.log4j.Logger;

public class RabotaHarvester {
    static Logger logger = Logger.getLogger(RabotaHarvester.class);

    private ScraperConfiguration config;
    private Scraper scraper;
    private Jobs jobs = null;



    public RabotaHarvester(RabotaHarvesterConfig config) {
        logger.setLevel(Level.ALL);
        InitHarvester(config.getUrl(), config.getMaxNumberOfLoops());
    }

    private void InitHarvester(String url, int maxNumberOfLoops) {
        logger.info("Setting up harvester...");

        URL configFile = getClass().getClassLoader().getResource("META-INF/harvesting.xml");
        try {
            config = new ScraperConfiguration(configFile);
        } catch (Exception e) {
            throw new RuntimeException("Resource file couldn't be found.");
        }
        scraper = new Scraper(config, "./");

        scraper.addVariableToContext("url", url);
        logger.debug(String.format("Putting in url variable to the harvester context. url=%s", url));

        maxNumberOfLoops = (maxNumberOfLoops != 0) ? maxNumberOfLoops : Constants.DEFAULT_MAX_LOOPS;
        scraper.addVariableToContext("numberOfLists", maxNumberOfLoops);
        logger.debug(String.format("Putting in numberOfLists variable to the harvester context. url=%s", url));

        logger.debug(String.format("Checking...url=%s, numberOfLists=%s",
                scraper.getContext().getVar("url").toString(),
                scraper.getContext().getVar("numberOfLists").toString()));

    }

    public void Execute() throws Exception {
        scraper.execute();
        String result = scraper.getContext().getVar("jobs").toString();

        /*
        The XML example to parse:

        <jobs> --> Jobs.jobs
            <job link="http://www.rabota.ru/vacancy30386554.html?vac_spec=1">
                <title>Программист php</title>
                <company link="http://www.rabota.ru/company42158.html">
                    <title>Бета Пресс</title>
                </company>
                <salary>договорная</salary>
                <city>Москва м.Тульская</city>
                <metro/>
                <publishingDate>Сегодня</publishingDate>
            </job>
        </jobs>
        */

        try{
            XStream xstream = new XStream(new DomDriver());
            xstream.processAnnotations(Jobs.class);
            xstream.processAnnotations(Job.class);
            xstream.processAnnotations(Company.class);
            jobs = (Jobs)xstream.fromXML(result);
        } catch (Exception e) {
            throw new Exception("The harvested data couldn't be parsed.\nCause:\n" + e.getCause());
        }
    }

    public Jobs getJobs() throws Exception {
        if(jobs == null)
            throw new Exception("Harvester wasn't started. Call the Execute() method before.");
        return jobs;
    }


}
