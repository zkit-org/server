package org.zkit.support.server.mail.template.service;

import org.zkit.support.server.mail.template.entity.dto.Template;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator
 * @since 2024-06-07
 */
public interface TemplateService extends IService<Template> {

    Template findByLanguageAndPath(String language, String path);

}
