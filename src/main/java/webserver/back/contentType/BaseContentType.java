package webserver.back.contentType;

import java.util.Arrays;

public enum BaseContentType {
    TEXT(new String[]{"html", "css"},"text"),
    IMAGE(new String[]{"png", "jpg", "ico", "svg"},"image"),
    APPLICATION(new String[]{"json"},"application");

    private final String[] extensions;
    private final String value;

    BaseContentType(String[] extensions, String value) {
        this.extensions = extensions;
        this.value = value;
    }
    public static String getBaseContentType(String extension) {
        return Arrays.stream(BaseContentType.values())
                .filter(type -> Arrays.stream(type.extensions).anyMatch(ext -> extension.endsWith(ext)))
                .findAny()
                .map(type -> type.value)
                .orElse(TEXT.value);
        //없다면 text/plain
    }

    public String[] getExtensions() {
        return extensions;
    }

    public String getValue() {
        return value;
    }
}
