package demo.pages;

import demo.model.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Malakhov on 9/7/16.
 */
public class ResultObject implements Serializable {

    private final List<Question> questions = new ArrayList<>();
    private String filter;
    private String message;
    private int remaining;

    public ResultObject() {
    }

    public ResultObject(List<Question> questions) {
        this.questions.addAll(questions);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getExceptionMessage() {
        return message;
    }

    public void setExceptionMessage(String message) {
        this.message = message;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
