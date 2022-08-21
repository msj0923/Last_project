package project.project.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import project.project.Dto.MemberDto;
import project.project.Repository.MemberRepository;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<MemberDto.Request> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDto.Request dto, Errors errors) {
        if (memberRepository.existsByEmail(dto.toEntity().getMember_email())){
            errors.rejectValue("email","이메일 중복 오류", "이미 사용중인 이메일입니다.");
        }
    }
}
