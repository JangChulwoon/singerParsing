package org.karaoke.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.experimental.theories.Theories;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MusicController {

	private final Logger logger = Logger.getLogger(this.getClass());

	KaraokeService karaokeService;

	@Autowired
	public void setKaraokeService(KaraokeService karaokeService) {
		this.karaokeService = karaokeService;
	}

	@GetMapping(value = "/{company}/{type}/{title}")
	public List<?> TitleView(@PathVariable String company, @PathVariable String type, @PathVariable String title,
			Model model) throws IOException {
		return karaokeService.makeKaraokeNumber(company, type, title);
	}

}