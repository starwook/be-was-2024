package webserver.back.dynamic;

import webserver.back.byteReader.Body;
import webserver.back.fileFounder.TemplateFileFounder;
import webserver.back.model.User;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DynamicHtmlChanger {


    public byte[] showUserList(List<User> userList,String url) throws FileNotFoundException {

        Body body = new TemplateFileFounder().findFile(url);

        StringBuilder sb = new StringBuilder();
        String html = new String(body.makeBytes(), StandardCharsets.UTF_8);
        String[] lines = html.split("\n");
        for(int i=0;i<lines.length;i++) {
            String line = lines[i];
            if(line.contains("table table-hover")){
                sb.append(line).append("\n");
                i++;
                while(!lines[i].contains("<tbody>")){
                    sb.append(lines[i]).append("\n");
                    i++;
                }

                for(int j=0;j<userList.size();j++){
                    writeUserInformation(userList, j, sb);
                }
            }
            else{
                sb.append(line).append("\n");
            }
        }
        System.out.println(sb);

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private static void writeUserInformation(List<User> userList, int i, StringBuilder sb) {
        User user = userList.get(i);
        String firstLine ="<tr>\n";
        String userName = "<td>" + user.getName() + "</td>";
        String userNickname = "<td>" + user.getName() + "</td>";
        String userEmail = "<td>" + user.getEmail() + "</td>";
        String modifyButton = "<td><a href=\"#\" class=\"btn btn-success\" role=\"button\">수정</a></td>";
        String lastLine ="</tr>\n";
        sb.append(firstLine)
                .append("<th scope=\"row\">").append(i +1).append("</th>")
                .append(userName)
                .append(userNickname)
                .append(userEmail)
                .append(modifyButton)
                .append(lastLine);
    }
}
