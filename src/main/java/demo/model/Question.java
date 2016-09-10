package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Max Malakhov on 9/7/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question implements Serializable {

    private String id;
    private Author author;
    private String title;
    private Date creationDate;
    private boolean answered = false;
    private int score = 0;
    private String link;

    public String getId() {
        return id;
    }

    @JsonProperty("question_id")
    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    @JsonProperty("owner")
    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creation_date")
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isAnswered() {
        return answered;
    }

    @JsonProperty("is_answered")
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
