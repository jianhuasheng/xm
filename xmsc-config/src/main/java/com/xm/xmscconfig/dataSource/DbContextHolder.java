package com.xm.xmscconfig.dataSource;

public class DbContextHolder {
    private static final ThreadLocal<DataSourceType> local = ThreadLocal.withInitial(() -> DataSourceType.WRITE);

    public static void setDataSourceType(DataSourceType type) {
        if (type == null) {
            throw new NullPointerException();
        }
        local.set(type);
    }

    public static DataSourceType getDataSourceType() {
        return local.get();
    }

    public static void clearDataSourceType() {
        local.remove();
    }

}
