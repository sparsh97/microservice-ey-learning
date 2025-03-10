package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

    AccountsDto accountToAccountDto(Accounts accounts);

    Accounts accountsDtoToAccount(AccountsDto accountsDto);
}
