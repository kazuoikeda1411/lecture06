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
	@GetMapping ("/input")
	public String input(Model model) {
		model.addAttribute("fdat", new FDat());
		return "tmp_input";
	}

	@PostMapping ("/result")
	public String result(
		@ModelAttribute FDat fdat, Model model
	) {
		setGreetingAndTodayDate(fdat);
		model.addAttribute("fdat", fdat);
		return "tmp_result";
	}
	private void setGreetingAndTodayDate(FDat fdat) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String isGreeting = "";
		String ope = fdat.getOpe();
		String todayDate = "";
		switch (ope) {
			case "JAPAN" -> {
				isGreeting = "こんにちは〜！";
				ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
				todayDate = LocalDateTime.now().format(dtf);
			}
			case "USA" -> {
				isGreeting = "ハロ〜！";
				ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
				todayDate = LocalDateTime.now().format(dtf);
			}
			case "FRANCE" -> {
				isGreeting = "ボンジュ〜ル！";
				ZonedDateTime.now(ZoneId.of("Europe/Paris"));
				todayDate = LocalDateTime.now().format(dtf);
			}
			case "KOREAN" -> {
				isGreeting = "アニハセヨ〜！";
				ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
//			韓国は日本と時差がないため東京で設定
				todayDate = LocalDateTime.now().format(dtf);
			}
		}
		fdat.setIsGreeting(isGreeting);
		fdat.setTodayDate(todayDate);
	}
}
