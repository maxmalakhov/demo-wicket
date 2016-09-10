package demo.pages;

import demo.model.Question;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.*;

/**
 * Created by Max Malakhov on 9/8/16.
 */
public class SortableQuestionDataProvider extends SortableDataProvider<Question, String> implements IFilterStateLocator<String> {

    private final ResultObject resultObject;

    /**
     * constructor
     * @param resultObject
     */
    public SortableQuestionDataProvider(final ResultObject resultObject)
    {
        this.resultObject = resultObject;
        // set default sort
        setSort("score", SortOrder.DESCENDING);
    }

    @Override
    public Iterator<Question> iterator(long first, long count)
    {
        List<Question> questions = resultObject.getQuestions();

        return filterQuestions(sort(questions, getSort())).
                subList((int)first, (int)(first + count)).
                iterator();
    }

    private List<Question> filterQuestions(List<Question> questions)
    {
        List<Question> result = new ArrayList<>();

        for (Question question : questions)
        {
            if(question.getTitle().contains(getFilterState()))
            {
                result.add(question);
            }
            else if(question.getAuthor().getName().contains(getFilterState()))
            {
                result.add(question);
            }
        }

        return result;
    }

    public List<Question> sort(List<Question> questions, final SortParam sort)
    {
        if (sort == null)
        {
            return questions;
        }

        if (sort.getProperty().equals("date"))
        {
            Collections.sort(questions, new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    if(sort.isAscending()) {
                        return o1.getCreationDate().compareTo(o2.getCreationDate());
                    } else {
                        return o2.getCreationDate().compareTo(o1.getCreationDate());
                    }
                }
            });
        }
        else if (sort.getProperty().equals("title"))
        {
            Collections.sort(questions, new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    if(sort.isAscending()) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    } else {
                        return o2.getTitle().compareTo(o1.getTitle());
                    }
                }
            });
        }
        else if (sort.getProperty().equals("score"))
        {
            Collections.sort(questions, new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    if(sort.isAscending()) {
                        return o1.getScore() - o2.getScore();
                    } else {
                        return o2.getScore() - o1.getScore();
                    }
                }
            });
        }
        else if (sort.getProperty().equals("author"))
        {
            Collections.sort(questions, new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    if(sort.isAscending()) {
                        return o1.getAuthor().getName().compareTo(o2.getAuthor().getName());
                    } else {
                        return o2.getAuthor().getName().compareTo(o1.getAuthor().getName());
                    }
                }
            });
        }

        return questions;
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
     */
    @Override
    public long size()
    {
        return filterQuestions(resultObject.getQuestions()).size();
    }

    /**
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
     */
    @Override
    public IModel<Question> model(Question object)
    {
        return new Model(object);
    }

    @Override
    public String getFilterState()
    {
        return (resultObject.getFilter() != null) ? resultObject.getFilter() : "";
    }

    @Override
    public void setFilterState(String state)
    {
        resultObject.setFilter(state);
    }

}
