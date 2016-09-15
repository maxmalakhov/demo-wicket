package demo.pages;

import demo.client.ClientException;
import demo.model.Question;
import demo.services.QuestionDataService;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Malakhov on 9/7/16.
 */
public class SearchPanel extends Panel {

    @SpringBean(name = "questionDataService")
    private QuestionDataService dataService;

    private String searchValue;

    public SearchPanel(String id, final Component resultPanel, final ResultObject resultObject) {
        super(id);

        final TextField<String> searchInput = new TextField<String>("searchInput", new PropertyModel<String>(this, "searchValue"));

        searchInput.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(searchInput);
            }
        });

        Form<?> form = new Form<Void>("searchForm") {

            @Override
            protected void onSubmit()
            {
                try
                {
                    List<Question> questions = new ArrayList<Question>();
                    if (StringUtils.isEmpty(searchValue))
                    {
                        questions.addAll(dataService.allQuestions());
                    }
                    else
                    {
                        questions.addAll(dataService.searchQuestions(searchValue));
                    }
                    resultObject.setQuestions(questions);
                    resultObject.setFilter("");
                    resultObject.setExceptionMessage("");
                }
                catch (ClientException ex)
                {
                    resultObject.setExceptionMessage(ex.getMessage());
                }
            }

            @Override
            protected void onError()
            {
            }
        };
        form.add(searchInput);

        AjaxLink<Void> searchButton = new AjaxLink<Void>("searchButton")
        {
            private static final long serialVersionUID = 6075189655980473305L;

            @Override
            public void onClick(AjaxRequestTarget target)
            {
                target.add(resultPanel);
            }
        };
        form.add(searchButton);

        add(form);

    }
}
