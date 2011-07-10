package ru.blokhin.originalizer.rabotaharvester;

/**
 * Created by IntelliJ IDEA.
 * User: Eugene Blokhin
 * Date: 05.07.11
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RabotaHarvesterConfig {
    private Location location = Location.ST_PETERSBURG;
    private int minSalary = 0;
    private int daysFromSubmitting = 30;
    private String position = "";
    private int maxNumberOfLoops = 0;

    public RabotaHarvesterConfig() {}

    public RabotaHarvesterConfig(String position) {
        setPosition(position);
    }

    public RabotaHarvesterConfig(String position, Location location) {
        setPosition(position);
        setLocation(location);
    }

    public RabotaHarvesterConfig(String position, Location location, int minSalary) {
        setPosition(position);
        setLocation(location);
        setMinSalary(minSalary);
    }

    public RabotaHarvesterConfig(String position, Location location, int minSalary, int daysFromSubmitting) {
        setPosition(position);
        setLocation(location);
        setMinSalary(minSalary);
        setDaysFromSubmitting(daysFromSubmitting);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getDaysFromSubmitting() {
        return daysFromSubmitting;
    }

    public void setDaysFromSubmitting(int daysFromSubmitting) {
        this.daysFromSubmitting = daysFromSubmitting;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) throws NullPointerException {
        if (position == null)
            throw new NullPointerException("position cannot be null.");
        this.position = position;
    }

    public int getMaxNumberOfLoops() {
        return maxNumberOfLoops;
    }

    public void setMaxNumberOfLoops(int maxNumberOfLoops) {
        if(maxNumberOfLoops < 0)
            throw new IllegalArgumentException("Maximum number of lists. Zero for no limit.");
        this.maxNumberOfLoops = maxNumberOfLoops;
    }

    public String getUrl() {
        StringBuilder stringBuilder =
                new StringBuilder("http://www.rabota.ru/v3_searchVacancyByParamsResults.html?sm=102&d=desc");

        stringBuilder.append("&w=");
        stringBuilder.append(URLEncoder.encode(position));

        stringBuilder.append("&sf=");
        stringBuilder.append(minSalary);

        stringBuilder.append("&c=");
        stringBuilder.append(location.getCode());

        stringBuilder.append("&p=");
        stringBuilder.append(daysFromSubmitting);

        return stringBuilder.toString();
    }
}
