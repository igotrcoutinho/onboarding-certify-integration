package io.smilego.onboarding.certify.service.api.domain.properties.domain;

import java.util.Arrays;

public class Order {

    public static final Order INIT_ORDER = new Order(0, new int[]{0});

    private final int[] values;

    private final int index;

    private final String stringValue;

    private Order(int index, int[] values) {
        this.index = index;
        this.values = values;
        this.stringValue = this.formartAsString(values);
    }

    private String formartAsString(int[] values) {
        StringBuilder builder = new StringBuilder();
        for (int value : values) {
            builder.append(",");
            builder.append(value);
        }
        return builder.toString().substring(1);
    }

    public Order next() {
        int[] newValues = Arrays.copyOf(this.values, values.length +1);
        return new Order(this.index + 1, newValues);
    }

    public Order incrementValue() {
        int[] newValues = Arrays.copyOf(this.values, values.length);
        newValues[index] = values[index] + 1;
        return new Order(this.index, newValues);
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String toString() {
        return "Order: " + stringValue + ", index: " + index + ".";
    }

}
