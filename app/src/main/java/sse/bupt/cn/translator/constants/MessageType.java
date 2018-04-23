package sse.bupt.cn.translator.constants;

public class MessageType {
    //GetChineseHandler
    public static final int GET_CHINESE_ERROR = 0;
    public static final int GET_CHINESE_TEXT_AND_SHOW_TO_ACTIVITY = 1;

    //SendChineseHandler
    public static final int SEND_CHINESE_TEXT_AND_SUCCESS = 2;
    public static final int SEND_CHINESE_TEXT_AND_FAIL = 3;
    public static final int SEND_CHINESE_TEXT_AND_INTERNET_ERROR = 4;

    //TextHandler
    public static final int TEXT_RESPONSE_DO_NOT_CONTAIN_JSON_OBJECT = 5;
    public static final int TEXT_RESPONSE_PARSE_ERROR = 6;
    public static final int TEXT_RESPONSE_SUCCESS = 7;
    public static final int TEXT_REQUEST_INTERNET_ERROR = 8;

    //MenuItemHandler
    public static final int MENU_RESPONSE_DO_NOT_CONTAIN_JSON_OBJECT = 9;
    public static final int MENU_RESPONSE_PARSE_ERROR = 10;
    public static final int MENU_RESPONSE_SUCCESS = 11;
    public static final int MENU_REQUEST_INTERNET_ERROR = 12;

    //ImageHandler
    public static final int IMAGE_RESPONSE_SUCCESS = 13;

    //TextFileReader
    public static final int TEXT_READ_SUCCESS = 14;
    public static final int TEXT_FILE_READ_ERROR = 15;
    public static final int TEXT_CLASS_NOT_FOUND = 16;
    public static final int TEXT_FILE_NOT_FOUND = 17;

    //TextFileWriter
    public static final int TEXT_WRITE_SUCCESS = 18;
    public static final int TEXT_FILE_WRITE_ERROR = 19;
}
