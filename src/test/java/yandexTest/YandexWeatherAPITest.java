package yandexTest;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import utils.Models;


public class YandexWeatherAPITest {
    private Models models = new Models();

    @Test
    @DisplayName("Latitude test")
    public void latitude() throws Exception {
        int latitude = 55;
        Assert.assertEquals(latitude, models.latActual());
    }

    @Test
    @DisplayName("Longitude test")
    public void longitude() throws Exception {
        int longitude = 37;
        Assert.assertEquals(longitude, models.lonActual());
    }

    @Test
    @DisplayName("Offset test")
    public void offset() throws Exception {
        int offset = 10800;
        Assert.assertEquals(offset, models.offsetActual());
    }

    @Test
    @DisplayName("Abbreviation test")
    public void abbr() throws Exception {
        String abbr = "MSK";
        Assert.assertEquals(abbr, models.abbrActual());
    }

    @Test
    @DisplayName("Name test")
    public void name() throws Exception {
        String name = "Europe/Moscow";
        Assert.assertEquals(name, models.nameActual());
    }

    @Test
    @DisplayName("Daylight saving time test")
    public void dst() throws Exception {
        Assert.assertFalse(models.dstActual());
    }

    @Test
    @DisplayName("Limit days test")
    public void limit() throws Exception {
        int limit = 2;
        Assert.assertEquals(limit, models.limitActual());
    }

    @Test
    @DisplayName("Season test")
    public void season() throws Exception {
        String season = "autumn";
        Assert.assertEquals(season, models.seasonActual());
    }

    @Test
    @DisplayName("Url containing Lat and Lon test")
    public void urlActual() throws Exception {
        String urlLatActual = "lat=55";
        String urlLonActual = "lon=37";
        Assert.assertTrue(models.urlActual().contains(urlLatActual));
        Assert.assertTrue(models.urlActual().contains(urlLonActual));
    }

    @Test
    @DisplayName("Moon code test")
    public void moon_textActual1() throws Exception {
        Assert.assertTrue(models.moonTextActual());
    }


}
