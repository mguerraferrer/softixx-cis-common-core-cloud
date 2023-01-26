package mx.softixx.cis.common.feign.decoder;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.val;
import lombok.extern.log4j.Log4j2;
import mx.softixx.cis.common.validation.exception.CustomException;
import mx.softixx.cis.common.validation.payload.ErrorResponse;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
	
	@Override
	public Exception decode(String methodKey, Response response) {
		log.info("Feign error decoder url: {}", response.request().url());
		log.info("Feign error decoder headers: {}", response.request().headers());
		try {
			
			val objectMapper = new ObjectMapper(); 
			val errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
			throw CustomException.buildException(errorResponse);
			
		} catch (IOException e) {
			throw CustomException.internalServerError();
		}
	}

}