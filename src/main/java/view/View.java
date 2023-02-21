package view;

public interface View extends ViewLight {
    String scanString(String s); //считывание строки

    void printStr(String s); //вывод текста

    Integer scanInt(String text); //считывание числа

}
