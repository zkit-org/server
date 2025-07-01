package org.zkit.support.server.message.service;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.StringWriter;
import java.util.Map;

@Slf4j
@Component
public class FreemarkerService {

    @Resource
    private FreeMarkerConfigurer configurer;

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
