package org.zkit.support.server.mail.template.mapper;
import org.apache.ibatis.annotations.Param;

import org.zkit.support.server.mail.template.entity.dto.Template;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2024-06-07
 */
public interface TemplateMapper extends BaseMapper<Template> {

    Template findOneByLanguageAndPath(@Param("language") String language, @Param("path") String path);

}
