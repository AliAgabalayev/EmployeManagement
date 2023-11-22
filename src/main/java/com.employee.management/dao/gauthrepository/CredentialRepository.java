package com.employee.management.dao.gauthrepository;

import com.employee.management.dao.repository.GAuthActionRepository;
import com.employee.management.mapper.GAuthMapper;
import com.employee.management.model.GAuthDto;
import com.warrenstrange.googleauth.ICredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
@RequiredArgsConstructor
public class CredentialRepository implements ICredentialRepository {
    private final GAuthActionRepository gAuthActionRepository;


    @Override
    public String getSecretKey(String userName) {
        return gAuthActionRepository.findByUsername(userName).getSecretKey();
    }

    @Override
    public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
        GAuthDto gAuthDto = GAuthDto.builder()
                .username(userName)
                .secretKey(secretKey)
                .validationCode(validationCode)
                .scratchCodes(scratchCodes)
                .build();
        gAuthActionRepository.save(GAuthMapper.INSTANCE.dtoToEntity( gAuthDto));

    }
}
