package webserver.back.contentType;

import java.util.stream.Stream;

public enum ContentType {
    HTML("html","html"),
    CSS("css","css"),
    JSON("json","json"),
    PLAIN("plain","plain"),
    PNG("png","png"),
    JPG("jpg","jpeg"),
    ICO("ico","x-icon"),
    SVG("svg","svg+xml"),
    XXX_FORM("x-www-form-urlencoded","x-www-form-urlencoded"),
    ;

    private String extension;
    private String value;

    public String getExtension() {
        return extension;
    }

    public String getValue() {
        return value;
    }

    ContentType(String extension, String value) {
        this.extension = extension;
        this.value = value;
    }
    public static String getContentType(String extension){
        return Stream.of(ContentType.values())
                .filter(ct -> extension.endsWith(ct.extension))
                .map(ct->ct.value)
                .findFirst()
                .orElse(PLAIN.value);
    }
}
