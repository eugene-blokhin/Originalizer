package ru.blokhin.originalizer;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.*;
import ru.blokhin.originalizer.rabotaharvester.*;
import ru.blokhin.originalizer.rabotaharvester.entities.*;

import java.io.*;
import java.util.*;

/**
 * Hello world!
 */

public class App {
    static Logger logger = Logger.getRootLogger();

    static void showHelpScreen() {
        System.out.println("Usage:\n" +
                "\tjava -jar originalizer.jar <-p=...> [-l=...] [-s=...] [-d=...] [-np=...]\n" +
                "Options:\t\n" +
                "\t-p\tDesired position\n" +
                "\t-l\tLocation (Could be ST_PETERSBURG or MOSCOW)\n" +
                "\t-s\tDesired minimum salary\n" +
                "\t-d\tDays from announcement\n" +
                "\t-np\tNumber of pages to analyze");

    }

    static void initLogger() {
        ConsoleAppender ca = new ConsoleAppender();
        ca.setWriter(new OutputStreamWriter(System.out));
        ca.setLayout(new PatternLayout("%-5p [%t] %m%n"));
        logger.addAppender(ca);
        logger.setLevel(Level.INFO);
    }


    public static void main(String[] args) {
        initLogger();

        RabotaHarvesterConfig config = new RabotaHarvesterConfig();


        for (String arg : args) {
            if (arg.substring(0, 3).equals("-p=")) {
                config.setPosition(arg.substring(3).trim());
            } else if (arg.substring(0, 3).equals("-l=")) {
                try {
                    config.setLocation(Location.valueOf(arg.substring(3).trim()));
                } catch (IllegalArgumentException e) {
                    System.out.printf("Location \"%s\" couldn't be found. Argument will be skipped.\n", arg.substring(2));
                }
            } else if (arg.substring(0, 3).equals("-s=")) {
                config.setMinSalary(Integer.parseInt(arg.substring(3)));
            } else if (arg.substring(0, 3).equals("-d=")) {
                config.setDaysFromSubmitting(Integer.parseInt(arg.substring(3)));
            } else if (arg.substring(0, 4).equals("-np=")) {
                config.setMaxNumberOfLoops(Integer.parseInt(arg.substring(4)));
            } else {
                System.out.printf("Argument \"%s\" couldn't be resolved and will be skipped.\n", arg);
                showHelpScreen();
            }
        }

        if (config.getPosition().isEmpty()) {
            System.out.println("Need -p parameter.");
            showHelpScreen();
        } else {
            RabotaHarvester harvester = new RabotaHarvester(config);
            try {
                harvester.Execute();

                List<JobSimilarityData> jobSimilarityDataList;
                jobSimilarityDataList = analyzeJobs(harvester.getJobs());

                generateReport(jobSimilarityDataList);
            } catch (Exception e) {
                System.out.println("Something went wrong:\n" + e.getCause());
            }
        }
    }

    static List<JobSimilarityData> analyzeJobs(Jobs jobs) {
        List<JobSimilarityData> similarityDataList = new LinkedList<JobSimilarityData>();
        for(Job j : jobs.getJobs()){
            similarityDataList.add(new JobSimilarityData(jobs.getJobs(), j));
        }
        return similarityDataList;
    }

    static void generateReport(List<JobSimilarityData> similarityDataList){
        try {
            JasperReport jr = JasperCompileManager.compileReport(App.class.getClassLoader().getResourceAsStream("META-INF/report.jrxml"));
            JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), new JRBeanCollectionDataSource(similarityDataList));
            JasperExportManager.exportReportToHtmlFile(jp, "report.html");

        } catch (JRException e) {
            System.err.println("Report couldn't be exported:\n" + e.getMessage() + e.getCause() + e.toString() + e.getStackTrace().toString());
        }

    }
}