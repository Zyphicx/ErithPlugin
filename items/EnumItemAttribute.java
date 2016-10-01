package items;

public enum EnumItemAttribute{
    BLOOD_LUST("Blood lust", "%"),
    WALK_SPEED("Walk speed", "%"),
    SWAG_FACTOR("Swag factor", "");

    private String attributeName;
    private String suffix;

    EnumItemAttribute(String attributeName, String suffix){
        this.attributeName = attributeName;
        this.suffix = suffix;
    }

    public String getAttributeName(){
        return attributeName;
    }

    public String getSuffix(){
        return suffix;
    }

}
