package org.zkit.support.server.assets.oss.service;

import org.zkit.support.server.assets.api.entity.request.OSSPresignRequest;
import org.zkit.support.server.assets.api.entity.response.OSSPresignResponse;
import org.zkit.support.server.assets.oss.entity.dto.OssFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator
 * @since 2024-07-10
 */
public interface OssFileService extends IService<OssFile> {

    public OSSPresignResponse presign(OSSPresignRequest request);

}
