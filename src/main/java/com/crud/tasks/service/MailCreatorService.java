package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public final static String CREATED_TRELLO_CARD = "created-trello-card-mail";
    public final static String SCHEDULED_MAIL = "scheduled-mail";

    public Context buildTrelloCardEmailContext(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with trello account");
        functionality.add("Application allows sending tasks to trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://infinite-reef-78397.herokuapp.com/actuator/info");
        context.setVariable("button", "See the app info");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Goodbye! Have a nice day!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return context;
    }

    public Context buildScheduledMailContext(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your database");
        functionality.add("You can call us to change the current used database type");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://zuzia219.github.io/");
        context.setVariable("button", "Visit Website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Goodbye! Have a nice day!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("company_email", companyConfig.getCompanyMail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return context;
    }

    public String chooseEmailTemplate(String template, String message) {
        if (template.equals(CREATED_TRELLO_CARD)) {
            return templateEngine.process(CREATED_TRELLO_CARD, buildTrelloCardEmailContext(message));
        } else if (template.equals(SCHEDULED_MAIL)) {
            return templateEngine.process(SCHEDULED_MAIL, buildScheduledMailContext(message));
        } else {
            return null;
        }
    }
}
