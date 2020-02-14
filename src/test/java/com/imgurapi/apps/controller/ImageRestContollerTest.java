package com.imgurapi.apps.controller;

import com.imgurapi.apps.data.UpdateReq;
import com.imgurapi.apps.services.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImageRestContollerTest {

    @Mock
    ImageService imageService;
    @InjectMocks
    ImageRestContoller imageRestContoller;


    @Test
    void uploadImage() {

        imageService.uploadImage("url");
    }

    @Test
    void viewImage1() {

        imageRestContoller.viewImage1("efXEQ3HuO2M4Yxf");
        imageService.viewImage1("efXEQ3HuO2M4Yxf");
       // Mockito.verify(imageService.viewImage1("efXEQ3HuO2M4Yxf"));
    }

    @Test
    void deleteImage() {
        imageRestContoller.deleteImage("efXEQ3HuO2M4Yxf");
        imageService.deleteImage("efXEQ3HuO2M4Yxf");

    }

    @Test
    void updateImage() {
        imageRestContoller.updateImage("efXEQ3HuO2M4Yxf", new UpdateReq());
        imageService.updateImage("efXEQ3HuO2M4Yxf", new UpdateReq());

    }
}