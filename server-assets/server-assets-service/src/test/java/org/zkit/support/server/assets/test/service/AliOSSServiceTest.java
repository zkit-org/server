package org.zkit.support.server.assets.test.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.zkit.support.server.assets.alioss.service.AliOSSService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class AliOSSServiceTest {

    @Resource
    private AliOSSService aliOSSService;

    @Test
    public void testSign() throws IOException {
        String signedUrl = aliOSSService.presign("test.pdf", 0);
        log.info("signedUrl {}", signedUrl);
        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String pathName = "/Users/zhaozhenhua/Downloads/test.pdf";

        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> userMetadata = new HashMap<String, String>();


        // 通过签名URL临时授权简单上传文件，以HttpClients为例说明。
        aliOSSService.upload(signedUrl, pathName, headers, userMetadata);
    }

}
