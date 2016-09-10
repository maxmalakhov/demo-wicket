package demo.client;

/**
 * Created by Max Malakhov on 9/7/16.
 */
public enum OrderTypeEnum {

    DESC("desc"), ASC("asc");

    private String value;

    private OrderTypeEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
