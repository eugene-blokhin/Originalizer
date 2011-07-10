package ru.blokhin.originalizer.rabotaharvester.entities;

import java.util.LinkedList;
import java.util.List;
import com.thoughtworks.xstream.annotations.*;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 07.07.11
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */

@XStreamAlias("jobs")
public class Jobs {

    @XStreamImplicit(itemFieldName = "job")
    private List<Job> jobs;

    public Jobs(){
        jobs = new LinkedList<Job>();
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
