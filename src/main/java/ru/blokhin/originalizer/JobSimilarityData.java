package ru.blokhin.originalizer;

import ru.blokhin.originalizer.rabotaharvester.JobStringPair;
import ru.blokhin.originalizer.rabotaharvester.entities.Job;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 10.07.11
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class JobSimilarityData {
    Job job;
    List<JobStringPair> similarities;


    public Job getJob() {
        return job;
    }

    public JobSimilarityData(Collection<Job> jobCollection, Job job) {
        if (job == null || jobCollection == null)
            throw new RuntimeException("arguments \"jobCollection\" and \"job\" cannot be null");

        similarities = new LinkedList<JobStringPair>();
        this.job = job;

        for (Job j : jobCollection) {
            if(j == job)
                continue;

            List<String> sameFields = new LinkedList<String>();

            if (j.getTitle().equalsIgnoreCase(job.getTitle()))
                sameFields.add("title");
            /*if (j.getCity().equalsIgnoreCase(job.getCity()))
                sameFields.add("city");*/
            if (j.getCompany().equals(job.getCompany()))
                sameFields.add("company");
            if (j.getSalary().equalsIgnoreCase(job.getSalary()))
                sameFields.add("salary");
            if (j.getMetro().equalsIgnoreCase(job.getMetro()) && !j.getMetro().isEmpty())
                sameFields.add("metro station");
            if (sameFields.size() == 1) {
                similarities.add(new JobStringPair(j, String.format(" has same %s", sameFields.get(0))));
            } else if (sameFields.size() > 1) {
                StringBuilder stringBuilder = new StringBuilder(" has same ");
                for (int i = 0; i < sameFields.size() - 1; i++) {
                    stringBuilder
                            .append((i > 0) ? ", " : " ")
                            .append(sameFields.get(i));
                }
                stringBuilder
                        .append(" and ")
                        .append(sameFields.get(sameFields.size() - 1));
                similarities.add(new JobStringPair(j, stringBuilder.toString()));
            }
        }
    }

    public List<JobStringPair> getSimilarJobs(){
        return similarities;
    }
}
