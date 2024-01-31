package com.ai.springdemo.aispringdemo.models;

import java.util.List;

public class GenerateImages {

    private List<Image> data;

    public List<Image> getData() {
        return data;
    }

    public void setData(List<Image> data) {
        this.data = data;
    }

    public static class Image {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
