package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    private static final TrelloListDto trelloListDto1 = new TrelloListDto("1", "list1", true);
    private static final TrelloListDto trelloListDto2 = new TrelloListDto("2", "list2", false);

    private static final TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board1", new ArrayList<>(Arrays.asList(trelloListDto1, trelloListDto2)));
    private static final TrelloBoardDto trelloBoardDto2 =  new TrelloBoardDto("2", "board2", new ArrayList<>(Arrays.asList(trelloListDto2, trelloListDto1)));

    private static final TrelloList trelloList1 = new TrelloList("1", "list1", true);
    private static final TrelloList trelloList2 = new TrelloList("2", "list2", false);

    private static final TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", new ArrayList<>(Arrays.asList(trelloList1, trelloList2)));
    private static final TrelloBoard trelloBoard2 = new TrelloBoard("2", "board2", new ArrayList<>(Arrays.asList(trelloList2, trelloList1)));

    private static final List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>(Arrays.asList(trelloBoardDto1, trelloBoardDto2));
    private static final List<TrelloBoard> trelloBoards = new ArrayList<>(Arrays.asList(trelloBoard1, trelloBoard2));

    private static final List<TrelloListDto> trelloListDtos = new ArrayList<>(Arrays.asList(trelloListDto1, trelloListDto2));
    private static final List<TrelloList> trelloLists = new ArrayList<>(Arrays.asList(trelloList1, trelloList2));

    private static final TrelloCard trelloCard = new TrelloCard("test1", "card1", "pos1","1");
    private static final TrelloCardDto trelloCardDto = new TrelloCardDto("test1", "card1", "pos1", "1");


    @Test
    public void mapToBoardsTest() {
        assertThat(trelloBoards,
                sameBeanAs(trelloMapper.mapToBoards(trelloBoardDtos)));
    }

    @Test
    public void mapToBoardsDtoTest() {
        assertThat(trelloBoardDtos,
                sameBeanAs(trelloMapper.mapToBoardsDto(trelloBoards)));
    }

    @Test
    public void mapToListTest() {
        assertThat(trelloLists,
                sameBeanAs(trelloMapper.mapToList(trelloListDtos)));
    }

    @Test
    public void mapToListDtoTest() {
        assertThat(trelloListDtos,
                sameBeanAs(trelloMapper.mapToListDto(trelloLists)));
    }

    @Test
    public void mapToCardDtoTest() {
        assertThat(trelloCardDto,
                sameBeanAs(trelloMapper.mapToCardDto(trelloCard)));
    }

    @Test
    public void mapToCardTest() {
        assertThat(trelloCard,
                sameBeanAs(trelloMapper.mapToCard(trelloCardDto)));
    }
}