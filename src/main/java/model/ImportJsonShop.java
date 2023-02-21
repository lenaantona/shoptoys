package model;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * класс для записи игрушек из магазина в файл json(ShopToys), а также игрушек, выйгравших в лоторею(LottoToys)
 */
public class ImportJsonShop implements ImportJson<Shop> {
    private Shop shops;
    private JSONArray ar = new JSONArray();


    public ImportJsonShop(Shop shops) throws JSONException {
        this.shops = shops;
    }

    public JSONArray getAr() {
        return ar;
    }

    @Override
    public void writeTextJson(Shop shops) throws JSONException {
        if (shops.getShop() != null) {
            for (Toys p : shops.getShop()) {
                JSONObject obgJson = new JSONObject();
                obgJson.put("id", p.getId());
                obgJson.put("name", p.getName());
                obgJson.put("count", p.getCount());
                obgJson.put("frequency", p.getFrequencyLoss());
                ;
                ar.add(obgJson);
            }
        }
    }

    @Override
    public void writeFileJson(String namePath, Shop shops) throws JSONException {
        this.writeTextJson(shops);
        try (FileWriter file = new FileWriter(namePath)) {
            file.write(ar.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAddFileJson(String namePath, Shop lots) throws IOException, ParseException, JSONException { //для лотерейных игрушек
        if (findFiles(namePath) == true) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(namePath));
            JSONArray ar1 = (JSONArray) obj;
            for (int i = 0; i < ar1.size(); i++) {
                ar.add(ar1.get(i));
            }
        }
        writeFileJson(namePath, lots);
    }

    @Override
    public boolean findFiles(String namePath) {
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




