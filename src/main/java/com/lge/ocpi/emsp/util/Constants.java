package com.lge.ocpi.emsp.util;

import com.lge.ocpi.emsp.model.dto.version.EndPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Constants {
    public static final class Endponints {
        private Endponints() {
        }

        public static final String emsp_endpoint_url_app = "/emsp/app/api/2.2.1/";
        public static final String emsp_endpoint_url_server = "/emsp/api/2.2.1/";

      public static final  String endpoint = "[\n" +
                "            {\n" +
                "                \"identifier\": \"cdrs\",\n" +
                "                    \"url\": \"http://10.221.61.185:9898/emsp/2.1.1/cdrs\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"identifier\": \"credentials\",\n" +
                "                    \"url\": \"http://10.221.61.185:9898/emsp/2.1.1/credentials\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"identifier\": \"locations\",\n" +
                "                    \"url\": \"http://10.221.61.185:9898/emsp/2.1.1/locations\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"identifier\": \"commands\",\n" +
                "                    \"url\": \"http://10.221.61.185:9898/emsp/2.1.1/commands\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"identifier\": \"sessions\",\n" +
                "                    \"url\": \"http://10.221.61.185:9898/emsp/2.1.1/sessions\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"identifier\": \"tariffs\",\n" +
                "                    \"url\": \"http://10.221.61.185:9898/emsp/2.1.1/tariffs\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"identifier\": \"tokens\",\n" +
                "                    \"url\": \"http://10.221.61.185:9898/emsp/2.1.1/tokens\"\n" +
                "            }\n" +
                "        ]";
    }


    public static final String ASCII_REGEXP = "(?i)\\p{Print}+";

    private Constants() {
    }
}
