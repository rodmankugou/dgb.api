package com.verificer.enums;

/**
 * Created by 35336 on 2021/1/4.
 */
public enum NftStandard {
    ERC721("ERC721"),
    ERC1155 ("ERC1155");


    private String value;


    public String getValue() {
        return value;
    }

    NftStandard(String value) {
        this.value = value;
    }


    public static boolean isValidStatus(String val) {
        if (val == null)
            return false;
        for (NftStandard c : NftStandard.values()) {
            if (c.getValue().equals(val)) {
                return true;
            }
        }
        return false;
    }


}
