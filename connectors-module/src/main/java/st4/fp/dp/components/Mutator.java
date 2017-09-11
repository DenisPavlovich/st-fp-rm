package st4.fp.dp.components;

import st4.fp.dp.annotation.Column;
import st4.fp.dp.annotation.Id;
import st4.fp.dp.annotation.Table;
import st4.fp.dp.dto.Dto;
import st4.fp.dp.dto.Users;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Created by denis on 11.09.17.
 */
public class Mutator {

    public void write(Dto dto) {

    }

    public String buildWriteQuery(Dto dto) {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        Class clazz = dto.getClass();

        query.append(getTable(clazz));
        query.append(String.format(" (%s) ", getColumnsNames(clazz)));
        query.append(String.format("VALUES (%s);", getColumnsValues(clazz, dto)));

        System.out.println(query.toString());
        return query.toString();
    }

    private String getColumnsValues(Class clazz, Dto dto) {
        StringBuilder values = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                values.append(field.get(dto))
                        .append(", ");
            } catch (IllegalAccessException e) {
                e.printStackTrace(); //// TODO: 11.09.17 log
            }
        }
        values.delete(values.length() - 2, values.length());
        return values.toString();
    }

    private String getColumnsNames(Class clazz) {
        StringBuilder names = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            names.append(field.getAnnotation(Column.class).value())
                    .append(", ");
        }
        names.delete(names.length() - 2, names.length());

        return names.toString();
    }

    private String getTable(Class clazz) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        return table.value();
    }

    private Long getId(Class clazz, Dto dto) {
        Id id = null;
        for (Field field : clazz.getDeclaredFields()) {
            id = field.getAnnotation(Id.class);
            System.out.println("~~~ id " + id);
            if (id != null) {
                field.setAccessible(true);
                try {
                    return ((Long) field.get(dto));
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); //// TODO: 11.09.17 log
                }
            }
        }
        return null;
    }

}
