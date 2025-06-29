package org.zkit.support.server.message.template.entity.dto;

import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator
 * @since 2025-04-22
 */
@SuppressWarnings("unused")
@Data
@Accessors(chain = true)
@TableName("template_language")
@Schema(name = "TemplateLanguage", description = "")
public class TemplateLanguage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "语言代码")
    private String code;

    @Schema(description = "描述")
    private String memo;
}
