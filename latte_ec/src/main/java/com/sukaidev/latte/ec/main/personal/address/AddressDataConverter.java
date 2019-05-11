package com.sukaidev.latte.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sukaidev.latte_core.ui.recycler.DataConverter;
import com.sukaidev.latte_core.ui.recycler.MultipleFields;
import com.sukaidev.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by sukaidev on 2019/05/11.
 * 收货地址数据转换.
 */
public class AddressDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(AddressItemFields.ADDRESS, address)
                    .setField(AddressItemFields.PHONE, phone)
                    .setField(AddressItemFields.IS_DEFAULT,isDefault)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
