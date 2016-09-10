package demo.pages;

import demo.client.ClientException;
import demo.model.Question;
import demo.services.QuestionDataService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Max Malakhov on 9/7/16.
 */
public class ResultPanel extends Panel {

    @SpringBean(name = "questionDataService")
    private QuestionDataService dataService;

    public ResultPanel(String id, final ResultObject result) {
        super(id);

        try
        {
            List<Question> questions = dataService.allQuestions();
            result.setQuestions(questions);
            result.setFilter("");
            result.setExceptionMessage("");
        }
        catch (ClientException ex)
        {
            result.setExceptionMessage(ex.getMessage());
        }

        SortableQuestionDataProvider dataProvider = new SortableQuestionDataProvider(result);

        FilterForm<String> filterForm = new FilterForm<>("filterForm", dataProvider);

        filterForm.add(new TextField<>("filter", PropertyModel.of(dataProvider, "filterState")));

        add(filterForm);


        final DataView<Question> dataView = new DataView<Question>("sorting", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final Item<Question> item) {
                Question question = item.getModelObject();
//                item.add(new ActionPanel("actions", item.getModel()));
//                item.add(new Label("id", String.valueOf(question.getId())));
                item.add(new Label("date", new SimpleDateFormat("yyyy-MM-dd hh:mm").format(question.getCreationDate())));
                item.add(new Label("title", question.getTitle()));
                item.add(new Label("score", question.getScore()));
                item.add(new Label("author", question.getAuthor().getName()));

                item.add(AttributeModifier.replace("class", new AbstractReadOnlyModel<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject() {
                        return (item.getModelObject().isAnswered()) ? "odd" : "even";
                    }
                }));
            }
        };

        dataView.setItemsPerPage(8L);

        add(new OrderByBorder("orderByDate", "date", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                dataView.setCurrentPage(0);
            }
        });

        add(new OrderByBorder("orderByTitle", "title", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                dataView.setCurrentPage(0);
            }
        });

        add(new OrderByBorder("orderByScore", "score", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                dataView.setCurrentPage(0);
            }
        });

        add(new OrderByBorder("orderByAuthor", "author", dataProvider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                dataView.setCurrentPage(0);
            }
        });

        add(dataView);

        add(new Label("exceptionMessage", new PropertyModel<String>(result, "exceptionMessage")));


        add(new PagingNavigator("navigator", dataView));

    }
}