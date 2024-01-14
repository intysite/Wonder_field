package org.javaacademy.wonder_field;

public enum SuperThing {
    AUTO("Автомобиль"),
    TOUR("Тур в Прагу"),
    DINNER("ужин на двоих в ресторане 3 звезды Мишлен"),
    SAFARI("Сафари"),
    RACE("Участие в автогонке"),
    COURSES("Курсы Java + Spring framework");

    private String description;
    SuperThing(String description) {
    }

    @Override
    public String toString() {
        return description;
    }
}
