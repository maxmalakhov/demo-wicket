package demo.client;

/**
 * Created by Max Malakhov on 9/7/16.
 */
public enum ActionTypeEnum {

    SEARCH("search"), QUESTIONS("questions");

    private String value;

    private ActionTypeEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

}
