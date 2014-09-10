package com.dzer6.box.generator;

import com.box.boxjavalibv2.dao.BoxItemPermissions;

public class BoxItemPermissionsGenerator extends BoxObjectGenerator<BoxItemPermissions> {

    public BoxItemPermissionsGenerator() {
        super(BoxItemPermissions.class);
    }

    @Override
    protected Class<?> getMethodReturnType(final Class<BoxItemPermissions> type, final String key) {
        if (BoxItemPermissions.FIELD_CAN_PREVIEW.equals(key)) {
            return Boolean.class;
        }
        return super.getMethodReturnType(type, key);
    }
}
