package com.dzer6.box.generator;

import com.box.boxjavalibv2.dao.BoxSharedLinkPermissions;

public class BoxSharedLinkPermissionsGenerator extends BoxObjectGenerator<BoxSharedLinkPermissions> {

    public BoxSharedLinkPermissionsGenerator() {
        super(BoxSharedLinkPermissions.class);
    }

    @Override
    protected Class<?> getMethodReturnType(final Class<BoxSharedLinkPermissions> type, final String key) {
        if (BoxSharedLinkPermissions.FIELD_CAN_DOWNLOAD.equals(key)) {
            return Boolean.class;
        }
        return super.getMethodReturnType(type, key);
    }
}
