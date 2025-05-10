package org.zkit.support.server.ai.mcp.configurer;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zkit.support.server.ai.mcp.service.WeatherService;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class MCPConfigurer {

  @Bean
	public ToolCallbackProvider weatherTools(WeatherService weatherService) {
		return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
	}

}
