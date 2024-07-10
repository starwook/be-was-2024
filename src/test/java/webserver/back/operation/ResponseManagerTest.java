package webserver.back.operation;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import webserver.StringUtil;
import webserver.back.Error.WrongDataFormatException;
import webserver.back.contentType.BaseContentType;
import webserver.back.contentType.ContentType;
import webserver.back.data.StatusCode;
import webserver.front.data.HttpRequest;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

class ResponseManagerTest {


    ResponseManager responseManager = new ResponseManager(new UserMapper());

    String createUserParam ="userId=javajigi&password=password&name=%EB%B0%95%EC%9E%AC%EC%84%B1";
    byte[] createUserByte = createUserParam.getBytes(StandardCharsets.UTF_8);
    String createUserbaseUrl ="/create";



    @Test
    @DisplayName("/create api 검증")
    void registerUser() {
        HttpRequest httpRequest = new HttpRequest(
                StringUtil.STANDARD_HTTP_VERSION,
                StringUtil.METHOD_POST,
                createUserbaseUrl,
                createUserByte,
                BaseContentType.APPLICATION.getValue()+"/"+ ContentType.XXX_FORM.getValue());
        Assertions.assertThat(StatusCode.FOUND.getMessage()).isEqualTo(responseManager.getResponse(httpRequest).getStatusText());
    }
    @Test
    @DisplayName("/create api 검증(GET 일떄)")
    void registerUserMethodError() {
        HttpRequest httpRequest = new HttpRequest(
                StringUtil.STANDARD_HTTP_VERSION,
                StringUtil.METHOD_GET,
                createUserbaseUrl,
                createUserByte,
                BaseContentType.APPLICATION.getValue()+"/"+ ContentType.XXX_FORM.getValue());
        Assertions.assertThat(StatusCode.NOT_FOUND.getMessage()).isEqualTo(responseManager.getResponse(httpRequest).getStatusText());
    }
    @Test
    @DisplayName("/create api 검증(param으로 정보가 올떄)")
    void registerUserParamError() {
        HttpRequest httpRequest = new HttpRequest(
                StringUtil.STANDARD_HTTP_VERSION,
                StringUtil.METHOD_POST,
                createUserbaseUrl+"?",
                new byte[0],
                BaseContentType.APPLICATION.getValue()+"/"+ ContentType.XXX_FORM.getValue());
        Assertions.assertThat(StatusCode.BAD_REQUEST.getMessage()).isEqualTo(responseManager.getResponse(httpRequest).getStatusText());
    }

}