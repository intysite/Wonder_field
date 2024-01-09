package org.javaacademy.wonder_field;

public enum Things {
    KITCHEN_SET("Набор бытовой техники для кухни (13 предметов)", 3500),
    MUSEUM_TOUR("Экскурсионный тур по музеям родного края", 2000),
    BEAUTY_DAY("День красоты (спа процедуры, массаж и т.д.)", 1200),
    SMART_TV("Телевизор с интернетом", 1000),
    SMARTPHONE("Смартфон", 900),
    SPORTS_TRAINER("Спортивный тренажер", 800),
    MUSIC_SYNTHESIZER("Музыкальный синтезатор", 700),
    TELESCOPE("Телескоп", 600),
    REFLEX_CAMERA("Зеркальный фотоаппарат", 500),
    BICYCLE("Велосипед", 400),
    COFFEE_MACHINE("Кофемашина с набором капсул", 300),
    SPORTS_SET("Набор для спорта и отдыха", 200),
    MOBILE_PHONE("Мобильный телефон", 100);

    private final String description;
    private final int cost;

    Things(String description, int cost) {
        this.description = description;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this. ordinal() + 1 +  " " + description + " - " + cost;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }
}
