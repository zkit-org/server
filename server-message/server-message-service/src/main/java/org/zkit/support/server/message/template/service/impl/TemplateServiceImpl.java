package org.zkit.support.server.message.template.service.impl;

import org.zkit.support.server.message.template.entity.dto.Template;
import org.zkit.support.server.message.template.mapper.TemplateMapper;
import org.zkit.support.server.message.template.service.TemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator
 * @since 2024-06-07
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements TemplateService {

    @Override
    public Template findByLanguageAndPath(String language, String path) {
        return baseMapper.findOneByLanguageAndPath(language, path);
    }
}
