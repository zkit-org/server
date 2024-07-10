package org.zkit.support.server.assets.oss.service;

import org.zkit.support.server.assets.oss.entity.dto.OssFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.zkit.support.server.assets.oss.entity.request.AliOSSPreSignRequest;
import org.zkit.support.server.assets.oss.entity.response.AliOSSPreSignResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator
 * @since 2024-07-10
 */
public interface OssFileService extends IService<OssFile> {

    public AliOSSPreSignResponse preSign(AliOSSPreSignRequest request);

}
