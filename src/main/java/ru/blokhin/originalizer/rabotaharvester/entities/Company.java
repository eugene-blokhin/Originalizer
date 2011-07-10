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

@XStreamAlias("company")
public class Company implements Serializable {
    @XStreamAsAttribute
    @XStreamAlias("link")
    private String link;

    @XStreamAlias("title")
    private String title;

    public Company() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (link != null ? !link.equals(company.link) : company.link != null) return false;
        if (title != null ? !title.equals(company.title) : company.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
