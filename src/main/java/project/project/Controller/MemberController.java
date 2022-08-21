package project.project.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import project.project.Dto.MemberDto;
import project.project.Service.MemberService;
import project.project.Validator.CheckEmailValidator;
import project.project.Validator.CheckNicknameValidator;
import project.project.Validator.CheckUsernameValidator;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final CheckUsernameValidator checkUsernameValidator;
    private final CheckNicknameValidator checkNicknameValidator;
    private final CheckEmailValidator checkEmailValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkUsernameValidator);
        binder.addValidators(checkNicknameValidator);
        binder.addValidators(checkEmailValidator);

    }

    @GetMapping("/auth/join")
    public String join(){
        return "/member/member-join";
    }

    //회원가입
    @PostMapping("/auth/joinForm")
    public String joinForm(@Valid MemberDto.Request memberDto, Errors errors, Model model){
        if (errors.hasErrors()){
            //회원가입 실패시 입력 데이터 값 유지
            model.addAttribute("MemberDto",memberDto);

            //유효성 통과 못한 필드, 메시지 핸들링
            Map<String ,String > validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()){
                model.addAttribute(key,validatorResult.get(key));
            }
            //회원가입으로 다시 리턴
            return "/member/member-join";
        }
        memberService.memberJoin(memberDto);
        return "redirect:/auth/login";

    }


}
