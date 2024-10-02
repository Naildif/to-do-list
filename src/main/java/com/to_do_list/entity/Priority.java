package com.to_do_list.entity;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;
    public static String [] getPriority() {
        return new String[] {
                LOW.name(),
                MEDIUM.name(),
                HIGH.name()
        };
    }
}
