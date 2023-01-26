package mx.softixx.cis.common.feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;
import mx.softixx.cis.common.feign.decoder.CustomErrorDecoder;

@Configuration
public class FeignConfig {
	
	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
	
}