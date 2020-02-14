package com.imgurapi.apps.services;

import com.imgurapi.apps.data.ResponseData;
import com.imgurapi.apps.data.UpdateReq;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ImageServiceTest {
    /*@Mock
    @Value("${imgurapi.endpoint}")*/
    private  static String imgurapiendpoint="https://api.imgur.com/3/";
  /*  @Mock
    @Value("${clientId}")*/
    private  static String clientId="Client-ID be68528b7523b76";
    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    private ImageService imageService= new ImageService();


    @Test
    void uploadImage() {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", clientId);
      headers.setContentType(MediaType.APPLICATION_JSON);

      MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
      ResponseEntity<String> responseEntity = null;
      try {


        bodyMap.add("file", new FileSystemResource(new File("Rakesh.jpg")));



        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(bodyMap, headers);

        responseEntity = restTemplate.exchange(imgurapiendpoint + "upload", HttpMethod.POST, requestEntity,
                String.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
//Verify request succeed
      org.junit.Assert.assertEquals(201, responseEntity.getStatusCodeValue());
      org.junit.Assert.assertEquals(true, responseEntity.hasBody());

    }

    @Test
    void viewImageSuccess() {


      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", clientId);
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

      ResponseEntity<Map> responseEntity = restTemplate.exchange(imgurapiendpoint + "image/" + "r3dl7Cw",
              HttpMethod.GET, requestEntity, Map.class);

      responseEntity.getStatusCode();
//Verify request succeed
      org.junit.Assert.assertEquals(200, responseEntity.getStatusCodeValue());
      org.junit.Assert.assertEquals(true, responseEntity.hasBody());


    }
  @Test
  public void testviewImageMissingHeader()
  {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    //headers.add("Authorization", clientId);
    //headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> responseEntity =null;

    try
    {
      restTemplate.exchange(imgurapiendpoint + "image/" + "efXEQ3HuO2M4Yxf",
              HttpMethod.GET, requestEntity, String.class);
      Assert.fail();
    }
    catch(HttpClientErrorException ex)
    {
      //Verify bad request and missing header
      Assert.assertEquals(401, ex.getRawStatusCode());
     // Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
    }
  }


    @Test
    void deleteImage() {
    }
  @Test
  public void testdeleteImageMissingHeader()
  {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> responseEntity =null;

    try
    {
      restTemplate.exchange(imgurapiendpoint + "image/" + "efXEQ3HuO2M4Yxf13",
              HttpMethod.DELETE, requestEntity, String.class);
      Assert.fail();
    }
    catch(HttpClientErrorException ex)
    {
      //Verify bad request and missing header
      Assert.assertEquals(401, ex.getRawStatusCode());
      // Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
    }
  }

    @Test
    void updateImage() {
    }

  @Test
  public void testupdateImageMissingHeader()
  {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<ResponseData> responseEntity =null;

    try
    {
      restTemplate.exchange(imgurapiendpoint + "image/" + "efXEQ3HuO2M4Yxf",
              HttpMethod.POST, requestEntity, ResponseData.class);
      Assert.fail();
    }
    catch(HttpClientErrorException ex)
    {
      //Verify bad request and missing header
      Assert.assertEquals(401, ex.getRawStatusCode());
      // Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
    }
  }

}