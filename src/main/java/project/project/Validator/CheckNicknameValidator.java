package project.project.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import project.project.Dto.MemberDto;
import project.project.Repository.MemberRepository;

@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<MemberDto.Request>{
    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDto.Request dto, Errors errors) {
            if (memberRepository.existsByNickname(dto.toEntity().getMember_nickname())){
                errors.rejectValue("nickname","닉네임 중복 오류","이미 사용중인 닉네임입니다.");
            }
        }
}
