package model;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * интерфейс для создания файла формата json
 */

public interface ImportJson <T> {
    void writeTextJson(Shop shops) throws JSONException; //создание объекта Json
    void writeFileJson(String namePath, Shop shops) throws IOException, ParseException, JSONException; //запись в файл

    void writeAddFileJson(String namePath, Shop lots) throws IOException, ParseException, JSONException; //дозапись в файл лотерейных игрушек
    boolean findFiles(String namePath); //метод проверки наличия файла с таким именем
}
