package ru.blokhin.originalizer.rabotaharvester;

import ru.blokhin.originalizer.rabotaharvester.entities.Job;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 10.07.11
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class JobStringPair implements Serializable {
    private Job job;
    private String string;

    public JobStringPair() {
    }

    public JobStringPair(Job job, String string) {
        this.job = job;
        this.string = string;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
