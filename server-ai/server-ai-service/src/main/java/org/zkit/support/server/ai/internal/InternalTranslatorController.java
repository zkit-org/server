package org.zkit.support.server.ai.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zkit.support.server.ai.api.constant.AIApiRoute;
import org.zkit.support.server.ai.api.entity.TranslatorRequest;
import org.zkit.support.server.ai.service.TranslatorService;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "internal-translator", description = "[内部接口]翻译")
public class InternalTranslatorController {

    @Resource
    private TranslatorService translatorService;

    @Operation(summary = "根据用户名查找账户")
    @PostMapping(AIApiRoute.TRANSLATOR)
    public List<String> translator(@RequestBody TranslatorRequest request) {
        return translatorService.translate(request);
    }

}
