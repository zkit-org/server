package org.zkit.support.server.assets.oss.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
 * @since 2025-06-18
 */
@Data
@Accessors(chain = true)
@TableName("oss_file")
@Schema(name = "OssFile", description = "")
public class OssFile implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "文件长度")
    private Long length;

    @Schema(description = "Content Type")
    private String contentType;

    @Schema(description = "访问地址")
    private String url;

    @Schema(description = "上传者ID")
    private Long userId;

    @Schema(description = "上传时间")
    private Date createTime;

    @Schema(description = "UUID")
    private String hash;

    @Schema(description = "桶")
    private String bucket;
}
