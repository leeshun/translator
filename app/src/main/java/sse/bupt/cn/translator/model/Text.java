package sse.bupt.cn.translator.model;

import java.io.Serializable;
import java.util.List;

public class Text implements Serializable {
    private int paraId;

    private String pictureUrl;

    private String englishText;

    private List<String> chineseText;

    private boolean isOnlyText;

    private boolean isLocate;

    private String pitcureLocalPath;

    public Text() {
        isOnlyText = false;
        isLocate = false;
    }

    public Text(int paraId, String pictureUrl, String englishText, List<String> chineseText, boolean isOnlyText) {
        this.paraId = paraId;
        this.pictureUrl = pictureUrl;
        this.englishText = englishText;
        this.chineseText = chineseText;
        this.isOnlyText = isOnlyText;
        isLocate = false;
    }

    public int getParaId() {
        return paraId;
    }

    public void setParaId(int paraId) {
        this.paraId = paraId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        if (pictureUrl == null || pictureUrl.equals("")) {
            isOnlyText = true;
        }
        this.pictureUrl = pictureUrl;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public List<String> getChineseText() {
        return chineseText;
    }

    public void setChineseText(List<String> chineseText) {
        this.chineseText = chineseText;
    }

    public boolean isOnlyText() {
        return isOnlyText;
    }

    public static String makePara(int paraIndex, String value) {
        String result = paraIndex + "   " + value;
        return result;
    }

    public boolean isLocate() {
        return isLocate;
    }

    public void setLocate(boolean locate) {
        isLocate = locate;
    }

    public String getPitcureLocalPath() {
        return pitcureLocalPath;
    }

    public void setPitcureLocalPath(String pitcureLocalPath) {
        this.pitcureLocalPath = pitcureLocalPath;
    }

    @Override
    public String toString() {
        return "Text{" +
                "paraId=" + paraId +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", englishText='" + englishText.length() + '\'' +
                ", chineseText='" + chineseText + '\'' +
                ", isOnlyText=" + isOnlyText +
                ", isLocate=" + isLocate +
                ", pitcureLocalPath='" + pitcureLocalPath + '\'' +
                '}';
    }
}
