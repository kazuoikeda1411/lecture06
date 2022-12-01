package com.raisetech.HelloWorld.controller;

import com.raisetech.HelloWorld.model.FDat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HelloController {
	@GetMapping ("/language-age")
	public String languageAge(Model model) {
		model.addAttribute("languageAge", new FDat());
		return "tmp_input";
	}

	@PostMapping ("/self-introduction")
	public String selfIntroduction(
		@ModelAttribute FDat languageAge, Model model
	) {
		setGreeting(languageAge);
		setTodayDate(languageAge);
		model.addAttribute("languageAge", languageAge);
		return "tmp_result";
	}
	private void setGreeting(FDat languageAge) {
		String isGreeting = "";
		String ope = languageAge.getOpe();
		switch (ope) {
			case "JAPAN" -> {
				isGreeting = "こんにちは〜！";
			}
			case "USA" -> {
				isGreeting = "ハロ〜！";
			}
			case "FRANCE" -> {
				isGreeting = "ボンジュ〜ル！";
			}
			case "KOREAN" -> {
				isGreeting = "アニハセヨ〜！";
			}
		}
		languageAge.setIsGreeting(isGreeting);
	}
	private void setTodayDate(FDat languageAge) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String ope = languageAge.getOpe();
		String todayDate = "";
		switch (ope) {
			case "JAPAN", "KOREAN" -> {
				ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
				todayDate = LocalDateTime.now().format(dtf);
//				韓国は日本と標準時刻が同じ
			}
			case "USA" -> {
				ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
				todayDate = LocalDateTime.now().format(dtf);
			}
			case "FRANCE" -> {
				ZonedDateTime.now(ZoneId.of("Europe/Paris"));
				todayDate = LocalDateTime.now().format(dtf);
			}
		}
		languageAge.setTodayDate(todayDate);
	}
}
