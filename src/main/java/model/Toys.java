package model;

/**
 * класс игрушки
 */
public class Toys {
    private Integer id; //инденитификатор игрушки
    private String name; //имя игрушки
    private Integer count; //количество
    private Integer frequencyLoss;// частота выпадения игрушки

    public Toys( String name, Integer count) {
        this.id = 0;
        this.name = name;
        this.count = count;
        this.frequencyLoss = 0;
    }

    public Toys(Integer id, String name, Integer count, Integer frequencyLoss) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.frequencyLoss = frequencyLoss;
    }

    public Toys() {
        this.id = null;
        this.name = null;
        this.count = null;
        this.frequencyLoss = null;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getFrequencyLoss() {
        return frequencyLoss;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setFrequencyLoss(Integer frequencyLoss) {
        this.frequencyLoss = frequencyLoss;
    }

    public void lotteryShop(Toys toys, Integer l){ //изменение кол-ва, при выпадании в лотерее
            toys.setCount(toys.getCount()-1);
            toys.setFrequencyLoss(1*100/l);
    }
}
