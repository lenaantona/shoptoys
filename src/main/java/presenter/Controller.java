package presenter;

import model.ExportJsonShop;
import model.ImportJsonShop;
import model.Shop;
import model.Toys;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import view.ConsoleTerminal;

import java.io.IOException;

/**
 * класс презентер, для взаимодействия view и model(person)
 */
public class Controller {
    private ConsoleTerminal view;

    public Controller(ConsoleTerminal view) {
        this.view = view;
        view.setController(this);
    }

    public void onClick() throws JSONException, IOException, ParseException {
        view.printStr("Вас приветствует магазин игрушек. ");
        Shop shop = new Shop(); //создадим эк. для хранения игрушек в магазине
        Shop lots = new Shop(); //создадим эк. для хранения игрушек, выйграшивших лотерею
        ExportJsonShop ex = new ExportJsonShop(shop);
        ex.reedFileJson("ShopToys.json", shop); //экспорт игрушек из json файла
        while (true) {
            switch (view.scanInt("Доступны следущие функции:\n 1 - просмотр всех игрушеу в магзине,\n " +
                    "2 - добавление игрушки,\n 3 - розыгрыш и получение любой игрушки прямо сейчас из магазина,\n 4 - выход и сохранение")) {
                case 1: //просмотр всех игрушек
                    if (shop.getShop().size()<1) {view.printStr("Пока нет игрушек в магазине. Добавьте игрушки");}
                    printShop(shop);
                    break;
                case 2: //добавление игрушки
                    String name = view.scanString("Введите наименование игрушки");
                    Integer count = view.scanInt("Введите количество игрушек");
                    if (count <= 0) {
                        view.printStr("Количество не может быть меньше 0 или ввели не число");
                        break;
                    }
                    try {
                        Toys toy = new Toys(name, count);
                        shop.addShop(toy); //добавление в магазин
                        view.printStr("Игрушка добавлена");
                    } catch (Exception e) {
                        view.printStr("Произошла ошибка при добавлении игрушки");
                    }
                    break;
                case 3: //розыгрышь игрушек
                   view.printStr("Участвуют следующие игрушки:");
                   printShop(shop);
                   view.printStr("Итоги лотереи:");
                   Toys lotToy = shop.randomToys(shop);
                   printToys(lotToy);
                   if (lotToy.getCount()>1){
                        shop.editToysLott(lotToy);
                   }
                   else {
                       shop.deleteToysLott(lotToy);
                   }
                   lots.addShopLots(lotToy);
                   break;
                case 4: //при выходе сохраняем все изменения в файл ShopToys.json
                    try {
                        ImportJsonShop im = new ImportJsonShop(shop);
                        im.writeFileJson("ShopToys.json",shop); //запись игрушек в магазине
                        ImportJsonShop iml = new ImportJsonShop(lots);
                        iml.writeAddFileJson("LottoToys.json",lots);//зпись игрушек, выйгравших в лотерею
                        view.printStr("Перечень игрушек сохранен в файл Json");
                    }catch (Exception e){
                        view.printStr(e.getMessage());
                    }
                    return;

                default:
                    view.printStr("Такой команды нет");
                    break;
            }

        }
    }
    public void printToys(Toys p){
            view.printStr(p.getId()+", "+p.getName()+", "+p.getCount()+", "+p.getFrequencyLoss()+"%");
    }
    public void printShop(Shop shop){
        view.printStr("Id, наименование, количество, частота выпадения");
        for (Toys p: shop.getShop()){
            printToys(p);
        }
    }
}