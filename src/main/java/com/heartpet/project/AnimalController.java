package com.heartpet.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.heartpet.action.AnimalDAO;
import com.heartpet.model.AnimalDTO;
import com.heartpet.model.FileUploadImage;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Controller
public class AnimalController {

	@Autowired
	private AnimalDAO animalDAO;

	@RequestMapping("/jsptest")
	public String jsptest(Model model) {
		model.addAttribute("list", animalDAO.list());

		return "jsptest";
	}

	@RequestMapping(value = "/user_dog_list" , method = RequestMethod.GET)
	public String dog_list(Model model) {
		model.addAttribute("dogList", animalDAO.listTag("dog"));
		return "animal/dog/user_dog_list";
	}

	@RequestMapping(value = "/user_dog_content", method = RequestMethod.GET)
	public String dog_content(@RequestParam("no") int no, Model model) {
		System.out.println("===========================");
		String path = this.getClass().getResource("/").getPath().toString();
		System.out.println(new File("../../../"+this.getClass().getResource("/").getPath().toString()).toString());
		//model.addAttribute(null, model);
		return "animal/dog/user_dog_content";
	}

	@RequestMapping(value = "/user_animal_insert", method = RequestMethod.GET)
	public String dog_insert() {
		return "animal/user_animal_insert";
	}

	@RequestMapping(value = "/user_animal_insert", method = RequestMethod.POST)
	public String dog_insert_ok(@RequestParam("files") List<MultipartFile> files, HttpServletRequest request
			, AnimalDTO animalDTO)
			throws IllegalStateException, IOException {
		System.out.println(animalDTO.toString());
		FileUploadImage upload = new FileUploadImage();
		String[] images = upload.uploadAnimalImg(request, files);
		animalDTO.setAnimal_img1(images[0]);
		animalDTO.setAnimal_img2(images[1]);
		animalDTO.setAnimal_img3(images[2]);
		
		animalDTO.setAnimal_status("입양 대기");
		
		animalDAO.insert(animalDTO);

		return "redirect:/";
	}

}
