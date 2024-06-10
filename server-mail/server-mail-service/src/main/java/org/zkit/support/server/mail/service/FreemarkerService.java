package org.zkit.support.server.mail.service;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zkit.support.server.mail.template.service.TemplateService;

import java.io.StringWriter;
import java.util.Map;

@Slf4j
@Component
public class FreemarkerService {

    public String render(String templateName, String templateContext, Map<String, Object> data) {
        log.info("render template: {}", data);
        Version version = new Version("2.3.0");
        Configuration configuration = new Configuration(version);
        try {
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate(templateName, templateContext);
            configuration.setTemplateLoader(stringLoader);
            Template temp = configuration.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            temp.process(data, writer);
            return writer.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
