package com.choimroc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author choimroc
 * @since 2019/8/23
 */
@Validated
@RestController
@RequestMapping("test")
public class TestController {
    private final LocaleMessage localeMessage;

    @Autowired
    public TestController(LocaleMessage localeMessage) {
        this.localeMessage = localeMessage;
    }

    @PostMapping
    public Result test(
            @NotBlank(message = "{test.msg1}") String param1,
            @NotBlank(message = "{test.msg2}") String param2
    ) {
        return new Result(200, localeMessage.getMessage("test.msg3"));
    }
}
