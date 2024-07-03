import Mapper.ResponseManager;
import Mapper.UserMapper;
import byteReader.ByteReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import requestForm.SignInForm;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ResponseManagerTest {
    ResponseManager responseManager = new ResponseManager(new UserMapper());
    String FILE_BASE_URL ="./src/main/resources/static";

    @Test
    @DisplayName("file to byte[]")
    void FileFoundAndGetByte() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FILE_BASE_URL+"/index.html");
        String realFile = new String(fileInputStream.readAllBytes());
        System.out.println(realFile);
        ByteReader helloPage = responseManager.getByte("/index.html");
        String foundFile = new String(helloPage.readBytes());
        Assertions.assertTrue(realFile.equals(foundFile));
    }
    @DisplayName("json to byte[]")
    @Test
    void JsonGetByte() throws IOException {
        String[] key ={"userId","password","name"};
        String[] value ={"test","junit","123"};

        HashMap<String,String> map = new HashMap<>();
        for(int i=0;i<key.length;i++){
            map.put(key[i],value[i]);
        }
        SignInForm signInForm = new SignInForm(map);
        ByteReader newUserInform = responseManager.getByte("/create?" +
                "userId="+value[0] +
                "&password="+value[1] +
                "&name=" +value[2]+
                "\n");
        System.out.println(new String(newUserInform.readBytes()));

    }
    public String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for(final byte b: a)
            sb.append(String.format("%02x ", b&0xff));
        return sb.toString();
    }
}
