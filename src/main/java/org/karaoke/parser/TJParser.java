package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.karaoke.domain.Category.NUMBER;
import static org.karaoke.domain.Category.SINGER;
import static org.karaoke.domain.Category.SONG;

@Component("TJ")
@Slf4j
public class TJParser extends Parser {

    private static final String URL = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?";
    private static final String DOC_QUERY = "table.board_type1 tr:has(td)";
    private static final Map<Category, String> URLQuery = new HashMap<>();

    @PostConstruct
    public void setUp() {
        URLQuery.put(SINGER, "strType=2");
        URLQuery.put(SONG, "strType=1");
        URLQuery.put(NUMBER, "strType=16");
    }

    public List<Karaoke> tryToExtract(Argument argument) throws IOException {
        Elements dom = fetchDOM(getUrl(argument), DOC_QUERY);
        return extractKaraokes(dom);
    }

    private String getUrl(Argument argument) {
        return new StringBuilder(URL)
                .append(URLQuery.get(argument.getCategory()))
                .append("&strText=")
                .append(argument.getWord())
                .append("&strSize02=10")
                .append("&intPage=")
                .append(argument.getPage()).toString();
    }
}
