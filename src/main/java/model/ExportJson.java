package model;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * интерфейс для экспорта файл json
 */

public interface ExportJson<T> {
    void reedFileJson(String namePath, Shop shops) throws IOException, ParseException; //экспорт файла
    boolean findFiles(String namePath); //метод проверки наличия файла с таким именем
}
