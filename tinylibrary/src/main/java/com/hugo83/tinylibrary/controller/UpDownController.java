package com.hugo83.tinylibrary.controller;

import java.io.File;
// import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
// import java.awt.image.BufferedImage;

// import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hugo83.tinylibrary.dto.UploadResultDTO;

// import com.hugo83.tinylibrary.dto.UploadFileDTO;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UpDownController {
	@Value("${com.hugo83.upload.path}") // import 시에 springframework으로 시작하는 Value
	private String uploadPath;

	@Operation(summary = "POST 파일 업로드(RequestParam!)")
	@PostMapping(value = "/upload", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<UploadResultDTO> upload(@RequestParam("files") List<MultipartFile> uploadFiles) {
		log.info("ImageFileUpload START!!!!!!! ");

		if (uploadFiles.size() >= 0) {
			final List<UploadResultDTO> list = new ArrayList<>();

			// uploadFiles.getFiles().forEach(uploadFile -> {
			uploadFiles.forEach(uploadFile -> {
				String originalName = uploadFile.getOriginalFilename();
				log.info(originalName);

				String uuid = UUID.randomUUID().toString();
				Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);
				boolean image = false;

				try {
					uploadFile.transferTo(savePath);

					// 이미지 파일 종류라면
					if (Files.probeContentType(savePath).startsWith("image")) {
						image = true;

						File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
						Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				list.add(UploadResultDTO.builder()
						.uuid(uuid).fileName(originalName).img(image).build());
			}); // end forEach

			return list;
		} // end if

		return null;
	}

	@Operation(summary = "GET 파일 조회하기")
	@GetMapping(value = "/view/{fileName}")
	public ResponseEntity<Resource> getViewFile(@PathVariable String fileName) {
		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

		String resourceName = resource.getFilename();
		log.info("resourceName :::::: " + resourceName);
		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

		return ResponseEntity.ok().headers(headers).body(resource);
	}

	@Operation(summary = "DELETE 파일 삭제하기")
	@DeleteMapping(value = "/remove/{fileName}")
	public Map<String, Boolean> deleteUploadFile(@PathVariable String fileName) {
		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		String resourceName = resource.getFilename();
		log.info("resourceName :::::: " + resourceName);

		Map<String, Boolean> resultMap = new HashMap<>();
		boolean removed = false;

		try {
			String contentType = Files.probeContentType(resource.getFile().toPath());
			removed = resource.getFile().delete();

			// 썸네일이 있다면
			if (contentType.startsWith("image")) {
				File thumbFile = new File(uploadPath + File.separator + "s_" + fileName);
				thumbFile.delete();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		resultMap.put("result", removed);
		return resultMap;
	}
}
