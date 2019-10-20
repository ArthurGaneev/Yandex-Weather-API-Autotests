package utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Models {
    private String url = "https://api.weather.yandex.ru/v1/forecast?lat=55.75396&lon=37.620393&lang=en_US&limit=2&extra=true";
    private String APIKeyName = "X-Yandex-API-Key";
    private String APIKey = "848d24c9-153d-4a10-93e5-6baa820e74f2";
    private String infoField = "info";
    private String tzinfoField = "tzinfo";
    private String forecastsField = "forecasts";
    private String factField = "fact";

    private JSONObject response() throws UnirestException {
        HttpResponse<JsonNode> res = Unirest.get(url)
                .header(APIKeyName, APIKey)
                .asJson();
        return res.getBody().getObject();
    }

    private JSONObject responseInfo() throws Exception {
        return response().getJSONObject(infoField);
    }

    private JSONObject responseInfoTzinfo() throws Exception {
        return responseInfo().getJSONObject(tzinfoField);
    }

    private JSONArray responseForecast() throws Exception {
        return response().getJSONArray(forecastsField);
    }

    private JSONObject responseFact() throws Exception {
        return response().getJSONObject(factField);
    }


    public int latActual() throws Exception {
        String latValue = "lat";
        return responseInfo().getInt(latValue);
    }

    public int lonActual() throws Exception {
        String lonValue = "lon";
        return responseInfo().getInt(lonValue);
    }

    public int offsetActual() throws Exception {
        String offsetValue = "offset";
        return responseInfoTzinfo().getInt(offsetValue);
    }

    public String abbrActual() throws Exception {
        String abbrValue = "abbr";
        return responseInfoTzinfo().getString(abbrValue);
    }

    public String nameActual() throws Exception {
        String nameValue = "name";
        return responseInfoTzinfo().getString(nameValue);
    }

    public boolean dstActual() throws Exception {
        String dstValue = "dst";
        return responseInfoTzinfo().getBoolean(dstValue);

    }

    public int limitActual() throws Exception {
        return responseForecast().length();
    }

    public String seasonActual() throws Exception {
        String season = "season";
        return responseFact().getString(season);
    }

    public String urlActual() throws Exception {
        String urlField = "url";
        return responseInfo().getString(urlField);
    }

    public boolean moonTextActual() throws Exception {
        String moon_textField = "moon_text";
        String moon_codeField = "moon_code";
        String fullMoonField = "full-moon";
        String lastQuarterField = "last-quarter";
        String decreasingMoonField = "decreasing-moon";
        String newMoon = "new-moon";
        String growingMoon = "growing-moon";
        String firstQuarter = "first-quarter";
        int secondDay = 1;
        int moon_codeZero = 0;
        int moon_codeOne = 1;
        int moon_codeThree = 3;
        int moon_codeFour = 4;
        int moon_codeFive = 5;
        int moon_codeSeven = 7;
        int moon_codeEight = 8;
        int moon_codeNine = 9;
        int moon_codeEleven = 11;
        int moon_codeTwelve = 12;
        int moon_codeThirteen = 13;
        int moon_codeFifteen = 15;
        for (int i = secondDay; i < responseForecast().length(); i++) {
            int moon_code = responseForecast().getJSONObject(i).getInt(moon_codeField);
            String moon_text = responseForecast().getJSONObject(i).getString(moon_textField);
            if (moon_code == moon_codeZero) {
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(fullMoonField)));
            }
            if (moon_code > moon_codeOne && moon_code < moon_codeThree) {
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(decreasingMoonField)));
            }
            if (moon_code == moon_codeFour) {
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(lastQuarterField)));
            }
            if (moon_code >= moon_codeFive && moon_code <= moon_codeSeven) {
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(decreasingMoonField)));
            }
            if (moon_code == moon_codeEight) {
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(newMoon)));
            }
            if (moon_code >= moon_codeNine && moon_code <= moon_codeEleven) {
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(growingMoon)));
            }
            if (moon_code == moon_codeTwelve) {
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(firstQuarter)));
            }
            if (moon_code >= moon_codeThirteen && moon_code <= moon_codeFifteen)
                return Boolean.parseBoolean(String.valueOf(moon_text.contains(growingMoon)));
        }
        return false;
    }


}

