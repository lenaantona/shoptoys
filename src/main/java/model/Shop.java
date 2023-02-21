package model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * класс магазин, содержит перечень игрушек (перечень игрушек, выпавших в лотерею)
 */
public class Shop implements Iterable<Toys>{
    private ArrayList<Toys> shops; //содержит весь список игрушек

    public Shop() {
        this.shops = new ArrayList<Toys>();
    }

    public ArrayList<Toys> getShop() {
        return shops;
    }

    public void setShop(ArrayList<Toys> shop) {
        this.shops = shop;
    }

    public void addShop (Toys t){ //метод добавления объекта в массив с измен.количества
        if (this.shops == null){
            t.setId(0);
        }
        else {
            Integer l = this.shops.size();
            t.setId(l);
        }
        this.shops.add(t);
    }
    public void addShopLots (Toys t){ //метод добавления объекта в массив, для лотерейных
        this.shops.add(t);
    }

    @Override
    public Iterator<Toys> iterator() {
        return shops.iterator();
    }
    public void deleteToysLott(Toys t) {
        ArrayList<Toys> tmpToys = new ArrayList<>();
        for (Toys p : this.shops) {
            if (p.getId() != t.getId()) {
                tmpToys.add(p);
            }
        }
        setShop(tmpToys);
    }
    public void editToysLott(Toys t){ //изменеия по игрушке, выпавшей в лотерее
        ArrayList<Toys> tmpToys = new ArrayList<>();
        for (Toys p : this.shops) {
            if (p.getId() == t.getId()) {
                p.lotteryShop(p, this.shops.size());
            }
            tmpToys.add(p);
        }
        setShop(tmpToys);
    }
    public Toys randomToys(Shop sh){ //функция рандомной выборки игрушки
        var random = new SecureRandom();
        var randomElement = sh.getShop().get(random.nextInt(sh.getShop().size()));
        return randomElement;

    }
}

