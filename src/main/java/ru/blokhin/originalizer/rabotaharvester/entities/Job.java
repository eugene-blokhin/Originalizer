package ru.blokhin.originalizer.rabotaharvester.entities;

import com.thoughtworks.xstream.annotations.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 07.07.11
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */

@XStreamAlias("job")
public class Job implements Serializable {
    @XStreamAlias("title")
    private String title;

    @XStreamAsAttribute
    private String link;

    @XStreamAlias("company")
    private Company company;

    @XStreamAlias("city")
    private String city;

    @XStreamAlias("metro")
    private String metro;

    @XStreamAlias("salary")
    private String salary;

    @XStreamAlias("publishingDate")
    private String publishingDate;

    public Job() {
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //Don't considers the link and the publishingDate fields
    public boolean shallowEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (city != null ? !city.equals(job.city) : job.city != null) return false;
        if (company != null ? !company.equals(job.company) : job.company != null) return false;
        if (metro != null ? !metro.equals(job.metro) : job.metro != null) return false;
        if (salary != null ? !salary.equals(job.salary) : job.salary != null) return false;
        if (title != null ? !title.equals(job.title) : job.title != null) return false;

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (city != null ? !city.equals(job.city) : job.city != null) return false;
        if (company != null ? !company.equals(job.company) : job.company != null) return false;
        if (link != null ? !link.equals(job.link) : job.link != null) return false;
        if (metro != null ? !metro.equals(job.metro) : job.metro != null) return false;
        if (publishingDate != null ? !publishingDate.equals(job.publishingDate) : job.publishingDate != null)
            return false;
        if (salary != null ? !salary.equals(job.salary) : job.salary != null) return false;
        if (title != null ? !title.equals(job.title) : job.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (metro != null ? metro.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (publishingDate != null ? publishingDate.hashCode() : 0);
        return result;
    }

    //Don't considers the link and the publishingDate fields
    public int shallowHashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (metro != null ? metro.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }
}
