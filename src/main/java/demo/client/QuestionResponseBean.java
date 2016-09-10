package demo.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import demo.model.Question;

import java.util.List;

/**
 * Created by Max Malakhov on 9/7/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionResponseBean {

    private List<Question> questions;

    private int remaining = 0;

    public List<Question> getQuestions() {
        return questions;
    }

    @JsonProperty("items")
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getRemaining() {
        return remaining;
    }

    @JsonProperty("quota_remaining")
    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
