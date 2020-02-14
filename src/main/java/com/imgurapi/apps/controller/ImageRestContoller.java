package com.imgurapi.apps.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imgurapi.apps.data.ResponseData;
import com.imgurapi.apps.data.UpdateReq;
import com.imgurapi.apps.services.ImageService;

@RestController
@RequestMapping("/imguaapi")
public class ImageRestContoller {

	@Autowired
	ImageService imageService;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestBody String url) {

		return imageService.uploadImage(url);

	}

	@GetMapping("/image/{imageHash}")
	public ResponseEntity<String> viewImage1(@PathVariable("imageHash") String imageHash) {
		return imageService.viewImage1(imageHash);

	}

	@DeleteMapping("/image/{imageHash}")
	public ResponseEntity<String> deleteImage(@PathVariable("imageHash") String imageHash) {

		return imageService.deleteImage(imageHash);

	}

	@PostMapping("/image/{imageHash}")
	public ResponseEntity<ResponseData> updateImage(@PathVariable("imageHash") String imageHash,
			@RequestBody UpdateReq data) {

		return imageService.updateImage(imageHash, data);

	}

}
