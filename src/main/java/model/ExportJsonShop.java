package model;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * класс для экспорта файла json, считывание игрушек магазина
 **/
public class ExportJsonShop implements ExportJson<Shop> {
    private Shop shops;


    public ExportJsonShop(Shop shops) throws JSONException {
        this.shops = shops;
    }
    @Override
    public void reedFileJson(String namePath, Shop shops) throws IOException, ParseException {
        if (findFiles(namePath) == true) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(namePath));
            JSONArray ar1 = (JSONArray) obj;
            for (int i = 0; i < ar1.size(); i++) {
                HashMap m = (HashMap) ar1.get(i);
                Toys t =  new Toys();
                t.setId(Integer.parseInt(m.get("id").toString()));
                t.setName(m.get("name").toString());
                t.setCount(Integer.parseInt(m.get("count").toString()));
                t.setFrequencyLoss(Integer.parseInt(m.get("frequency").toString()));
                shops.addShop(t);
            }
        }
    }

    @Override
    public boolean findFiles (String namePath){
        File f = new File(".");
        String[] list = f.list();     //список файлов в текущей папке
        boolean b = false;
        for (String file : list) {      //проверка на совпадение
            if (namePath.equals(file)) {
                b = true;
                break;
            } else b = false;
        }
        return b;
        }
    }




