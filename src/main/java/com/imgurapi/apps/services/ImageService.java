package com.imgurapi.apps.services;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.imgurapi.apps.data.ResponseData;
import com.imgurapi.apps.data.UpdateReq;

import javassist.bytecode.ByteArray;


@Service
public class ImageService {
	
	@Value("${imgurapi.endpoint}")
	private  String imgurapiendpoint;
	@Value("${clientId}")
	private  String clientId;

	@Autowired
	private RestTemplate restTemplate;

	static Resource createTempFileResource(byte [] content) throws IOException {
		Path tempFile = Files.createTempFile("upload-file", ".jpg");
		Files.write(tempFile, content);
		return new FileSystemResource(tempFile.toFile());
	}

	/**
	 * @param url
	 * @return esponseEntity<?>
	 */
	public ResponseEntity<?> uploadImage(String url) {

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		ResponseEntity<String> responseEntity = null;
		try {

			if (url != null) {
				bodyMap.add("images", url);

			}

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, getRequestHeader());

			responseEntity = restTemplate.exchange(imgurapiendpoint + "upload", HttpMethod.POST, requestEntity,
					String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}


	/**
	 * @param imageHash
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> viewImage1(String imageHash) {
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(getRequestHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(imgurapiendpoint + "image/" + imageHash,
				HttpMethod.GET, requestEntity, String.class);

		return responseEntity;

	}


	/**
	 * @param imageHash
	 * @return ResponseEntity<String>
	 */
	public ResponseEntity<String> deleteImage(String imageHash) {

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(getRequestHeader());
		ResponseEntity<String> responseEntity = restTemplate.exchange(imgurapiendpoint + "image/" + imageHash,
				HttpMethod.DELETE, requestEntity, String.class);

		return responseEntity;

	}


	/**
	 * @param imageHash
	 * @param data
	 * @return ResponseEntity<ResponseData>
	 */
	public ResponseEntity<ResponseData> updateImage( String imageHash, UpdateReq data) {
		
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("title", data.getTitle());
		bodyMap.add("description", data.getDescription());

		HttpEntity<UpdateReq> requestEntity = new HttpEntity<>(data, getRequestHeader());
		ResponseEntity<ResponseData> responseEntity = restTemplate.exchange(imgurapiendpoint + "image/" + imageHash,
				HttpMethod.POST, requestEntity, ResponseData.class);

		return responseEntity;

	}

	/**
	 * @return HttpHeaders
	 */
	public  HttpHeaders getRequestHeader() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", clientId);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return headers;
	}
	
	

	
}
