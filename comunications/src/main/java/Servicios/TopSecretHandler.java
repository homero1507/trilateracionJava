package Servicios;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.RequestDto;
import dto.ResponseDto;
import dto.SateliteRequestDto;
import facade.SateliteFacadeImp;
import facade.SatelitesFacade;

public class TopSecretHandler implements RequestHandler<Object, String> {

	private LambdaLogger logger;
	private Gson gson = new Gson();

	public String handleRequest(Object input, Context context) {
		logger = context.getLogger();
		ResponseDto response = procesarPeticion(input);
		String respuestaJSON = gson.toJson(response);
		logger.log(respuestaJSON);
		return respuestaJSON;
	}

	private ResponseDto procesarPeticion(Object input) {
		ResponseDto response = new ResponseDto();

		Object obj = JSONValue.parse(gson.toJson(input));

		JSONObject jsonObject = (JSONObject) obj;
		String body = (String) jsonObject.get("body");
		JSONObject jsonContext = (JSONObject) jsonObject.get("requestContext");
		JSONObject http = (JSONObject) jsonContext.get("http");
		String metodoHttp = (String) http.get("method");

		logger.log(http.toJSONString());
		logger.log(jsonContext.toJSONString());

		if ("POST".equals(metodoHttp)) {

			String path = ((String) http.get("path"));
			logger.log(path);
			if (path.startsWith("/topsecret_split/")) {
				String sName = path.split("/")[2];
				response = procesarPost(body, sName);
			} else {
				response = procesarPost(body);
			}

		}
		if ("GET".equals(metodoHttp)) {
			response.setCode("404");
		} else {
			response.setCode("403");
		}

		return response;
	}

	private ResponseDto procesarPost(String body, String sateliteName) {
		logger.log(body);
		ResponseDto response = new ResponseDto();

		SateliteRequestDto satelitesRequest = gson.fromJson(body, SateliteRequestDto.class);

		response.setCode("200");
		response.setMensaje("recibido: " + sateliteName);
		response.setPosition(null);

		return response;
	}

	private ResponseDto procesarPost(String body) {

		logger.log(body);

		ResponseDto response = new ResponseDto();

		RequestDto satelitesRequest = gson.fromJson(body, RequestDto.class);

		SatelitesFacade sf = new SateliteFacadeImp();

		satelitesRequest.getSatellites().forEach(sateliteRequest -> {
			logger.log(sateliteRequest.toString());
			logger.log(sateliteRequest.getMessage().toString());
			ResponseDto res = sf.getLocation(sateliteRequest.getDistance());
			response.setCode(res.getCode());
			response.setPosition(res.getPosition());
			response.setMensaje(sf.GetMessage(sateliteRequest.getMessage()));
		});

		return response;
	}

}
