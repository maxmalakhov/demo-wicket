package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Max Malakhov on 9/7/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author implements Serializable {

    private String id;
    private String name;
    private String avatarLink;
    private String link;

    public String getId() {
        return id;
    }

    @JsonProperty("user_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("display_name")
    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    @JsonProperty("profile_image")
    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
