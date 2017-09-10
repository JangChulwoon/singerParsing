package org.karaoke.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.domain.KaraokeBuild;
import org.karaoke.parser.KYParser;
import org.karaoke.parser.Parser;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class KaraokeServiceTest {

	KaraokeService service;

	@Mock
	Parser parser;

	@Mock 
	Parser companyParser;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		service = new KaraokeServiceImpl();
		service.setParser(parser);
	}

	
	
	@Test
	public void shouldParseKaraoke() throws IOException {
		String company = "KY", type = "song", title = "첫눈";
		KaraokeBuild karaoke = new KaraokeBuild();
		karaoke.setNumber("010").setSinger("정준일").setComposer("정준일").setTitle("첫눈").setLyricist("정준일");
		List<KaraokeBuild> list = new ArrayList<KaraokeBuild>();
		when(parser.initCompany(company)).thenReturn(companyParser);
		when(companyParser.runParser(type,title)).thenReturn(list);

		// when
		List<KaraokeBuild> returnKategory = service.makeKaraokeNumber(company, type, title);

		verify(parser,times(1)).initCompany(company);
		verify(companyParser,times(1)).runParser(type,title);
		Assert.assertThat(list, is(returnKategory));
	}

}
