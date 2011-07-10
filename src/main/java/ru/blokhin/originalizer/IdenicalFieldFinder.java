package ru.blokhin.originalizer;



import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import ru.blokhin.originalizer.rabotaharvester.entities.Job;

import net.sf.jasperreports.engine.JasperCompileManager;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 08.07.11
 * Time: 1:39
 * To change this template use File | Settings | File Templates.
 */
public class IdenicalFieldFinder {
    HashMap<String, HashSet<Job>> title;
    HashMap<String, HashSet<Job>> company;

    public IdenicalFieldFinder() {
        title = new HashMap<String, HashSet<Job>>();
        company = new HashMap<String, HashSet<Job>>();
    }

    public void analyze(Collection<Job> jobs) {
        for(Job j : jobs) {
            if(!title.containsKey(j.getTitle())) {
                title.put(j.getTitle(), new HashSet<Job>());
            }
            title.get(j.getTitle()).add(j);

            if(!company.containsKey(j.getCompany().getTitle())) {
                company.put(j.getCompany().getTitle(), new HashSet<Job>());
            }
            company.get(j.getCompany().getTitle()).add(j);
        }
    }

    void saveReport() {

    }

}
