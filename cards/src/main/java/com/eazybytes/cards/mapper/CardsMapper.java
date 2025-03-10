package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardsMapper {

    Cards cardsDtoToCards(CardsDto cardsDto);

    CardsDto cardsToCardsDto(Cards cards);


}
