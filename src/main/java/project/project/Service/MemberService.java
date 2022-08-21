package project.project.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import project.project.Dto.MemberDto;
import project.project.Entity.MemberEntity;
import project.project.Repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MemberService {

    private MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입

    @Transactional
    public void memberJoin(MemberDto.Request dto){
        dto.setMember_password(bCryptPasswordEncoder.encode(dto.getMember_password()));

        memberRepository.save(dto.toEntity());
    }
    //회원 가입시 유효성 체크
    @Transactional(readOnly = true)
    public Map<String , String> validateHandling(Errors errors){
        Map<String, String> validatorResult = new HashMap<>();
        /*유효성 검사에 실패한 필드 목록 받는다.*/
        for (FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }
    //회원 수정
    @Transactional(readOnly = true)
    public void modify(MemberDto.Request dto){
        MemberEntity memberEntity = memberRepository.findById(dto.toEntity().getMember_num()).orElseThrow(()
                -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        String encPassword = bCryptPasswordEncoder.encode(dto.getMember_password());
        memberEntity.modify(dto.getMember_nickname(), encPassword);
    }
}
