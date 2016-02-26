package nobodysperfect;

import nobodysperfect.domain.Game;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NobodysPerfectApplication.class)
@WebAppConfiguration
public class NobodysPerfectRestControllerTest {

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    MongoTemplate mongoTemplate;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
        mongoTemplate.getDb().dropDatabase();
    }

    @Test
    public void getGamesReturnsEmptyArray() throws Exception {
        mockMvc.perform(get("/api/games"))
               .andExpect(status().isOk())
               .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
               .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void postGamesAddsExpectedGame() throws Exception {
        Game newGame = new Game("Test Game Title", "Test Game Description");

        // First we create a new game
        mockMvc.perform(post("/api/games")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .param("title", newGame.getTitle())
                    .param("description", newGame.getDescription())
                )
                .andExpect(status().is(302))
                .andDo(print());

        // Then we request all games
        mockMvc.perform(get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

        // And check whether our game contains what we expect
        mockMvc.perform(get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].title", is(newGame.getTitle())))
                .andExpect(jsonPath("$[0].description", is(newGame.getDescription())));
    }

    @Test
    public void deleteGameDeletesExpectedGame() throws Exception {
        Game newGame = new Game("Game to be deleted", "Game to be deleted");

        mockMvc.perform(post("/api/games")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .param("title", newGame.getTitle())
                    .param("description", newGame.getDescription())
            );
/*
        String newGameId = mockMvc.perform()

        mockMvc.perform() */
    }

}
