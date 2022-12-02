package com.raisetech.HelloWorld.controller;

import com.raisetech.HelloWorld.model.UserDate;
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
	@GetMapping ("/languages-ages")
	public String getLanguageAge(Model model) {
		model.addAttribute("languageAge", new UserDate());
		return "tmp_input";
	}

	@PostMapping ("/self-introductions")
	public String postSelfIntroduction(
		@ModelAttribute UserDate languageAge, Model model
	) {
		getGreeting(languageAge);
		getTodayDate(languageAge);
		model.addAttribute("languageAge", languageAge);
		return "tmp_result";
	}

	private void getGreeting(UserDate languageAge) {
		String greeting = "";
		String country = languageAge.getCountry();
		Countries countries = Countries.valueOf(country);
		switch (countries) {
			case JAPAN -> greeting = "こんにちは〜！";
			case USA -> greeting = "ハロ〜！";
			case FRANCE -> greeting = "ボンジュ〜ル！";
			case KOREAN -> greeting = "アニハセヨ〜！";
		}
		languageAge.setGreeting(greeting);
	}
	private void getTodayDate(UserDate languageAge) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String country = languageAge.getCountry();
		Countries countries = Countries.valueOf(country);
		String todayDate = "";
		switch (countries) {
			case JAPAN, KOREAN -> {
				ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
				todayDate = LocalDateTime.now().format(dtf);
//				韓国は日本と標準時刻が同じ
			}
			case USA -> {
				ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
				todayDate = LocalDateTime.now().format(dtf);
			}
			case FRANCE -> {
				ZonedDateTime.now(ZoneId.of("Europe/Paris"));
				todayDate = LocalDateTime.now().format(dtf);
			}
		}
		languageAge.setTodayDate(todayDate);
	}
	protected enum Countries {
		JAPAN, USA, FRANCE, KOREAN
	}
}
