package com.to_do_list.entity;

public enum Status {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;
    public static String[] getStatus() {
        return new String[]{
                PENDING.name(),
                IN_PROGRESS.name(),
                COMPLETED.name(),
                CANCELLED.name()
        };
    }
}
