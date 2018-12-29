package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

	@GetMapping("/toUpload")
	public String index() {
	    return "toUpload";
	}
	
	@RequestMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
	
	
	@PostMapping("/upload") 
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
	                               RedirectAttributes redirectAttributes) {
	    if (file.isEmpty()) {
	        redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	        return "redirect:uploadStatus";
	    }
	    try {
	        // Get the file and save it somewhere
	        byte[] bytes = file.getBytes();
	        // UPLOADED_FOLDER 文件本地存储地址
	        Path path = Paths.get("E:/keYun/springBoot/fileUpload/" + file.getOriginalFilename());
	        Files.write(path, bytes);

	        redirectAttributes.addFlashAttribute("message",
	                "You successfully uploaded '" + file.getOriginalFilename() + "'");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "redirect:/uploadStatus";
	}
	
	@PostMapping("/uploadMore")
	public String moreFileUpload(@RequestParam("file") MultipartFile[] files,
			RedirectAttributes redirectAttributes) {
		if(files.length==0) {
			redirectAttributes.addFlashAttribute("message", "请选择至少一个文件上传");
			return "redirect:uploadStatus";
		}
		
		for(MultipartFile file : files) {
			try {
				if(!file.isEmpty()) {
					byte[] bytes = file.getBytes();
					Path path = Paths.get("E:/keYun/springBoot/fileUpload/" + file.getOriginalFilename());
					Files.write(path, bytes);
					redirectAttributes.addFlashAttribute("message", "上传成功");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:uploadStatus";
		
	}
	
	
}
