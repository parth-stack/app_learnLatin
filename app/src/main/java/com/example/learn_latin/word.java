package com.example.learn_latin;

public class word {
    private String defaultranslation;
    private String miwoktranslation;
    private int resourceid;
    private int playresource;
    public word(String defaultranslation,String miwoktranslation,int resourceid,int playresource){
        this.defaultranslation=defaultranslation;
        this.miwoktranslation=miwoktranslation;
        this.resourceid=resourceid;
        this.playresource=playresource;
    }
    public word(String defaultranslation,String miwoktranslation,int playresource){
        this.defaultranslation=defaultranslation;
        this.miwoktranslation=miwoktranslation;
        this.playresource=playresource;
    }

    public int getResourceid() {
        return resourceid;
    }

    public String getDefaultranslation() {
        return defaultranslation;
    }

    public String getMiwoktranslation() {
        return miwoktranslation;
    }

    public int getPlayresource(){return playresource;}

    @Override
    public String toString() {
        return "word{" +
                "defaultranslation='" + defaultranslation + '\'' +
                ", miwoktranslation='" + miwoktranslation + '\'' +
                ", resourceid=" + resourceid +
                ", playresource=" + playresource +
                '}';
    }

    public void setDefaultranslation(String defaultranslation) {
        this.defaultranslation = defaultranslation;
    }

    public void setResourceid(int resourceid) {
        this.resourceid = resourceid;
    }

    public void setMiwoktranslation(String miwoktranslation) {
        this.miwoktranslation = miwoktranslation;
    }

    public void setPlayresource(int playresource){
        this.playresource = playresource;
    }
}
