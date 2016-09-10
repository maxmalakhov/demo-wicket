package demo.client;

/**
 * Created by Max Malakhov on 9/7/16.
 */
public enum SortTypeEnum {

    ACTIVITY("activity"), VOTES("votes"), CREATION("creation"), RELEVANCE("relevance");

    private String value;

    private SortTypeEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
