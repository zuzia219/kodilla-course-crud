package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://zuzia219.github.io/" );
        context.setVariable("button", "Visit Website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Goodbye! Have a nice day!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("company_email" , companyConfig.getCompanyMail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        return templateEngine.process("created-trello-card-mail", context);
    }
}
